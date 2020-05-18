package pl.seb.czech.ilegal.front.ui.components.judgement;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextAreaVariant;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.StringLengthValidator;
import pl.seb.czech.ilegal.front.domain.judgement.JudgmentSynopsis;
import pl.seb.czech.ilegal.front.stub.judgement.JudgmentDBService;
import pl.seb.czech.ilegal.front.ui.components.CustomGrid;

public class JudgmentEditDialog extends Dialog {

    private TextArea customNameTextField;

    public JudgmentEditDialog(JudgmentDBService dbService, JudgmentSynopsis currentElement, CustomGrid<JudgmentSynopsis> grid) {
        setSizeFull();
        setCloseOnEsc(false);
        setCloseOnOutsideClick(false);

        customNameTextField = new TextArea("Nowa nazwa");
        customNameTextField.addThemeVariants(TextAreaVariant.LUMO_SMALL);

        Button saveButton = new Button("Zapisz", new Icon(VaadinIcon.CHECK));
        saveButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_SUCCESS);

        Button cancelButton = new Button("Anuluj", new Icon(VaadinIcon.CLOSE));
        cancelButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_ERROR);

        HorizontalLayout buttonBar = new HorizontalLayout(saveButton, cancelButton);
        buttonBar.setAlignItems(FlexComponent.Alignment.CENTER);

        VerticalLayout changeBox = new VerticalLayout(customNameTextField, buttonBar);
        changeBox.setSizeFull();

        add(changeBox);

        Binder<JudgmentSynopsis> binder = new Binder<>(JudgmentSynopsis.class);
        binder.setBean(currentElement);
        binder.forField(customNameTextField)
                .withValidator(new StringLengthValidator("Nazwa musi zawierać od 1 do 255 znaków", 1, 255))
                .bind(JudgmentSynopsis::getCustomName, JudgmentSynopsis::setCustomName);


        saveButton.addClickListener(event -> {
            if(binder.isValid()){
                dbService.saveElement(binder.getBean());
                grid.setGridContent(dbService.getAll());
                clearAndCloseForm();
            }
        });

        cancelButton.addClickListener(event -> {
            clearAndCloseForm();
        });

        open();
    }

    private void clearAndCloseForm() {
        customNameTextField.clear();
        close();
    }
}
