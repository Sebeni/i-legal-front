package pl.seb.czech.ilegal.front.ui.view;

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
import pl.seb.czech.ilegal.front.backend.clients.ChangeViewClient;
import pl.seb.czech.ilegal.front.backend.clients.act.ActHistoryClient;
import pl.seb.czech.ilegal.front.backend.clients.judgment.JudgmentHistoryClient;
import pl.seb.czech.ilegal.front.domain.ChangeViewLog;
import pl.seb.czech.ilegal.front.ui.components.log.DeleteLogGrid;
import pl.seb.czech.ilegal.front.ui.components.log.SearchLogGrid;
import pl.seb.czech.ilegal.front.ui.layout.MainLayout;

@PageTitle("I-Legal | Historia")
@Route(value = "history", layout = MainLayout.class)
public class HistoryView extends VerticalLayout {
    private ActHistoryClient actHistoryClient;
    private JudgmentHistoryClient judgmentHistoryClient;

    private final String searchActs = "aktów";
    private final String searchJudgments = "orzeczeń";
    private VerticalLayout content;
    private Button deleteHistoryButton;

    private SearchLogGrid actSearchGrid;
    private SearchLogGrid judgmentSearchGrid;

    @Autowired
    public HistoryView(ActHistoryClient actHistoryClient, JudgmentHistoryClient judgmentHistoryClient, 
                       ChangeViewClient changeViewClient) {
        changeViewClient.save(new ChangeViewLog(this.getClass().getSimpleName()));
        this.actHistoryClient = actHistoryClient;
        this.judgmentHistoryClient = judgmentHistoryClient;
        setSizeFull();
        content = new VerticalLayout();
        content.setSizeFull();
        add(getTopBar(), content);

        actSearchGrid = new SearchLogGrid(actHistoryClient.getSearchLogs());
        judgmentSearchGrid = new SearchLogGrid(judgmentHistoryClient.getSearchLogs());
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

        ComboBox<String> historyCombo = new ComboBox<>("Pokaż historię wyszukiwań");
        historyCombo.setItems(searchActs, searchJudgments);
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
}
