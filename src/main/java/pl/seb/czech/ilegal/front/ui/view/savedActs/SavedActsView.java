package pl.seb.czech.ilegal.front.ui.view.savedActs;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
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
    private SavedActsGrid savedActsGrid;
    private SavedActsDetailBox savedActsDetailBox;
    private IsapClient isapClient;
    
    private Button refreshButton = new Button("Odśwież");
    
    @Autowired
    public SavedActsView(ActService actService, IsapClient isapClient) {
        this.actService = actService;
        this.isapClient = isapClient;
        this.savedActsGrid = new SavedActsGrid(new ListDataProvider<>(actService.getAllActs()));
        this.savedActsDetailBox = new SavedActsDetailBox(actService, isapClient); 

        setClassName("saved-acts");
        setSizeFull();

        refreshButton.addClickListener(click -> refreshActs());

        Div middleContent = new Div(this.savedActsGrid, this.savedActsDetailBox);
        this.savedActsDetailBox.setVisible(false);
        middleContent.setSizeFull();
        middleContent.setClassName("middle-content");

        add(refreshButton, middleContent);

        this.savedActsGrid.asSingleSelect().addValueChangeListener(event -> {
                showDetails(event.getValue());
        });
    }


    private void refreshActs() {
        savedActsGrid.setGridContent(actService.getAllActs());
    }

    private void showDetails(Act act) {
        if (act == null) {
            savedActsDetailBox.setVisible(false);
        } else {
            savedActsDetailBox.setCurrentActAndUpdateBox(act);
            savedActsDetailBox.setVisible(true);
        }
        refreshActs();
    }


}
