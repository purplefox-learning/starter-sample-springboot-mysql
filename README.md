
This sample application is the minimum set to get an spring boot application started with the following features
- web container
- rest controller
- spring jpa
- mysql integration


Prepare the Environment

* connect to the local mysql, and run the following SQLs
  * `create database playground;`
  * `create user 'user1'@'%' identified by 'password';`
  * `grant all on playground.* to 'user1'@'%';`
  * the 3 sqls above creates a database, a user, allows it from any host to connect, grant all privileges
* now note that the database is currently empty
  * `select * from playground.topic;` returns an error as the table does not exist yet
* open src/resources/application.properties, uncomment `spring.jpa.hibernate.ddl-auto=create` and comment other similar lines
  * this will instruct hibernate to auto create table structure everytime hibernate get a connection to the mysql
  * and yes, that also means that we need to change ddl-auto's value to none when the database table is created

How to Build

* to compile-test, run "gradle test"
* to compile-test-build, run "gradle build"
* to compile-run, run "gradle run/bootRun" (this is what we want most of time for spring boot application)
* to dry run a task, run "gradle -m xxxTask"
* the test report will be generated at build/reports/tests/test/index.html


Use Cases

Test using httpie (especially handful for Mac)
Enter the following command in the given order
```
http localhost:8080/topics
http POST localhost:8080/topics name=the-world-is-flat description=good-book
http POST localhost:8080/topics name=the-three-body-problem description=great-book
http GET "localhost:8080/topics/new?name=quantitative-trading&desc=thick-book"
http localhost:8080/topics
http localhost:8080/topics/1
http localhost:8080/topics/2
http localhost:8080/topics/3
http POST localhost:8080/topics name=test-name description=test-desc
http localhost:8080/topics/4
http DELETE localhost:8080/topics/4
http localhost:8080/topics
http PUT localhost:8080/topics id=1 name=the-three-body-problem description=great-book
http PUT localhost:8080/topics id=1 name=the-three-body-problem description=this-is-indeed-a-great-book
```

Test using Postman
* either use postman or browser to send http get request
* use postman to send a http post/put/delete
  * in Headers, set Content-Type=application/json
  * in Body, change type to raw, and paste a json string in the textbox
