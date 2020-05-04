package pl.seb.czech.ilegal.front.ui.view.savedActs;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.seb.czech.ilegal.front.domain.Act;
import pl.seb.czech.ilegal.front.stub.ActService;
import pl.seb.czech.ilegal.front.ui.layout.MainLayout;

@PageTitle("I-Legal | Moje ustawy")
@Route(value = "savedActs", layout = MainLayout.class)
public class SavedActsView extends VerticalLayout {
    private ActService actService;
    private GridSavedAct savedActGrid;
    private ActDetailsBox actDetailsBox;
    private Button refreshButton = new Button("Odśwież");

    public SavedActsView(ActService actService) {
        this.actService = actService;
        savedActGrid = new GridSavedAct(new ListDataProvider<>(actService.getAllActs()));
        actDetailsBox = new ActDetailsBox(new Act());
        
        
        setClassName("saved-acts");
        setSizeFull();

        refreshButton.addClickListener(click -> refreshActs());

        Div middleContent = new Div(savedActGrid, actDetailsBox);
        middleContent.setSizeFull();
        middleContent.setClassName("middle-content");

        add(refreshButton, middleContent);

        savedActGrid.asSingleSelect().addValueChangeListener(event -> {
                showDetails(event.getValue());
        });
    }


    private void refreshActs() {
        savedActGrid.setGridContent(actService.getAllActs());
    }

    private void showDetails(Act act) {
        if (act == null) {
            actDetailsBox.setVisible(false);
        } else {
            actDetailsBox.setCurrentAct(act);
            actDetailsBox.setVisible(true);
        }
        refreshActs();
    }


}
