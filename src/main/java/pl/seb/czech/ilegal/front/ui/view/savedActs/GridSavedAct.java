package pl.seb.czech.ilegal.front.ui.view.savedActs;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.LocalDateTimeRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import pl.seb.czech.ilegal.front.domain.Act;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class GridSavedAct extends Grid<Act> {
    private ListDataProvider<Act> gridContent;
    private Column<Act> yearColumn;
    private Column<Act> positionColumn;
    private Column<Act> titleColumn;
    private Column<Act> statusColumn;
    private Column<Act> lastChangeColumn;
    private static final String FILTER_PLACEHOLDER = "Filtr";

    public GridSavedAct(ListDataProvider<Act> gridContent) {
        super(Act.class);
        this.gridContent = gridContent;
        setClassName("act-grid");
        setSizeFull();
        configureGridColumns();
        configureGridFilterRow();
        setDataProvider(gridContent);
    }

    private void configureGridColumns() {
        setColumns("year", "position", "title", "status", "changeDate");
        yearColumn = getColumnByKey("year").setHeader("Rok").setFlexGrow(2);
        positionColumn = getColumnByKey("position").setHeader("Poz.").setFlexGrow(2);
        titleColumn = getColumnByKey("title").setHeader("Tytuł").setFlexGrow(8);
        statusColumn = getColumnByKey("status").setFlexGrow(6);
        lastChangeColumn = getColumnByKey("changeDate")
                .setHeader("Ost. zmiana")
                .setFlexGrow(4);
        
//addColumn(new LocalDateTimeRenderer<Act>(Act::getChangeDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))

        addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
    }

    private void configureGridFilterRow() {
        HeaderRow filterRow = appendHeaderRow();

        TextField yearFieldFilter = createAndAddFilterField();
        yearFieldFilter.addValueChangeListener(event -> gridContent
                .addFilter(act -> String.valueOf(act.getYear()).contains(yearFieldFilter.getValue())));
        filterRow.getCell(yearColumn).setComponent(yearFieldFilter);
        
        TextField posFieldFilter = createAndAddFilterField();
        posFieldFilter.addValueChangeListener(event -> gridContent
                .addFilter(act -> String.valueOf(act.getPosition()).contains(posFieldFilter.getValue())));
        filterRow.getCell(positionColumn).setComponent(posFieldFilter);
        
        TextField titleFieldFilter = createAndAddFilterField();
        titleFieldFilter.addValueChangeListener(event -> gridContent
                .addFilter(act -> act.getTitle().toLowerCase().contains(titleFieldFilter.getValue().toLowerCase())));
        filterRow.getCell(titleColumn).setComponent(titleFieldFilter);
        
        TextField statusFieldFilter = createAndAddFilterField();
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
        filterRow.getCell(lastChangeColumn).setComponent(clearButton);
    }

    private TextField createAndAddFilterField() {
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
    
}

