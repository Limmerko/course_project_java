package vlsu.pri117.mep.model.enums;

public enum Roles {
    USER("Пользователь"),
    ADMIN("Администратор"),
    MODERATOR("Модератор"),
    NEWS_MODERATOR("Новостной модератор");

    private String description;

    Roles(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
