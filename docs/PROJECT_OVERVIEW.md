# Project Overview

---

## **Project Title: Intelligent Assignment Distribution System for Teachers**

---  

## **Objective:**  
  
Build a hybrid system that enables teachers to efficiently manage a coding question bank, create personalized assignments, and distribute them to students via email—automatically and pseudo-randomly. All data is stored in MongoDB collections (NoSQL).
  
---

## **Key Stakeholders:**  
  
* **Admin:** Sets up the system with teacher, course, and student records (all stored in MongoDB).
* **Teacher:** Manages questions and assignments, and initiates assignment distribution.
* **Student:** Does not interact with the system directly; receives personalized assignments via email.

---

## **Core Features:**

### 1. **Admin Panel:**

* Add, update, and delete:
  * Teachers (MongoDB: `teachers` collection)
  * Students (MongoDB: `students` collection)
  * Courses (MongoDB: `courses` collection)
* Assign students to courses (update `courses.students` array in MongoDB).
* Assign teachers to courses (update `courses.teacherId` in MongoDB).

---

### 2. **Teacher Panel:**

#### **A. Question Bank Management**

* CRUD operations on questions:

  * Each question includes:

    * Topic
    * Difficulty
    * Marks
    * Source info
    * MongoDB-stored question text (description, input/output format, constraints, sample I/O) in `questionTexts` collection

#### **B. Assignment Management**

* Create an assignment by specifying:

  * Topic
  * Total number of questions
  * Number per difficulty (e.g., 2 easy, 2 medium, 1 hard)
* For a given course, pseudo-randomly assign different sets of questions to each student based on:

  * `timestamp` (used as a seed for randomization)
  * Student's ObjectId
* Assignment metadata and per-student assignments are stored in the `assignments` collection in MongoDB.

---

### 3. **Distribution & Email**

* Generate assignment PDFs for each student (assignment data from MongoDB).
* Email assignments to students using their email addresses from the `students` collection.

---

## **Data Flow (MongoDB-centric):**

1. Admin creates teachers, students, and courses (all documents in MongoDB).
2. Teachers create questions and assignments (documents in `questionTexts` and `assignments`).
3. Assignments are distributed to students (linked by ObjectId in MongoDB).
4. All CRUD and lookup operations are performed on MongoDB collections.

---

## **Technical Architecture:**

### 🟩 **MongoDB (NoSQL)**

All data is now stored and managed using MongoDB.

* **teachers**:
  * `name`, `email`, `createdAt`, `updatedAt`
* **students**:
  * `rollNumber`, `name`, `email`, `createdAt`, `updatedAt`
* **questionTexts**:
  * `description`, `inputFormat`, `outputFormat`, `constraints`, `sampleInputs`, `sampleOutputs`
* **solutionTexts**:
  * `answerCode`, `explanation`
* **courses**:
  * `courseName`, `teacherId`, `students` (array of ObjectId), `assignments`
* **assignments**:
  * `name`, `description`, `dueDate`, `courseId`, `difficultyDistribution`, `totalMarks`, `students` (mapping), `createdAt`, `updatedAt`

---

## **Access Control Summary:**

| Role    | Access Description                                       |
| ------- | -------------------------------------------------------- |
| Admin   | CRUD for teachers, students, courses                     |
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
| **Student Identifier**                 | `rollNumber` (string) |                 |
| **Create Course**                      | ✅         | ❌               |
| **Assign Teacher to Course**           | ✅         | ❌               |
| **Assign Students to Course**          | ✅         | ❌               |
| **View Teachers / Students / Courses** | ✅         | ✅ (own courses) |
|                                        |           |                 |
| **Create Question**                    | ❌         | ✅               |
| **Read/View Questions**                | ❌         | ✅ (own)         |
| **Update Question**                    | ❌         | ✅ (own)         |
| **Delete Question**                    | ❌         | ✅ (own)         |
|                                        |           |                 |
| **Create Assignment**                  | ❌         | ✅ (own courses) |
| **Read/View Assignment**               | ✅ (all)   | ✅ (own)         |
| **Update Assignment**                  | ❌         | ✅ (own)         |
| **Delete Assignment**                  | ❌         | ✅ (own)         |

---

### 🔎 Notes

* **Students are passive**: Identified by `rollNumber` (string).
* **No login or interface** for students is required at this stage.
* **Future functionality** like submission or performance tracking can revisit student interactivity.
* **No need for a separate teacher login**: Each teacher is associated with their course(s) and can only manage their own questions and assignments.

---

## 🐳 Docker Compose Overview

### **Service Architecture**

The project uses Docker Compose to orchestrate multiple services, each encapsulated in its own container. The following table summarizes the services:

| **Service**  | **Container Name** | **Exposed Ports** | **Description**                                         | **Dependencies**                 | **Volumes**                                                                            |
| ------------ | ------------------ | ----------------- | ------------------------------------------------------- | -------------------------------- | -------------------------------------------------------------------------------------- |
| **Frontend** | `react-frontend`   | `8003:3000`       | React frontend, accessible via `http://localhost:8003`  | Depends on: **Backend**          | `./frontend:/app` (live reloading during development)                                  |
| **Backend**  | `spring-backend`   | `8002:8080`       | Java Spring Boot backend, accessible via `http://localhost:8002` | Depends on: **Mongo** | `./backend:/app`, `./docker-data/mongo:/data/db` |
| **MongoDB**  | `mongo-db`         | `8001:27017`      | MongoDB database, accessible via `localhost:8001` (host)| None                             | `./docker-data/mongo:/data/db` (persistent MongoDB data)                               |

## 🏁 Running Database Separately for Development

To run MongoDB in a separate container for development:

1. Make sure Docker and Docker Compose are installed.
2. In your project root, run:
   ```bash
   docker-compose up -d mongo-db
   ```
   This will start only the MongoDB container in the background.
3. MongoDB will be available at `localhost:8001`.
4. To stop it, run:
   ```bash
   docker-compose stop mongo-db
   ```

Data will persist in the `./docker-data/mongo` folder.