# Project Overview

---

## **Project Title: Intelligent Assignment Distribution System for Teachers**

---  

## **Objective:**  
  
Build a hybrid system that enables teachers to efficiently manage a coding question bank, create personalized assignments, and distribute them to students via email—automatically and pseudo-randomly.
  
---

## **Key Stakeholders:**  
  
* **Admin:** Sets up the system with teacher, class, and student records.
* **Teacher:** Manages questions and assignments, and initiates assignment distribution.
* **Student:** Does not interact with the system directly; receives personalized assignments via email.

---

## **Core Features:**

### 1. **Admin Panel:**

* Add, update, and delete:

  * Teachers
  * Students
  * Classes
* Assign students to classes.
* Assign teachers to classes.

---

### 2. **Teacher Panel:**

#### **A. Question Bank Management**

* CRUD operations on questions:

  * Each question includes:

    * Topic
    * Difficulty
    * Marks
    * Source info
    * MongoDB-stored question text (description, input/output format, constraints, sample I/O)

#### **B. Assignment Management**

* Create an assignment by specifying:

  * Topic
  * Total number of questions
  * Number per difficulty (e.g., 2 easy, 2 medium, 1 hard)
* For a given class, pseudo-randomly assign different sets of questions to each student based on:

  * `timestamp`
  * `teacherId`
  * `studentId`
  * `classId`
* The system:

  * Shows a preview table: students with their assigned question sets.
  * Allows **regeneration** if the teacher wants a new random set.
  * Generates **individual PDFs** per student with their question set.
  * Sends assignment PDF to each student using a **faculty email template**.

---

## **Technical Architecture:**

### 🟩 **MongoDB (NoSQL)**

Used for flexible storage of complex text content.

* **questionTexts**:

  * `description`, `inputFormat`, `outputFormat`, `constraints`, `sampleInputs`, `sampleOutputs`
* **solutionTexts**:

  * `answerCode`, `explanation`
* **classes**:

  * `classId`, `teacherId`, `students`: `[studentId, ...]`
* **assignments**:

  * `assignmentId`, `classId`, `dueDate`, metadata like topic, counts, questionMap

---

### 🟦 **MySQL (SQL/RDBMS)**

Used for managing structured and relational data.

* **Teachers**, **Students**
* **Questions** (metadata only):

  * Includes topic, difficulty, source info, and MongoDB reference.
* **Solutions** (metadata):

  * Includes MongoDB reference and question ID.
* **AssignmentQuestions**:

  * Mapping between assignment and questions.

---

## **Access Control Summary:**

| Role    | Access Description                                       |
| ------- | -------------------------------------------------------- |
| Admin   | CRUD for teachers, students, classes                     |
| Teacher | CRUD for questions and assignments                       |
| Student | Only receives personalized email (no login or UI access) |

---

## **Key Selling Point (USP):**

> "One-click personalized assignment distribution."

* Teachers only specify topic and difficulty breakdown.
* System intelligently and deterministically generates different sets per student.
* Previews allow transparency and regeneration.
* Everything is emailed automatically in a formatted PDF using a template.

---

## ✅ Summary of Role Access

| **Feature / Action**                   | **Admin** | **Teacher**     |
| -------------------------------------- | --------- | --------------- |
| **Create Teacher**                     | ✅         | ❌               |
| **Create Student (in DB)**             | ✅         | ❌               |
| **Create Class**                       | ✅         | ❌               |
| **Assign Teacher to Class**            | ✅         | ❌               |
| **Assign Students to Class**           | ✅         | ❌               |
| **View Teachers / Students / Classes** | ✅         | ✅ (own classes) |
|                                        |           |                 |
| **Create Question**                    | ❌         | ✅               |
| **Read/View Questions**                | ❌         | ✅ (own)         |
| **Update Question**                    | ❌         | ✅ (own)         |
| **Delete Question**                    | ❌         | ✅ (own)         |
|                                        |           |                 |
| **Create Assignment**                  | ❌         | ✅ (own classes) |
| **Read/View Assignment**               | ✅ (all)   | ✅ (own)         |
| **Update Assignment**                  | ❌         | ✅ (own)         |
| **Delete Assignment**                  | ❌         | ✅ (own)         |

---

### 🔎 Notes

* **Students are passive**: Their data is managed by Admin for assignment delivery purposes (e.g., name + email).
* **No login or interface** for students is required at this stage.
* **Future functionality** like submission or performance tracking can revisit student interactivity.
* **No need for a separate teacher login**: Each teacher is associated with their class(es) and can only manage their own questions and assignments.

---

## 🐳 Docker Compose Overview

### **Service Architecture**

The project uses Docker Compose to orchestrate multiple services, each encapsulated in its own container. The following table summarizes the services:

| **Service**  | **Container Name** | **Exposed Ports** | **Description**                                         | **Dependencies**                 | **Volumes**                                                                            |
| ------------ | ------------------ | ----------------- | ------------------------------------------------------- | -------------------------------- | -------------------------------------------------------------------------------------- |
| **Frontend** | `react-frontend`   | `8003:3000`       | React frontend, accessible via `http://localhost:8003`  | Depends on: **Backend**          | `./frontend:/app` (live reloading during development)                                  |
| **Backend**  | `fastapi-backend`  | `8002:8000`       | FastAPI backend, accessible via `http://localhost:8002` | Depends on: **MySQL**, **Mongo** | `./backend:/app`, `./docker-data/mysql:/var/lib/mysql`, `./docker-data/mongo:/data/db` |
| **MySQL**    | `mysql-db`         | `8000:3306`       | MySQL database, accessible via `localhost:8000` (host)  | None                             | `./docker-data/mysql:/var/lib/mysql` (persistent MySQL data)                           |
| **MongoDB**  | `mongo-db`         | `8001:27017`      | MongoDB database, accessible via `localhost:8001` (host)| None                             | `./docker-data/mongo:/data/db` (persistent MongoDB data)                               |

## 🏁 Running Databases Separately for Development

To run MySQL and MongoDB in separate containers for development:

1. Make sure Docker and Docker Compose are installed.
2. In your project root, run:
   ```bash
   docker-compose up -d mysql-db mongo-db
   ```
   This will start only the MySQL and MongoDB containers in the background.
3. MySQL will be available at `localhost:8000` and MongoDB at `localhost:8001`.
4. To stop them, run:
   ```bash
   docker-compose stop mysql-db mongo-db
   ```

Data will persist in the `./docker-data/mysql` and `./docker-data/mongo` folders.