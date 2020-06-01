package pl.seb.czech.ilegal.front.ui.components.log;

import pl.seb.czech.ilegal.front.domain.log.SearchLog;

import java.util.List;

public class SearchLogGrid extends LogGrid<SearchLog>{

    public SearchLogGrid(List<SearchLog> gridContent) {
        super(SearchLog.class, gridContent);
    }
    
    @Override
    protected void configureGridColumns() {
        setColumns("createdOn", "searchParams", "resultCount");
        getColumnByKey("createdOn").setHeader("Data wyszukania").setAutoWidth(true);
        getColumnByKey("searchParams").setHeader("Nazwa parametrów").setAutoWidth(true);
        getColumnByKey("resultCount").setHeader("Liczba wyników").setAutoWidth(true);
    }
}
