package pl.seb.czech.ilegal.front.ui.layout;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import pl.seb.czech.ilegal.front.ui.view.HomeView;

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
        RouterLink homeViewLink = new RouterLink("HOME", HomeView.class);
        
        VerticalLayout drawerContent = new VerticalLayout(homeViewLink);
        
        
        this.addToDrawer(drawerContent);
        
    }
}
