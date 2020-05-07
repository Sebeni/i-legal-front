package pl.seb.czech.ilegal.front.ui.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.seb.czech.ilegal.front.client.IsapClient;
import pl.seb.czech.ilegal.front.domain.Act;
import pl.seb.czech.ilegal.front.domain.ActSearchQuery;
import pl.seb.czech.ilegal.front.domain.ActSearchResult;
import pl.seb.czech.ilegal.front.stub.ActService;
import pl.seb.czech.ilegal.front.ui.components.ActsDetailBox;
import pl.seb.czech.ilegal.front.ui.components.ActsGrid;
import pl.seb.czech.ilegal.front.ui.components.ActsSearchForm;
import pl.seb.czech.ilegal.front.ui.layout.MainLayout;

import java.util.ArrayList;

@PageTitle("I-Legal | Szukaj aktów prawnych")
@Route(value = "searchActs", layout = MainLayout.class)
public class SearchActsView extends VerticalLayout {
    private ActsDetailBox actsDetailBox;
    private IsapClient isapClient;
    private ActsSearchForm actsSearchForm;
    private ActsGrid actsGrid;
    private Act selectedAct;
    private ActService actService;
    private Button saveActButton;


    @Autowired
    public SearchActsView(IsapClient isapClient, ActService actService) {
        this.actService = actService;
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

        Div middleContent = new Div(actsGrid, actsDetailBox);
        middleContent.setSizeFull();
        middleContent.setClassName("middle-content");
        actsDetailBox.setVisible(false);

        add(actsSearchForm, getButtonBar(), middleContent);
    }

    private HorizontalLayout getButtonBar() {
        Span resultCount = new Span();

        Button searchButton = new Button("Szukaj", new Icon(VaadinIcon.SEARCH));
        searchButton.addClickListener(event -> {
            ActSearchQuery userQuery = actsSearchForm.getBinder().getBean();
            ActSearchResult searchResult = isapClient.performActSearchQuery(userQuery);
            actsGrid.setGridContent(searchResult.getFoundActsList());
            resultCount.setText("Liczba znalezionych wyników: " + searchResult.getTotalCount());
        });

        Button clearButton = new Button("Wyczyść pola", new Icon(VaadinIcon.ERASER));
        clearButton.addClickListener(event -> {
            actsSearchForm.getActName().clear();
            actsSearchForm.getKeyWord().clear();
            actsSearchForm.getProperName().clear();
            actsSearchForm.getYear().clear();
            actsSearchForm.getPosition().clear();
        });

        Button hideFormButton = new Button("Ukryj formularz", new Icon(VaadinIcon.ANGLE_DOUBLE_UP));
        hideFormButton.addClickListener(event -> {
            if (actsSearchForm.isVisible()) {
                actsSearchForm.setVisible(false);
                hideFormButton.setText("Pokaż formularz");
                hideFormButton.setIcon(new Icon(VaadinIcon.ANGLE_DOUBLE_DOWN));
            } else {
                actsSearchForm.setVisible(true);
                hideFormButton.setText("Ukryj formularz");
                hideFormButton.setIcon(new Icon(VaadinIcon.ANGLE_DOUBLE_UP));
            }
        });

        Notification actAlreadyExistsNotification = new Notification("Akt istnieje już w Twoich aktach!", 3000, Notification.Position.TOP_CENTER);
        Notification actSuccessfullySaved = new Notification("Akt został zapisany!", 3000, Notification.Position.TOP_CENTER);
        
        saveActButton = new Button("Zapisz zaznaczony akt", new Icon(VaadinIcon.FILE_ADD));
        saveActButton.setEnabled(false);
        saveActButton.addClickListener(event -> {
            if(actService.findIfExists(selectedAct.getId())) {
                actAlreadyExistsNotification.open();
            } else {
                actService.addAct(selectedAct);
                actSuccessfullySaved.open();
            }
        });

        HorizontalLayout searchAndResultBar = new HorizontalLayout(hideFormButton, clearButton, saveActButton, searchButton, resultCount);
        searchAndResultBar.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        return searchAndResultBar;
    }

    private void showDetails(Act clickedAct) {
        selectedAct = clickedAct;
        if (clickedAct == null) {
            actsDetailBox.setVisible(false);
            saveActButton.setEnabled(false);
        } else {
            actsDetailBox.setCurrentActAndUpdateBox(clickedAct);
            actsDetailBox.setVisible(true);
            saveActButton.setEnabled(true);
        }
        actsGrid.getGridContent().refreshAll();
    }
}
