package pl.seb.czech.ilegal.front.ui.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
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
    private ActSearchResult searchResult;
    private ActSearchQuery userQuery;
    private Span pageInfo;
  


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
        actsGrid.asSingleSelect().addValueChangeListener(event -> showDetails(event.getValue()));
        
        setSizeFull();

        actsDetailBox = new ActsDetailBox(isapClient);

        Div middleContent = new Div(actsGrid, actsDetailBox);
        middleContent.setSizeFull();
        middleContent.setClassName("middle-content");
        actsDetailBox.setVisible(false);
        
        add(actsSearchForm, getButtonBar(), middleContent, getPaginationBar());
    }

    private HorizontalLayout getButtonBar() {
        Span resultCount = new Span();

        Button searchButton = new Button("Szukaj", new Icon(VaadinIcon.SEARCH));
        searchButton.addClickListener(event -> {
            searchAndSetGridAndPage(1);
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

        Notification actAlreadyExistsNotification = new Notification("Akt istnieje już w Twoich aktach!", 3000, Notification.Position.MIDDLE);
        Notification actSuccessfullySaved = new Notification("Akt został prawidłowo zapisany!", 3000, Notification.Position.MIDDLE);
        
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

    private void searchAndSetGridAndPage(int pageNumber) {
        userQuery = actsSearchForm.getBinder().getBean();
        userQuery.setPageNumber(pageNumber);
        searchResult = isapClient.performActSearchQuery(userQuery);
        actsGrid.setGridContent(searchResult.getFoundActsList());
        pageInfo.setText("Strona " + (userQuery.getCurrentPageNumber()) + " z " + calculateMaxPageNumber());
    }

    private HorizontalLayout getPaginationBar() {
        Button firstPageButton = new Button("Pierwsza", new Icon(VaadinIcon.ANGLE_DOUBLE_LEFT));
        firstPageButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
        firstPageButton.addClickListener(event -> {
            if(userQuery != null && searchResult != null) {
                searchAndSetGridAndPage(1);
            }
        });
        
        Button previousPageButton = new Button(new Icon(VaadinIcon.ANGLE_LEFT));
        previousPageButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
        previousPageButton.addClickListener(event -> {
            if(userQuery != null && searchResult != null) {
                int currentPage = userQuery.getCurrentPageNumber();
                if(currentPage > 1) {
                    searchAndSetGridAndPage(currentPage - 1);
                }
            }
        });
        
        pageInfo = new Span();

        Button nextPageButton = new Button(new Icon(VaadinIcon.ANGLE_RIGHT));
        nextPageButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
        nextPageButton.addClickListener(event -> {
            if(userQuery != null && searchResult != null) {
                int currentPage = userQuery.getCurrentPageNumber();
                if(currentPage < calculateMaxPageNumber()) {
                    searchAndSetGridAndPage(currentPage + 1);
                }
            }
        });
        
        Button lastPageButton = new Button("Ostatnia", new Icon(VaadinIcon.ANGLE_DOUBLE_RIGHT));
        lastPageButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
        lastPageButton.addClickListener(event -> {
            if(userQuery != null && searchResult != null) {
                searchAndSetGridAndPage(calculateMaxPageNumber());
            }
        });
        
        HorizontalLayout paginationBar = new HorizontalLayout(firstPageButton, previousPageButton, pageInfo, nextPageButton, lastPageButton);
        paginationBar.setDefaultVerticalComponentAlignment(Alignment.CENTER);
      

        return paginationBar;
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
    
    private int calculateMaxPageNumber(){
        if(userQuery != null && searchResult != null) {
            Integer resultLimit = userQuery.getResultLimit();
            if (resultLimit.equals(0)) {
                resultLimit = 1;
            }
            return (searchResult.getTotalCount() + resultLimit - 1) / resultLimit;
        } else {
            return 0;
        }
    }
    
}
