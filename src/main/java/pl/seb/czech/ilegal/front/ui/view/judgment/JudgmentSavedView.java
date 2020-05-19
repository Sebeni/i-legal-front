package pl.seb.czech.ilegal.front.ui.view.judgment;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextAreaVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.seb.czech.ilegal.front.client.judgment.SaosClient;
import pl.seb.czech.ilegal.front.domain.judgement.JudgmentSynopsis;
import pl.seb.czech.ilegal.front.stub.judgement.JudgmentDBService;
import pl.seb.czech.ilegal.front.ui.components.judgement.JudgmentDetailBox;
import pl.seb.czech.ilegal.front.ui.components.judgement.JudgmentEditDialog;
import pl.seb.czech.ilegal.front.ui.components.judgement.JudgmentGrid;
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
            new JudgmentEditDialog(dbService, selectedElement, grid);
        });
        
        
        
    }

  
}
