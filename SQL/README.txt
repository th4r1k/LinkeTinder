# LinkeTinder

## Features

Inside SQL.txt, the SQL commands create user,candidates,enterprises, qualification, candidate_qualification, jobs_qualification, candidade_like and enterprise_likes tables, populted with 5 candidates, 5 enterprises, 6 jobs opportunities, 4 qualifications, and some likes(all insertion are described in file).

To see the matches you can run this SQL query:

SELECT * from enterprise_likes, candidate_likes where enterprise_likes.candidate_id = candidate_likes.candidate_id AND enterprise_likes.enterprise_id in (SELECT enterprise_id from jobs where id = candidate_likes.job_id );


### Installing

Clone or download files, copy SQL.txt in your PostgreSQL query or

Enter in link below(select PostgreSQL 9.6) and past all code from SQL.txt and click in Build Schema:

    http://sqlfiddle.com/


## Built With

  - PostgreSQL 9.6
  - https://app.diagrams.net/
 
 
## Authors

  - *Tharik Honda* -
    

## Acknowledgments

  - AceleraZG