package pl.seb.czech.ilegal.front.ui.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.seb.czech.ilegal.front.client.act.IsapClient;
import pl.seb.czech.ilegal.front.domain.act.Act;
import pl.seb.czech.ilegal.front.stub.act.ActDBService;
import pl.seb.czech.ilegal.front.ui.components.act.ActDetailBox;
import pl.seb.czech.ilegal.front.ui.components.act.ActGrid;
import pl.seb.czech.ilegal.front.ui.layout.MainLayout;

@PageTitle("I-Legal | Zapisane akty")
@Route(value = "acts-saved", layout = MainLayout.class)
public class ActSavedView extends VerticalLayout {
    private ActDBService actService;
    private ActGrid actGrid;
    private ActDetailBox actDetailBox;
    private Act selectedAct;
    
    private IsapClient isapClient;
    private Button deleteButton;
    private Button hideDetailsButton;

    @Autowired
    public ActSavedView(ActDBService actService, IsapClient isapClient) {
        this.actService = actService;
        this.isapClient = isapClient;
        this.actGrid = new ActGrid(actService.getAll());
        
        this.actGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        
        this.actDetailBox = new ActDetailBox(isapClient); 

        setClassName("saved-acts");
        setSizeFull();
        
        Div middleContent = new Div(this.actGrid, this.actDetailBox);
        this.actDetailBox.setVisible(false);
        middleContent.setSizeFull();
        middleContent.setClassName("middle-content");
        
        add(getButtonTopBar(), middleContent);

        this.actGrid.asSingleSelect().addValueChangeListener(event -> showDetailsAndEnableButtons(event.getValue()));
    }

    private HorizontalLayout getButtonTopBar() {
        Button refreshButton = new Button("Odśwież", new Icon(VaadinIcon.REFRESH));
        refreshButton.addClickListener(event -> refreshActs());

        hideDetailsButton = new Button("Ukryj szczegóły", new Icon(VaadinIcon.ANGLE_DOUBLE_RIGHT));
        hideDetailsButton.addClickListener(event -> {
            actDetailBox.setVisible(false);
            actGrid.getGridContent().refreshAll();
            hideDetailsButton.setEnabled(false);
        });
        hideDetailsButton.setEnabled(false);
        
        deleteButton = new Button("Usuń zaznaczony", new Icon(VaadinIcon.TRASH));
        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        deleteButton.setEnabled(false);
        deleteButton.addClickListener(event -> {
            actDetailBox.setVisible(false);
            actService.deleteById(selectedAct.getId());
            refreshActs();
            deleteButton.setEnabled(false);
        });
        
        return new HorizontalLayout(refreshButton, hideDetailsButton, deleteButton);
    }


    private void refreshActs() {
        actGrid.setGridContent(actService.getAll());
    }

    private void showDetailsAndEnableButtons(Act act) {
        selectedAct = act;
        if (act == null) {
            actDetailBox.setVisible(false);
            deleteButton.setEnabled(false);
            hideDetailsButton.setEnabled(false);
        } else {
            actDetailBox.setCurrentActAndUpdateBox(act);
            actDetailBox.setVisible(true);
            deleteButton.setEnabled(true);
            hideDetailsButton.setEnabled(true);
        }
        actGrid.getGridContent().refreshAll();
    }


}
