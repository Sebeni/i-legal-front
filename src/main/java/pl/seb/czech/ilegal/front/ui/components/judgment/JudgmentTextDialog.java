package pl.seb.czech.ilegal.front.ui.components.judgment;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import pl.seb.czech.ilegal.front.domain.judgment.JudgmentDetails;

public class JudgmentTextDialog extends Dialog {
    private Label judgmentText;

    public JudgmentTextDialog() {
        judgmentText = new Label();
        setCloseOnEsc(true);
        setCloseOnOutsideClick(true);
        setSizeFull();
        
        Button closeButton = new Button("Zamknij", new Icon(VaadinIcon.EXIT));
        closeButton.addClickListener(event -> this.close());

        add(judgmentText, new Div(), closeButton);
    }

    public void setJudgmentDetailsText(JudgmentDetails judgmentDetails) {
        judgmentText.removeAll();
        judgmentText.getElement().setProperty("innerHTML", formatToPseudoHtml(judgmentDetails.getTextContent()));
    }

    private String formatToPseudoHtml(String textContent) {
        String judgmentContent = textContent;
        if(!judgmentContent.contains("<div>")) {
            judgmentContent = judgmentContent.replaceAll("\n", "<br>");
        }
        return judgmentContent;
    }
}
