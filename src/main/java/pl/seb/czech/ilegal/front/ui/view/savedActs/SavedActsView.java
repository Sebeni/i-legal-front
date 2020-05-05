package pl.seb.czech.ilegal.front.ui.view.savedActs;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
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
import pl.seb.czech.ilegal.front.stub.ActService;
import pl.seb.czech.ilegal.front.ui.layout.MainLayout;

@PageTitle("I-Legal | Moje ustawy")
@Route(value = "savedActs", layout = MainLayout.class)
public class SavedActsView extends VerticalLayout {
    private ActService actService;
    private ActsGrid actsGrid;
    private ActsDetailBox actsDetailBox;
    private Act selectedAct;
    
    private IsapClient isapClient;
    private Button deleteButton;

    @Autowired
    public SavedActsView(ActService actService, IsapClient isapClient) {
        this.actService = actService;
        this.isapClient = isapClient;
        this.actsGrid = new ActsGrid(new ListDataProvider<>(actService.getAllActs()));
        this.actsDetailBox = new ActsDetailBox(isapClient); 

        setClassName("saved-acts");
        setSizeFull();
        
        
        Div middleContent = new Div(this.actsGrid, this.actsDetailBox);
        this.actsDetailBox.setVisible(false);
        middleContent.setSizeFull();
        middleContent.setClassName("middle-content");

        
        add(getButtonTopBar(), middleContent);

        this.actsGrid.asSingleSelect().addValueChangeListener(event -> {
                showDetailsAndEnableDelete(event.getValue());
        });
        
    }

    private HorizontalLayout getButtonTopBar() {
        Button refreshButton = new Button("Odśwież", new Icon(VaadinIcon.REFRESH));
        refreshButton.addClickListener(event -> refreshActs());

        deleteButton = new Button("Usuń zaznaczony", new Icon(VaadinIcon.TRASH));
        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        deleteButton.setEnabled(false);
        deleteButton.addClickListener(event -> {
            actsDetailBox.setVisible(false);
            actService.deleteActById(selectedAct.getId());
            refreshActs();
            deleteButton.setEnabled(false);
        });
        
        return new HorizontalLayout(refreshButton, deleteButton);
    }


    private void refreshActs() {
        actsGrid.setGridContent(actService.getAllActs());
    }

    private void showDetailsAndEnableDelete(Act act) {
        selectedAct = act;
        if (act == null) {
            actsDetailBox.setVisible(false);
            deleteButton.setEnabled(false);
        } else {
            actsDetailBox.setCurrentActAndUpdateBox(act);
            actsDetailBox.setVisible(true);
            deleteButton.setEnabled(true);
        }
        actsGrid.getGridContent().refreshAll();
    }


}
