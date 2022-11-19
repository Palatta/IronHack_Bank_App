# Bank Application #

This application consists of a Rest API which is able to make CRUD operations with users and perform operations on their accounts (for information about classes and methods, see the UML folder).

All end points was tested with POSTMAN and are working properly.

Unit testing of endpoints are available in the test section (Some tests left, need to be implemented).

### Technologies used ###

MySql, Java, Spring Boot.

## Setup environment ##

### Importing database ###
    mysql -u username -p mid_term_project_db2 < mid_term_project_db2.sql

### Create an env.properties file into the root folder ###
2 databases are needed: 1 is the application database and 1 is the test database
        
    DB_DATABASE=jdbc:mysql://localhost:3306/mid_term_project_db2?createDatabaseIfNotExist=true
    DB_DATABASE_TEST=jdbc:mysql://localhost:3306/mid_term_project_db_test
    DB_USER=*youruser*
    DB_PASSWORD=*yourpassword*

## Endpoint structure ##
- /account-holders --> GET: get all account holders
- /account-holders/new --> POST: create an account holder
- /account-holders/{id} --> PUT: update account holder by id
- /account-holders/delete-all --> DEL: delete all account holders

- /admins --> GET: get all admins
- /admins/new --> POST: create an admin
- /admins/{name} --> PUT: update admin by name
- /admins/{name} --> DEL: delete an admin by name
- /admins/delete-all --> DEL: delete all admins

- /checking --> GET: get all checking accounts
- /checking/new --> POST: create a credit card account
- /checking/{id}/transaction-in --> PATCH: make a transaction into the checking account, update the balance
- /checking/{id}/transaction-out --> PATCH: make a transaction out of the checking account, update the balance
- /checking/{accountId} --> GET: get a checking by id
- /checking/{account-id} --> DEL: delete a checking by id
- /checking/delete-all --> DEL: delete all checkings

- /credit-card --> GET: get all credit cards
- /credit-card/new --> POST: create a credit card
- /credit-card/{id}/transaction-in --> PATCH: make a transaction into the credit card account, update the balance
- /credit-card/{id}/transaction-out --> PATCH: make a transaction out of the credit card account, update the balance
- /credit-card/{accountId} --> DEL: delete credit card account by id
- /credit-card/delete-all --> DEL: delete all credit card accounts

- /savings --> GET: get all saving accounts
- /savings/new --> POST: create new saving account (if the age is <= 24, create a student checking account)
- /savings/{id}/transaction-in --> PATCH: make a transaction into the saving account, update the balance
- /savings/{id}/transaction-out --> PATCH: make a transaction into the saving account, update the balance
- /savings/{accountId} --> GET: get saving account by id
- /savings/{accountId}/delete --> DEL: delete saving account by id
- /savings/delete-all --> DEL: delete all saving accounts

- /student-checking --> GET: get all student checking accounts
- /student-checking/{id}/transaction-in --> PATCH: make a transaction into the student checking account, update the balance
- /student-checking/{id}/transaction-out --> PATCH: make a transaction into the student checking account, update the balance
- /student-checking/{accountId}/delete --> DEL: delete student checking account by id
- /student-checking/delete-all --> DEL: delete all student checking accounts

- /third-party --> GET: get all third party users
- /third-party/{name} --> PUT: update a third party user by name
- /third-party/{name} --> DEL: delete a third party user by name
- /third-party/delete-all --> DEL: delete all third party users
