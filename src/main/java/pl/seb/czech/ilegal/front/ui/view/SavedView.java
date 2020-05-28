package pl.seb.czech.ilegal.front.ui.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.seb.czech.ilegal.front.domain.BaseEntity;
import pl.seb.czech.ilegal.front.backend.clients.DbClient;
import pl.seb.czech.ilegal.front.ui.components.CustomGrid;
import pl.seb.czech.ilegal.front.ui.components.DetailBox;

public abstract class SavedView<E extends BaseEntity> extends VerticalLayout {
    protected DbClient<E> dbService;
    protected CustomGrid<E> grid;
    private DetailBox<E> detailBox;
    protected E selectedElement;

    private Button hideDetailsButton;
    private Button refreshButton;
    private Button deleteButton;
    private Button deleteAllButton;
    protected Button changeNameButton;
    protected Button checkForUpdatesButton;
    protected HorizontalLayout buttonTopBar;
    
    public SavedView(DbClient<E> dbService, CustomGrid<E> grid, DetailBox<E> detailBox) {
        this.dbService = dbService;
        this.grid = grid;
        this.detailBox = detailBox;
        this.detailBox.setVisible(false);
        
        setSizeFull();
        
        Div middleContent = new Div(this.grid, this.detailBox);
        middleContent.setSizeFull();
        middleContent.setClassName("middle-content");

        configureTopBar();
        add(buttonTopBar, middleContent);

        this.grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        this.grid.asSingleSelect().addValueChangeListener(event -> showDetailsAndEnableButtons(event.getValue()));
    }
    

    private void configureTopBar() {
        refreshButton = new Button("Odśwież", new Icon(VaadinIcon.REFRESH));
        refreshButton.addClickListener(event -> updateActsFromDB());

        hideDetailsButton = new Button("Ukryj szczegóły", new Icon(VaadinIcon.ANGLE_DOUBLE_RIGHT));
        hideDetailsButton.addClickListener(event -> {
            detailBox.setVisible(false);
            grid.getGridContent().refreshAll();
            hideDetailsButton.setEnabled(false);
        });
        hideDetailsButton.setEnabled(false);

        deleteButton = new Button("Usuń zaznaczony", new Icon(VaadinIcon.TRASH));
        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        deleteButton.setEnabled(false);
        deleteButton.addClickListener(event -> {
            disableButtonsAndBox();
            dbService.deleteById(selectedElement.getId());
            updateActsFromDB();
        });

        deleteAllButton = new Button("Usuń wszystkie", new Icon(VaadinIcon.CLOSE_CIRCLE_O));
        deleteAllButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        deleteAllButton.addClickListener(event -> {
            disableButtonsAndBox();
            dbService.deleteAll();
            updateActsFromDB();
        });
                
        changeNameButton = new Button("Edytuj nazwę", new Icon(VaadinIcon.EDIT));
        changeNameButton.setVisible(false);

        checkForUpdatesButton = new Button("Aktualizuj wszystkie akty", new Icon(VaadinIcon.TIME_FORWARD));
        checkForUpdatesButton.setVisible(false);
        
        buttonTopBar = new HorizontalLayout(refreshButton, hideDetailsButton, deleteButton, deleteAllButton, changeNameButton, checkForUpdatesButton);
    }

    private void showDetailsAndEnableButtons(E element) {
        selectedElement = element;
        if (element == null) {
            disableButtonsAndBox();
        } else {
            detailBox.setDetailsAndUpdateBox(element);
            enableButtonsAndBox();
        }
        grid.getGridContent().refreshAll();
    }

    private void disableButtonsAndBox() {
        detailBox.setVisible(false);
        deleteButton.setEnabled(false);
        hideDetailsButton.setEnabled(false);
        changeNameButton.setEnabled(false);
    }

    private void enableButtonsAndBox() {
        detailBox.setVisible(true);
        deleteButton.setEnabled(true);
        hideDetailsButton.setEnabled(true);
        changeNameButton.setEnabled(true);
    }
    
    protected void updateActsFromDB() {
        grid.setGridContent(dbService.getAll());
    }
}
