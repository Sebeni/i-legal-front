package pl.seb.czech.ilegal.front.ui.components.act;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import pl.seb.czech.ilegal.front.backend.clients.act.ActDbClient;
import pl.seb.czech.ilegal.front.backend.clients.act.KeywordCacheSingleton;
import pl.seb.czech.ilegal.front.domain.SearchQuery;
import pl.seb.czech.ilegal.front.domain.act.ActPublisher;
import pl.seb.czech.ilegal.front.domain.act.ActSearchQuery;
import pl.seb.czech.ilegal.front.ui.components.SearchForm;

import java.util.ArrayList;
import java.util.List;

public class ActSearchForm extends SearchForm {
    private ActDbClient actDbClient;
   
    private List<String> actKeywords = new ArrayList<>();
    private List<String> actProperNames = new ArrayList<>();

    private ComboBox<String> onlyActInForce;
    private TextField title;
    private ComboBox<String> keyWord;
    private ComboBox<String> properName;
    private ComboBox<ActPublisher> publisher;
    private IntegerField year;
    private IntegerField position;
    
    private Binder<ActSearchQuery> binder;
    
    public ActSearchForm(ActDbClient actDbClient) {
        this.actDbClient = actDbClient;
        
        populateKeywordsAndNames();

        onlyActInForce = new ComboBox<>("Status aktu prawnego", ActSearchQuery.IN_FORCE_ACTS, ActSearchQuery.ALL_ACTS);
        ActSearchQuery currentQuery = new ActSearchQuery();
        currentQuery.setOnlyActInForce(ActSearchQuery.ALL_ACTS);
        formFields.add(onlyActInForce);

        title = new TextField("Tytuł aktu", "całość lub część");
        formFields.add(title);

        keyWord = new ComboBox<>("Słowo kluczowe", actKeywords);
        keyWord.setClearButtonVisible(true);
        keyWord.setPlaceholder("Wybierz z listy");
        keyWord.setSizeFull();
        formFields.add(keyWord);
        
        properName = new ComboBox<>("Nazwa własna", actProperNames);
        properName.setClearButtonVisible(true);
        properName.setPlaceholder("Wybierz z listy");
        properName.setSizeFull();
        formFields.add(properName);
        
        HorizontalLayout keywordAndProperNameBox = new HorizontalLayout(keyWord, properName);
        keywordAndProperNameBox.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.END);
        keywordAndProperNameBox.setSizeFull();
        
        publisher = new ComboBox<>("Wydawnictwo", ActPublisher.values());
        currentQuery.setPublisher(ActPublisher.ALL);
        formFields.add(publisher);

        year = new IntegerField("Rok");
        formFields.add(year);
        
        position = new IntegerField("Pozycja");
        formFields.add(position);
        
        HorizontalLayout publisherFormInputs = new HorizontalLayout(publisher, year, position);
        publisherFormInputs.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.END);
        publisherFormInputs.setSizeFull();
        
        add(onlyActInForce, title, keywordAndProperNameBox, publisherFormInputs);

        binder = new Binder<>(ActSearchQuery.class);
        binder.bindInstanceFields(this);
        binder.setBean(currentQuery);
    }
    
    private void populateKeywordsAndNames() {
        KeywordCacheSingleton cache = KeywordCacheSingleton.getInstance(actDbClient);
        actKeywords = cache.getActKeywords();
        actProperNames = cache.getActProperNames();
    }

    @Override
    public SearchQuery getSearchQueryFromForm() {
        return binder.getBean();
    }

    @Override
    public void setBinderBean(Object bean) {
        if(bean.getClass() == ActSearchQuery.class){
            binder.setBean((ActSearchQuery) bean);
        }
        
    }
}
