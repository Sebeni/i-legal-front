package pl.seb.czech.ilegal.front.ui.components.judgement;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.RegexpValidator;
import pl.seb.czech.ilegal.front.domain.SearchQuery;
import pl.seb.czech.ilegal.front.domain.judgement.CourtType;
import pl.seb.czech.ilegal.front.domain.judgement.JudgmentSynopsisSearchQuery;
import pl.seb.czech.ilegal.front.ui.components.SearchForm;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JudgmentSearchForm extends SearchForm {

    private Binder<JudgmentSynopsisSearchQuery> binder;
    private ComboBox<CourtType> courtType;
    private TextField signature;
    private IntegerField articleNumber;
    private TextField referencedRegulationYearPos;
    private TextField searchPhrase;

    public JudgmentSearchForm() {
        List<CourtType> courtTypes = Arrays.stream(CourtType.values())
                .filter(courtType -> !courtType.equals(CourtType.ADMINISTRATIVE))
                .collect(Collectors.toList());

        courtType = new ComboBox<>("Rodzaj sądu / organu", courtTypes);
        formFields.add(courtType);

        signature = new TextField("Sygnatura sprawy");
        formFields.add(signature);
        
        referencedRegulationYearPos = new TextField("Powołana ustawa Dz.U.(rok/poz.)", " np. 1964/93");
        formFields.add(referencedRegulationYearPos);
        
        articleNumber = new IntegerField("Numer artykułu", "tylko liczba");
        formFields.add(articleNumber);
        
        searchPhrase = new TextField("Szukana fraza");
        searchPhrase.setSizeFull();
        formFields.add(searchPhrase);

        HorizontalLayout wholeBox = new HorizontalLayout(courtType, signature, referencedRegulationYearPos, articleNumber, searchPhrase);
        wholeBox.setSizeFull();
        
        add(wholeBox);
        
        configureBinder();
    }

    private void configureBinder() {
        JudgmentSynopsisSearchQuery currentQuery = new JudgmentSynopsisSearchQuery();
        currentQuery.setCourtType(CourtType.ALL);
        
        binder = new Binder<>(JudgmentSynopsisSearchQuery.class);
        binder.bindInstanceFields(this);
        binder.forField(referencedRegulationYearPos)
                .withValidator(new RegexpValidator("Wyłącznie format rok[4]/pozycja[1-n] w Dz.U.", "^\\d{4}/\\d+$"))
                .bind(
                        JudgmentSynopsisSearchQuery::getReferencedRegulationYearPos,
                        JudgmentSynopsisSearchQuery::setReferencedRegulationYearPos);
        binder.setBean(currentQuery);
    }

    @Override
    public SearchQuery getSearchQueryFromForm() {
        return binder.getBean();
    }


}
