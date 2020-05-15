package pl.seb.czech.ilegal.front.ui.view.judgment;

import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.seb.czech.ilegal.front.client.judgment.SaosClient;
import pl.seb.czech.ilegal.front.domain.judgement.JudgmentSynopsis;
import pl.seb.czech.ilegal.front.stub.judgement.JudgmentDBService;
import pl.seb.czech.ilegal.front.ui.components.judgement.JudgmentDetailBox;
import pl.seb.czech.ilegal.front.ui.components.judgement.JudgmentGrid;
import pl.seb.czech.ilegal.front.ui.layout.MainLayout;
import pl.seb.czech.ilegal.front.ui.view.SavedView;


@PageTitle("I-Legal | Zapisane orzeczenia")
@Route(value = "judgment-saved", layout = MainLayout.class)
public class JudgmentSavedView extends SavedView<JudgmentSynopsis, Long> {
    
    @Autowired
    public JudgmentSavedView(JudgmentDBService repoService, SaosClient saosClient) {
        super(repoService, new JudgmentGrid(repoService.getAll()), new JudgmentDetailBox(saosClient));
    }
}
