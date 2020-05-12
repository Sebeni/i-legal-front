package pl.seb.czech.ilegal.front.ui.layout;

import com.vaadin.componentfactory.ToggleButton;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.Lumo;
import pl.seb.czech.ilegal.front.ui.view.*;

@CssImport("./styles/styles.css")
public class MainLayout extends AppLayout {
    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("I-Legal app");
        logo.addClassName("logo");
        
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setSizeFull();
        header.setClassName("header");
        this.addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink homeViewLink = new RouterLink("Start", HomeView.class);
        RouterLink savedActsLink = new RouterLink("Zapisane akty prawne", ActSavedView.class);
        RouterLink searchActsLink = new RouterLink("Szukaj aktów prawnych", ActSearchView.class);
        RouterLink savedJudgmentLink = new RouterLink("Zapisane orzeczenia", JudgmentSavedView.class);
        RouterLink searchJudgmentLink = new RouterLink("Szukaj orzeczeń", JudgmentSearchView.class);
        
        
        ToggleButton themeSwitch = new ToggleButton("Ciemny motyw", click -> {
            ThemeList themeList = UI.getCurrent().getElement().getThemeList();
            if (themeList.contains(Lumo.DARK)) {
                themeList.remove(Lumo.DARK);
            } else {
                themeList.add(Lumo.DARK);
            }
        });
        
        
        VerticalLayout drawerContent = new VerticalLayout(
                new HorizontalLayout(new Icon(VaadinIcon.HOME), homeViewLink), 
                new HorizontalLayout(new Icon(VaadinIcon.ARCHIVE), savedActsLink),
                new HorizontalLayout(new Icon(VaadinIcon.INSTITUTION), searchActsLink),
                new HorizontalLayout(new Icon(VaadinIcon.BRIEFCASE), savedJudgmentLink),
                new HorizontalLayout(new Icon(VaadinIcon.GAVEL), searchJudgmentLink),
                new HorizontalLayout(new Icon(VaadinIcon.MOON), themeSwitch)
        );
        
        addToDrawer(drawerContent);
        
    }
}
