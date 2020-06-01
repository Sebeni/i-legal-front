package pl.seb.czech.ilegal.front.ui.components.log;

import pl.seb.czech.ilegal.front.domain.log.DeleteLog;

import java.util.List;

public class DeleteLogGrid extends LogGrid<DeleteLog>{
    public DeleteLogGrid(List<DeleteLog> gridContent) {
        super(DeleteLog.class, gridContent);
    }

    @Override
    protected void configureGridColumns() {
        setColumns("timeStamp", "sourceName", "beforeDeleteElementCount", "deleteType");
        getColumnByKey("timeStamp").setHeader("Data usunięcia").setAutoWidth(true);
        getColumnByKey("sourceName").setHeader("Nazwa klasy").setAutoWidth(true);
        getColumnByKey("beforeDeleteElementCount").setHeader("Liczba elementów przed usunięciem").setAutoWidth(true);
        getColumnByKey("deleteType").setHeader("Rodzaj usunięcia").setAutoWidth(true);
    }
}