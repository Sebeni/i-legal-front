package pl.seb.czech.ilegal.front.ui.components.judgment;

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
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.validator.StringLengthValidator;
import lombok.extern.slf4j.Slf4j;
import pl.seb.czech.ilegal.front.domain.judgment.JudgmentSynopsis;
import pl.seb.czech.ilegal.front.backend.clients.judgment.JudgmentDbClient;
import pl.seb.czech.ilegal.front.ui.components.CustomGrid;

@Slf4j
public class JudgmentEditDialog extends Dialog {

    private TextArea customNameTextField;

    public JudgmentEditDialog(JudgmentDbClient dbService, JudgmentSynopsis currentElement, CustomGrid<JudgmentSynopsis> grid) {
        setSizeFull();
        setCloseOnEsc(true);
        setCloseOnOutsideClick(true);

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
        binder.forField(customNameTextField)
                .withValidator(new StringLengthValidator("Nazwa musi zawierać od 1 do 255 znaków", 1, 255))
                .bind(JudgmentSynopsis::getCustomName, JudgmentSynopsis::setCustomName);
        binder.readBean(currentElement);

        
       


        saveButton.addClickListener(event -> {
            if(binder.isValid()){
                try {
                    binder.writeBean(currentElement);
                } catch (ValidationException e) {
                    log.error("Error in edit judgment dialog box while writing to currentElement from textField", e);
                }
                dbService.updateElement(currentElement);
                grid.setGridContent(dbService.getAll());
                close();
            }
        });

        cancelButton.addClickListener(event -> close());

       
    }
}
