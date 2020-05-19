package pl.seb.czech.ilegal.front.ui.components;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.QueryParameters;
import pl.seb.czech.ilegal.front.ui.view.SearchView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class DetailBox<T> extends VerticalLayout {
    public abstract void setDetailsAndUpdateBox(T currentElement);
    
    protected QueryParameters getYearPosQueryParams(String year, String pos) {
        Map<String, List<String>> qp = new HashMap<>();
        
        qp.put(SearchView.YEAR_PARAM, Collections.singletonList(year));
        qp.put(SearchView.POS_PARAM, Collections.singletonList(pos));
        
        return new QueryParameters(qp);
        
    }

    protected QueryParameters getYearPosQueryParams(Integer year, Integer pos) {
        return getYearPosQueryParams(String.valueOf(year), String.valueOf(pos));
    }
    
}
