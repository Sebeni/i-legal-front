package pl.seb.czech.ilegal.front.ui.view;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.seb.czech.ilegal.front.domain.SearchQuery;
import pl.seb.czech.ilegal.front.domain.SearchResult;
import pl.seb.czech.ilegal.front.ui.layout.MainLayout;


@PageTitle("I-Legal | Szukaj orzeczeń")
@Route(value = "judgment-search", layout = MainLayout.class)
public class JudgmentSearchView extends HorizontalLayout implements SearchView {

    public JudgmentSearchView() {
        
    }

    @Override
    public void performSearchAndSetGrid(int pageNumber) {
        
    }

    @Override
    public SearchResult getSearchResult() {
        return null;
    }

    @Override
    public SearchQuery getSearchQuery() {
        return null;
    }
}
