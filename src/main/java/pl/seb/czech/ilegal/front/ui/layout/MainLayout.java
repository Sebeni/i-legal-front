package pl.seb.czech.ilegal.front.ui.layout;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import pl.seb.czech.ilegal.front.ui.view.HomeView;
import pl.seb.czech.ilegal.front.ui.view.SavedActsView;

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
        header.setWidth("100%");
        header.setClassName("header");
        
        this.addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink homeViewLink = new RouterLink("Start", HomeView.class);
     
        RouterLink savedActsLink = new RouterLink("Moje ustawy", SavedActsView.class);
        
        VerticalLayout drawerContent = new VerticalLayout(
                new HorizontalLayout(new Icon(VaadinIcon.HOME), homeViewLink), 
                new HorizontalLayout(new Icon(VaadinIcon.FOLDER_OPEN), savedActsLink)
        );
        
        
        this.addToDrawer(drawerContent);
        
    }
}
