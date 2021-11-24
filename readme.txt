This code has been tested on Windows 10 laptop.

Steps to run:

1) Start Zookeeper

.\bin\zookeeper-server-start.sh config\zookeeper.properties

2) Start Kafka

.\bin\kafka-server-start.sh config\server.properties

3) Start job processor server by running the application

4) Open a browser with postman

5) Create a job by entering - {localhost:8080/job-management/jobs} in the search bar and select post method 

6) Get job status by entering - {localhost:8080/job-management/jobs/<Job ID>} in the search bar and select get method



job.topic-name:job-queue
database name: testdb