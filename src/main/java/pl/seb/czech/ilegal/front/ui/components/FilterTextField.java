package pl.seb.czech.ilegal.front.ui.components;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.value.ValueChangeMode;

public class FilterTextField extends TextField {

    public FilterTextField() {
        setValueChangeMode(ValueChangeMode.EAGER);
        setPlaceholder("Filtruj");
        setSizeFull();
        addThemeVariants(TextFieldVariant.LUMO_SMALL);
    }
}
