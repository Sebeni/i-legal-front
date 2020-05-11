package pl.seb.czech.ilegal.front.ui.components;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import pl.seb.czech.ilegal.front.client.act.IsapClient;
import pl.seb.czech.ilegal.front.domain.act.ActSearchQuery;

import java.util.ArrayList;
import java.util.List;

public class ActSearchForm extends FormLayout {
    public final static String IN_FORCE_ACTS_ITEM = "Obowiązujące";
    public final static String ALL_ACTS_ITEM = "Wszystkie";
    public final static String ALL_PUBLISHERS = "Wszystkie";
    public final static String DZ_U = "Dziennik ustaw";
    public final static String M_P = "Monitor Polski";
    
    private IsapClient isapClient;
    private List<String> actKeywords = new ArrayList<>();
    private List<String> actProperNames = new ArrayList<>();

    private ComboBox<String> onlyActInForce;
    private TextField actName;
    private ComboBox<String> keyWord;
    private ComboBox<String> properName;
    private ComboBox<String> publisher;
    private TextField year;
    private TextField position;
    
    private ActSearchQuery currentQuery = new ActSearchQuery();
    private Binder<ActSearchQuery> binder;
    
    public ActSearchForm(IsapClient isapClient) {
        this.isapClient = isapClient;
       
        separateKeywordsAndNames();

        onlyActInForce = new ComboBox<>("Status aktu prawnego", IN_FORCE_ACTS_ITEM, ALL_ACTS_ITEM);
        currentQuery.setOnlyActInForce(ALL_ACTS_ITEM);

        actName = new TextField("Tytuł aktu", "całość lub część");

        keyWord = new ComboBox<>("Słowo kluczowe", actKeywords);
        keyWord.setClearButtonVisible(true);
        keyWord.setPlaceholder("Zacznij wpisywać lub wybierz z listy");

        properName = new ComboBox<>("Nazwa własna", actProperNames);
        properName.setClearButtonVisible(true);
        properName.setPlaceholder("Zacznij wpisywać lub wybierz z listy");

        publisher = new ComboBox<>("Wydawnictwo", ALL_PUBLISHERS, DZ_U, M_P);
        currentQuery.setPublisher(ALL_PUBLISHERS);

        year = new TextField("Rok");

        position = new TextField("Pozycja");
        
        HorizontalLayout publisherFormInputs = new HorizontalLayout(publisher, year, position);
        
        add(onlyActInForce, actName, keyWord, properName, publisherFormInputs);

        binder = new Binder<>(ActSearchQuery.class);
        binder.bindInstanceFields(this);
        binder.setBean(currentQuery);
        
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

    public Binder<ActSearchQuery> getBinder() {
        return binder;
    }
    
    public TextField getActName() {
        return actName;
    }

    public ComboBox<String> getKeyWord() {
        return keyWord;
    }

    public ComboBox<String> getProperName() {
        return properName;
    }

    public TextField getYear() {
        return year;
    }

    public TextField getPosition() {
        return position;
    }
}
