package vlsu.pri117.mep.model.enums;

public enum Roles {
    ROLE_ADMIN("Администратор"),
    ROLE_USER("Пользователь"),
    ROLE_MODERATOR("Модератор"),
    ROLE_NEWS_MODERATOR("Новостной модератор");

    private String description;

    Roles(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
