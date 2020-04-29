package pl.seb.czech.ilegal.front.ui.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import pl.seb.czech.ilegal.front.domain.Act;
import pl.seb.czech.ilegal.front.stub.ActService;
import pl.seb.czech.ilegal.front.ui.layout.MainLayout;

@Route(value = "savedActs", layout = MainLayout.class)
public class SavedActsView extends VerticalLayout {
    private ActService actService;
    
    private Grid<Act> savedActGrid = new Grid<>(Act.class);
    private TextField filterBox = new TextField("Filtruj", "Filtruj po tytule");
    private Button refreshButton = new Button("Odśwież");

    public SavedActsView(ActService actService) {
        this.actService = actService;
        setSizeFull();
        add(getTopToolBelt());
        configureGrid();
        add(savedActGrid);
        
        
    }

    private HorizontalLayout getTopToolBelt() {
        filterBox.setClearButtonVisible(true);
        filterBox.setValueChangeMode(ValueChangeMode.LAZY);
        filterBox.addValueChangeListener(e -> savedActGrid.setItems(actService.findByTitle(filterBox.getValue())));
        
        refreshButton.addClickListener(click -> savedActGrid.setItems(actService.getAllActs()));
        
        HorizontalLayout topToolBelt = new HorizontalLayout(filterBox, refreshButton);
        topToolBelt.setDefaultVerticalComponentAlignment(Alignment.END);
        return topToolBelt;
    }

    private void configureGrid() {
        savedActGrid.setSizeFull();
        savedActGrid.setColumns("year", "position", "title", "status", "changeDate");
        savedActGrid.getColumnByKey("year").setHeader("Rok").setFlexGrow(1);
        savedActGrid.getColumnByKey("position").setHeader("Poz.").setFlexGrow(1);
        savedActGrid.getColumnByKey("title").setHeader("Tytuł").setFlexGrow(10);
        savedActGrid.getColumnByKey("status").setFlexGrow(3);
        savedActGrid.getColumnByKey("changeDate").setHeader("Ost. aktualizacja").setFlexGrow(2);
        savedActGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        savedActGrid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
        
        
    }
}
