package pl.seb.czech.ilegal.front.domain.act;

public enum ActPublisher {
    WDU("Dziennik Ustaw"),
    WMP("Monitor Polski"),
    ALL("Wszystkie");
    
    private final String translation;

    ActPublisher(String s) {
        this.translation = s;
    }

    @Override
    public String toString() {
        return translation;
    }
}
