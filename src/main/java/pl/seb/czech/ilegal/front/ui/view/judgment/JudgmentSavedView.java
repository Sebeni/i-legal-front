package pl.seb.czech.ilegal.front.ui.view.judgment;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.seb.czech.ilegal.front.backend.clients.ChangeViewClient;
import pl.seb.czech.ilegal.front.backend.clients.judgment.JudgmentSaosClient;
import pl.seb.czech.ilegal.front.domain.ChangeViewLog;
import pl.seb.czech.ilegal.front.domain.judgment.JudgmentSynopsis;
import pl.seb.czech.ilegal.front.backend.clients.judgment.JudgmentDbClient;
import pl.seb.czech.ilegal.front.ui.components.judgment.JudgmentDetailBox;
import pl.seb.czech.ilegal.front.ui.components.judgment.JudgmentEditDialog;
import pl.seb.czech.ilegal.front.ui.components.judgment.JudgmentGrid;
import pl.seb.czech.ilegal.front.ui.layout.MainLayout;
import pl.seb.czech.ilegal.front.ui.view.SavedView;


@PageTitle("I-Legal | Zapisane orzeczenia")
@Route(value = "judgment/saved", layout = MainLayout.class)
public class JudgmentSavedView extends SavedView<JudgmentSynopsis> {
    
    private JudgmentDbClient dbService;

    @Autowired
    public JudgmentSavedView(JudgmentDbClient dbService, JudgmentSaosClient judgmentSaosClient, ChangeViewClient changeViewClient) {
        super(dbService, new JudgmentGrid(dbService.getAll()), new JudgmentDetailBox(judgmentSaosClient));
        changeViewClient.save(new ChangeViewLog(this.getClass().getSimpleName()));
        this.dbService = dbService;
        configureChangeNameButton();
    }

    private void configureChangeNameButton() {
        changeNameButton.setVisible(true);
        changeNameButton.setEnabled(false);
        
        changeNameButton.addClickListener(event -> {
            Dialog dialog = new JudgmentEditDialog(dbService, selectedElement, grid);
            dialog.open();
        });
        
        
        
    }

  
}
