# vaccine2u-app - Demo Spring Boot Project

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.


### Prerequisites

*	To start the application, you have installed h2 memory database and the connection is configurable in application properties as below:

~~~txt
spring.datasource.url=jdbc:h2:mem:vaccinedb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
~~~

## Swagger APIs Testing UI

For full Swagger APIs documentations overview, you may start this application and visit though this link:
http://localhost:8080/swagger-ui.html

## Integration

Third party APIs integration used in this application:
* To get the full vaccine schedule list: [GET] https://my-json-server.typicode.com/jayhny89/vaccine-3rdparty/vaccines
* To register the vaccine schedule : [POST] https://my-json-server.typicode.com/jayhny89/vaccine-3rdparty/register

To configure the third party URLs, configure in application properties as key value below:

~~~txt
com.app.config.hostName=https://my-json-server.typicode.com/jayhny89/vaccine-3rdparty
com.app.config.locationURL=${com.app.config.hostName}/vaccines
com.app.config.registerURL=${com.app.config.hostName}/register
~~~