package pl.seb.czech.ilegal.front.ui.view.act;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.seb.czech.ilegal.front.backend.clients.act.ActDifferenceClient;
import pl.seb.czech.ilegal.front.backend.clients.act.ActIsapClient;
import pl.seb.czech.ilegal.front.domain.act.Act;
import pl.seb.czech.ilegal.front.backend.clients.act.ActDbClient;
import pl.seb.czech.ilegal.front.ui.components.act.ActDetailBox;
import pl.seb.czech.ilegal.front.ui.components.act.ActGrid;
import pl.seb.czech.ilegal.front.ui.components.act.ActUpdateBox;
import pl.seb.czech.ilegal.front.ui.layout.MainLayout;
import pl.seb.czech.ilegal.front.ui.view.SavedView;

@PageTitle("I-Legal | Zapisane akty")
@Route(value = "acts/saved", layout = MainLayout.class)
public class ActSavedView extends SavedView<Act> {
    private ActDifferenceClient actDifferenceClient;
    
    @Autowired
    public ActSavedView(ActDbClient actService, ActIsapClient actIsapClient, ActDifferenceClient actDifferenceClient) {
        super(actService, new ActGrid(actService.getAll()), new ActDetailBox(actIsapClient));
        this.actDifferenceClient = actDifferenceClient;
        configureCheckForUpdatesButton();
    }

    private void configureCheckForUpdatesButton() {
        checkForUpdatesButton.setVisible(true);
        checkForUpdatesButton.addClickListener(event -> checkForUpdates());
    }
    
    private void checkForUpdates() {
        new ActUpdateBox(actDifferenceClient.updateActs());
        updateActsFromDB();
    }
}
