package pl.seb.czech.ilegal.front.ui.components.log;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;

import java.util.List;

public abstract class LogGrid<T> extends Grid<T> {
    protected ListDataProvider<T> gridContent;
    
    public LogGrid(Class<T> beanType, List<T> gridContent) {
        super(beanType);
        this.gridContent = new ListDataProvider<>(gridContent);
        setDataProvider(this.gridContent);
        configureGridColumns();
        setSizeFull();
    }

    protected abstract void configureGridColumns();
    
    public void deleteGridContent() {
        gridContent.getItems().clear();
        gridContent.refreshAll();
    }
}
