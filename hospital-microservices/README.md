# Hospital Management System - Microservices with Spring Boot, MongoDB, and Eureka

This project converts the hospital management system into **microservices** using:

- Spring Boot 3
- Spring Cloud Netflix Eureka Server
- Spring Data MongoDB
- RestTemplate for inter-service communication
- Jackson for JSON serialization/deserialization
- Maven multi-module project

## Services

### 1. Eureka Discovery Server
- **Port:** 8761
- Registers and discovers all microservices

### 2. Patient Service
- **Port:** 8081
- CRUD for patient records
- MongoDB database: `hospital_patient_db`

### 3. Doctor Service
- **Port:** 8082
- CRUD for doctor records
- MongoDB database: `hospital_doctor_db`

### 4. Appointment Service
- **Port:** 8083
- CRUD for appointments
- Validates `patientId` and `doctorId` by calling Patient Service and Doctor Service using **RestTemplate** through Eureka
- MongoDB database: `hospital_appointment_db`

## Project Structure

```text
hospital-microservices/
├── pom.xml
├── README.md
├── discovery-server/
├── patient-service/
├── doctor-service/
└── appointment-service/
```

## Ports

| Service | Port |
|---|---:|
| Eureka Server | 8761 |
| Patient Service | 8081 |
| Doctor Service | 8082 |
| Appointment Service | 8083 |

## How to Run

### Prerequisites
- Java 17+
- Maven 3.9+
- MongoDB running on `mongodb://localhost:27017`

### 1. Start MongoDB
If MongoDB is installed locally, start it normally.

Or with Docker:

```bash
docker run -d --name hospital-mongo -p 27017:27017 mongo:7
```

### 2. Build all modules
From the root folder:

```bash
mvn clean install
```

### 3. Start the Eureka Server
```bash
cd discovery-server
mvn spring-boot:run
```
Open: `http://localhost:8761`

### 4. Start Patient Service
```bash
cd patient-service
mvn spring-boot:run
```

### 5. Start Doctor Service
```bash
cd doctor-service
mvn spring-boot:run
```

### 6. Start Appointment Service
```bash
cd appointment-service
mvn spring-boot:run
```

## API Endpoints

## Patient Service - `http://localhost:8081/api/patients`

### Create Patient
```http
POST /api/patients
Content-Type: application/json
```

```json
{
  "name": "John Doe",
  "mobile": "9876543210",
  "email": "john@example.com",
  "address": "New York",
  "dob": "1998-05-10",
  "username": "john_doe",
  "password": "secret123"
}
```

### Get All Patients
```http
GET /api/patients
```

### Get Patient by ID
```http
GET /api/patients/{id}
```

### Update Patient
```http
PUT /api/patients/{id}
```

### Delete Patient
```http
DELETE /api/patients/{id}
```

---

## Doctor Service - `http://localhost:8082/api/doctors`

### Create Doctor
```json
{
  "name": "Dr. Smith",
  "mobile": "9123456780",
  "email": "smith@example.com",
  "address": "California",
  "specialization": "Cardiology",
  "username": "drsmith",
  "password": "secret123"
}
```

---

## Appointment Service - `http://localhost:8083/api/appointments`

### Create Appointment
```json
{
  "appointmentNumber": "APT-1001",
  "patientId": "PATIENT_ID_HERE",
  "doctorId": "DOCTOR_ID_HERE",
  "appointmentDate": "2026-04-20",
  "appointmentTime": "10:30 AM",
  "description": "General consultation",
  "type": "OPD"
}
```

### Response Example
```json
{
  "id": "661f5fa3f33dbe18f785e1c2",
  "appointmentNumber": "APT-1001",
  "appointmentDate": "2026-04-20",
  "appointmentTime": "10:30 AM",
  "description": "General consultation",
  "type": "OPD",
  "patient": {
    "id": "661f5e91f33dbe18f785e1a1",
    "name": "John Doe",
    "mobile": "9876543210",
    "email": "john@example.com",
    "address": "New York"
  },
  "doctor": {
    "id": "661f5f31f33dbe18f785e1b7",
    "name": "Dr. Smith",
    "mobile": "9123456780",
    "email": "smith@example.com",
    "address": "California",
    "specialization": "Cardiology"
  }
}
```

## Demo Flow

1. Start Eureka Server on port `8761`
2. Start Patient Service on port `8081`
3. Start Doctor Service on port `8082`
4. Start Appointment Service on port `8083`
5. Open Eureka dashboard and confirm all 3 services are registered
6. Create a patient using Patient Service
7. Create a doctor using Doctor Service
8. Create an appointment using the returned patient ID and doctor ID
9. Fetch all appointments and verify that patient and doctor details are enriched by inter-service calls

## Notes
- No API Gateway is used, as requested.
- Each service runs independently on a separate port.
- Appointment Service communicates using **RestTemplate** and service discovery.
- Jackson is used automatically by Spring Boot for JSON handling.

## Suggested Git Submission Steps

```bash
git init
git add .
git commit -m "Hospital management microservices with Eureka and MongoDB"
git branch -M main
git remote add origin <your-github-repo-url>
git push -u origin main
```

Replace `<your-github-repo-url>` with your own GitHub repository URL.
