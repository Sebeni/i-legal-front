package pl.seb.czech.ilegal.front.ui.components.judgement;

import com.vaadin.flow.component.textfield.TextField;
import pl.seb.czech.ilegal.front.domain.SearchQuery;
import pl.seb.czech.ilegal.front.ui.components.SearchForm;

public class JudgmentSearchForm extends SearchForm {

    public JudgmentSearchForm() {
        
        TextField textField = new TextField("Something");
        formFields.add(textField);
        add(textField);
    }

    @Override
    public SearchQuery getSearchQueryFromForm() {
        return null;
    }


}
