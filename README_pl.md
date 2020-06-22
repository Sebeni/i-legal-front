## I-Legal (frontend)
*Język README: [angielski](https://github.com/Sebeni/i-legal-front/blob/master/README.md), polski*

- link do frontendu: https://github.com/Sebeni/i-legal-front
- link do backendu: https://github.com/Sebeni/i-legal-back
- demo (z uwagi na darmowy serwer heroku czasem niezbędne jest kilkukrotne odświeżenie strony w celu "zbudzenia" frontu i backu): https://i-legal-front.herokuapp.com/

### Specyfikacja projektu

Projekt końcowy. Spełnione wymagania (w nawiasie faktyczna liczba):
1. 20 (31) różnych endpointów (przynajmniej raz użyte metody GET, POST, PUT, DELETE),
2. 2 (2) zewnętrzne źródła danych - API (ISAP oraz SAOS),
3. 1 (1) scheduler (raz dziennie automatyczne sprawdzenie aktualności zapisanych w BD aktów prawnych)
4. 10 (13) różnych operacji zapisu danych do bazy,
5. 65% (81 % metod, 74 % linii) pokrycia kodu testami,
6. 2 (5*) dowolne wzorce projektowe: fasada, strategia, template method, singleton, *aspekty;
7. warstwa widoku w Vaadin

### Użyte zależności
- Backend (Java 1.8) zbudowany przy użyciu Gradle. Zależności:
spring boot (data-jpa, web, test, validation, aop), h2database, lombok;

- Frontend(Java 1.8) zbudowany przy użyciu Maven. Zależności:
Vaadin 14.1.25, spring boot + dev-tools, lombok;

### Opis aplikacji 
Aplikacja składa się z 2 powiązanych ze sobą modułów

- Aktów prawnych
- Orzeczeń sądów i innych organów

Każdy z modułów posiada wyszukiwarkę, która za pośrednictwem publicznych API przeszukuje zbiory odpowiednio:
- [Internetowego System Aktów Prawnych](http://isap.sejm.gov.pl/api/isap/) (ISAP) oraz
- [Analizy Orzeczeń Sądowych](https://www.saos.org.pl/help/index.php/dokumentacja-api) (SAOS)

Wyniki wyszukiwań (tj. akty prawne i orzeczenia) oraz ich szczegóły mogą być zapisywane w bazie danych. 

Ponadto w panelu szczegółów (po kliknięciu w akt lub orzeczenie) można automatycznie 
przenieść się do uzupełnionego formularza wyszukiwań celem znalezienia powiązanych elementów.

W przypadku zapisanych aktów prawnych codziennie automatycznie sprawdzana ich 
aktualność (można to również zrobić ręcznie w zakładce zapisane akty prawne).

### Uruchomienie
- domyślnie backend uruchamia się na porcie 8081 a frontend 8080.
Strona startowa ładuje się z domyślnej ścieżki (tj. http://localhost:{nr portu});

- z uwagi na stworzenie frontu przy użyciu Vaadin konieczne jest ściągnięcie node.js https://nodejs.org/en/. 
Pierwsze uruchomienie może zająć nawet kilka minut;

- konieczne jest również pobranie pluginu lombok oraz włączenie annotation processing (IntelliJ);


