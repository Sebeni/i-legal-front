package pl.seb.czech.ilegal.front.stub.judgement;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class JudgmentExampleData {
    private List<String> jsonActs = new ArrayList<>();

    public JudgmentExampleData() {
        String commonCourt = " {\n" +
                "            \"id\": 1,\n" +
                "            \"href\": \"https://www.saos.org.pl/api/judgments/1\",\n" +
                "            \"courtType\": \"COMMON\",\n" +
                "            \"courtCases\": [\n" +
                "                {\n" +
                "                    \"caseNumber\": \"I ACa 1010/09\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"judgmentType\": \"DECISION\",\n" +
                "            \"judges\": [\n" +
                "                {\n" +
                "                    \"name\": \"Andrzej Struzik\",\n" +
                "                    \"function\": null,\n" +
                "                    \"specialRoles\": [\n" +
                "                        \"PRESIDING_JUDGE\"\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Józef Wąsik\",\n" +
                "                    \"function\": null,\n" +
                "                    \"specialRoles\": []\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Teresa Rak\",\n" +
                "                    \"function\": null,\n" +
                "                    \"specialRoles\": []\n" +
                "                }\n" +
                "            ],\n" +
                "            \"textContent\": \"Sygn. akt I ACa 1010/09 POSTANOWIENIE Dnia 14 września 2012 r. Sąd Apelacyjny w Krakowie – Wydział I Cywilny w składzie: Przewodniczący: SSA Andrzej Struzik Sędziowie: SSA Józef Wąsik SSA Teresa Rak Protokolant: st. prot. sądowy Marta Matys po rozpoznaniu na rozprawie w dniu 14 września 2012 r. w K. sprawy z powództwa H. C. przeciwko (...) Bank S.A. w W. o pozbawienie tytułu wykonawczego wykonalności w przedmiocie wniosku powódki H. C. o przywrócenie terminu do złożenia wniosku o doręczenie wyroku Sądu Apelacyjnego w Krakowie z dnia 21 grudnia 2009 r. wraz z uzasadnieniem p o s t a n a w i a: odrzucić wniosek o przywrócenie terminu oraz wniosek o doręczenie wyroku Sądu Apelacyjnego w Krakowie z dnia 21 grudnia 2009 r. wraz z uzasadnieniem. Sygn. akt I A Ca 1010/09 UZASADNIENIE Wyrokiem z d\",\n" +
                "            \"keywords\": [\n" +
                "                \"przywrócenie terminu procesowego\"\n" +
                "            ],\n" +
                "            \"division\": {\n" +
                "                \"href\": \"https://www.saos.org.pl/api/ccDivisions/816\",\n" +
                "                \"id\": 816,\n" +
                "                \"name\": \"I Wydział Cywilny\",\n" +
                "                \"code\": \"0000503\",\n" +
                "                \"court\": {\n" +
                "                    \"href\": \"https://www.saos.org.pl/api/commonCourts/123\",\n" +
                "                    \"id\": 123,\n" +
                "                    \"code\": \"15200000\",\n" +
                "                    \"name\": \"Sąd Apelacyjny w Krakowie\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"judgmentDate\": \"2012-09-14\"\n" +
                "        }";
        
        String supremeCourt = "{\n" +
                "            \"id\": 76256,\n" +
                "            \"href\": \"https://www.saos.org.pl/api/judgments/76256\",\n" +
                "            \"courtType\": \"SUPREME\",\n" +
                "            \"courtCases\": [\n" +
                "                {\n" +
                "                    \"caseNumber\": \"SNO 10/10\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"judgmentType\": \"SENTENCE\",\n" +
                "            \"judges\": [\n" +
                "                {\n" +
                "                    \"name\": \"Marian Buliński\",\n" +
                "                    \"function\": null,\n" +
                "                    \"specialRoles\": []\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Piotr Hofmański\",\n" +
                "                    \"function\": null,\n" +
                "                    \"specialRoles\": [\n" +
                "                        \"PRESIDING_JUDGE\"\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Przemysław Kalinowski\",\n" +
                "                    \"function\": null,\n" +
                "                    \"specialRoles\": [\n" +
                "                        \"REASONS_FOR_JUDGMENT_AUTHOR\",\n" +
                "                        \"REPORTING_JUDGE\"\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"textContent\": \"WYROK Z DNIA 15 MARCA 2010 R. SNO 10/10 Przewodniczący: sędzia SN Piotr Hofmański. Sędziowie SN: Przemysław Kalinowski (sprawozdawca), Marian Buliński. S ą d N a j w y ż s z y – S ą d D y s c y p l i n a rny z udziałem sędziego – Zastępcy Rzecznika Dyscyplinarnego Sądu Okręgowego oraz protokolanta po rozpoznaniu w dniu 15 marca 2010 r. sprawy sędziego Sądu Rejonowego w związku z odwołaniem Ministra Sprawiedliwości od wyroku Sądu Apelacyjnego – Sądu Dyscyplinarnego z dnia 20 listopada 2009 r., sygn. ASD (...) 1) z m i e n i ł zaskarżony w y r o k w ten sposób, że w miejsce orzeczonej wobec sędziego Sądu Rejonowego kary dyscyplinarnej nagany – na podstawie art. 109 § 1 pkt 4 u.s.p. wymierzył mu karę dyscyplinarną przeniesienia na inne miejsce służbowe w okręgu Sądu Apelacyjnego w A., 2) kosz\",\n" +
                "            \"keywords\": [],\n" +
                "            \"personnelType\": \"THREE_PERSON\",\n" +
                "            \"judgmentForm\": \"wyrok SN SD\",\n" +
                "            \"division\": {\n" +
                "                \"href\": \"https://www.saos.org.pl/api/scDivisions/1\",\n" +
                "                \"id\": 1,\n" +
                "                \"name\": \"Wydział VI\",\n" +
                "                \"chambers\": [\n" +
                "                    {\n" +
                "                        \"href\": \"https://www.saos.org.pl/api/scChambers/1\",\n" +
                "                        \"id\": 1,\n" +
                "                        \"name\": \"Izba Karna\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"judgmentDate\": \"2010-03-15\"\n" +
                "        }";
        
        String constitutionalTribunal = "{\n" +
                "            \"id\": 206684,\n" +
                "            \"href\": \"https://www.saos.org.pl/api/judgments/206684\",\n" +
                "            \"courtType\": \"CONSTITUTIONAL_TRIBUNAL\",\n" +
                "            \"courtCases\": [\n" +
                "                {\n" +
                "                    \"caseNumber\": \"K 35/15\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"judgmentType\": \"SENTENCE\",\n" +
                "            \"judges\": [\n" +
                "                {\n" +
                "                    \"name\": \"Andrzej Wróbel\",\n" +
                "                    \"function\": null,\n" +
                "                    \"specialRoles\": [\n" +
                "                        \"PRESIDING_JUDGE\"\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Małgorzata Pyziak-Szafnicka\",\n" +
                "                    \"function\": null,\n" +
                "                    \"specialRoles\": []\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Mirosław Granat\",\n" +
                "                    \"function\": null,\n" +
                "                    \"specialRoles\": []\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Piotr Tuleja\",\n" +
                "                    \"function\": null,\n" +
                "                    \"specialRoles\": [\n" +
                "                        \"REPORTING_JUDGE\"\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Sławomira Wronkowska-Jaśkiewicz\",\n" +
                "                    \"function\": null,\n" +
                "                    \"specialRoles\": []\n" +
                "                }\n" +
                "            ],\n" +
                "            \"textContent\": \"186/11/A/2015 WYROK z dnia 9 grudnia 2015 r. Sygn. akt K 35/15* * Sentencja została ogłoszona dnia 18 grudnia 2015 r. w Dz. U. poz. 2147. W imieniu Rzeczypospolitej Polskiej Trybunał Konstytucyjny w składzie: Andrzej Wróbel – przewodniczący Mirosław Granat Małgorzata Pyziak-Szafnicka Piotr Tuleja – sprawozdawca Sławomira Wronkowska-Jaśkiewicz, protokolant: Grażyna Szałygo, po rozpoznaniu, z udziałem wnioskodawców oraz Sejmu i Prokuratora Generalnego, na rozprawie w dniu 9 grudnia 2015 r., połączonych wniosków: 1) grupy posłów o zbadanie zgodności: a) art. 1 pkt 6 ustawy z dnia 19 listopada 2015 r. o zmianie ustawy o Trybunale Konstytucyjnym (Dz. U. poz. 1928), zaś w razie jej wejścia w życie przed dniem orzekania w niniejszej sprawie przez Trybunał – art. 137a ustawy z dnia 25 czerwca 2015\",\n" +
                "            \"keywords\": [],\n" +
                "            \"judgmentDate\": \"2015-12-09\"\n" +
                "        }";
        
        String nationalAppealChamber = "{\n" +
                "            \"id\": 354890,\n" +
                "            \"href\": \"https://www.saos.org.pl/api/judgments/354890\",\n" +
                "            \"courtType\": \"NATIONAL_APPEAL_CHAMBER\",\n" +
                "            \"courtCases\": [\n" +
                "                {\n" +
                "                    \"caseNumber\": \"KIO 1711/18\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"judgmentType\": \"DECISION\",\n" +
                "            \"judges\": [\n" +
                "                {\n" +
                "                    \"name\": \"Irmina Pawlik\",\n" +
                "                    \"function\": null,\n" +
                "                    \"specialRoles\": [\n" +
                "                        \"PRESIDING_JUDGE\"\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"textContent\": \"Sygn. akt KIO 1711/18 POSTANOWIENIE z dnia 6 września 2018 r. Krajowa Izba Odwoławcza - w składzie: Przewodniczący: Irmina Pawlik Protokolant: Marta Słoma po rozpoznaniu na posiedzeniu z udziałem stron i uczestników postępowania w Warszawie w dniu 6 września 2018 r. odwołania wniesionego do Prezesa Krajowej Izby Odwoławczej w dniu 23 sierpnia 2018 r. przez wykonawcę Przedsiębiorstwo Budowy Dróg i Mostów KOBYLARNIA Spółka Akcyjna z siedzibą w Kobylarni w postępowaniu prowadzonym przez zamawiającego Gminę Wrocław oraz Miejskie Przedsiębiorstwo Wodociągów i Kanalizacji Spółka Akcyjna z siedzibą we Wrocławiu reprezentowanego przez Wrocławskie Inwestycje Spółka z ograniczoną odpowiedzialnością z siedzibą we Wrocławiu przy udziale wykonawców wspólnie ubiegających się o udzielenie zamówienia: Prz\",\n" +
                "            \"keywords\": [],\n" +
                "            \"judgmentDate\": \"2018-09-06\"\n" +
                "        }";
        
        jsonActs.addAll(Arrays.asList(commonCourt, constitutionalTribunal, supremeCourt, nationalAppealChamber));
    }

    public List<String> getJsonActs() {
        return jsonActs;
    }
}
