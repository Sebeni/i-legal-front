package pl.seb.czech.ilegal.front.ui.view.act;

import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.seb.czech.ilegal.front.client.act.IsapClient;
import pl.seb.czech.ilegal.front.domain.act.Act;
import pl.seb.czech.ilegal.front.domain.act.ActSearchQuery;
import pl.seb.czech.ilegal.front.stub.act.ActDBService;
import pl.seb.czech.ilegal.front.ui.components.act.ActDetailBox;
import pl.seb.czech.ilegal.front.ui.components.act.ActGrid;
import pl.seb.czech.ilegal.front.ui.components.act.ActSearchForm;
import pl.seb.czech.ilegal.front.ui.layout.MainLayout;
import pl.seb.czech.ilegal.front.ui.view.SearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@PageTitle("I-Legal | Szukaj aktów prawnych")
@Route(value = "acts/search", layout = MainLayout.class)
public class ActSearchView extends SearchView<Act, String> implements HasUrlParameter<String> {

    @Autowired
    public ActSearchView(IsapClient isapClient, ActDBService actService) {
        super(actService, isapClient, new ActDetailBox(isapClient), new ActSearchForm(isapClient), new ActGrid(new ArrayList<>()));
        grid.removeColumn(grid.getColumnByKey("changeDate"));
    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        QueryParameters qp = event.getLocation().getQueryParameters();

        if(qp != null && !qp.getParameters().isEmpty()) {
            Map<String, List<String>> paramMap = qp.getParameters();

            ActSearchQuery query = new ActSearchQuery();

            query.setYear(Integer.parseInt(paramMap.get(SearchView.YEAR_PARAM).get(0)));
            query.setPosition(Integer.parseInt(paramMap.get(SearchView.POS_PARAM).get(0)));

            setBinderBean(query);
        }
    }
}
