
#### Purpose

This sample application is the minimum set to get an spring boot application started with the following features
- web container
- rest controller
- spring jpa
- spring-mysql integration
- spring container + mysql container communication

#### Prepare the Environment

* we assume there is already a mysql server up and running on the host machine, at port 3306
  * if the mysql server is not ready, refer to setup-mac-via-ansible mysql section to get it setup 
* use any sql client (vscode, idea, or independent clients) to connect to the local mysql, and run the following SQLs
  * `create database playground;`
  * `create user 'user1'@'%' identified by 'password';`
  * `grant all on playground.* to 'user1'@'%';`
  * the 3 sqls above creates a database, a user, allows it from any host to connect, grant all privileges
  * `select * from mysql.user;` to verify the creation of the user
  * at this point of time, the playground database is currently empty
  * `select * from playground.topic;` returns an error as the table does not exist yet
* open src/resources/application.properties, uncomment `spring.jpa.hibernate.ddl-auto=create` and comment other similar lines
  * this will instruct hibernate to auto create table structure everytime hibernate get a connection to the mysql
  * and yes, that also means that we need to change ddl-auto's value to `none` when the database table is created
* in other words, the very first time we start the spring boot application, when hibernate connects to mysql,
  * it will automatically create tables for us. after we verify this, we stop spring boot application, and immediately change this value to `none`
* if the app is started as a jar, we need to manually add `127.0.0.1      mysqlhost` to '/etc/hosts' file before starting the app
  * note that it is 127.0.0.1 here, not the same ip as 172.17.0.1 in docker compose file. why, think about it
  * lastly run `sudo killall -HUP mDNSResponder` to flush the dns cache
  * test `ping mysqlhost` to verify the dns update is successful


#### How to Build/Run

* to start the application in the form of a fat jar, run `./run-app-in-jar.sh`
  * to see the log, it should be in the `log` subdirectory where we run the command
* to start the application in the form of a docker container, run `./run-app-in-container.sh`
  * verify the container is listening on port 8888 through port mapping, `lsof -i :8888`
  * verify we can access the service from the host, `http localhost:8888/topics` where 'http' is a useful network utility installed via brew
    * if we get `http: error: ConnectionError` that means the service is not up
    * otherwise we should get `HTTP/1.1 200` and some other http specific contents
  * it is very important to note that `network=host` is not working on both windows or mac, so the best bet is to use default network setting 
  * to see the logs, check out app.log in `~/logs/starter-sample-service`
    * this is achieved by slf4j+logback config within the app, plus the volume mount which mounts the log dir to the host dir  


#### More on Gradle

* to clean-compile-test-build-package, run `./gradlew clean build` (`build` is the magic task which does almost all the jobs for us)
* to run the application, run `./gradlew bootRun`
* to dry run a task, run `./gradlew -m clean build assemble` produces the following result
  * :clean
  * :bootBuildInfo
  * :compileJava
  * :compileGroovy
  * :processResources
  * :classes
  * :bootJar
  * :bootStartScripts
  * :bootDistTar
  * :bootDistZip
  * :javadoc
  * :javadocJar
  * :sourceJar
  * :jar
  * :startScripts
  * :distTar
  * :distZip
  * :assemble
  * :compileTestJava
  * :compileTestGroovy
  * :processTestResources
  * :testClasses
  * :test
  * :check
  * :build
* by the way, the test report will be generated at build/reports/tests/test/index.html


#### Testing

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
