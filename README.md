## I-Legal (frontend)
- link do backendu: https://github.com/Sebeni/i-legal-back
- link do frontendu: https://github.com/Sebeni/i-legal-front

### Uruchomienie
- domyślnie backend uruchamia się na porcie 8080 a frontend 8081. W przypadku konieczności zmiany tych portów trzeba 
również odpowiednio zmienić pliki application.properties zarówno we froncie jak i backendzie (miejsca są zaznaczone). 
Strona startowa ładuje się z domyślnej ścieżki (tj. http://localhost:{nr portu});

- z uwagi na stworzenie frontu przy użyciu Vaadin konieczne jest ściągnięcie node.js https://nodejs.org/en/. 
Pierwsze uruchomienie może zająć nawet kilka minut;

- konieczne jest również pobranie pluginu lombok oraz włączenie annotation processing (IntelliJ);

- przy starcie ładowane są do bazy danych (h2) przykładowe elementy (akty prawne i orzeczenia) a także zapisywany 
jest timestamp;

- przy pierwszym wywołaniu widoku „Szukaj aktów prawnych” pobierane są i zapisywane do BD wszystkie 
słowa kluczowe z ISAP API, dlatego przejście do tej zakładki (wyłącznie za pierwszym razem) 
może chwilę potrwać;

###Specyfikacja
- Backend (Java 1.8) zbudowany przy użyciu Gradle. Użyte zależności:
spring boot
(data-jpa,
web,
test,
validation,
aop)
h2database,
lombok

- Frontend(Java 1.8) zbudowany przy użyciu Maven. Użyte zależności:
Vaadin 14.1.25, spring boot + dev-tools, lombok


Spełnione wymagania projektu (w nawiasie liczba minimalna):
1. 31 (20) różnych endpointów (przynajmniej raz użyte metody GET, POST, PUT, DELETE),
2. 2 (2) zewnętrzne źródła danych (ISAP API oraz SAOS API),
3. 1 (1) scheduler (raz dziennie automatyczne sprawdzenie aktualności zapisanych w BD aktów prawnych)
4. 13 (10) różnych operacji zapisu danych do bazy  (zapisy: aktu, uaktualnienie aktu, wyszukiwania 
aktów, usunięć aktów, zmian aktów (jakie zmiany nastąpiły i kiedy), listy słów kluczowych (za pierwszym 
razem z ISAP API), orzeczenia, uaktualnienia orzeczenia, wyszukiwania orzeczenia, usunięć orzeczeń, timestamp startu aplikacji, zmian widoku aplikacji, wyrzucanych zdefiniowanych wyjątków)
5. 81 % (method), 74 % (line) (65%) pokrycia kodu testami,
6. 4* (2) wzorce projektowe: fasada (back ActDifferenceFinderFacade), strategia (w wielu miejscach), singleton (front KeywordCacheSingleton), *aspekty (back IsapWatcher, SaosWatcher);
7. warstwa widoku w Vaadin

###Opis aplikacji 
Aplikacja składa się z 2 powiązanych ze sobą modułów

- Aktów prawnych
- Orzeczeń sądów i innych organów

Każdy z modułów posiada wyszukiwarkę, która za pośrednictwem publicznych API przeszukuje zbiory odpowiednio:
- [Internetowego System Aktów Prawnych](http://isap.sejm.gov.pl/api/isap/) (ISAP) oraz
- [Analizy Orzeczeń Sądowych](https://www.saos.org.pl/help/index.php/dokumentacja-api) (SAOS)

Wyniki wyszukiwań (tj. akty prawne i orzeczenia) oraz ich szczegóły mogą być zapisywane w bazie danych. 

Ponadto w panelu szczegółów (po kliknięciu w akt lub orzeczenie) można automatycznie 
przenieść się do uzupełnionego formularza wyszukiwań celem znalezienia powiązanych elementów.

W przypadku zapisanych aktów prawnych codziennie automatycznie sprawdzana ich aktualność (można to również zrobić ręcznie w zakładce zapisane akty prawne).


