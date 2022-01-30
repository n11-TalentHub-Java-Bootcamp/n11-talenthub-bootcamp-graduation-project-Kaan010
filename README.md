# n11-talenthub-bootcamp-graduation-project

This app takes customer information and gives opportunity to apply credit application. The applications result calculated by given algorithm and result is sent via SMS to phone number.
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



