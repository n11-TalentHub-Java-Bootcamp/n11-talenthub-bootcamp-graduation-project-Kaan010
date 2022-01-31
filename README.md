  # ***n11-talenthub-bootcamp-graduation-project***



This app takes customer information and gives opportunity to apply credit application. The applications result calculated by given algorithm and result is sent via SMS to customers phone number.

<p align="center">
<img width="600" alt="Ekran Resmi 2022-01-31 02 43 13" src="https://user-images.githubusercontent.com/68256356/151764749-b042f537-6d79-4970-a9f7-8dffb3e35e1f.png">
</p>

#### Tech Stack

* Java 11
* Spring Boot
* Spring Data JPA
* Kotlin 1.5.0
* Flyway
* PostgreSQL  (for real DB)
* H2 Database (for test DB)
* JUnit 5
* Twilio API (to send SMS)

#### Features
* OpenAPI documentation
* Exception Handling
* Validation
* Unit Tests of Service and Integration Tests (for important bussiness logics)


### Endpoints:

#### Create Customer
````
POST /v1/customer 
Host: localhost:8080

{
    "identityNumber": 18800000010,
    "name": "Kaan",
    "surName": "Kalan",
    "salary": "9000",
    "telephone": "+905430000000",
    "birthDate": "1996-02-10",
    "assurance": 1000
}
````

#### Get Customer By IdentityNumber
````
GET /v1/customer/filter/18800000010 
Host: localhost:8080
````

#### Update Customer By IdentityNumber
````
PUT /v1/customer/18800000010
Host: localhost:8080

{
    "identityNumber": 18800000010,
    "name": "Kaan",
    "surName": "Kalan",
    "salary": "7000",
    "telephone": "+05430000000",
    "birthDate": "1996-02-10",
    "assurance": 1500
}
````

#### Delete Customer By IdentityNumber
````
DELETE /v1/customer/18800000010
Host: localhost:8080
````

#### Apply Credit with Customer IdentityNumber
````
POST /v1/credit/apply/18800000010
Host: localhost:8080
````

#### Get Credit Applications By Customer IdentityNumber and Birthday
````
GET /v1/credit/customer
Host: localhost:8080
{
            "customerIdentityNumber": 18800000501,
            "customerBirthDate": "1996-02-10"
}
````

### Sample SMS

<p align="center">
<img width="300" alt="Ekran Resmi 2022-01-31 02 43 13" src="https://user-images.githubusercontent.com/68256356/151723840-a24ccd38-0d74-44e2-9d81-42cc068123ee.PNG">
</p>


### Swagger UI will be run on this url
http://localhost:${PORT}/swagger-ui.html

