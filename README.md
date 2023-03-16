# LinkeTinder

# 0.10.0

### Backend
Webserver for Backend using Tomcat-embed-core version: 10.1.7;
jakarta HttpServlet handles standart HTTP request for Controllers.

Before Gradle run:

Configure Postgres

    Backend/app/src/main/groovy/linketinder/Utils/DbConnection/PostgresConnectionFactory.groovy

Configure Tomcat

    Backend/app/src/main/groovy/linketinder/App.groovy

REST API to create new candidates/enterprises
Post containing body as json like.
{
	"name":"username",
	"email":"email@email.com",
	"country":"country",
	"state":"state",
	"password":"password",
	"category":"candidate",
	"doc":"12312312312",
	"zipCode":"12312321"
}

Routes:
/candidate
/enterprise



### Frontend 
Webserver using Node.js
work as a microservice, its possible to insert new users in Postgres Database

Configure Node server

    LinkeTinder/Frontend/src/server.ts

Configure URL for POST method

    LinkeTinder/Frontend/src/app.ts



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
  
