package pl.seb.czech.ilegal.front.ui.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.util.MultiValueMap;
import pl.seb.czech.ilegal.front.client.IsapClient;

import java.util.ArrayList;
import java.util.List;

public class ActsSearchForm extends FormLayout {
    private IsapClient isapClient;
    private List<String> actKeywords = new ArrayList<>();
    private List<String> actProperNames = new ArrayList<>();
    private ActsGrid actsGrid;
   
    
    public ActsSearchForm(IsapClient isapClient, ActsGrid actsGrid) {
        this.isapClient = isapClient;
        this.actsGrid = actsGrid;
        
        ComboBox<String> actInForce = new ComboBox<>("Status aktu prawnego", "Obowiązujące", "Wszystkie");
        actInForce.setValue("Obowiązujące");
        
        separateKeywordsAndNames();
        
        TextField actName = new TextField("Tytuł aktu", "całość lub część");
        
        ComboBox<String> keyWords = new ComboBox<>("Słowo kluczowe", actKeywords);
        keyWords.setClearButtonVisible(true);
        keyWords.setPlaceholder("Zacznij wpisywać lub wybierz z listy");
        
        ComboBox<String> properNames = new ComboBox<>("Nazwa własna", actProperNames);
        properNames.setClearButtonVisible(true);
        properNames.setPlaceholder("Zacznij wpisywać lub wybierz z listy");
        
        ComboBox<String> publisher = new ComboBox<>("Wydawnictwo", "Wszystkie", "Dziennik ustaw", "Monitor Polski");
        publisher.setValue("Wszystkie");
        TextField year = new TextField("Rok");
        TextField position = new TextField("Pozycja");
        HorizontalLayout publisherFormInputs = new HorizontalLayout(publisher, year, position);
        publisherFormInputs.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.END);

        Button search = new Button("Szukaj", new Icon(VaadinIcon.SEARCH));
        
        
        add(actInForce, actName, keyWords, properNames, publisherFormInputs, search);
        
    }

    private void separateKeywordsAndNames() {
        List<String> allKeywordsAndName = isapClient.getAllKeywordsAndNames();
        allKeywordsAndName.forEach(s -> {
            if(Character.isUpperCase(s.charAt(0))) {
                actProperNames.add(s);
            } else {
                actKeywords.add(s);
            }
        });
    }
}
