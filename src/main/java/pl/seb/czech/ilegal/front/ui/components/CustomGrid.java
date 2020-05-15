package pl.seb.czech.ilegal.front.ui.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.function.SerializablePredicate;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomGrid<T> extends Grid<T> {
    protected ListDataProvider<T> gridContent;
    protected HeaderRow filterRow;
    private List<TextField> filterTextFields = new ArrayList<>();


    public CustomGrid(Class<T> beanType, List<T> gridContent) {
        super(beanType);
        this.gridContent = new ListDataProvider<>(gridContent);
        setSizeFull();
        
        configureGridColumns();
        filterRow = appendHeaderRow();
        configureGridFilterRow();

        setDataProvider(this.gridContent);
        addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
        
    }

    protected boolean compareFieldWithFilter(Object field, TextField filter){
        return String.valueOf(field).toLowerCase().contains(filter.getValue().toLowerCase());
    }

    protected void addFilter(Column<T> columnToAddFilter, TextField filter, SerializablePredicate<T> predicate){
        filter.addValueChangeListener(event -> gridContent
                .addFilter(predicate));
        filterRow.getCell(columnToAddFilter).setComponent(filter);
        filterTextFields.add(filter);
    }

    protected void createClearButton(Column<T> columnWithButton){
        Button clearButton = new Button("Usuń filtry");
        clearButton.addClickListener(event -> {
            filterTextFields.forEach(textField -> textField.clear());
        });
        clearButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
        filterRow.getCell(columnWithButton).setComponent(clearButton);
    }

    public void setGridContent(List<T> content) {
        gridContent.getItems().clear();
        gridContent.getItems().addAll(content);
        gridContent.refreshAll();
    }

    protected abstract void configureGridFilterRow();

    protected abstract void configureGridColumns();

    public ListDataProvider<T> getGridContent() {
        return gridContent;
    }

}
