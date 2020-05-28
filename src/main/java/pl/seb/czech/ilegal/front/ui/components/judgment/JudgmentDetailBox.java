package pl.seb.czech.ilegal.front.ui.components.judgment;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.RouterLink;

import pl.seb.czech.ilegal.front.backend.clients.judgment.JudgmentSaosClient;
import pl.seb.czech.ilegal.front.domain.judgment.JudgmentDetails;
import pl.seb.czech.ilegal.front.domain.judgment.JudgmentSynopsis;
import pl.seb.czech.ilegal.front.domain.judgment.ReferencedRegulation;
import pl.seb.czech.ilegal.front.ui.components.DetailBox;

import pl.seb.czech.ilegal.front.ui.view.act.ActSearchView;
import pl.seb.czech.ilegal.front.ui.view.judgment.JudgmentSearchView;

import java.util.Set;

public class JudgmentDetailBox extends DetailBox<JudgmentSynopsis> {
    private JudgmentDetails judgmentDetails = new JudgmentDetails();
    private Accordion accordionDetails;
    private JudgmentTextDialog textDialog;
    private AccordionPanel refRegPanel;
    private AccordionPanel legalBasesPanel;
    private AccordionPanel keywordsPanel;
    private JudgmentSaosClient judgmentSaosClient;
    private Button showJudgmentText;


    public JudgmentDetailBox(JudgmentSaosClient judgmentSaosClient) {
        this.judgmentSaosClient = judgmentSaosClient;
        setClassName("judgment-details");
        accordionDetails = new Accordion();

        showJudgmentText = new Button("Pokaż uzasadnienie", new Icon(VaadinIcon.SCALE));
        textDialog = new JudgmentTextDialog();

        showJudgmentText.addClickListener(event -> {
            if (judgmentDetails != null) {
                textDialog.setJudgmentDetailsText(judgmentDetails);
                textDialog.open();
            }
        });
        
        refRegPanel = new AccordionPanel();
        refRegPanel.setSummaryText("Powołane ustawy");

        legalBasesPanel = new AccordionPanel();
        legalBasesPanel.setSummaryText("Powołane przepisy");

        keywordsPanel = new AccordionPanel();
        keywordsPanel.setSummaryText("Słowa kluczowe");

        accordionDetails.add(refRegPanel);
        accordionDetails.add(legalBasesPanel);
        accordionDetails.add(keywordsPanel);
        
        add(showJudgmentText, accordionDetails);
    }
    

    @Override
    public void setDetailsAndUpdateBox(JudgmentSynopsis currentElement) {
        judgmentDetails = currentElement.getJudgmentDetails();
        if(judgmentDetails == null){
            judgmentDetails = judgmentSaosClient.getJudgmentDetails(currentElement.getSaosId());
        }
        this.setVisible(true);
        configureRefRegPanel();
        configureStringPanels(legalBasesPanel, judgmentDetails.getLegalBases());
        configureStringPanels(keywordsPanel, judgmentDetails.getKeywords());
        
        if(judgmentDetails.getTextContent() != null && judgmentDetails.getTextContent().length() > 0) {
            showJudgmentText.setEnabled(true);
        } else {
            showJudgmentText.setEnabled(false);
        }
    }

    private void configureRefRegPanel() {
        refRegPanel.setContent(null);
        Set<ReferencedRegulation> referencedRegulations = judgmentDetails.getReferencedRegulations();
        if (validateSet(referencedRegulations)) {
            refRegPanel.setEnabled(true);
            for (ReferencedRegulation refReg : referencedRegulations) {
                Text refRegText = new Text(refReg.getText());

                QueryParameters qp = getYearPosQueryParams(refReg.getYear(), refReg.getPosition());
                VerticalLayout buttonBox = new VerticalLayout(new Span("Szukaj:"), getFindJudgmentLinkButton(qp), getSearchActLinkButton(qp));
                
                VerticalLayout content = new VerticalLayout(refRegText, buttonBox);
                content.setSizeFull();
                
                Details titleDetail = new Details(refReg.getTitle(), content);
                titleDetail.addThemeVariants(DetailsVariant.FILLED);

                refRegPanel.addContent(titleDetail);
            }
        } else {
            refRegPanel.setEnabled(false);
        }
    }
    
    private RouterLink getFindJudgmentLinkButton(QueryParameters qp) {
        Button findJudgment = getFindJudgmentButton();
        findJudgment.addThemeVariants(ButtonVariant.LUMO_SMALL);
        
        RouterLink searchJudgmentLink = new RouterLink("", JudgmentSearchView.class);
        searchJudgmentLink.setQueryParameters(qp);
        
        searchJudgmentLink.add(findJudgment);
        
        return searchJudgmentLink;
    }
    

    private RouterLink getSearchActLinkButton(QueryParameters qp) {
        Button findActButton = new Button("Przywołanego aktu", new Icon(VaadinIcon.INSTITUTION));
        findActButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
        
        RouterLink searchActLink = new RouterLink("", ActSearchView.class);
        searchActLink.setQueryParameters(qp);
        
        searchActLink.add(findActButton);
        
        return searchActLink;
    }
    
    
    private void configureStringPanels(AccordionPanel panelToSet, Set<String> stringSet) {
        panelToSet.setContent(null);
        if (validateSet(stringSet)) {
            panelToSet.setEnabled(true);
            VerticalLayout content = new VerticalLayout();
            for (String element : stringSet) {
                Text text = new Text(element);
                content.add(text);
            }
            panelToSet.setContent(content);
        } else {
            panelToSet.setEnabled(false);
        }
    }

    @SuppressWarnings("rawtypes")
    private boolean validateSet(Set set) {
        return set != null && set.size() > 0;
    }

    private Button getFindJudgmentButton() {
        return new Button("Orzeczeń z tym aktem", new Icon(VaadinIcon.GAVEL));
    }
}
