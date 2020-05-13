package pl.seb.czech.ilegal.front.ui.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.seb.czech.ilegal.front.stub.judgement.JudgmentDBService;
import pl.seb.czech.ilegal.front.ui.components.judgement.JudgmentGrid;
import pl.seb.czech.ilegal.front.ui.layout.MainLayout;


@PageTitle("I-Legal | Zapisane orzeczenia")
@Route(value = "judgment-saved", layout = MainLayout.class)
public class JudgmentSavedView extends VerticalLayout {
    private JudgmentDBService repoService;
    private JudgmentGrid judgmentGrid;

    @Autowired
    public JudgmentSavedView(JudgmentDBService repoService) {
        this.repoService = repoService;
        this.judgmentGrid = new JudgmentGrid(this.repoService.getAll());
        
        setSizeFull();
        
        add(this.judgmentGrid);
        
    }
}
