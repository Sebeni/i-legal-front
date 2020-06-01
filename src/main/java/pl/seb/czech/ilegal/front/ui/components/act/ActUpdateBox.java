package pl.seb.czech.ilegal.front.ui.components.act;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.seb.czech.ilegal.front.domain.act.ActDifference;

import java.util.List;

public class ActUpdateBox extends Dialog {
    private List<ActDifference> actDifferences;
    
    public ActUpdateBox(List<ActDifference> actsDifference) {
        this.actDifferences = actsDifference;
        setSizeFull();
        setCloseOnEsc(true);
        setCloseOnOutsideClick(true);
        
        VerticalLayout content = new VerticalLayout();

        if(!somethingChanged()) {
            content.add(new HorizontalLayout(new Text("Wszystkie akty były aktualne!")));
        } else {
            content.add(new HorizontalLayout(new Text("Następujące akty zostały zaktualizowane:")));

            
           actsDifference.stream().filter(ActDifference::isDifferentAfter).forEach(actDifference -> {
               content.add(new Div());
               content.add(new HorizontalLayout(new Text(actDifference.getTitle())));
               content.add(new HorizontalLayout(new Text(actDifference.getBeforeChangesText())));
               content.add(new HorizontalLayout(new Text(actDifference.getAfterChangesText())));
           });
        }

        Button closeButton = new Button("Zamknij", new Icon(VaadinIcon.EXIT));
        closeButton.addClickListener(event -> close());
        content.add(closeButton);
        content.setAlignItems(FlexComponent.Alignment.CENTER);
        
        add(content);
        open();
    }
    
    private boolean somethingChanged(){
        return actDifferences.stream()
                .anyMatch(ActDifference::isDifferentAfter);
    }
    
}
