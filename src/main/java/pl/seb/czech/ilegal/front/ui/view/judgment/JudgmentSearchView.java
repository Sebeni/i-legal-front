package pl.seb.czech.ilegal.front.ui.view.judgment;

import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.seb.czech.ilegal.front.backend.clients.judgment.JudgmentSaosClient;
import pl.seb.czech.ilegal.front.domain.judgment.JudgmentSynopsis;
import pl.seb.czech.ilegal.front.domain.judgment.JudgmentSynopsisSearchQuery;
import pl.seb.czech.ilegal.front.backend.clients.judgment.JudgmentDbClient;
import pl.seb.czech.ilegal.front.ui.components.judgment.JudgmentDetailBox;
import pl.seb.czech.ilegal.front.ui.components.judgment.JudgmentGrid;
import pl.seb.czech.ilegal.front.ui.components.judgment.JudgmentSearchForm;
import pl.seb.czech.ilegal.front.ui.layout.MainLayout;
import pl.seb.czech.ilegal.front.ui.view.SearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@PageTitle("I-Legal | Szukaj orzeczeń")
@Route(value = "judgment/search", layout = MainLayout.class)
public class JudgmentSearchView extends SearchView<JudgmentSynopsis> implements HasUrlParameter<String> {

    @Autowired
    public JudgmentSearchView(JudgmentSaosClient judgmentSaosClient, JudgmentDbClient judgmentDBService) {
        super(judgmentDBService, judgmentSaosClient, new JudgmentDetailBox(judgmentSaosClient), new JudgmentSearchForm(), new JudgmentGrid(new ArrayList<>()));
    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        
        QueryParameters qp = event.getLocation().getQueryParameters();
        if(qp != null && !qp.getParameters().isEmpty()){
            Map<String, List<String>> paramMap = qp.getParameters();

            JudgmentSynopsisSearchQuery query = new JudgmentSynopsisSearchQuery();
            query.setReferencedRegulationYearPos(paramMap.get(SearchView.YEAR_PARAM).get(0) + "/" + paramMap.get(SearchView.POS_PARAM).get(0));
            setBinderBean(query);
        }
        
    }
}