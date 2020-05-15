package pl.seb.czech.ilegal.front.ui.components.act;

import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import pl.seb.czech.ilegal.front.client.act.IsapClient;
import pl.seb.czech.ilegal.front.domain.SearchQuery;
import pl.seb.czech.ilegal.front.domain.act.ActSearchQuery;
import pl.seb.czech.ilegal.front.ui.components.SearchForm;

import java.util.ArrayList;
import java.util.List;

public class ActSearchForm extends SearchForm {
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
    
    
    @SuppressWarnings("rawtypes")
    private List<HasValue> formFields = new ArrayList<>();

    private Binder<ActSearchQuery> binder;
    
    public ActSearchForm(IsapClient isapClient) {
        this.isapClient = isapClient;
       
        separateKeywordsAndNames();

        onlyActInForce = new ComboBox<>("Status aktu prawnego", IN_FORCE_ACTS_ITEM, ALL_ACTS_ITEM);
        ActSearchQuery currentQuery = new ActSearchQuery();
        currentQuery.setOnlyActInForce(ALL_ACTS_ITEM);
        formFields.add(onlyActInForce);

        actName = new TextField("Tytuł aktu", "całość lub część");
        formFields.add(actName);

        keyWord = new ComboBox<>("Słowo kluczowe", actKeywords);
        keyWord.setClearButtonVisible(true);
        keyWord.setPlaceholder("Zacznij wpisywać lub wybierz z listy");
        formFields.add(keyWord);
        
        
        properName = new ComboBox<>("Nazwa własna", actProperNames);
        properName.setClearButtonVisible(true);
        properName.setPlaceholder("Zacznij wpisywać lub wybierz z listy");
        formFields.add(properName);
        
        publisher = new ComboBox<>("Wydawnictwo", ALL_PUBLISHERS, DZ_U, M_P);
        currentQuery.setPublisher(ALL_PUBLISHERS);
        formFields.add(publisher);

        year = new TextField("Rok");
        formFields.add(year);
        
        position = new TextField("Pozycja");
        formFields.add(position);
        
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

    @Override
    public SearchQuery getSearchQueryFromForm() {
        return binder.getBean();
    }

    @SuppressWarnings("rawtypes")
    public List<HasValue> getFormFieldsForClear() {
        return formFields;
    }
}
