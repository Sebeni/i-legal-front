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
import pl.seb.czech.ilegal.front.ui.view.HomeView;
import pl.seb.czech.ilegal.front.ui.view.SavedActsView;
import pl.seb.czech.ilegal.front.ui.view.SearchActsView;

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
        RouterLink savedActsLink = new RouterLink("Moje akty", SavedActsView.class);
        RouterLink searchActsLink = new RouterLink("Szukaj aktów", SearchActsView.class);
        
        
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
                new HorizontalLayout(new Icon(VaadinIcon.FOLDER_OPEN), savedActsLink),
                new HorizontalLayout(new Icon(VaadinIcon.SEARCH), searchActsLink),
                
                new HorizontalLayout(new Icon(VaadinIcon.MOON), themeSwitch)
        );
        
        addToDrawer(drawerContent);
        
    }
}
