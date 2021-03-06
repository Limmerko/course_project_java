package vlsu.pri117.mep.bot;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import vlsu.pri117.mep.bot.model.TgProblemWithStatus;
import vlsu.pri117.mep.bot.model.TgStatus;
import vlsu.pri117.mep.model.enums.CategoriesProblem;
import vlsu.pri117.mep.service.impl.AsyncService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@EnableScheduling
@Component
public class KonohaTgBot extends TelegramLongPollingBot{

    private final String _token = BotConstans.getS_TOKEN();
    private final String _botName = BotConstans.getS_BOT_NAME();
    private final AsyncService _asyncService;
    private HashMap<String, TgProblemWithStatus> _mapTgObjects;


    public KonohaTgBot(AsyncService asyncService, BotsOptions options){
        super(options.getOptions());
        this._asyncService = asyncService;

        _mapTgObjects = new HashMap<String, TgProblemWithStatus>();

        var botsApi = new TelegramBotsApi();
        try{
            botsApi.registerBot(this);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }

        checkForAfk();
    }

    @Override
    public void onUpdateReceived(Update update) {

        var tgProblem = getTgProblem(update);

        if (switchCommands(tgProblem, update)){
            return;
        }
        switchStatusTgObj(tgProblem, update);
    }

    private boolean switchCommands(TgProblemWithStatus tgProblem, Update update){
        if (update.hasMessage() &&
                update.getMessage().hasText() &&
                update.getMessage().getText().equals("/start") &&
                tgProblem.get_status() != TgStatus.JUST_STARTED){
            try {
                execute(new SendMessage(update.getMessage().getChatId(), "Создание проблемы уже началось!"));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            return true;
        }

        if (update.hasMessage() &&
                update.getMessage().hasText() &&
                update.getMessage().getText().equals("/start") &&
                tgProblem.get_status() == TgStatus.JUST_STARTED){
            handleJustStartedProblem(update, tgProblem);
            return true;
        }

        if (update.hasMessage() &&
                update.getMessage().hasText() &&
                update.getMessage().getText().equals("/reset")){
            resetTgProblem(update.getMessage().getChatId());
            return true;
        }

        if (tgProblem.get_status() == TgStatus.ADDED_LOCATION ||
                tgProblem.get_status() == TgStatus.ADDED_PHOTOS){
            if (update.hasMessage() &&
                    update.getMessage().hasText() &&
                    update.getMessage().getText().toLowerCase().contains("/send")){
                endCreationProblem(update, tgProblem);
                return true;
            }
        } else {
            if (update.hasMessage() &&
                    update.getMessage().hasText() &&
                    update.getMessage().getText().toLowerCase().contains("/send")) {
                try {
                    execute(new SendMessage(tgProblem.get_chatId(), "Сначала выполни все шаги создания"));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        return false;
    }

    //Проверка активности пользователя
    @Scheduled(cron = "0 * * ? * *")
    private void checkForAfk(){
        var dateNow = new Date(System.currentTimeMillis());
        for (var tgProblem : _mapTgObjects.values()) {
            if (tgProblem == null)
                continue;
            Long diffDate = dateNow.getTime() - tgProblem.get_date().getTime();

            if (diffDate/1000 > 2 * 60){
                try {
                    execute(new SendMessage(tgProblem.get_chatId(), "Закнончи создание проблемы или отмени через команду /reset"));
                    execute(new SendSticker().setSticker(BotConstans.getS_STICKER_HEY()).setChatId(tgProblem.get_chatId()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (diffDate/1000 > 5 * 60)
                resetTgProblem(tgProblem.get_chatId());

        }
    }

    private void resetTgProblem(Long chatId) {
        _mapTgObjects.remove(chatId.toString());
        try {
            execute(new SendMessage(chatId, "Создание проблемы отменено!"));
            execute(new SendSticker().setSticker(BotConstans.getS_STICKER_CROSS()).setChatId(chatId));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void switchStatusTgObj(TgProblemWithStatus tgProblem, Update update){
        switch (tgProblem.get_status()){
            case JUST_STARTED:
                handleJustStartedProblem(update, tgProblem);
                break;
            case ADDED_CATEGORY:
                handleAddedCategory(update, tgProblem);
                break;
            case ADDED_DESCRIPTION:
                handleAddedDescription(update, tgProblem);
                break;
            case ADDED_LOCATION:
            case ADDED_PHOTOS:
                handleAddedLocation(update, tgProblem);
                break;
        }
    }

    private void handleAddedLocation(Update update, TgProblemWithStatus tgProblem) {
        if (update.hasMessage() && update.getMessage().hasPhoto()){

            if(tgProblem.checkPhotoCount()){
                try {
                    execute(new SendMessage(update.getMessage().getChatId(), "Добавлено 5 фото."));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                endCreationProblem(update, tgProblem);
                return;
            }

            try {
                execute(new SendChatAction(update.getMessage().getChatId(), "upload_photo"));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            var photos = update.getMessage().getPhoto();
            //get middle size photo
            var filePath = getFilePath(photos.get(1));
            File file = downloadPhoto(filePath);
            try {
                tgProblem.get_photosToBeAdded().add(Files.readAllBytes(file.toPath()));
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    var number = tgProblem.get_photosToBeAdded().size() + 1;
                    execute(new SendMessage(update.getCallbackQuery().getMessage().getChatId(),
                            "Ошибка при загрузке фото №" + number + ". Загрузи его еще раз."));
                    return;
                } catch (TelegramApiException ex) {
                    ex.printStackTrace();
                }
            }
            tgProblem.set_status(TgStatus.ADDED_PHOTOS);

            try {
                var number = tgProblem.get_photosToBeAdded().size();
                execute(new SendMessage(update.getMessage().getChatId(),"Фотография №" + number + " добавлена к проблеме!"));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            tgProblem.updateDate();
        } else {
            try {
                execute(new SendMessage(update.getCallbackQuery().getMessage().getChatId(), "Отправь фотографии проблемы (Скрепка -> Локация)!"));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    private void endCreationProblem(Update update, TgProblemWithStatus tgProblem) {
        _asyncService.saveProblemAsync(tgProblem.get_problem(), tgProblem.get_photosToBeAdded());
        var chatId = update.getMessage().getChatId();

        try {
            execute(new SendMessage(chatId, "Спасибо, ваша заявка была отправлена! Наши модераторы ее проверят и скоро она появится на сайте."));
            execute(new SendSticker().setSticker(BotConstans.getS_STICKER_THANKS()).setChatId(chatId));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        _mapTgObjects.put(chatId.toString(), null);


        return;
    }

    private void handleAddedDescription(Update update, TgProblemWithStatus tgProblem) {
        if (update.hasMessage() && update.getMessage().hasLocation()){
            setLocation(update.getMessage(), tgProblem);
        } else {
            try {
                if (update.hasMessage())
                    execute(new SendMessage(update.getMessage().getChatId(), "Отправь локацию проблемы (Скрепка -> Локация)!"));
                if (update.hasCallbackQuery())
                    execute(new SendMessage(update.getCallbackQuery().getMessage().getChatId(), "Отправь локацию проблемы (Скрепка -> Локация)!"));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private String getFilePath(final PhotoSize photo) {
        if (photo.hasFilePath()) {
            return photo.getFilePath();
        }
        final GetFile getFileMethod = new GetFile();
        getFileMethod.setFileId(photo.getFileId());
        try {
            final org.telegram.telegrambots.meta.api.objects.File file = execute(getFileMethod);
            return file.getFilePath();
        } catch (final TelegramApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    private java.io.File downloadPhoto(final String filePath) {
        try {
            return this.downloadFile(filePath);
        } catch (final TelegramApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setLocation(Message message, TgProblemWithStatus tgProblem){
        var problem = tgProblem.get_problem();
        String address = message.getLocation().getLatitude().toString() + ", " + message.getLocation().getLongitude().toString();
        problem.setAddress(address);

        tgProblem.set_status(TgStatus.ADDED_LOCATION);

        try {
            execute(new SendMessage(message.getChatId(),"Локация добавлена к проблеме!"));
            execute(new SendMessage(message.getChatId(),"Теперь отправь фотографии проблемы. (Скрепка и выбери фото (Максимум 5 штук))"));
            execute(new SendMessage(message.getChatId(), "После того, как отправишь все фото, отправь команду /send"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        tgProblem.updateDate();
    }

    private void handleAddedCategory(Update update, TgProblemWithStatus tgProblem) {
        if (update.hasMessage() && update.getMessage().hasText()){
            setDescription(update.getMessage(), tgProblem);
        } else{
            try {
                execute(new SendMessage(update.getCallbackQuery().getMessage().getChatId(), "Отправь описание проблемы (Текстом, максимум 255 символов)!"));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    private void handleJustStartedProblem(Update update, TgProblemWithStatus tgProblem) {
        if (tgProblem.get_problem().getCategory() == null){
            if (update.hasCallbackQuery()){
                addCategoryFromCallback(update, tgProblem);
                return;
            }
            if (update.hasMessage()){
                try {
                    execute(new SendMessage(update.getMessage().getChatId(), "Начинаем создание проблемы!"));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            replyMarkupCategory(update.getMessage().getChatId());
        }
    }

    private void addCategoryFromCallback(Update update, TgProblemWithStatus tgProblem) {

        var callbackData = update.getCallbackQuery().getData();
        tgProblem.get_problem().setCategory(CategoriesProblem.valueOf(callbackData));

        tgProblem.updateDate();
        tgProblem.set_status(TgStatus.ADDED_CATEGORY);
        try {
            execute(new SendMessage(update.getCallbackQuery().getMessage().getChatId(), "Отправь описание проблемы (максимум 255 символов)"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void replyMarkupCategory(Long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup =new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList= new ArrayList<>();

        for (var categ : CategoriesProblem.values()){
            keyboardButtonsRow.add(new InlineKeyboardButton().setText(categ.getDescription())
                    .setCallbackData(categ.toString()));
            rowList.add(keyboardButtonsRow);
            keyboardButtonsRow = new ArrayList<>();
        }

        inlineKeyboardMarkup.setKeyboard(rowList);

        try {
            execute(new SendMessage().setChatId(chatId).setText("Выбери категорию проблемы:").setReplyMarkup(inlineKeyboardMarkup));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private TgProblemWithStatus getTgProblem(Update update) {
        TgProblemWithStatus tgProblem = null;
        Long chatId = null;
        if (update.getMessage() != null){
            chatId = update.getMessage().getChatId();
        } else if (update.getCallbackQuery() != null){
            chatId = update.getCallbackQuery().getMessage().getChatId();
        }
        tgProblem = _mapTgObjects.get(chatId.toString());
        if (tgProblem == null){
            tgProblem = new TgProblemWithStatus(chatId);
            _mapTgObjects.put(chatId.toString(), tgProblem);
        }
        return tgProblem;
    }

    private void setDescription(Message message, TgProblemWithStatus tgProblemWithStatus) {
        if (message.getText().length() > 255){
            try {
                execute(new SendMessage(message.getChatId(), "Максимум 255 символов, попробуй еще раз"));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            return;
        }

        var problem = tgProblemWithStatus.get_problem();
        problem.setDescription(message.getText());
        tgProblemWithStatus.set_status(TgStatus.ADDED_DESCRIPTION);
        try {
            execute(new SendMessage(message.getChatId(),"Описание добавлено к проблеме!"));
            execute(new SendMessage(message.getChatId(),"Теперь отправь геолокацию проблемы. (Скрепка -> Локация)"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        tgProblemWithStatus.updateDate();
    }

    @Override
    public String getBotUsername() {
        return _botName;
    }

    @Override
    public String getBotToken() {
        return _token;
    }
}
