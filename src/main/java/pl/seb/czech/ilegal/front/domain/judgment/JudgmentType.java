package pl.seb.czech.ilegal.front.domain.judgment;

public enum JudgmentType {
    DECISION("postanowienie"),
    RESOLUTION("uchwała"),
    SENTENCE("wyrok"),
    REGULATION("zarządzenie"),
    REASONS("uzasadnienie");

    private final String translation;

    JudgmentType(String s) {
        this.translation = s;
    }

    @Override
    public String toString() {
        return translation;
    }
}
