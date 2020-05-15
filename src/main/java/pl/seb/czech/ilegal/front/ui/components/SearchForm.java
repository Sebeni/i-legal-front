package pl.seb.czech.ilegal.front.ui.components;

import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.data.binder.Binder;
import pl.seb.czech.ilegal.front.client.Client;
import pl.seb.czech.ilegal.front.domain.SearchQuery;
import pl.seb.czech.ilegal.front.domain.act.ActSearchQuery;

import java.util.ArrayList;
import java.util.List;

public abstract class SearchForm extends FormLayout {
    @SuppressWarnings("rawtypes")
    protected List<HasValue> formFields = new ArrayList<>();

    @SuppressWarnings("rawtypes")
    public List<HasValue> getFormFieldsForClear() {
        return formFields;
    }

    public SearchForm() {
        setSizeFull();
    }
    
    public abstract SearchQuery getSearchQueryFromForm();
}
