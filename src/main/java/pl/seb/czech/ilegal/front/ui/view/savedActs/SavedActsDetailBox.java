package pl.seb.czech.ilegal.front.ui.view.savedActs;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.seb.czech.ilegal.front.client.ActTextType;
import pl.seb.czech.ilegal.front.client.IsapClient;
import pl.seb.czech.ilegal.front.domain.Act;
import pl.seb.czech.ilegal.front.stub.ActService;

import java.net.URI;
import java.time.LocalDate;

public class SavedActsDetailBox extends VerticalLayout {
    private Act currentAct = new Act();
    
    private ActService actService;
    private IsapClient isapClient;
    
    private Accordion detailsAccordion;
    private H2 actTitle;
    private AccordionPanel statusPanel;
    private AccordionPanel promulgationPanel;
    private AccordionPanel entryIntoForcePanel;
    private Paragraph lastChange;
    private HorizontalLayout showTextBar;
    private Button deleteButton;
    private Anchor originalTextShowAnchor;
    private Anchor unifiedTextShowAnchor;
    private Button originalTextButton = new Button("Ogłoszony");
    private Button unifiedTextButton = new Button("Ujednolicony");
   

    public SavedActsDetailBox(ActService actService, IsapClient isapClient) {
        this.actService = actService;
        this.isapClient = isapClient;
        setClassName("act-details");
        configure();
    }

    private void configure() {
        setSizeFull();
        
        actTitle = new H2();

        configureDeleteButton();
        
        lastChange = new Paragraph();
        
        configureShowTextBar();
        configureAccordion();
        
        add(actTitle, deleteButton, lastChange, showTextBar, new H3("Szczegóły"), detailsAccordion);
    }

    private void configureDeleteButton() {
        deleteButton = new Button("Usuń akt", new Icon(VaadinIcon.TRASH));
        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        deleteButton.addClickListener(event -> {
            actService.deleteActById(currentAct.getId());
//            TODO REFRESH
        });
        
        
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
//        showTextBar.setSizeFull();
        showTextBar.setMaxHeight(unifiedTextButton.getHeight());
    }

    private void configureAccordion() {
        detailsAccordion = new Accordion();
        detailsAccordion.setSizeFull();
        statusPanel = detailsAccordion.add("Status", new Span("stat"));
        promulgationPanel = detailsAccordion.add("Data ogłoszenia", new Span("promul"));
        entryIntoForcePanel = detailsAccordion.add("Data wejścia w życie", new Span("entry"));
    }

    public void setCurrentActAndUpdateBox(Act currentAct) {
        this.currentAct = currentAct;
        actTitle.setText(currentAct.getTitle());
        lastChange.setText("Ostatnia zmiana: " + currentAct.getChangeDate().toString());

        updateShowTextButtons();
        updateStatusPanel();
    }

    private void updateShowTextButtons() {
        originalTextShowAnchor.setHref(isapClient.generateDownloadActURI(currentAct, ActTextType.PUBLISHED).toString());
        originalTextShowAnchor.setTarget("_blank");
        
        URI unifiedTextUri = isapClient.generateDownloadActURI(currentAct, ActTextType.UNIFIED);
        if(isapClient.checkIfFileExists(unifiedTextUri)){
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
        promulgationPanel.setContent(new Span(currentAct.getPromulgation().toString()));

        LocalDate entryIntoForce = currentAct.getEntryIntoForce();
        if(entryIntoForce != null){
            entryIntoForcePanel.setContent(new Span(entryIntoForce.toString()));
            entryIntoForcePanel.setVisible(true);
        } else {
            entryIntoForcePanel.setVisible(false);
        }
    }
}
