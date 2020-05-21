package pl.seb.czech.ilegal.front.ui.components.judgment;

import com.vaadin.flow.component.textfield.TextField;
import pl.seb.czech.ilegal.front.domain.judgment.JudgmentSynopsis;
import pl.seb.czech.ilegal.front.ui.components.CustomGrid;
import pl.seb.czech.ilegal.front.ui.components.FilterTextField;

import java.util.List;

public class JudgmentGrid extends CustomGrid<JudgmentSynopsis> {
    private Column<JudgmentSynopsis> judgmentDateColumn;
    private Column<JudgmentSynopsis> customNameColumn;
    private Column<JudgmentSynopsis> synopsisColumn;


    public JudgmentGrid(List<JudgmentSynopsis> gridContent) {
        super(JudgmentSynopsis.class, gridContent);
        setClassName("judgment-grid");
    }

    @Override
    protected void configureGridColumns() {
        setColumns("judgmentDate", "customName", "synopsis");
        judgmentDateColumn = getColumnByKey("judgmentDate").setHeader("Data orzeczenia").setFlexGrow(2).setWidth("10px");
        customNameColumn = getColumnByKey("customName").setHeader("Nazwa").setFlexGrow(4).setWidth("20px");
        synopsisColumn = getColumnByKey("synopsis").setHeader("Treść orzeczenia").setFlexGrow(14);
    }

    @Override
    protected void configureGridFilterRow() {
        TextField customNameFilter = new FilterTextField();
        addFilter(customNameColumn, customNameFilter, judgmentSynopsis -> compareFieldWithFilter(judgmentSynopsis.getCustomName(), customNameFilter));

        TextField synopsisFilter = new FilterTextField();
        addFilter(synopsisColumn, synopsisFilter, judgmentSynopsis -> compareFieldWithFilter(judgmentSynopsis.getSynopsis(), synopsisFilter));
        
        createClearButton(judgmentDateColumn);
    }


}
