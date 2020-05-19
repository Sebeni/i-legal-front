package pl.seb.czech.ilegal.front.ui.view.judgment;

import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.seb.czech.ilegal.front.client.judgment.SaosClient;
import pl.seb.czech.ilegal.front.domain.act.ActSearchQuery;
import pl.seb.czech.ilegal.front.domain.judgement.JudgmentSynopsis;
import pl.seb.czech.ilegal.front.domain.judgement.JudgmentSynopsisSearchQuery;
import pl.seb.czech.ilegal.front.stub.judgement.JudgmentDBService;
import pl.seb.czech.ilegal.front.ui.components.judgement.JudgmentDetailBox;
import pl.seb.czech.ilegal.front.ui.components.judgement.JudgmentGrid;
import pl.seb.czech.ilegal.front.ui.components.judgement.JudgmentSearchForm;
import pl.seb.czech.ilegal.front.ui.layout.MainLayout;
import pl.seb.czech.ilegal.front.ui.view.SearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@PageTitle("I-Legal | Szukaj orzeczeń")
@Route(value = "judgment/search", layout = MainLayout.class)
public class JudgmentSearchView extends SearchView<JudgmentSynopsis, Long> implements HasUrlParameter<String> {

    @Autowired
    public JudgmentSearchView(SaosClient saosClient, JudgmentDBService judgmentDBService) {
        super(judgmentDBService, saosClient, new JudgmentDetailBox(saosClient), new JudgmentSearchForm(), new JudgmentGrid(new ArrayList<>()));
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