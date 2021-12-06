This code has been tested on Windows 10. Kafka should be run on the same machine where the application code runs. This application uses the default kafka port.

Steps to run:

1) Start Zookeeper

.\bin\zookeeper-server-start.sh config\zookeeper.properties

2) Start Kafka

.\bin\kafka-server-start.sh config\server.properties

3) Start job processor server by running the application

4) Create a database in postgres named testdb

5) Open a browser with postman

6) Create a job by entering - {localhost:8080/job-management/jobs} in the search bar and select post method 

7) Get job status by entering - {localhost:8080/job-management/jobs/<Job ID>} in the search bar and select get method

8) API should be running on port 8080
