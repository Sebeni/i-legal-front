package pl.seb.czech.ilegal.front.ui.components;

import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.formlayout.FormLayout;
import pl.seb.czech.ilegal.front.domain.SearchQuery;

import java.util.ArrayList;
import java.util.List;

public abstract class SearchForm extends FormLayout {
    @SuppressWarnings("rawtypes")
    protected List<HasValue> formFields = new ArrayList<>();

    @SuppressWarnings("rawtypes")
    public List<HasValue> getFormFieldsForClear() {
        return formFields;
    }

    public abstract SearchQuery getSearchQueryFromForm();
    
    public abstract void setBinderBean(Object bean);
}
