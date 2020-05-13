package pl.seb.czech.ilegal.front.ui.components.act;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import pl.seb.czech.ilegal.front.domain.act.Act;

import java.util.List;

public class ActGrid extends Grid<Act> {
    private ListDataProvider<Act> gridContent;
    private Column<Act> yearColumn;
    private Column<Act> positionColumn;
    private Column<Act> titleColumn;
    private Column<Act> statusColumn;
    private Column<Act> lastChangeColumn;
    private static final String FILTER_PLACEHOLDER = "Filtr";

    public ActGrid(List<Act> gridContent) {
        super(Act.class);
        this.gridContent = new ListDataProvider<>(gridContent);
        setClassName("act-grid");
        setSizeFull();
        configureGridColumns();
        configureGridFilterRow();
        setDataProvider(this.gridContent);
        addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
    }

    private void configureGridColumns() {
        setColumns("year", "position", "title", "status", "changeDate");
        yearColumn = getColumnByKey("year").setHeader("Rok").setFlexGrow(3).setWidth("20px");
        positionColumn = getColumnByKey("position").setHeader("Poz.").setFlexGrow(2).setWidth("15px");
        titleColumn = getColumnByKey("title").setHeader("Tytuł").setFlexGrow(8);
        statusColumn = getColumnByKey("status").setFlexGrow(6).setWidth("30px");
        lastChangeColumn = getColumnByKey("changeDate")
                .setHeader("Ost. zmiana")
                .setFlexGrow(4)
                .setWidth("25px");

        
    }

    private void configureGridFilterRow() {
        HeaderRow filterRow = appendHeaderRow();

        TextField yearFieldFilter = createFilterTxtField();
        yearFieldFilter.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        yearFieldFilter.addValueChangeListener(event -> gridContent
                .addFilter(act -> String.valueOf(act.getYear()).contains(yearFieldFilter.getValue())));
        filterRow.getCell(yearColumn).setComponent(yearFieldFilter);
        
        TextField posFieldFilter = createFilterTxtField();
        posFieldFilter.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        posFieldFilter.addValueChangeListener(event -> gridContent
                .addFilter(act -> String.valueOf(act.getPosition()).contains(posFieldFilter.getValue())));
        filterRow.getCell(positionColumn).setComponent(posFieldFilter);
        
        TextField titleFieldFilter = createFilterTxtField();
        titleFieldFilter.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        titleFieldFilter.addValueChangeListener(event -> gridContent
                .addFilter(act -> act.getTitle().toLowerCase().contains(titleFieldFilter.getValue().toLowerCase())));
        filterRow.getCell(titleColumn).setComponent(titleFieldFilter);
        
        TextField statusFieldFilter = createFilterTxtField();
        statusFieldFilter.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        statusFieldFilter.addValueChangeListener(event -> gridContent
                .addFilter(act -> act.getStatus().toLowerCase().contains(statusFieldFilter.getValue().toLowerCase())));
        filterRow.getCell(statusColumn).setComponent(statusFieldFilter);

        Button clearButton = new Button("Wyczyść filtry");
        clearButton.addClickListener(event -> {
            yearFieldFilter.clear();
            posFieldFilter.clear();
            titleFieldFilter.clear();
            statusFieldFilter.clear();
        });
        clearButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
        filterRow.getCell(lastChangeColumn).setComponent(clearButton);
    }

    private TextField createFilterTxtField() {
        TextField filterField = new TextField();
        filterField.setValueChangeMode(ValueChangeMode.EAGER);
        filterField.setPlaceholder(FILTER_PLACEHOLDER);
        filterField.setSizeFull();
        return filterField;
    }

    public ListDataProvider<Act> getGridContent() {
        return gridContent;
    }

    public void setGridContent(List<Act> content) {
        gridContent.getItems().clear();
        gridContent.getItems().addAll(content);
        gridContent.refreshAll();
    }

    public Column<Act> getLastChangeColumn() {
        return lastChangeColumn;
    }
}

