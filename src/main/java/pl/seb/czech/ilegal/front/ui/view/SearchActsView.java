package pl.seb.czech.ilegal.front.ui.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.seb.czech.ilegal.front.client.IsapClient;
import pl.seb.czech.ilegal.front.domain.Act;
import pl.seb.czech.ilegal.front.domain.ActSearchQuery;
import pl.seb.czech.ilegal.front.domain.ActSearchResult;
import pl.seb.czech.ilegal.front.ui.components.ActsDetailBox;
import pl.seb.czech.ilegal.front.ui.components.ActsGrid;
import pl.seb.czech.ilegal.front.ui.components.ActsSearchForm;
import pl.seb.czech.ilegal.front.ui.layout.MainLayout;

import java.util.ArrayList;

@PageTitle("I-Legal | Szukaj aktów prawnych")
@Route(value = "searchedActs", layout = MainLayout.class)
public class SearchActsView extends VerticalLayout {
    private ActsDetailBox actsDetailBox;
    private IsapClient isapClient;
    private ActsSearchForm actsSearchForm;
    private ActsGrid actsGrid;
    
    
    @Autowired
    public SearchActsView(IsapClient isapClient) {
        setSizeFull();
        this.isapClient = isapClient;
        actsSearchForm = new ActsSearchForm(isapClient);

        actsGrid = new ActsGrid(new ArrayList<Act>());
        actsGrid.removeColumn(actsGrid.getLastChangeColumn());
        actsGrid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
        actsGrid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);
        actsGrid.addThemeVariants(GridVariant.LUMO_COMPACT);
        actsGrid.asSingleSelect().addValueChangeListener(event -> showDetails(event.getValue()));
        
        setSizeFull();

        actsDetailBox = new ActsDetailBox(isapClient);
        
        Button searchButton = new Button("Szukaj", new Icon(VaadinIcon.SEARCH));
        Span resultCount = new Span();
        HorizontalLayout searchAndResultBar = new HorizontalLayout(searchButton, resultCount);

        searchButton.addClickListener(event -> {
            ActSearchQuery userQuery = actsSearchForm.getBinder().getBean();
            ActSearchResult searchResult = isapClient.performActSearchQuery(userQuery);

            actsGrid.setGridContent(searchResult.getFoundActsList());
            resultCount.setText("Znaleziono " + searchResult.getTotalCount() + " wyników");
        });

        Div middleContent = new Div(actsGrid, actsDetailBox);
        middleContent.setSizeFull();
        actsDetailBox.setVisible(false);
        
        add(actsSearchForm, searchAndResultBar, middleContent);
    }

    private void showDetails(Act clickedAct) {
        if (clickedAct == null) {
            actsDetailBox.setVisible(false);
            
        } else {
            actsDetailBox.setCurrentActAndUpdateBox(clickedAct);
            actsDetailBox.setVisible(true);
        }
        actsGrid.getGridContent().refreshAll();
    }
}
