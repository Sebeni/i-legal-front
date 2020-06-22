package pl.seb.czech.ilegal.front.ui.view.act;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.seb.czech.ilegal.front.backend.clients.ChangeViewClient;
import pl.seb.czech.ilegal.front.backend.clients.act.ActDifferenceClient;
import pl.seb.czech.ilegal.front.backend.clients.act.ActIsapClient;
import pl.seb.czech.ilegal.front.domain.ChangeViewLog;
import pl.seb.czech.ilegal.front.domain.act.Act;
import pl.seb.czech.ilegal.front.backend.clients.act.ActDbClient;
import pl.seb.czech.ilegal.front.domain.act.ActDifference;
import pl.seb.czech.ilegal.front.domain.act.ScheduledMessage;
import pl.seb.czech.ilegal.front.ui.components.act.ActDetailBox;
import pl.seb.czech.ilegal.front.ui.components.act.ActGrid;
import pl.seb.czech.ilegal.front.ui.components.act.ActUpdateBox;
import pl.seb.czech.ilegal.front.ui.layout.MainLayout;
import pl.seb.czech.ilegal.front.ui.view.SavedView;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.stream.Collectors;

@PageTitle("I-Legal | Zapisane akty")
@Route(value = "acts/saved", layout = MainLayout.class)
public class ActSavedView extends SavedView<Act> {
    private ActDifferenceClient actDifferenceClient;
    
    @Autowired
    public ActSavedView(ActDbClient actService, ActIsapClient actIsapClient, ActDifferenceClient actDifferenceClient, 
                        ChangeViewClient changeViewClient) {
        super(actService, new ActGrid(actService.getAll()), new ActDetailBox(actIsapClient));
        changeViewClient.save(new ChangeViewLog(this.getClass().getSimpleName()));
        this.actDifferenceClient = actDifferenceClient;
        configureCheckForUpdatesButton();

        add(new Text(getUpdateText()));
    }

    private void configureCheckForUpdatesButton() {
        checkForUpdatesButton.setVisible(true);
        checkForUpdatesButton.addClickListener(event -> checkForUpdates());
    }
    
    private void checkForUpdates() {
        new ActUpdateBox(actDifferenceClient.updateActs());
        updateActsFromDB();
    }
    
    private String getUpdateText() {
        ScheduledMessage scheduledMessage = actDifferenceClient.getScheduledMessage();
        List<String> updated = scheduledMessage.getUpdatedActs().stream()
                .filter(ActDifference::isDifferentAfter)
                .map(ActDifference::getTitle)
                .collect(Collectors.toList());

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        
        String updatedText = String.format("Ostatnia automatyczna aktualizacja: %s. Liczba sprawdzonych aktów prawnych: %d.", 
                scheduledMessage.getCreatedOn().format(format), scheduledMessage.getUpdatedActs().size());
        
        if(updated.size() > 0) {
            updatedText = updatedText + " Zaktualizowano: " + updated;
        }
        return updatedText;
    }
}
