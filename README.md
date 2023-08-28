Question#1

This is a test application, developed using JAVA with Spring boot and MAVEN as a build tool, application has the following capabilities:

* A User rest controller with all CRUD operations [For those partners who do not have APIs].
* A partner controller to consume a mock third party API [For those partners who have APIs].
* Command objects with validations are used, so that only necessary data is being transferred to API layer.
* DTOs are used to return the desired response without any extra fields to ensure minimum payload size.
* Unit Testing with JUnit and Mockito to ensure all the functionalities are being tested before deploying via CI/CD pipeline. 
* Docker is added to support containerization and easy to execute without any environment setup.

How to use this application ?
1) clone the repo
2) execeute docker-compose up
3) generate a JWT token to access secure API:

POST URL: http://localhost:8080/login
Payload:
{
    "email" : "muzzammil@example.com",
    "password": "password"
}

4) Now you can access (JWT token is required to access this API):
API URL: http://localhost:8080/api/v1/users


5) For Partner API, access the following URL (JWT token is required to access this API):
API URL: http://localhost:8080/jubilee-test
  

Security:
1) JWT
2) IP Whitelisting

Scalability
1) This app is an starting point of microservices, and can be extended to utilize the capabilities.

Reliability: 
1) Global Error Handling
2) Third party API logging in database

Cost:
1) Use of microservices architecture will help in cost reduction while deploying on AWS cloud.


Ease of Maintenance of Development:
1) We have used Spring data JPA to speed up the development process and minimize overall cost.
2) For team understanding, code is commented properly.
3) doc strings are there to help them understand every functional unit.
4) Unit test cases to ensure smooth feature delivery.
5) Standard http error codes for better undrstanding and debugging.

Ease of Operation Management:
1) Bug fixing is easy due to proper error handling and logging
2) New enhancements can be smoothly delivered via versioning.
3) Security is used to prevent against potential threats and malwares.
4) Database first approach to minimize vulnerabilities.
5) Pull requests management to encure code quality.
6) JIRA or Trello can be used to maximize team productivity.
