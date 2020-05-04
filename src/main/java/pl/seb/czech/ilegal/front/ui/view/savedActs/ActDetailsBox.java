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
import pl.seb.czech.ilegal.front.domain.Act;

import java.time.LocalDate;

public class ActDetailsBox extends VerticalLayout {
    private Act currentAct;
    private Accordion detailsAccordion;
    private H2 actTitle;
    private AccordionPanel statusPanel;
    private AccordionPanel promulgationPanel;
    private AccordionPanel entryIntoForcePanel;
    private Paragraph lastChange;

    public ActDetailsBox(Act currentAct) {
        this.currentAct = currentAct;
        setClassName("act-details");
        configure();
    }

    private void configure() {
        setSizeFull();

        Icon hideIcon = new Icon(VaadinIcon.ANGLE_DOUBLE_RIGHT);
        Button hideButton = new Button("Schowaj", hideIcon, event -> {
            setVisible(false);
        });
        hideButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
        
        actTitle = new H2();
        
        Button deleteButton = new Button("Usuń akt", new Icon(VaadinIcon.TRASH));
        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        
        lastChange = new Paragraph();

        Paragraph download = new Paragraph("Pobierz tekst:");
        Button originalTextDownload = new Button("Ogłoszony");
        Button unifedTextDownload = new Button("Ujednolicony");
        HorizontalLayout downloadButtonBar = new HorizontalLayout(download, originalTextDownload, unifedTextDownload);
        downloadButtonBar.setSizeFull();
        
        H3 latestActDetails = new H3("Szczegóły");
        configureAccordion();
        add(hideButton, actTitle, deleteButton, lastChange, downloadButtonBar, latestActDetails, detailsAccordion);


    }

    private void configureAccordion() {
        detailsAccordion = new Accordion();
        detailsAccordion.setSizeFull();
        statusPanel = detailsAccordion.add("Status", new Span("stat"));
        promulgationPanel = detailsAccordion.add("Data ogłoszenia", new Span("promul"));
        entryIntoForcePanel = detailsAccordion.add("Data wejścia w życie", new Span("entry"));
        
        
    }

    public void setCurrentAct(Act currentAct) {
//        TODO add check if null
        this.currentAct = currentAct;
        lastChange.setText("Ostatnia zmiana: " + currentAct.getChangeDate().toString());
        actTitle.setText(currentAct.getTitle());
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
