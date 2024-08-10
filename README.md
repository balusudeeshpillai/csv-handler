
# CSV READER

This application is a csv reader project which can upload a csv file by accepting a csv file and do fetch, fetch by code, Delete All operations.
The application is developed using Java 21 , Spring Boot 3 and in-memory H2 database. Following are the major
features of the application.

* fetch all data
* upload data
* fetch by code
* delete all data

### Operations Supported

| Method | Path                    | Description                 |
|--------|-------------------------|-----------------------------|
| POST   | /csv           | user can upload a csv file  |
| GET    | /csv           | Retrieves all the records uploaded|
| GET    | /csv/{code}    | Retrieves record using `code` |
| DELETE | /csv           | Deletes all data            |

## Steps to install

1. Pull the repository into your local drive.
2. Project is built on `JDK 21` and `Spring Boot 3.3.2`
3. `application.yaml` is kept along with the project
4. This project uses `In-memory database - H2 `
5. Run the application using mvn `spring-boot:run` or from your IDE.

## Postman Collection

A postman collection `CSV_Handler_Postman_Collection.json` is included in the projects folder which could be used to test
and validate once the API is up and running.
