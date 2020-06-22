## I-Legal (frontend)
*README language: English, [Polish](https://github.com/Sebeni/i-legal-front/blob/master/README_pl.md)*

- frontend github repo: https://github.com/Sebeni/i-legal-front
- backend github repo: https://github.com/Sebeni/i-legal-back
- demo (due to heroku dyno sleeping sometimes it is necessary to refresh couple times, first visit may take a while): https://i-legal-front.herokuapp.com/

### Specification 

Final project, which had to meet following requirements (in brackets actual number):
1. 20 (31) different endpoints (at least once use: GET, POST, PUT, DELETE),
2. 2 (2) usage of external APIs (ISAP and SAOS),
3. 1 (1) scheduler (updating saved acts once a day)
4. 10 (13) different saving operations to database,  
5. 65% (81 % method, 74 % line)  tests coverage,
6. 2 (5*) design patterns: facade, strategy, template method, singleton, *aspects (AOP - no specific design pattern),
7. UI build with Vaadin;

### Used dependencies
- Backend (Java 1.8) build with Gradle. Used dependencies:
spring boot (data-jpa, web, test, validation, aop), h2database, lombok;

- Frontend(Java 1.8) build with Maven. Used dependencies:
Vaadin 14.1.25, spring boot + dev-tools, lombok;

### Description
App consists of 2 modules:
- legal acts
- court judgments

Each of them provide search form that connects with public APIs:
- [Internetowego System Aktów Prawnych](http://isap.sejm.gov.pl/api/isap/) (ISAP)
- [Analizy Orzeczeń Sądowych](https://www.saos.org.pl/help/index.php/dokumentacja-api) (SAOS)

Search results, and their details can be saved (also updated, deleted) in the database. App also gives ability to search
related elements (for example search for all judgments based on the given legal act).

Once an hour legal acts are automatically checked and updated if needed (this can be also done manually).

### Installation

- by default backend runs on port 8081 and frontend 8080.
Homepage url is http://localhost:{port number}
- Vaadin framework requires downloading node.js https://nodejs.org/en/. First run can take couple minutes.
- project uses lombok so adding plugin and enabling annotation processing (IntelliJ) is a must;
