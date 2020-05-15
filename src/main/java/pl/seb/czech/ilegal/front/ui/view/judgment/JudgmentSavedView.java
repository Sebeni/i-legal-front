package pl.seb.czech.ilegal.front.ui.view.judgment;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.seb.czech.ilegal.front.domain.judgement.JudgmentSynopsis;
import pl.seb.czech.ilegal.front.stub.judgement.JudgmentDBService;
import pl.seb.czech.ilegal.front.ui.components.judgement.JudgmentDetailBox;
import pl.seb.czech.ilegal.front.ui.components.judgement.JudgmentGrid;
import pl.seb.czech.ilegal.front.ui.components.judgement.JudgmentTextDialog;
import pl.seb.czech.ilegal.front.ui.layout.MainLayout;


@PageTitle("I-Legal | Zapisane orzeczenia")
@Route(value = "judgment-saved", layout = MainLayout.class)
public class JudgmentSavedView extends VerticalLayout {
    private JudgmentDBService repoService;
    private JudgmentGrid judgmentGrid;
    private JudgmentDetailBox judgmentDetailBox;

    @Autowired
    public JudgmentSavedView(JudgmentDBService repoService) {
        this.repoService = repoService;
        this.judgmentGrid = new JudgmentGrid(this.repoService.getAll());
        this.judgmentDetailBox = new JudgmentDetailBox();
        judgmentGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        
        setSizeFull();
        
        Div middleContent = new Div(judgmentGrid, judgmentDetailBox);
        judgmentDetailBox.setVisible(false);
        
        middleContent.setClassName("middle-content");
        middleContent.setSizeFull();
        
        add(middleContent);
        
        judgmentGrid.asSingleSelect().addValueChangeListener(event -> {
            JudgmentSynopsis selected = event.getValue();
            if(selected != null) {
                judgmentDetailBox.setJudgmentDetailsAndUpdateBox(selected.getJudgmentDetails());
            } else {
                judgmentDetailBox.setVisible(false);
            }
            judgmentGrid.getGridContent().refreshAll();
        });
        
        
        
        
    }
}
