package vlsu.pri117.mep.model.enums;

public enum CategoriesProblem {
    ROADS("Дороги"),
    HCS("ЖКХ");

    private String description;

    CategoriesProblem(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}
