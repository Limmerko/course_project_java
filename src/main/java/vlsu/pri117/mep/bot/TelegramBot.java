package vlsu.pri117.mep.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import vlsu.pri117.mep.model.Problem;
import vlsu.pri117.mep.model.enums.CategoriesProblem;
import vlsu.pri117.mep.service.ProblemService;
import vlsu.pri117.mep.service.impl.AsyncService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private static final String s_TOKEN ="1074615896:AAH83HsLHHT8FQTgDAzOj1o4TM48kRyqsaw";
    private static final String s_BOT_NAME = "KonohaLiveBot";
    private final AsyncService asyncService;
    private final ProblemService problemService;
    private Problem problem = new Problem();
    private List<byte[]> photosToBeAdded = new ArrayList<byte[]>();

    public TelegramBot(AsyncService asyncService, BotsOptions options, ProblemService problemService){
        super(options.getOptions());
        this.asyncService = asyncService;
        this.problemService = problemService;

        var botsApi = new TelegramBotsApi();
        try{
            botsApi.registerBot(this);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {

        if (update.getCallbackQuery() != null){
            var category = checkCategory(update.getCallbackQuery().getData());
            if (category != null){
                problem.setCategory(category);
                return;
            }
        }

        if (update.getMessage() != null) {

            Message message = update.getMessage();
            long chatId = message.getChatId();
/*            if (message.hasText() && message.getText().contains("save")){

                return;
            }*/
            if (message.hasText() && message.getText().toLowerCase().contains("save")) {
                asyncService.saveProblemAsync(problem, photosToBeAdded);
                problem = new Problem();
                photosToBeAdded = new ArrayList<>();
                return;
            }

            if (message.hasText() && message.getText().toLowerCase().equals("/start")){

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
                    execute(new SendMessage().setChatId(chatId).setText("Выбери категорию проблемы").setReplyMarkup(inlineKeyboardMarkup));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                return;
            }

            if (message.hasText()) {
                problem.setDescription(message.getText());
                return;
            }
            if (message.hasLocation()) {
                String address = message.getLocation().getLatitude().toString() + ", " + message.getLocation().getLongitude().toString();
                problem.setAddress(address);
                return;
            }
            if (message.hasPhoto()) {
                var photos = message.getPhoto();
                var filePath = getFilePath(photos.get(1));
                File file = downloadPhoto(filePath);
                try {
                    photosToBeAdded.add(Files.readAllBytes(file.toPath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
    }

    private CategoriesProblem checkCategory(String text){
        CategoriesProblem cat;
        try {
            cat = CategoriesProblem.valueOf(text);
        } catch (IllegalArgumentException ex){
            return null;
        }
        return cat;
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

    public String getBotUsername() {
        return s_BOT_NAME;
    }

    public String getBotToken() {
        return s_TOKEN;
    }
}
