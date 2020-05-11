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
import pl.seb.czech.ilegal.front.client.act.IsapClient;
import pl.seb.czech.ilegal.front.domain.act.Act;
import pl.seb.czech.ilegal.front.domain.act.ActSearchQuery;
import pl.seb.czech.ilegal.front.domain.act.ActSearchResult;
import pl.seb.czech.ilegal.front.stub.act.ActDBService;
import pl.seb.czech.ilegal.front.ui.components.ActDetailBox;
import pl.seb.czech.ilegal.front.ui.components.ActGrid;
import pl.seb.czech.ilegal.front.ui.components.ActSearchForm;
import pl.seb.czech.ilegal.front.ui.components.PaginationBar;
import pl.seb.czech.ilegal.front.ui.layout.MainLayout;

import java.util.ArrayList;

@PageTitle("I-Legal | Szukaj aktów prawnych")
@Route(value = "acts-search", layout = MainLayout.class)
public class ActSearchView extends VerticalLayout implements SearchView {
    private ActDetailBox actDetailBox;
    private IsapClient isapClient;
    private ActSearchForm actSearchForm;
    private ActGrid actGrid;
    private Act selectedAct;
    private ActDBService actService;
    private Button saveActButton;
    private ActSearchResult searchResult;
    private ActSearchQuery userQuery;
    private PaginationBar paginationBar;
    private Button hideFormButton;

    @Autowired
    public ActSearchView(IsapClient isapClient, ActDBService actService) {
        this.actService = actService;
        setSizeFull();
        this.isapClient = isapClient;
        actSearchForm = new ActSearchForm(isapClient);

        actGrid = new ActGrid(new ArrayList<Act>());
        actGrid.removeColumn(actGrid.getLastChangeColumn());
        actGrid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
        actGrid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);
        actGrid.asSingleSelect().addValueChangeListener(event -> showDetails(event.getValue()));
        actGrid.setHeightByRows(true);
        
        setSizeFull();

        actDetailBox = new ActDetailBox(isapClient);

        Div middleContent = new Div(actGrid, actDetailBox);
        middleContent.setSizeFull();
        middleContent.setClassName("middle-content");
        actDetailBox.setVisible(false);
        
        paginationBar = new PaginationBar(this);
        
        add(actSearchForm, getButtonBar(), paginationBar, middleContent);
    }

    private HorizontalLayout getButtonBar() {
        Span resultCount = new Span();

        Button searchButton = new Button("Szukaj", new Icon(VaadinIcon.SEARCH));
        searchButton.addClickListener(event -> {
            performSearchAndSetGrid(1);
            resultCount.setText("Liczba znalezionych wyników: " + searchResult.getNumOfResults());
        });

        Button clearButton = new Button("Wyczyść pola", new Icon(VaadinIcon.ERASER));
        clearButton.addClickListener(event -> {
            actSearchForm.getActName().clear();
            actSearchForm.getKeyWord().clear();
            actSearchForm.getProperName().clear();
            actSearchForm.getYear().clear();
            actSearchForm.getPosition().clear();
        });

        hideFormButton = new Button("Ukryj formularz", new Icon(VaadinIcon.ANGLE_DOUBLE_UP));
        hideFormButton.addClickListener(event -> {
            if (actSearchForm.isVisible()) {
                hideForm();
            } else {
                showForm();
            }
        });

        Notification actAlreadyExistsNotification = new Notification("Akt istnieje już w Twoich aktach!", 3000, Notification.Position.MIDDLE);
        Notification actSuccessfullySaved = new Notification("Akt został prawidłowo zapisany!", 3000, Notification.Position.MIDDLE);
        
        saveActButton = new Button("Zapisz zaznaczony akt", new Icon(VaadinIcon.FILE_ADD));
        saveActButton.setEnabled(false);
        saveActButton.addClickListener(event -> {
            if(actService.findIfExists(selectedAct.getId())) {
                actAlreadyExistsNotification.open();
            } else {
                actService.addElement(selectedAct);
                actSuccessfullySaved.open();
            }
        });

        HorizontalLayout searchAndResultBar = new HorizontalLayout(searchButton, clearButton, saveActButton, hideFormButton, resultCount);
        searchAndResultBar.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        return searchAndResultBar;
    }

    private void showForm() {
        actSearchForm.setVisible(true);
        hideFormButton.setText("Ukryj formularz");
        hideFormButton.setIcon(new Icon(VaadinIcon.ANGLE_DOUBLE_UP));
    }

    private void hideForm() {
        actSearchForm.setVisible(false);
        hideFormButton.setText("Pokaż formularz");
        hideFormButton.setIcon(new Icon(VaadinIcon.ANGLE_DOUBLE_DOWN));
    }

    @Override
    public void performSearchAndSetGrid(int pageNumber) {
        userQuery = actSearchForm.getBinder().getBean();
        userQuery.setPageNumber(pageNumber);
        searchResult = isapClient.performActSearchQuery(userQuery);
        actGrid.setGridContent(searchResult.getFoundActsList());
        paginationBar.updateQueryAndResult(userQuery, searchResult);
        hideForm();
    }
    

    private void showDetails(Act clickedAct) {
        selectedAct = clickedAct;
        if (clickedAct == null) {
            actDetailBox.setVisible(false);
            saveActButton.setEnabled(false);
        } else {
            actDetailBox.setCurrentActAndUpdateBox(clickedAct);
            actDetailBox.setVisible(true);
            saveActButton.setEnabled(true);
        }
        actGrid.getGridContent().refreshAll();
    }
    

    @Override
    public ActSearchResult getSearchResult() {
        return searchResult;
    }

    @Override
    public ActSearchQuery getSearchQuery() {
        return userQuery;
    }
    
}
