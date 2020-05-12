package pl.seb.czech.ilegal.front.stub;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.seb.czech.ilegal.front.domain.judgement.JudgmentDetails;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JudgmentDetailsDeserializationTest {
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldMapJudgmentDetails() {
         String jsonDetails = "{\n" +
                "    \"links\": [\n" +
                "        {\n" +
                "            \"rel\": \"self\",\n" +
                "            \"href\": \"https://www.saos.org.pl/api/judgments/20994\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"data\": {\n" +
                "        \"id\": 20994,\n" +
                "        \"courtType\": \"COMMON\",\n" +
                "        \"href\": \"https://www.saos.org.pl/api/judgments/20994\",\n" +
                "        \"courtCases\": [\n" +
                "            {\n" +
                "                \"caseNumber\": \"I ACa 698/13\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"judgmentType\": \"SENTENCE\",\n" +
                "        \"judgmentDate\": \"2013-07-31\",\n" +
                "        \"judges\": [\n" +
                "            {\n" +
                "                \"name\": \"Franciszek Marcinowski\",\n" +
                "                \"function\": null,\n" +
                "                \"specialRoles\": []\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"Jan Gibiec\",\n" +
                "                \"function\": null,\n" +
                "                \"specialRoles\": [\n" +
                "                    \"PRESIDING_JUDGE\"\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"Małgorzata Bohun\",\n" +
                "                \"function\": null,\n" +
                "                \"specialRoles\": []\n" +
                "            }\n" +
                "        ],\n" +
                "        \"source\": {\n" +
                "            \"code\": \"COMMON_COURT\",\n" +
                "            \"judgmentUrl\": \"http://orzeczenia.ms.gov.pl/ncourt-api/judgement/details?id=155000000000503_I_ACa_000698_2013_Uz_2013-08-12_001\",\n" +
                "            \"judgmentId\": \"155000000000503_I_ACa_000698_2013_Uz_2013-08-12_001\",\n" +
                "            \"publisher\": \"Monika Kurkowiak\",\n" +
                "            \"reviser\": \"Małgorzata Kurek\",\n" +
                "            \"publicationDate\": \"2013-10-01\"\n" +
                "        },\n" +
                "        \"courtReporters\": [\n" +
                "            \"Justyna Łupkowska\"\n" +
                "        ],\n" +
                "        \"decision\": null,\n" +
                "        \"summary\": null,\n" +
                "        \"textContent\": \"<p>Sygn. akt I A Ca 698/13</p>\\n    <div>\\n      <h2>WYROK</h2>\\n      <h5>W IMIENIU RZECZYPOSPOLITEJ POLSKIEJ</h5>\\n      <p>Dnia 31 lipca 2013 r.</p>\\n      \\n      <p>Sąd Apelacyjny we Wrocławiu – Wydział I Cywilny w składzie:</p>\\n      \\n      <table>\\n        <colgroup>\\n          <col width=\\\"219\\\"/>\\n          <col width=\\\"398\\\"/>\\n        </colgroup>\\n        <tr>\\n          <td>\\n            <p>Przewodniczący:</p>\\n          </td>\\n          <td>\\n            <p>SSA Jan Gibiec</p>\\n          </td>\\n        </tr>\\n        <tr>\\n          <td>\\n            <p>Sędziowie:</p>\\n          </td>\\n          <td>\\n            <p>SSA Małgorzata Bohun (spr.)</p>\\n            <p>SSA Franciszek Marcinowski</p>\\n          </td>\\n        </tr>\\n        <tr>\\n          <td>\\n            <p>Protokolant:</p>\\n          </td>\\n          <td>\\n            <p>Justyna Łupkowska</p>\\n          </td>\\n        </tr>\\n      </table>\\n      \\n      <p>po rozpoznaniu w dniu 31 lipca 2013 r. we Wrocławiu na rozprawie</p>\\n      <p>sprawy z powództwa <strong>\\n<!-- -->\\n<span class=\\\"anon-block\\\">B. K.</span> </strong>\\n</p>\\n      <p>przeciwko <strong>\\n<!-- -->\\n      \\n      <span class=\\\"anon-block\\\">K. E.</span> </strong>\\n  </p>\\n      <p>o ochronę dóbr osobistych</p>\\n      <p>na skutek apelacji powódki</p>\\n      <p>od wyroku Sądu Okręgowego we Wrocławiu</p>\\n      <p>z dnia 21 lutego 2013 r. sygn. akt I C 1090/11</p>\\n      \\n      \\n        \\n        <p>1. \\n        <strong>\\n<!-- -->oddala apelację; </strong>\\n</p>\\n      \\n      \\n        \\n        <p>2. \\n        <strong>\\n<!-- -->zasądza od powódki na rzecz pozwanego 405 zł tytułem zwrotu kosztów postępowania apelacyjnego.  </strong>\\n</p>\\n      \\n    </div>\\n    <div>\\n      <h2>UZASADNIENIE</h2>\\n      <p>Wyrokiem z dnia 21 lutego 2013 r. Sąd Okręgowy we Wrocławiu oddalił powództwo <span class=\\\"anon-block\\\">B. K.</span> o ochronę dóbr osobistych oraz o zasądzenie od pozwanego <span class=\\\"anon-block\\\">K. E.</span> na rzecz Kliniki <span class=\\\"anon-block\\\">(...)</span>we <span class=\\\"anon-block\\\">W.</span> kwoty 1.000 zł.</p>\\n      <p>Apelację od powyższego wyroku wywiodła powódka, zaskarżając wyrok w całości, zarzuciła mu:</p>\\n      <p>1. naruszenie przepisu prawa materialnego - <a href=\\\"http://isap.sejm.gov.pl/DetailsServlet?id=WDU19640160093\\\" title=\\\"Ustawa z dnia 23 kwietnia 1964 r. - Kodeks cywilny - Dz. U. z 1964 r. Nr 16, poz. 93 (art. 24)\\\">art. 24 k.c.</a>, poprzez:</p>\\n      <p>- przyjęcie, że działania pozwanego nie stanowiły naruszenia (zagrożenia) dóbr osobistych powódki;</p>\\n      <p>- błędne przyjęcie, że wystąpienie powódki o naruszenie dóbr osobistych przeciwko pozwanemu miało źródło w konflikcie sąsiedzkim stron o ogródki przydomowe i piwnice;</p>\\n      <p>- uznanie, że zarzucane naruszenie dóbr osobistych powódki polegało na zachowaniach pozwanego oraz świadków przez pozwanego powołanych w związku z zabieganiem przez pozwanego i jego żonę o ogródki i piwnice, w granicach ich pism i wystąpień formalnych.</p>\\n      <p>2. naruszenie prawa procesowego, to jest <a href=\\\"http://isap.sejm.gov.pl/DetailsServlet?id=WDU19640430296\\\" title=\\\"Ustawa z dnia 17 listopada 1964 r. - Kodeks postępowania cywilnego - Dz. U. z 1964 r. Nr 43, poz. 296 (art. 233;art. 233 § 1)\\\">art. 233 § 1 k.p.c.</a> poprzez błędną (dowolną) ocenę materiału dowodowego i przyjęcie</p>\\n      <p>- za niewiarygodne dowodów w postaci zeznań świadków wskazanych przez powódkę;</p>\\n      <p>- bezkrytycznym uznaniu za wiarygodne zeznań <span class=\\\"anon-block\\\">A. E.</span> gdy w świetle materiału dowodowego świadek ten miał jednoznacznie wrogie nastawienie wobec powódki i w sposób nie budzący wątpliwości zainteresowany był stronniczym przedstawieniem faktów i wynikiem korzystnym dla pozwanego;</p>\\n      <p>- poprzez ocenę wiarygodności świadków tylko przez pryzmat okoliczności konfliktu sąsiedzkiego, bez wszechstronnej oceny tych dowodów;</p>\\n      <p>- poprzez ustalenie, że powódka niewystarczająco wykazała naruszenie jej dóbr osobistych;</p>\\n      <p>3. nierozpoznanie istoty sprawy, przez uznanie, że zarzucane naruszenie dóbr osobistych powódki polegało na zachowaniach pozwanego oraz świadków przez niego powołanych w związku z zabieganiem poprzez formalne wystąpienia pozwanego i jego żony o ogródki i piwnice.</p>\\n      <p>Podnosząc powyższe powódka wniosła o zmianę zaskarżonego wyroku i uwzględnienie powództwa w całości, ewentualnie o uchylenie wyroku i przekazanie sprawy Sądowi Okręgowemu do ponownego rozpoznania.</p>\\n      <p>W odpowiedzi na apelację pozwany wniósł o oddalenie w całości apelacji powódki jako bezzasadnej oraz o zasądzenie od powódki na rzecz pełnomocnika procesowego pozwanego ustanowionego z urzędu kosztów nieopłaconej pomocy prawnej udzielonej z urzędu w postępowaniu odwoławczym.</p>\\n      <p>Powyższy wyrok Sąd Okręgowy wydał na podstawie następujących ustaleń faktycznych. Strony postępowania są sąsiadami, zamieszkują przy <span class=\\\"anon-block\\\">ul. (...)</span> we <span class=\\\"anon-block\\\">W.</span>. Od kwietnia 2003 r. trwa konflikt, którego stronami są z jednej strony powódka <span class=\\\"anon-block\\\">B. K.</span> i mieszkańcy <span class=\\\"anon-block\\\">lokalu nr (...)</span> - <span class=\\\"anon-block\\\">G. M.</span>, <span class=\\\"anon-block\\\">H. M.</span> i ich synowie, a z drugiej pozwany i jego rodzina. Pozwany wraz z żoną, w związku ze zbliżającymi się narodzinami pierwszego dziecka, postanowili uregulować kwestie dotyczące korzystania z terenu ogródka znajdującego się za budynkiem oraz proporcjonalnego podziału piwnic i wystąpił do Gminy <span class=\\\"anon-block\\\">W.</span> z oficjalnymi pismami w tej sprawie. Działania te nie spodobały się powódce oraz rodzinie <span class=\\\"anon-block\\\">M.</span>. Do chwili zainicjowania przez pozwanego sprawy o podział ogrodu i piwnic stosunki między stronami były dobre, po tym wydarzeniu uległy wyraźnemu ochłodzeniu. Obecnie między stronami często dochodzi do kłótni i nieporozumień, strony nawzajem sobie ubliżają i utrudniają codzienne funkcjonowanie, donoszą na siebie wzajemnie do Urzędu Gminy, Zasobu Komunalnego, wszczynają przeciwko sobie postępowania, w tym postępowania sądowe.</p>\\n      <p>Na podstawie tak ustalonego stanu faktycznego Sąd Okręgowy zważył, iż uwzględniając całokształt materiału dowodowego, brak jest podstaw do przyjęcia, iż doszło do naruszenia czy też zagrożenia dóbr osobistych powódki, co skutkowało oddaleniem powództwa. Powyższe okoliczności powódka miała wykazać zeznaniami świadków <span class=\\\"anon-block\\\">S. M.</span>, <span class=\\\"anon-block\\\">G. M.</span>, <span class=\\\"anon-block\\\">H. M.</span> oraz <span class=\\\"anon-block\\\">H. T. (1)</span>, którym Sąd Okręgowy nie dał jednak wiary. Jednocześnie Sąd Okręgowy podkreślił, iż nie było wystarczające dla przyjęcia, iż w sprawie doszło do naruszenia dóbr osobistych oparcie się jedynie na twierdzeniach powódki.</p>\\n      \\n      <p>\\n<strong>\\n<!-- --> Sąd Apelacyjny zważył, co następuje:</strong>\\n</p>\\n      \\n      <p>Apelacja jako bezzasadna podlegała oddaleniu.</p>\\n      <p>Sąd Apelacyjny w całości podzielił ustalenia faktyczne dokonane w sprawie przez Sąd Okręgowy, czyniąc je jednocześnie podstawą swojego orzeczenia.  Sąd I instancji, w wyniku prawidłowo przeprowadzonego postępowania dowodowego, ustalił wszystkie istotne dla niniejszej sprawy okoliczności, które znajdowały odzwierciedlenie w zaoferowanym przez strony materiale dowodowym.</p>\\n      <p>Apelacja powódki opierała się w zasadzie na kwestionowaniu przeprowadzonej przez sąd meriti oceny materiału dowodowego, a więc naruszeniu przepisu <a href=\\\"http://isap.sejm.gov.pl/DetailsServlet?id=WDU19640430296\\\" title=\\\"Ustawa z dnia 17 listopada 1964 r. - Kodeks postępowania cywilnego - Dz. U. z 1964 r. Nr 43, poz. 296 (art. 233;art. 233 § 1)\\\">art. 233 § 1 k.p.c.</a> Błędna ocena materiału dowodowego dokonana przez Sąd I instancji miała, zdaniem skarżącej, sprowadzać się m.in. do: uznania za niewiarygodne dowodów w postaci zeznań świadków wskazanych przez powódkę, uznania za wiarygodne zeznań świadka <span class=\\\"anon-block\\\">A. E.</span>, oceny wiarygodności świadków tylko przez pryzmat okoliczności konfliktu sąsiedzkiego, ustalenia, że powódka niewystarczająco wykazała naruszenie jej dóbr osobistych. Odnosząc się do zarzutu niewystarczającego wykazania naruszenia dóbr osobistych powódki, stwierdzić należy, iż podniosła ona również zarzut naruszenia <a href=\\\"http://isap.sejm.gov.pl/DetailsServlet?id=WDU19640160093\\\" title=\\\"Ustawa z dnia 23 kwietnia 1964 r. - Kodeks cywilny - Dz. U. z 1964 r. Nr 16, poz. 93 (art. 24)\\\">art. 24 k.c.</a>, który w zasadzie sprowadzał się do zakwestionowania prawidłowości ustaleń faktycznych sądu, na skutek dowolnej oceny materiału dowodowego.</p>\\n      <p>W ocenie Sądu Apelacyjnego zarzut naruszenia <a href=\\\"http://isap.sejm.gov.pl/DetailsServlet?id=WDU19640430296\\\" title=\\\"Ustawa z dnia 17 listopada 1964 r. - Kodeks postępowania cywilnego - Dz. U. z 1964 r. Nr 43, poz. 296 (art. 233;art. 233 § 1)\\\">art. 233 § 1 k.p.c.</a> nie mógł zasługiwać na uwzględnienie. W ocenie Sądu Apelacyjnego wszelkie ustalenia faktyczne poczynione przez Sąd I instancji były prawidłowe i znajdowały oparcie w zgromadzonym materiale dowodowym, dlatego też Sąd Apelacyjny przyjął je za podstawę swojego rozstrzygnięcia. Czyniąc rozważania co do zarzutu naruszenia przepisu <a href=\\\"http://isap.sejm.gov.pl/DetailsServlet?id=WDU19640430296\\\" title=\\\"Ustawa z dnia 17 listopada 1964 r. - Kodeks postępowania cywilnego - Dz. U. z 1964 r. Nr 43, poz. 296 (art. 233;art. 233 § 1)\\\">art. 233 § 1 k.p.c.</a> wskazać trzeba w pierwszej kolejności, iż według ugruntowanego w orzecznictwie stanowiska, zarzut obrazy wskazanego przepisu nie może polegać jedynie na zaprezentowaniu własnych, korzystniejszych dla skarżącego ustaleń stanu faktycznego, dokonanych na podstawie własnej, oceny materiału dowodowego (zob. postanowienie SN z dnia 10 stycznia 2002 r., II CKN 572/99). Wykazanie przez skarżącą, iż Sąd naruszył przepis <a href=\\\"http://isap.sejm.gov.pl/DetailsServlet?id=WDU19640430296\\\" title=\\\"Ustawa z dnia 17 listopada 1964 r. - Kodeks postępowania cywilnego - Dz. U. z 1964 r. Nr 43, poz. 296 (art. 233;art. 233 § 1)\\\">art. 233 § 1 k.p.c.</a> oraz że fakt ten mógł mieć istotny wpływ na wynik sprawy, nie może być zastąpione odmienną interpretacją dowodów zebranych w sprawie, chyba, że strona jednocześnie wykaże, iż ocena dowodów, przyjęta przez Sąd za podstawę rozstrzygnięcia, przekracza granice swobodnej oceny dowodów (zob. <em>\\n<!-- -->wyrok SN z dnia 10 kwietnia 2000 r., V CKN 17/00</em>). Oznacza to, że postawienie zarzutu naruszenia przepisu <a href=\\\"http://isap.sejm.gov.pl/DetailsServlet?id=WDU19640430296\\\" title=\\\"Ustawa z dnia 17 listopada 1964 r. - Kodeks postępowania cywilnego - Dz. U. z 1964 r. Nr 43, poz. 296 (art. 233;art. 233 § 1)\\\">art. 233 § 1 k.p.c.</a> nie może polegać na zaprezentowaniu przez skarżącą stanu faktycznego przyjętego przez nią na podstawie własnej oceny dowodów. Osoba skarżąca może tylko wykazywać, posługując się wyłącznie argumentami jurydycznymi, że Sąd naruszył ustanowione w wymienionym przepisie zasady oceny wiarygodności oraz mocy dowodów i że naruszenie to miało wpływ na wynik sprawy (zob. <em>\\n<!-- -->postanowienie Sądu Najwyższego z dnia 14 stycznia 2000 r., I CKN 1169/99)</em>.</p>\\n      <p>Wskazać przy tym należy, iż Sąd I instancji ma obowiązek wyprowadzenia <br/>z zebranego w sprawie materiału dowodowego wniosków logicznie prawidłowych. Reguła ta, współokreślająca granice swobodnej oceny dowodów, nie będzie zachowana jedynie wtedy, gdy wnioski wyprowadzone przez Sąd przy ocenie dowodów nie układają się w logiczną całość, zgodną z doświadczeniem życiowym, lecz pozostają ze sobą w sprzeczności, a także gdy nie istnieje logiczne powiązanie wniosków z zebranym w sprawie materiałem dowodowym (zob. <em>\\n<!-- -->wyrok SN z dnia 9 grudnia 2009 r., IV CSK 290/09</em>). Innymi słowy, jeżeli z określonego materiału dowodowego Sąd wyprowadza wnioski logicznie poprawne i zgodne z doświadczeniem życiowym, to ocena Sądu nie narusza reguł swobodnej oceny dowodów i musi się ostać, chociażby w równym stopniu, na podstawie tego materiału dowodowego, dawały się wysnuć wnioski odmienne.</p>\\n      <p>W ocenie Sądu Apelacyjnego Sąd Okręgowy przeprowadził skrupulatną analizę każdego z przedłożonych dowodów, dokonując konfrontacji ich treści oraz zwracając przy tym uwagę na niezwykle istotną dla podjęcia prawidłowego rozstrzygnięcia w przedmiotowej sprawie kwestię konfliktu sąsiedzkiego jaki istnieje pomiędzy pozwanym, a powódką i świadkami <span class=\\\"anon-block\\\">G.</span>, <span class=\\\"anon-block\\\">S.</span> i <span class=\\\"anon-block\\\">H. M.</span>. Proces oceny dowodów, a zwłaszcza przyczyn dla których poszczególnym dowodom Sąd I instancji odmówił wiarygodności został w sposób wyczerpujący i rzetelny przedstawiony w uzasadnieniu do zaskarżonego wyroku. Niekorzystna dla skarżącej ocena dowodów i powzięcie przez Sąd Okręgowych odmiennych ocen niż te, które obejmowało stanowisko procesowe powódki, nie mogło w tej sytuacji uzasadniać sformułowania zarzutu naruszenia zasady swobodnej oceny dowodów.</p>\\n      <p>Oparcie przez powódkę zarzutu naruszenia przepisu <a href=\\\"http://isap.sejm.gov.pl/DetailsServlet?id=WDU19640430296\\\" title=\\\"Ustawa z dnia 17 listopada 1964 r. - Kodeks postępowania cywilnego - Dz. U. z 1964 r. Nr 43, poz. 296 (art. 233;art. 233 § 1)\\\">art. 233 § 1 k.p.c.</a> jedynie na własnej, samodzielnie dokonanej ocenie zeznań świadków <span class=\\\"anon-block\\\">S. M.</span>, <span class=\\\"anon-block\\\">G. M.</span> i <span class=\\\"anon-block\\\">H. M.</span> oraz <span class=\\\"anon-block\\\">H. T.</span>nie mogło więc doprowadzić do oceny dowodów odmiennej niż ta, którą przeprowadził Sąd I instancji. Wbrew twierdzeniom powódki Sąd Okręgowy trafnie zakwestionował wiarygodność zeznań świadków <span class=\\\"anon-block\\\">G. M.</span>, <span class=\\\"anon-block\\\">S. M.</span> i <span class=\\\"anon-block\\\">H. M.</span>. Skarżąca zarzuciła, że niesłusznie Sąd Okręgowy przyjął, iż świadkowie ci mieli interes w tym, aby rozstrzygnięcie sądu było niekorzystne dla pozwanego. Ze stanowiskiem tym, nie sposób się zgodzić, nie ulega wątpliwości bowiem, iż świadkowie Ci, podobnie jak powódka, są skonfliktowani z pozwanym i w tymże konflikcie trzymają stronę powódki. Uzasadnione jest zatem, w ocenie Sądu Apelacyjnego, twierdzenie Sądu meriti, iż byli oni osobiście zainteresowani treścią rozstrzygnięcia w niniejszej sprawie, w związku z czym ocena ich zeznań musiała być dokonana w sposób szczególnie ostrożny.</p>\\n      <p>Powódka zarzuciła również w uzasadnieniu apelacji, iż błędne jest działanie Sądu Okręgowego, który rozpoznał całą sprawę przez pryzmat konfliktu sąsiedzkiego, wynikającego z nieporozumień na tle zamieszkiwania w jednym budynku i sposobu korzystania z jego części wspólnych. Twierdzenia te, zdaniem Sądu Apelacyjnego, są bezpodstawne i nie znajdują podstaw w treści uzasadnienia wyroku. Sąd Okręgowy niewątpliwie rozważył całokształt okoliczności istotnych w sprawie, nie czyniąc tego wyłącznie przez pryzmat konfliktu sąsiedniego. Nie ulega przy tym jednak wątpliwości Sądu Apelacyjnego, iż konflikt ten w znaczny sposób wpływa na treść relacji jakie istnieją pomiędzy stronami postępowania. Uzasadnionym jest twierdzenie, że determinuje on kształt tych stosunków już od około 10 lat i nie sposób było pominąć go przy ocenie zachowania pozwanego <span class=\\\"anon-block\\\">K. E.</span>wobec powódki. Nie ulega wątpliwości, że zachowanie pozwanego wobec powódki, należy ocenić negatywnie, choć nie w stopniu, który uzasadniałby przyjęcie, iż dopuścił się on naruszenia dóbr osobistych powódki. Jednakże również niewątpliwym jest, iż nie można było dokonać tej oceny, w oderwaniu od szerszego kontekstu, jakim jest istniejący pomiędzy stronami konflikt sąsiedzki. Gdyby Sąd Okręgowy pokusił się o dokonanie takiej oceny zachowania pozwanego, to jest w oderwaniu od istniejącego między stronami konfliktu, mógłby narazić się na trafny zarzut naruszenia swobodnej oceny dowodów poprzez brak wszechstronnego rozważenia zebranego materiału dowodowego.</p>\\n      <p>Podniosła również apelująca, że nigdy nie twierdziła, iż dochodzenie przez pozwanego i jego żonę ewentualnych praw związanych z podziałem ogródków i piwnic stanowi naruszenie jej dóbr osobistych i nie z powodu takich zachowań sprawa powinna być prowadzona, jak również, iż nie jest dopuszczalne przyjęcie, że istnienie konfliktu sąsiedzkiego wyłącza możliwość naruszenia przez jedną ze stron tego konfliktu dóbr osobistych drugiej strony. Również ten zarzut, w ocenie Sądu Apelacyjnego, uznać należy za całkowicie chybiony. Lektura uzasadnienia zaskarżonego wyroku nie daje nawet najmniejszych podstaw do formułowania twierdzenia, jakoby Sąd Okręgowy oddalając powództwo stanął na stanowisku, iż istnienie konfliktu sąsiedzkiego wyłącza możliwość naruszenia przez jedną ze stron tego konfliktu dóbr osobistych drugiej strony. Stwierdzić również należy, iż Sąd Okręgowy nie badał, czy do naruszenia dóbr osobistych powódki mogło dojść poprzez dochodzenie przez pozwanego i jego żonę praw związanych z korzystaniem z ogródka i piwnic. Okoliczności dotyczące istnienia konfliktu sąsiedzkiego jako istotne dla sprawy podlegały ustaleniu przez Sąd, co nie znaczy, iż Sąd Okręgowy badał, czy poprzez działania pozwanego zmierzające do uzyskania dostępu do ogródka i piwnicy mogło dojść do naruszenia dóbr osobistych powódki.</p>\\n      <p>Polemizuje również powódka w uzasadnieniu apelacji ze stanowiskiem Sądu Okręgowego, który oddalenie powództwa, oparł m.in. na poglądzie, iż niewystarczające dla przyjęcia naruszenia dóbr osobistych są wyłącznie twierdzenia powódki, zwłaszcza, że powód konsekwentnie im zaprzeczał przedstawiając własną wersję wydarzeń. W ocenie skarżącej, w sprawach o ochronę dóbr osobistych wyjaśnienie powódki, a więc osoby, której dobra osobiste zostały naruszone, nabierają szczególnego charakteru. Również z tym poglądem nie sposób się zgodzić. Brak jest jakichkolwiek podstaw prawnych, do przyjęcia, że dowód z przesłuchania powoda, w sprawach z zakresu ochrony dóbr osobistych, ma szczególną moc dowodową. Nie ulega wątpliwości, iż dowód ten podlega takiej samej ocenie, jak każdy inny dowód przeprowadzany przez Sąd w toku postępowania dowodowego. Jak słusznie zauważył Sąd Okręgowy, sytuacja dowodowa w przedmiotowej sprawie sprowadzała się do tego, iż Sąd dysponował słowem powódki przeciwko słowu pozwanego. W takiej sytuacji, wobec braku innych (wiarygodnych) dowodów, które potwierdzałyby podnoszone przez powódkę okoliczności, prawidłowo Sąd przyjął, że brak jest podstaw do przyjęcia, że doszło do naruszenia dóbr osobistych powódki. Niewątpliwie to na powódce spoczywał ciężar udowodnienia, że zdarzenia o których mówi miały miejsce, i obowiązkowi temu powódka nie sprostała, co musiało skutkować oddaleniem powództwa.</p>\\n      <p>Podsumowując, Sąd Okręgowy z wyjątkową dokładnością i cierpliwością zgromadził w sprawie odpowiedni materiał dowodowy, a następnie ustalił na jego podstawie wszystkie istotne dla rozstrzygnięcia sprawy okoliczności. Dokonując natomiast oceny zgromadzonego materiału dowodowego nie naruszył reguł swobodnej oceny dowodów wyznaczonych treścią przepisu <a href=\\\"http://isap.sejm.gov.pl/DetailsServlet?id=WDU19640430296\\\" title=\\\"Ustawa z dnia 17 listopada 1964 r. - Kodeks postępowania cywilnego - Dz. U. z 1964 r. Nr 43, poz. 296 (art. 233;art. 233 § 1)\\\">art. 233 § 1 k.p.c.</a>, a w szczególności zasad logiki, wiedzy i doświadczenia życiowego. Na podstawie przeprowadzonej w należyty sposób oceny dowodów, nie sposób nie zgodzić się z poglądem Sądu Okręgowego, iż działania pozwanego wobec powódki nie nosiły cech, które mogłyby je kwalifikować w kategoriach naruszenia dóbr osobistych. Tym samym Sąd Okręgowy stwierdził, mając na względzie przepis <a href=\\\"http://isap.sejm.gov.pl/DetailsServlet?id=WDU19640160093\\\" title=\\\"Ustawa z dnia 23 kwietnia 1964 r. - Kodeks cywilny - Dz. U. z 1964 r. Nr 16, poz. 93 (art. 6)\\\">art. 6 k.c.</a>, iż brak było podstaw do przyjęcia, że doszło do naruszenia czy też zagrożenia dóbr osobistych powódki, a stanowisko to Sąd Apelacyjny w pełni akceptuje.</p>\\n      <p>W związku z powyższym apelacja powódki, na podstawie <a href=\\\"http://isap.sejm.gov.pl/DetailsServlet?id=WDU19640430296\\\" title=\\\"Ustawa z dnia 17 listopada 1964 r. - Kodeks postępowania cywilnego - Dz. U. z 1964 r. Nr 43, poz. 296 (art. 385)\\\">art. 385 k.p.c.</a> podlegała oddaleniu, o czym orzeczono w pkt I sentencji wyroku.</p>\\n      <p>W pkt II sentencji wyroku Sąd zasądził od powódki na rzecz pozwanego kwotę 405 zł tytułem zwrotu kosztów postępowania. Powyższe rozstrzygnięcie znajduje uzasadnienie w treści przepisów <a href=\\\"http://isap.sejm.gov.pl/DetailsServlet?id=WDU19640430296\\\" title=\\\"Ustawa z dnia 17 listopada 1964 r. - Kodeks postępowania cywilnego - Dz. U. z 1964 r. Nr 43, poz. 296 (art. 98)\\\">art. 98 k.p.c.</a> w zw. z <a href=\\\"http://isap.sejm.gov.pl/DetailsServlet?id=WDU19640430296\\\" title=\\\"Ustawa z dnia 17 listopada 1964 r. - Kodeks postępowania cywilnego - Dz. U. z 1964 r. Nr 43, poz. 296 (art. 391;art. 391 § 1)\\\">art. 391 § 1 k.p.c.</a>, albowiem powódka jako strona przegrywająca sprawę w postępowaniu apelacyjnym winna zwrócić pozwanemu koszty niezbędne do celowego dochodzenia przez niego praw i celowej obrony. Na koszty te złożyło się wynagrodzenie pełnomocnika procesowego w osobie adwokata, ustalone na podstawie § 11 ust. 1 pkt 2 (w zakresie roszczenia o ochronę dóbr osobistych) i § 6 pkt 2 (w zakresie roszczenia o zapłatę kwoty 1.000 zł) w zw. z <a href=\\\"http://isap.sejm.gov.pl/DetailsServlet?id=WDU20021631348\\\" title=\\\"Rozporządzenie Ministra Sprawiedliwości z dnia 28 września 2002 r. w sprawie opłat za czynności adwokackie oraz ponoszenia przez Skarb Państwa kosztów nieopłaconej pomocy prawnej udzielonej z urzędu - Dz. U. z 2002 r. Nr 163, poz. 1348 (§ 13;§ 13 ust. 1;§ 13 ust. 1 pkt. 2)\\\">§ 13 ust. 1 pkt 2 rozporządzenia Ministra Sprawiedliwości z dnia 28 września 2002 r. w sprawie opłat za czynności adwokackie oraz ponoszenia przez Skarb Państwa kosztów nieopłaconej pomocy prawnej udzielonej z urzędu</a>.</p>\\n      \\n      <p>\\n<em>\\n<!-- -->bp</em>\\n</p>\\n    </div>\",\n" +
                "        \"legalBases\": [\n" +
                "            \"art. 23 k.c.\",\n" +
                "            \"art. 24 k.c.\",\n" +
                "            \"art. 448 k.c.\"\n" +
                "        ],\n" +
                "        \"referencedRegulations\": [\n" +
                "            {\n" +
                "                \"journalTitle\": \"Ustawa z dnia 17 listopada 1964 r. - Kodeks postępowania cywilnego\",\n" +
                "                \"journalNo\": 43,\n" +
                "                \"journalYear\": 1964,\n" +
                "                \"journalEntry\": 296,\n" +
                "                \"text\": \"Ustawa z dnia 17 listopada 1964 r. - Kodeks postępowania cywilnego (Dz. U. z 1964 r. Nr 43, poz. 296 - art. 233; art. 233 § 1; art. 385; art. 391; art. 391 § 1; art. 98)\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"journalTitle\": \"Rozporządzenie Ministra Sprawiedliwości z dnia 28 września 2002 r. w sprawie opłat za czynności adwokackie oraz ponoszenia przez Skarb Państwa kosztów nieopłaconej pomocy prawnej udzielonej z urzędu\",\n" +
                "                \"journalNo\": 163,\n" +
                "                \"journalYear\": 2002,\n" +
                "                \"journalEntry\": 1348,\n" +
                "                \"text\": \"Rozporządzenie Ministra Sprawiedliwości z dnia 28 września 2002 r. w sprawie opłat za czynności adwokackie oraz ponoszenia przez Skarb Państwa kosztów nieopłaconej pomocy prawnej udzielonej z urzędu (Dz. U. z 2002 r. Nr 163, poz. 1348 - § 13; § 13 ust. 1; § 13 ust. 1 pkt. 2)\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"journalTitle\": \"Ustawa z dnia 23 kwietnia 1964 r. - Kodeks cywilny\",\n" +
                "                \"journalNo\": 16,\n" +
                "                \"journalYear\": 1964,\n" +
                "                \"journalEntry\": 93,\n" +
                "                \"text\": \"Ustawa z dnia 23 kwietnia 1964 r. - Kodeks cywilny (Dz. U. z 1964 r. Nr 16, poz. 93 - art. 24; art. 6)\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"keywords\": [\n" +
                "            \"dobra osobiste\"\n" +
                "        ],\n" +
                "        \"referencedCourtCases\": [\n" +
                "            {\n" +
                "                \"caseNumber\": \"IV CSK 290/09\",\n" +
                "                \"judgmentIds\": [\n" +
                "                    91468\n" +
                "                ],\n" +
                "                \"generated\": true\n" +
                "            },\n" +
                "            {\n" +
                "                \"caseNumber\": \"I A Ca 698/13\",\n" +
                "                \"judgmentIds\": [],\n" +
                "                \"generated\": true\n" +
                "            },\n" +
                "            {\n" +
                "                \"caseNumber\": \"I C 1090/11\",\n" +
                "                \"judgmentIds\": [],\n" +
                "                \"generated\": true\n" +
                "            },\n" +
                "            {\n" +
                "                \"caseNumber\": \"I CKN 1169/99\",\n" +
                "                \"judgmentIds\": [],\n" +
                "                \"generated\": true\n" +
                "            },\n" +
                "            {\n" +
                "                \"caseNumber\": \"II CKN 572/99\",\n" +
                "                \"judgmentIds\": [],\n" +
                "                \"generated\": true\n" +
                "            },\n" +
                "            {\n" +
                "                \"caseNumber\": \"V CKN 17/00\",\n" +
                "                \"judgmentIds\": [],\n" +
                "                \"generated\": true\n" +
                "            }\n" +
                "        ],\n" +
                "        \"receiptDate\": \"\",\n" +
                "        \"meansOfAppeal\": null,\n" +
                "        \"judgmentResult\": null,\n" +
                "        \"lowerCourtJudgments\": [],\n" +
                "        \"division\": {\n" +
                "            \"id\": 1,\n" +
                "            \"name\": \"I Wydział Cywilny\",\n" +
                "            \"href\": \"https://www.saos.org.pl/api/ccDivisions/1\",\n" +
                "            \"code\": \"0000503\",\n" +
                "            \"type\": \"Cywilny\",\n" +
                "            \"court\": {\n" +
                "                \"href\": \"https://www.saos.org.pl/api/commonCourts/1\",\n" +
                "                \"id\": 1,\n" +
                "                \"name\": \"Sąd Apelacyjny we Wrocławiu\",\n" +
                "                \"code\": \"15500000\",\n" +
                "                \"type\": \"APPEAL\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";



        JudgmentDetails judgmentDetails = new JudgmentDetails();

        try {
            judgmentDetails = objectMapper.readValue(jsonDetails, JudgmentDetails.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        JudgmentDetails finalJudgmentDetails = judgmentDetails;
        assertAll(
                () -> assertEquals("dobra osobiste", finalJudgmentDetails.getKeywords()[0])
        );
    }
}
