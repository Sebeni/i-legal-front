package pl.seb.czech.ilegal.front.ui.components.act;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.RouterLink;
import pl.seb.czech.ilegal.front.client.act.ActTextType;
import pl.seb.czech.ilegal.front.client.act.IsapClient;
import pl.seb.czech.ilegal.front.domain.act.Act;
import pl.seb.czech.ilegal.front.ui.components.DetailBox;
import pl.seb.czech.ilegal.front.ui.view.SearchView;
import pl.seb.czech.ilegal.front.ui.view.judgment.JudgmentSearchView;

import java.net.URI;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActDetailBox extends DetailBox<Act> {
    private Act currentAct = new Act();
    private IsapClient isapClient;

    private Accordion detailsAccordion;
    private H3 actTitle;
    private AccordionPanel statusPanel;
    private AccordionPanel promulgationPanel;

    private Paragraph lastChange;
    private HorizontalLayout showTextBar;
    private Anchor originalTextShowAnchor;
    private Anchor unifiedTextShowAnchor;
    private Button originalTextButton = new Button("Ogłoszony");
    private Button unifiedTextButton = new Button("Ujednolicony");
    private RouterLink searchJudgmentLink;

    public ActDetailBox(IsapClient isapClient) {
        this.isapClient = isapClient;
        setClassName("act-details");
        configure();
    }

    private void configure() {
        setSizeFull();

        actTitle = new H3();
        lastChange = new Paragraph();

        configureShowTextBar();
        configureAccordion();

        configureSearchJudgmentLink();
        
        
        
        add(actTitle, lastChange, showTextBar, new H3("Szczegóły:"), detailsAccordion, searchJudgmentLink);
    }


    private void configureShowTextBar() {
        Paragraph download = new Paragraph("Wyświetl tekst:");

        originalTextShowAnchor = new Anchor();
        originalTextShowAnchor.add(originalTextButton);
        originalTextShowAnchor.setMaxHeight(originalTextButton.getHeight());

        unifiedTextShowAnchor = new Anchor();
        unifiedTextShowAnchor.add(unifiedTextButton);
        unifiedTextShowAnchor.setMaxHeight(unifiedTextButton.getHeight());

        showTextBar = new HorizontalLayout(download, originalTextShowAnchor, unifiedTextShowAnchor);
        showTextBar.setMaxHeight(unifiedTextButton.getHeight());
    }

    private void configureAccordion() {
        detailsAccordion = new Accordion();
        detailsAccordion.setSizeFull();
        
        statusPanel = detailsAccordion.add("Status:", new Span("brak"));
        statusPanel.addThemeVariants(DetailsVariant.FILLED);

        promulgationPanel = detailsAccordion.add("Data ogłoszenia:", new Span("brak"));
        promulgationPanel.addThemeVariants(DetailsVariant.FILLED);
        
    }

    private void configureSearchJudgmentLink() {
        searchJudgmentLink = new RouterLink();
        Button searchJudgmentButton = new Button("Szukaj orzeczeń", new Icon(VaadinIcon.GAVEL));
        searchJudgmentLink = new RouterLink("", JudgmentSearchView.class);
        searchJudgmentLink.add(searchJudgmentButton);
    }

    @Override
    public void setDetailsAndUpdateBox(Act currentAct) {
        this.currentAct = currentAct;
        actTitle.setText(currentAct.getTitle());
        lastChange.setText("Ostatnia zmiana: " + currentAct.getChangeDate().toString());

        updateShowTextButtons();
        updateStatusPanel();
        updateSearchJudgmentLink();
    }

    private void updateShowTextButtons() {
        originalTextShowAnchor.setHref(isapClient.generateDownloadActURI(currentAct, ActTextType.PUBLISHED).toString());
        originalTextShowAnchor.setTarget("_blank");

        URI unifiedTextUri = isapClient.generateDownloadActURI(currentAct, ActTextType.UNIFIED);
        if (isapClient.validateTxtExists(unifiedTextUri)) {
            unifiedTextShowAnchor.setHref(unifiedTextUri.toString());
            unifiedTextShowAnchor.setTarget("_blank");
            unifiedTextButton.setEnabled(true);
        } else {
            unifiedTextShowAnchor.removeHref();
            unifiedTextButton.setEnabled(false);
        }
    }

    private void updateStatusPanel() {
        statusPanel.setContent(new Span(currentAct.getStatus()));
        
        LocalDate promulgationDate = currentAct.getPromulgation();
        enableOptionalPanels(promulgationPanel, promulgationDate);
    }

    private void updateSearchJudgmentLink() {
        QueryParameters qp = getYearPosQueryParams(currentAct.getYear(), currentAct.getPosition());
        searchJudgmentLink.setQueryParameters(qp);
    }
    
    private void enableOptionalPanels(AccordionPanel optionalPanel, LocalDate correspondingDate) {
        if (correspondingDate != null) {
            optionalPanel.setContent(new Span(correspondingDate.toString()));
            optionalPanel.setVisible(true);
        } else {
            optionalPanel.setVisible(false);
        }
    }
}
