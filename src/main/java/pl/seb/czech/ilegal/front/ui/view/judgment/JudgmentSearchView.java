package pl.seb.czech.ilegal.front.ui.view.judgment;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.seb.czech.ilegal.front.client.judgment.SaosClient;
import pl.seb.czech.ilegal.front.domain.judgement.JudgmentSynopsis;
import pl.seb.czech.ilegal.front.stub.judgement.JudgmentDBService;
import pl.seb.czech.ilegal.front.ui.components.judgement.JudgmentDetailBox;
import pl.seb.czech.ilegal.front.ui.components.judgement.JudgmentGrid;
import pl.seb.czech.ilegal.front.ui.components.judgement.JudgmentSearchForm;
import pl.seb.czech.ilegal.front.ui.layout.MainLayout;
import pl.seb.czech.ilegal.front.ui.view.SearchView;

import java.util.ArrayList;


@PageTitle("I-Legal | Szukaj orzeczeń")
@Route(value = "judgment-search", layout = MainLayout.class)
public class JudgmentSearchView extends SearchView<JudgmentSynopsis, Long> {

    @Autowired
    public JudgmentSearchView(SaosClient saosClient, JudgmentDBService judgmentDBService) {
        super(judgmentDBService, saosClient, new JudgmentDetailBox(saosClient), new JudgmentSearchForm(), new JudgmentGrid(new ArrayList<>()));
    }
    
}