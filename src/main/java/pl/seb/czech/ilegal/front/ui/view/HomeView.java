package pl.seb.czech.ilegal.front.ui.view;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import pl.seb.czech.ilegal.front.backend.clients.ChangeViewClient;
import pl.seb.czech.ilegal.front.domain.ChangeViewLog;
import pl.seb.czech.ilegal.front.ui.layout.MainLayout;

import java.io.*;

@Slf4j
@PageTitle("I-Legal | Start")
@Route(value = "", layout = MainLayout.class)
public class HomeView extends VerticalLayout {
    
    @Autowired
    public HomeView(ChangeViewClient changeViewClient) {
        changeViewClient.save(new ChangeViewLog(this.getClass().getSimpleName()));
        
        Label content = new Label();
        String welcomeMessage = "<h1>Witaj w aplikacji I-Legal</h1>";
        try {
            welcomeMessage = getWelcomeContent();
        } catch (IOException e) {
            log.error("Error while reading Home.html file", e);
        } 
        content.getElement().setProperty("innerHTML", welcomeMessage);
        add(content);
        
    }
    
    public String getWelcomeContent() throws IOException {
        File html = new File(getClass().getClassLoader().getResource("templates/Home.html").getPath());
        BufferedReader br = new BufferedReader(new FileReader(html));
        
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
        
    }
}
