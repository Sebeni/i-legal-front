package pl.seb.czech.ilegal.front.ui.view.judgment;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.seb.czech.ilegal.front.client.judgment.SaosClient;
import pl.seb.czech.ilegal.front.domain.judgment.JudgmentSynopsis;
import pl.seb.czech.ilegal.front.stub.judgment.JudgmentDBService;
import pl.seb.czech.ilegal.front.ui.components.judgment.JudgmentDetailBox;
import pl.seb.czech.ilegal.front.ui.components.judgment.JudgmentEditDialog;
import pl.seb.czech.ilegal.front.ui.components.judgment.JudgmentGrid;
import pl.seb.czech.ilegal.front.ui.layout.MainLayout;
import pl.seb.czech.ilegal.front.ui.view.SavedView;


@PageTitle("I-Legal | Zapisane orzeczenia")
@Route(value = "judgment/saved", layout = MainLayout.class)
public class JudgmentSavedView extends SavedView<JudgmentSynopsis, Long> {
    
    private JudgmentDBService dbService;

    @Autowired
    public JudgmentSavedView(JudgmentDBService dbService, SaosClient saosClient) {
        super(dbService, new JudgmentGrid(dbService.getAll()), new JudgmentDetailBox(saosClient));
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
