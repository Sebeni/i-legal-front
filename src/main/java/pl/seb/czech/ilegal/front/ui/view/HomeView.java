package pl.seb.czech.ilegal.front.ui.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import pl.seb.czech.ilegal.front.ui.layout.MainLayout;

@Route(value = "", layout = MainLayout.class)
public class HomeView extends VerticalLayout {
    public HomeView() {
        H1 welcomeMessage = new H1("Witaj w aplikacji I-Legal!");
        welcomeMessage.addClassName("welcome-message");
        add(welcomeMessage);
    }
}
