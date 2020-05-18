package pl.seb.czech.ilegal.front.ui.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.seb.czech.ilegal.front.domain.DummyEntity;
import pl.seb.czech.ilegal.front.stub.StubDBService;
import pl.seb.czech.ilegal.front.ui.components.CustomGrid;
import pl.seb.czech.ilegal.front.ui.components.DetailBox;

public abstract class SavedView<E extends DummyEntity<K>, K> extends VerticalLayout {
    private StubDBService<E, K> dbService;
    private CustomGrid<E> grid;
    private DetailBox<E> detailBox;
    private E selectedElement;
    
    private Button deleteButton;
    private Button hideDetailsButton;
    private Button refreshButton;

    public SavedView(StubDBService<E, K> dbService, CustomGrid<E> grid, DetailBox<E> detailBox) {
        this.dbService = dbService;
        this.grid = grid;
        this.detailBox = detailBox;
        this.detailBox.setVisible(false);
        
        setSizeFull();

        Div middleContent = new Div(this.grid, this.detailBox);
        middleContent.setSizeFull();
        middleContent.setClassName("middle-content");

        add(getButtonTopBar(), middleContent);
        
        this.grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        this.grid.asSingleSelect().addValueChangeListener(event -> showDetailsAndEnableButtons(event.getValue()));
    }

    private HorizontalLayout getButtonTopBar() {
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
            detailBox.setVisible(false);
            dbService.deleteById(selectedElement.getId());
            updateActsFromDB();
            deleteButton.setEnabled(false);
            hideDetailsButton.setEnabled(false);
        });

        return new HorizontalLayout(refreshButton, hideDetailsButton, deleteButton);
    }

    private void showDetailsAndEnableButtons(E element) {
        selectedElement = element;
        if (element == null) {
            detailBox.setVisible(false);
            deleteButton.setEnabled(false);
            hideDetailsButton.setEnabled(false);
        } else {
            detailBox.setDetailsAndUpdateBox(element);
            detailBox.setVisible(true);
            deleteButton.setEnabled(true);
            hideDetailsButton.setEnabled(true);
        }
        grid.getGridContent().refreshAll();
    }

    private void updateActsFromDB() {
        grid.setGridContent(dbService.getAll());
    }
}
