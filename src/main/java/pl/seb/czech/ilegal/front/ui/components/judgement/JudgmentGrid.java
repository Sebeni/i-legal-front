package pl.seb.czech.ilegal.front.ui.components.judgement;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import pl.seb.czech.ilegal.front.domain.act.Act;
import pl.seb.czech.ilegal.front.domain.judgement.JudgmentSynopsis;
import pl.seb.czech.ilegal.front.ui.components.FilterTextField;

import java.util.List;

public class JudgmentGrid extends Grid<JudgmentSynopsis> {
    private ListDataProvider<JudgmentSynopsis> gridContent;
    private Column<JudgmentSynopsis> judgmentDateColumn;
    private Column<JudgmentSynopsis> customNameColumn;
    private Column<JudgmentSynopsis> synopsisColumn;


    public JudgmentGrid(List<JudgmentSynopsis> gridContent) {
        super(JudgmentSynopsis.class);
        this.gridContent = new ListDataProvider<>(gridContent);
        setDataProvider(this.gridContent);
        
        setSizeFull();
        addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);

        configureColumns();
        configureFilterRow();
        setClassName("judgment-grid");
    }

    private void configureColumns() {
        setColumns("judgmentDate", "customName", "synopsis");
        judgmentDateColumn = getColumnByKey("judgmentDate").setHeader("Data orzeczenia").setFlexGrow(2).setWidth("10px");
        customNameColumn = getColumnByKey("customName").setHeader("Nazwa").setFlexGrow(4).setWidth("20px");
        synopsisColumn = getColumnByKey("synopsis").setHeader("Treść orzeczenia").setFlexGrow(14);
    }

    private void configureFilterRow() {
        HeaderRow filterRow = appendHeaderRow();

        TextField customNameFilter = new FilterTextField();
        customNameFilter.addValueChangeListener(event -> gridContent
                .addFilter(judgmentSynopsis -> judgmentSynopsis.getCustomName().toLowerCase().contains(customNameFilter.getValue().toLowerCase())));
        filterRow.getCell(customNameColumn).setComponent(customNameFilter);

        TextField synopsisFilter = new FilterTextField();
        synopsisFilter.addValueChangeListener(event -> gridContent
                .addFilter(judgmentSynopsis -> judgmentSynopsis.getSynopsis().toLowerCase().contains(synopsisFilter.getValue().toLowerCase())));
        filterRow.getCell(synopsisColumn).setComponent(synopsisFilter);

        Button clearButton = new Button("Wyczyść filtry");
        clearButton.addClickListener(event -> {
            customNameFilter.clear();
            synopsisFilter.clear();
            
        });
        clearButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
        filterRow.getCell(judgmentDateColumn).setComponent(clearButton);
        
    }

    public ListDataProvider<JudgmentSynopsis> getGridContent() {
        return gridContent;
    }

    public void setGridContent(List<JudgmentSynopsis> content) {
        gridContent.getItems().clear();
        gridContent.getItems().addAll(content);
        gridContent.refreshAll();
    }
}
