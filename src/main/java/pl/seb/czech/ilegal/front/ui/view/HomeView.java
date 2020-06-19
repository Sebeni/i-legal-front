package pl.seb.czech.ilegal.front.ui.view;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
        content.getElement().setProperty("innerHTML", body);
        add(content);
        
    }
    
    private final String body = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>Home</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <h1>Witaj w aplikacji I-Legal</h1>\n" +
            "    <div>\n" +
            "        Aplikacja składa się z 2 powiązanych ze sobą modułów:\n" +
            "    </div>\n" +
            "    <ul>\n" +
            "        <li>Aktów prawnych</li>\n" +
            "        <li>Orzeczeń sądów i innych organów</li>\n" +
            "    </ul>\n" +
            "    <div>\n" +
            "        Każdy z modułów posiada wyszukiwarkę, która za pośrednictwem publicznych API przeszukuje zbiory odpowiednio:\n" +
            "    </div>\n" +
            "    <ul>\n" +
            "        <li><a href=\"http://isap.sejm.gov.pl/api/isap/\">Internetowego System Aktów Prawnych (ISAP)</a> oraz</li>\n" +
            "        <li><a href=\"https://www.saos.org.pl/help/index.php/dokumentacja-api\">Analizy Orzeczeń Sądowych (SAOS)</a></li>\n" +
            "    </ul>\n" +
            "    <div>\n" +
            "        Wyniki wyszukiwań (tj. akty prawne i orzeczenia) oraz ich szczegóły mogą być zapisywane w bazie danych. Ponadto w panelu szczegółów \n" +
            "        (po kliknięciu w akt lub orzeczenie) można automatycznie przenieść się do uzupełnionego formularza wyszukiwań \n" +
            "        celem znalezienia powiązanych elementów. \n" +
            "    </div>\n" +
            "    <div>\n" +
            "        W przypadku zapisanych aktów prawnych codziennie automatycznie sprawdzana ich aktualność (można to również \n" +
            "        zrobić ręcznie w zakładce zapisane akty prawne). \n" +
            "    </div>\n" +
            "    <div>\n" +
            "        <p>Autor: Sebastian Czech\n" +
            "    </div>\n" +
            "</body>\n" +
            "</html>";
}
