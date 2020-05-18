package pl.seb.czech.ilegal.front.ui.view;


import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.seb.czech.ilegal.front.client.Client;
import pl.seb.czech.ilegal.front.domain.DummyEntity;
import pl.seb.czech.ilegal.front.domain.SearchQuery;
import pl.seb.czech.ilegal.front.domain.SearchResult;
import pl.seb.czech.ilegal.front.stub.StubDBService;
import pl.seb.czech.ilegal.front.ui.components.PaginationBar;
import pl.seb.czech.ilegal.front.ui.components.SearchForm;
import pl.seb.czech.ilegal.front.ui.components.CustomGrid;
import pl.seb.czech.ilegal.front.ui.components.DetailBox;

public abstract class SearchView<E extends DummyEntity<K>, K> extends VerticalLayout {
    
    private StubDBService<E, K> dbService;
    private Client<E> client;

    private DetailBox<E> detailBox;
    protected SearchForm searchForm;
    protected CustomGrid<E> grid;
    
    private E currentElement;

    protected SearchResult<E> searchResult;
    protected SearchQuery searchQuery;

    private Button saveButton;
    private Button hideFormButton;
    protected PaginationBar<E, K> paginationBar;

    public SearchView(StubDBService<E, K> dbService, Client<E> client, DetailBox<E> detailBox, SearchForm searchForm, CustomGrid<E> grid) {
        this.dbService = dbService;
        this.client = client;

        this.detailBox = detailBox;
        detailBox.setVisible(false);

        this.searchForm = searchForm;

        this.grid = grid;
        this.grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);
        this.grid.setHeightByRows(true);

        this.grid.asSingleSelect().addValueChangeListener(event -> showDetails(event.getValue()));
        
        Div middleContent = new Div(this.grid, this.detailBox);
        middleContent.setSizeFull();
        middleContent.setClassName("middle-content");

        paginationBar = new PaginationBar<>(this);
        add(this.searchForm, getButtonBar(), paginationBar, middleContent);
        setSizeFull();
    }

    private HorizontalLayout getButtonBar() {
        Span resultCount = new Span();

        Button searchButton = new Button("Szukaj", new Icon(VaadinIcon.SEARCH));
        searchButton.addClickListener(event -> {
            performSearchAndSetGrid(1);
            if(detailBox.isVisible()){
                detailBox.setVisible(false);
            }
            resultCount.setText("Liczba znalezionych wyników: " + searchResult.getNumOfResults());
        });

        Button clearButton = new Button("Wyczyść pola", new Icon(VaadinIcon.ERASER));
        clearButton.addClickListener(event -> searchForm.getFormFieldsForClear().forEach(HasValue::clear));

        hideFormButton = new Button("Ukryj formularz", new Icon(VaadinIcon.ANGLE_DOUBLE_UP));
        hideFormButton.addClickListener(event -> {
            if (searchForm.isVisible()) {
                hideForm();
            } else {
                showForm();
            }
        });

        Notification actAlreadyExistsNotification = new Notification("Element jest już zapisany!", 3000, Notification.Position.MIDDLE);
        actAlreadyExistsNotification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        Notification actSuccessfullySaved = new Notification("Element został prawidłowo zapisany!", 3000, Notification.Position.MIDDLE);
        actSuccessfullySaved.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        
        saveButton = new Button("Zapisz zaznaczony element", new Icon(VaadinIcon.FILE_ADD));
        saveButton.setEnabled(false);
        saveButton.addClickListener(event -> {
            if(dbService.findIfExists(currentElement.getId())) {
                actAlreadyExistsNotification.open();
            } else {
                dbService.addElement(currentElement);
                actSuccessfullySaved.open();
            }
        });

        HorizontalLayout searchAndResultBar = new HorizontalLayout(searchButton, clearButton, saveButton, hideFormButton, resultCount);
        searchAndResultBar.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        return searchAndResultBar;
    }

    private void showForm() {
        searchForm.setVisible(true);
        hideFormButton.setText("Ukryj formularz");
        hideFormButton.setIcon(new Icon(VaadinIcon.ANGLE_DOUBLE_UP));
    }

    protected void hideForm() {
        searchForm.setVisible(false);
        hideFormButton.setText("Pokaż formularz");
        hideFormButton.setIcon(new Icon(VaadinIcon.ANGLE_DOUBLE_DOWN));
    }
    
    protected void showDetails(E selectedElement) {
        this.currentElement = selectedElement;
        if (selectedElement == null) {
            detailBox.setVisible(false);
            saveButton.setEnabled(false);
        } else {
            detailBox.setDetailsAndUpdateBox(selectedElement);
            detailBox.setVisible(true);
            saveButton.setEnabled(true);
        }
        grid.getGridContent().refreshAll();
    }
    
    public void performSearchAndSetGrid(int pageNumber) {
        searchQuery = searchForm.getSearchQueryFromForm();
        searchQuery.setPageNumber(pageNumber);
        searchResult = client.performSearchQuery(searchQuery);
        grid.setGridContent(searchResult.getResultsList());
        paginationBar.updateQueryAndResult(searchQuery, searchResult);
        hideForm();
    }
    
    public SearchResult<E> getSearchResult(){
        return searchResult;
    }
    
    public SearchQuery getSearchQuery(){
        return searchQuery;
    }
}
