
#  Hospital Management System

A **Spring Boot-based Hospital Management System** designed to manage patients, doctors, appointments, and billing efficiently using a structured MVC architecture.

---

##  Project Overview

This project is a web-based application that helps hospitals streamline their operations by managing:

* Patient records
* Doctor information
* Appointment scheduling
* Billing system

It follows a **layered architecture (Controller → Service → DAO → Model)** ensuring clean, maintainable, and scalable code.

---

##  Features

* 👩‍⚕️ **Doctor Management**

  * Add, update, delete, and view doctors

* 🧑‍🤝‍🧑 **Patient Management**

  * Maintain patient records
  * Edit and track patient details

* 📅 **Appointment Scheduling**

  * Book, update, and manage appointments

* 💳 **Billing System**

  * Generate and manage patient bills

* 🌐 **Web Interface**

  * User-friendly UI using HTML templates

---

##  Tech Stack

* **Backend:** Spring Boot (Java)
* **Frontend:** HTML, Thymeleaf
* **Build Tool:** Maven
* **Architecture:** MVC (Model-View-Controller)

---

## 📂 Project Structure

```
Hospital-Management-System
│── src/main/java/Hospital_Management_System
│   ├── controller        # Handles HTTP requests
│   ├── dao               # Data Access Layer
│   │   └── impl          # DAO Implementations
│   ├── model             # Entity Classes
│   ├── service           # Business Logic Layer
│   └── HospitalManagementSystemApplication.java
│
│── src/main/resources
│   ├── templates         # HTML pages (Thymeleaf)
│   ├── static            # Static resources
│   └── application.properties
│
│── src/test              # Unit Tests
│── pom.xml               # Maven dependencies
```

---

## ⚙️ Installation & Setup

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/your-username/hospital-management-system.git
cd hospital-management-system
```

### 2️⃣ Build the Project

```bash
mvn clean install
```

### 3️⃣ Run the Application

```bash
mvn spring-boot:run
```

### 4️⃣ Access the Application

```
http://localhost:8080
```

---

##  Modules Overview

###  Patients

* Add new patients
* Edit patient details
* View patient list

###  Doctors

* Add and manage doctors
* View doctor information

###  Appointments

* Schedule appointments
* Modify appointment details

###  Billing

* Generate bills
* View billing records

---

##  Testing

Run tests using:

```bash
mvn test
```

---

## 📈 Future Enhancements

* Authentication & Role-Based Access
* Database integration (MySQL/PostgreSQL)
* REST API support
* Dashboard & analytics
* Email/SMS notifications

---

## 🤝 Contributing

Contributions are welcome!

1. Fork the repository
2. Create a new branch
3. Commit your changes
4. Submit a pull request

---

## 📄 License

This project is for academic purposes and can be freely modified.

---

## 👩‍💻 Authors

* **Manyata Khothari**
* **Niharika Mathur**


