package pl.seb.czech.ilegal.front.ui.components.judgement;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.data.provider.ListDataProvider;
import pl.seb.czech.ilegal.front.domain.act.Act;
import pl.seb.czech.ilegal.front.domain.judgement.JudgmentSynopsis;

import java.util.List;

public class JudgmentGrid extends Grid<JudgmentSynopsis> {
    private ListDataProvider<JudgmentSynopsis> gridContent;


    public JudgmentGrid(List<JudgmentSynopsis> gridContent) {
        super(JudgmentSynopsis.class);
        this.gridContent = new ListDataProvider<>(gridContent);
        setSizeFull();
        setDataProvider(this.gridContent);
        
        setColumns("judgmentDate", "customName", "synopsis");
        getColumnByKey("judgmentDate").setHeader("Data orzeczenia").setFlexGrow(2).setWidth("10px");
        getColumnByKey("customName").setHeader("Nazwa").setFlexGrow(4).setWidth("20px");
        getColumnByKey("synopsis").setHeader("Treść orzeczenia").setFlexGrow(14);
        
        addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
       
        
    }
}
