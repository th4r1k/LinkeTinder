# LinkeTinder

# Backend

A simple Groovy program, with PostgreSql persistance.
It start's with 5 candidates, 5 enterprise, 6 jobs opportunities, its possible to create more Users(Candidates/Enterprises).Login with a user, see opportunities and like, if the like is reciprocal its a match and important information is now avaible.

## Features

- Create: add new candidates/enterprises ;
- Read: list all candidates/enterpise;
- login: using the user name;
- like: view opportunities and like;
- match: view reciprocal likes;


### Requisites

  - Java 8+;
  - Groovy 3+;
  - PostgreSql;
  - Gradle.

### Installing

Clone or download files

Configure PostgreSql or change configuration in:

    (line 7-10) /Backend/app/src/main/groovy/linketinder/DAO/CandidateDAO.groovy

After configured database, paste all code in a query from:

    SQL/SQL.txt

Inside the Backend path run:

    gradle run


## Built With

  - Java 19.0.1;
  - Groovy 4.0.7;
  - PostgreSql 15;
  - Gradle 7.6.


# Frontend

A simple Html page with Typescript, with localstorage persistance.
Create a new user(Candidate/Enterprise), Login with the user, add qualifications/job opportunities, see opportunities and like, if the like is reciprocal its a match and important information is now avaible.

## Features

- Create: add new candidates/enterprises ;
- Read: list all candidates/enterpise;
- Delete : delete jobs opportunities;
- login: using the user name;
- like: view opportunities and like;
- match: view reciprocal likes;
- chart: chart with total of candidates and qualifications.

### Installing

Clone or download files

Inside the main path open :

    Frontend\dist\index.html


## Built With

  - Typescript
  - Webpack Js
  - Chart Js
 
## Authors

  - *Tharik Honda* -
    

## Acknowledgments

  - AceleraZG
  
