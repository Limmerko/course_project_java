package vlsu.pri117.mep.model.enums;

public enum StatusProblem {
    RESOLVED("Решена"),
    NOT_RESOLVED("Не решена"),
    UNDER_CONSIDERATION("На рассмотрении"),
    REJECTED("Отклонено");

    StatusProblem(String description) {
    }
}
