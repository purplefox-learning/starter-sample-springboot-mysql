This sample application is the minimum set to get an spring boot application started with the following features
- web container
- rest controller


How to Build

* to compile-test, run "gradle test"
* to compile-test-build, run "gradle build"
* to compile-run, run "gradle run/bootRun"
* to dry run a task, run "gradle -m xxxTask"
* the test report will be generated at build/reports/tests/test/index.html


Use Cases

Topics
GET       /topics         Get all topics
GET       /topics/id      Get the topic
POST      /topics         Create new topic
PUT       /topics/id      Updates the topic
DELETE    /topics/id      Deletes the topic


Test using Postman
* either use postman or browser to send http get request
* use postman to send a http post/put/delete
  * in Headers, set Content-Type=application/json
  * in Body, change type to raw, and paste a json string in the textbox