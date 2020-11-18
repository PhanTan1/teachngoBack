package be.teachngo.data;

public enum Category {

    PRIMAIRE("Primaire"),
    SECONDAIRE("Secondaire"),
    SUPERIEUR("Superieur"),
    LANGUE("Langues");

    private final String label;

    Category(String label) {
        this.label = label;
    }
}
