package pl.seb.czech.ilegal.front.ui.components.judgement;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import pl.seb.czech.ilegal.front.domain.judgement.JudgmentDetails;
import pl.seb.czech.ilegal.front.domain.judgement.JudgmentSynopsis;

public class JudgmentTextDialog extends Dialog {
    private Label judgmentText;

    public JudgmentTextDialog() {
        judgmentText = new Label();
        setCloseOnEsc(true);
        setCloseOnOutsideClick(true);
        setSizeFull();
        
        Button closeButton = new Button("Zamknij", new Icon(VaadinIcon.EXIT));
        closeButton.addClickListener(event -> this.close());

        add(judgmentText, new Div(),closeButton);
        
    }

    public void setJudgmentDetailsText(JudgmentDetails judgmentDetails) {
        judgmentText.removeAll();
        judgmentText.getElement().setProperty("innerHTML", judgmentDetails.getTextContent());
        
    }
}
