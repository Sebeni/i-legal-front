package pl.seb.czech.ilegal.front.ui.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import pl.seb.czech.ilegal.front.client.IsapClient;
import pl.seb.czech.ilegal.front.domain.ActSearchQuery;

import java.util.ArrayList;
import java.util.List;

public class ActsSearchForm extends FormLayout {
    private IsapClient isapClient;
    private List<String> actKeywords = new ArrayList<>();
    private List<String> actProperNames = new ArrayList<>();
    private ActsGrid actsGrid;
    private final ComboBox<String> onlyActInForce;
    private final TextField actName;
    private final ComboBox<String> keyWord;
    private final ComboBox<String> properName;
    private final ComboBox<String> publisher;
    private final TextField year;
    private final TextField position;
    private ActSearchQuery currentQuery = new ActSearchQuery();
    
    public final static String IN_FORCE_ACTS_ITEM = "Obowiązujące";
    public final static String ALL_ACTS_ITEM = "Wszystkie";
    
    public final static String ALL_PUBLISHERS = "Wszystkie";
    public final static String DZ_U = "Dziennik ustaw";
    public final static String M_P = "Monitor Polski";
    


    public ActsSearchForm(IsapClient isapClient, ActsGrid actsGrid) {
        this.isapClient = isapClient;
        this.actsGrid = actsGrid;
        separateKeywordsAndNames();

        onlyActInForce = new ComboBox<>("Status aktu prawnego", IN_FORCE_ACTS_ITEM, ALL_ACTS_ITEM);
        onlyActInForce.setValue(ALL_ACTS_ITEM);

        actName = new TextField("Tytuł aktu", "całość lub część");

        keyWord = new ComboBox<>("Słowo kluczowe", actKeywords);
        keyWord.setClearButtonVisible(true);
        keyWord.setPlaceholder("Zacznij wpisywać lub wybierz z listy");

        properName = new ComboBox<>("Nazwa własna", actProperNames);
        properName.setClearButtonVisible(true);
        properName.setPlaceholder("Zacznij wpisywać lub wybierz z listy");

        publisher = new ComboBox<>("Wydawnictwo", ALL_PUBLISHERS, DZ_U, M_P);
        publisher.setValue(ALL_PUBLISHERS);

        year = new TextField("Rok");

        position = new TextField("Pozycja");
        
        HorizontalLayout publisherFormInputs = new HorizontalLayout(publisher, year, position);
        publisherFormInputs.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.END);


        Button searchButton = new Button("Szukaj", new Icon(VaadinIcon.SEARCH));
        Button clearButton = new Button("Wyczyść", new Icon(VaadinIcon.ERASER));
        HorizontalLayout buttonsBar = new HorizontalLayout(searchButton, clearButton);

        add(onlyActInForce, actName, keyWord, properName, publisherFormInputs, buttonsBar);

        Binder<ActSearchQuery> binder = new Binder<>(ActSearchQuery.class);
        binder.bindInstanceFields(this);
        
        searchButton.addClickListener(event -> {
            System.out.println(binder.getBean());
        });
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
