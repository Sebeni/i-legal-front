package pl.seb.czech.ilegal.front.ui.view;

import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.seb.czech.ilegal.front.client.IsapClient;
import pl.seb.czech.ilegal.front.domain.Act;
import pl.seb.czech.ilegal.front.ui.components.ActsGrid;
import pl.seb.czech.ilegal.front.ui.components.ActsSearchForm;
import pl.seb.czech.ilegal.front.ui.layout.MainLayout;

import java.util.ArrayList;

@PageTitle("I-Legal | Szukaj aktów prawnych")
@Route(value = "searchedActs", layout = MainLayout.class)
public class SearchActsView extends VerticalLayout {
    private IsapClient isapClient;
    private ActsSearchForm actsSearchForm;
    private ActsGrid actsGrid;
    
    
    @Autowired
    public SearchActsView(IsapClient isapClient) {
        this.isapClient = isapClient;
        
        actsGrid = new ActsGrid(new ArrayList<>());
        actsGrid.removeColumn(actsGrid.getLastChangeColumn());
        actsGrid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
        actsGrid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);
        actsGrid.addThemeVariants(GridVariant.LUMO_COMPACT);

        actsSearchForm = new ActsSearchForm(isapClient, actsGrid);
        
        add(actsSearchForm, actsGrid);
    }
}
