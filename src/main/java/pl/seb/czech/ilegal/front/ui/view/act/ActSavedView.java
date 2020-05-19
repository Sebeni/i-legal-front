package pl.seb.czech.ilegal.front.ui.view.act;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.seb.czech.ilegal.front.client.act.IsapClient;
import pl.seb.czech.ilegal.front.domain.act.Act;
import pl.seb.czech.ilegal.front.domain.act.ActDifference;
import pl.seb.czech.ilegal.front.domain.act.ActSearchQuery;
import pl.seb.czech.ilegal.front.domain.act.ActSearchResult;
import pl.seb.czech.ilegal.front.stub.act.ActDBService;
import pl.seb.czech.ilegal.front.ui.components.act.ActDetailBox;
import pl.seb.czech.ilegal.front.ui.components.act.ActGrid;
import pl.seb.czech.ilegal.front.ui.components.act.ActUpdateBox;
import pl.seb.czech.ilegal.front.ui.layout.MainLayout;
import pl.seb.czech.ilegal.front.ui.view.SavedView;

import java.util.ArrayList;
import java.util.List;

@PageTitle("I-Legal | Zapisane akty")
@Route(value = "acts/saved", layout = MainLayout.class)
public class ActSavedView extends SavedView<Act, String> {
    private final static Logger LOGGER = LoggerFactory.getLogger(ActSavedView.class);
    private ActDBService dbService;
    private IsapClient isapClient;
    
    @Autowired
    public ActSavedView(ActDBService actService, IsapClient isapClient) {
        super(actService, new ActGrid(actService.getAll()), new ActDetailBox(isapClient));
        this.dbService = actService;
        this.isapClient = isapClient;
        configureCheckForUpdatesButton();
    }

    private void configureCheckForUpdatesButton() {
        checkForUpdatesButton.setVisible(true);
        checkForUpdatesButton.addClickListener(event -> checkForUpdates());
    }
    
    private void checkForUpdates() {
        List<Act> actsInDB = dbService.getAll();
        List<ActDifference> actsDifference = new ArrayList<>();
        
        actsInDB.forEach(actFromDbBefore -> {
            ActSearchQuery query = createQueryFormAct(actFromDbBefore);
            
            ActSearchResult result = isapClient.performSearchQuery(query);
            if(result.getCount() == 1) {
                Act actFromIsapAfter = result.getFoundActs()[0];
                if(!actFromDbBefore.equals(actFromIsapAfter)) {
                    actsDifference.add(new ActDifference(actFromDbBefore, actFromIsapAfter));
                    actFromDbBefore.setChangeDate(actFromIsapAfter.getChangeDate());
                    actFromDbBefore.setStatus(actFromIsapAfter.getStatus());
                    dbService.saveElement(actFromDbBefore);
                } 
            } else {
                String warnMsg = String.format("Found %d results in isap website (instead of 1)! Query params: %s", 
                        result.getCount(), 
                        query.getQueryParams());
                LOGGER.warn(warnMsg);
            }
        });
        
        new ActUpdateBox(actsDifference);
        updateActsFromDB();
    }
    
    private ActSearchQuery createQueryFormAct(Act actFromDb){
        ActSearchQuery query = new ActSearchQuery();
        query.setTitle(actFromDb.getTitle());
        query.setYear(actFromDb.getYear());
        query.setPosition(actFromDb.getPosition());
        return query;
    }
}
