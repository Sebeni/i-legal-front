package pl.seb.czech.ilegal.front.domain.judgment;

public enum CourtType {
    ALL("Wszystkie"),
    COMMON("Sąd powszechny"),
    SUPREME("Sąd Najwyższy"),
    ADMINISTRATIVE("Sąd administracyjny"),
    CONSTITUTIONAL_TRIBUNAL("Trybunał Konstytucyjny"),
    NATIONAL_APPEAL_CHAMBER("Krajowa Izba Odwoławcza");

    private final String translation;
    
    CourtType(String s) {
        this.translation = s;
    }

    @Override
    public String toString() {
        return translation;
    }
}
