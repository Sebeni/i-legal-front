package pl.seb.czech.ilegal.front.ui.view;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.seb.czech.ilegal.front.backend.clients.act.ActHistoryClient;
import pl.seb.czech.ilegal.front.backend.clients.judgment.JudgmentHistoryClient;
import pl.seb.czech.ilegal.front.domain.log.DeleteLog;
import pl.seb.czech.ilegal.front.domain.log.SearchLog;
import pl.seb.czech.ilegal.front.ui.components.log.DeleteLogGrid;
import pl.seb.czech.ilegal.front.ui.components.log.SearchLogGrid;
import pl.seb.czech.ilegal.front.ui.layout.MainLayout;

import java.util.List;

@PageTitle("I-Legal | Historia")
@Route(value = "history", layout = MainLayout.class)
public class HistoryView extends VerticalLayout {
    private ActHistoryClient actHistoryClient;
    private JudgmentHistoryClient judgmentHistoryClient;

    private final String searchActs = "wyszukiwań aktów";
    private final String searchJudgments = "wyszukiwań orzeczeń";
    private final String deleteAct = "usunięć aktów";
    private final String deleteJudgments = "usunięć orzeczeń";
    private VerticalLayout content;
    private Button deleteHistoryButton;

    private SearchLogGrid actSearchGrid;
    private SearchLogGrid judgmentSearchGrid;
    private DeleteLogGrid actDeleteGrid;
    private DeleteLogGrid judgmentDeleteGrid;

    @Autowired
    public HistoryView(ActHistoryClient actHistoryClient, JudgmentHistoryClient judgmentHistoryClient) {
        this.actHistoryClient = actHistoryClient;
        this.judgmentHistoryClient = judgmentHistoryClient;
        setSizeFull();
        content = new VerticalLayout();
        content.setSizeFull();
        add(getTopBar(), content);

        actSearchGrid = new SearchLogGrid(actHistoryClient.getSearchLogs());
        judgmentSearchGrid = new SearchLogGrid(judgmentHistoryClient.getSearchLogs());
        
        actDeleteGrid = new DeleteLogGrid(actHistoryClient.getDeleteLogs());
        judgmentDeleteGrid = new DeleteLogGrid(judgmentHistoryClient.getDeleteLogs());
    }

    private HorizontalLayout getTopBar() {
        HorizontalLayout topBar = new HorizontalLayout();
        deleteHistoryButton = new Button("Wyczyść całą historię przeglądania", new Icon(VaadinIcon.TRASH));
        deleteHistoryButton.addClickListener(e -> {
            actHistoryClient.deleteSearchLogs();
            actSearchGrid.deleteGridContent();
        });
        deleteHistoryButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        deleteHistoryButton.setVisible(false);

        ComboBox<String> historyCombo = new ComboBox<>("Pokaż historię");
        historyCombo.setItems(searchActs, searchJudgments, deleteAct, deleteJudgments);
        historyCombo.addValueChangeListener(event -> {

            switch (event.getValue()) {
                case searchActs:
                    showSearchLogGrid(actSearchGrid);
                    break;
                case searchJudgments:
                    showSearchLogGrid(judgmentSearchGrid);
                    deleteHistoryButton.addClickListener(e -> {
                        judgmentHistoryClient.deleteSearchLogs();
                        judgmentSearchGrid.deleteGridContent();
                    });
                    break;
                case deleteAct:
                    showDeleteLogGrid(actDeleteGrid);
                    break;
                case deleteJudgments:
                    showDeleteLogGrid(judgmentDeleteGrid);
                    break;
            }
        });

        topBar.add(historyCombo, deleteHistoryButton);
        topBar.setDefaultVerticalComponentAlignment(Alignment.END);
        return topBar;
    }

    private void showSearchLogGrid(SearchLogGrid searchLogGrid) {
        deleteHistoryButton.setVisible(true);
        content.removeAll();
        content.add(searchLogGrid);
    }

    private void showDeleteLogGrid(DeleteLogGrid deleteLogGrid) {
        deleteHistoryButton.setVisible(false);
        content.removeAll();
        content.add(deleteLogGrid);
    }
}
