# 2307_India_Transactbridge
A Spring Boot application for managing student records with CRUD operations using both REST and SOAP APIs, backed by an H2 in-memory database.

## Features

- **Insert Student Records**: Add new student records to the database via REST or SOAP.
- **Amend Student Records**: Update existing student records by deleting the old record and inserting a new one.
- **Delete Student Records**: Remove student records from the database.
- **View Student Records**: Retrieve student records based on the unique primary key.
- **In-memory Database**: Uses H2 database for quick setup and testing.

## Technologies Used

- **Java**: Programming language used for the application.
- **Spring Boot**: Framework for building the application.
- **Spring Data JPA**: For data access and repository management.
- **H2 Database**: In-memory database for storing student records.
- **Lombok**: For reducing boilerplate code in Java classes.
- **Spring Web Services**: For creating SOAP web services.

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven

### API Endpoints

#### REST API

- **Insert Student**: `POST /students`
- **Amend Student**: `PUT /students`
- **Delete Student**: `DELETE /students/{primaryKey}`
- **Get Student**: `GET /students/{primaryKey}`

#### SOAP API

- **Insert Student**: `SOAP /ws/students`
- **Amend Student**: `SOAP /ws/students`
- **Delete Student**: `SOAP /ws/students`
- **Get Student**: `SOAP /ws/students`

### Example Request

#### REST API Example

To insert a student record via REST, you can use the following JSON body:

```json
{
    "studentTitle": "Mr.",
    "studentFirstName": "John",
    "studentSecondName": "Doe",
    "studentLastName": "Doe",
    "studentRollNumber": "11",
    "studentObtainedMarks": "85",
    "totalMarks": "100",
    "transactionType": "I"
}
```

#### SOAP API Example

To insert a student record via SOAP, you would send a SOAP request similar to the following:

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://www.transactbridge.com/wsdl">
    <soapenv:Header/>
    <soapenv:Body>
        <tns:BRIDGEOperation>
            <tns:LS_SRCH_MESSAGE>
                <tns:LS_SRCH_DATA>Your search data here</tns:LS_SRCH_DATA>
            </tns:LS_SRCH_MESSAGE>
        </tns:BRIDGEOperation>
    </soapenv:Body>
</soapenv:Envelope>
```

## Contributing

Contributions are welcome! Please feel free to submit a pull request or open an issue for any suggestions or improvements.

## Acknowledgments

- Thanks to the Spring community for their excellent documentation and support.