package vlsu.pri117.mep.model.enums;

public enum Roles {
    ROLE_ADMIN(1, "Администратор"),
    ROLE_USER(2, "Пользователь"),
    ROLE_MODERATOR(3, "Модератор"),
    ROLE_NEWS_MODERATOR(4, "Новостной модератор");

    private int id;
    private String description;

    Roles(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public int getId() {
        return id;
    }
}
