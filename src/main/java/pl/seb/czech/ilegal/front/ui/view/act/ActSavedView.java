package pl.seb.czech.ilegal.front.ui.view.act;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.seb.czech.ilegal.front.client.act.IsapClient;
import pl.seb.czech.ilegal.front.domain.act.Act;
import pl.seb.czech.ilegal.front.stub.act.ActDBService;
import pl.seb.czech.ilegal.front.ui.components.act.ActDetailBox;
import pl.seb.czech.ilegal.front.ui.components.act.ActGrid;
import pl.seb.czech.ilegal.front.ui.layout.MainLayout;
import pl.seb.czech.ilegal.front.ui.view.SavedView;

@PageTitle("I-Legal | Zapisane akty")
@Route(value = "acts-saved", layout = MainLayout.class)
public class ActSavedView extends SavedView<Act, String> {
   

    @Autowired
    public ActSavedView(ActDBService actService, IsapClient isapClient) {
        super(actService, new ActGrid(actService.getAll()), new ActDetailBox(isapClient));
    }
}