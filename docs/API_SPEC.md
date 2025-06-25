# Endpoints specifications

### ✅ 1. **Authentication APIs**

| Endpoint            | Controller Class         | Service Class         | Description                              |
| ------------------- | ----------------------- | --------------------- | ---------------------------------------- |
| `POST /auth/login`  | `AuthController.java`   | `AuthService.java`    | Authenticates a user and returns tokens. |
| `POST /auth/logout` | `AuthController.java`   | —                     | Logs out a user and invalidates tokens.  |

---

### ✅ 2. **Admin APIs**

#### 👤 Teacher Management

| Endpoint                      | Controller Class         | Service Class         | Description                            |
| ----------------------------- | ----------------------- | --------------------- | -------------------------------------- |
| `POST /admin/teachers`        | `AdminController.java`   | —                     | Add a new teacher to the system (MongoDB: `teachers`).       |
| `GET /admin/teachers`         | `AdminController.java`   | —                     | Retrieve a list of all teachers (MongoDB: `teachers`).       |
| `PUT /admin/teachers/{id}`    | `AdminController.java`   | —                     | Update details of an existing teacher (MongoDB: `teachers`). |
| `DELETE /admin/teachers/{id}` | `AdminController.java`   | —                     | Remove a teacher from the system (MongoDB: `teachers`).      |

#### 👨‍🎓 Student Management

| Endpoint                      | Controller Class         | Service Class         | Description                            |
| ----------------------------- | ----------------------- | --------------------- | -------------------------------------- |
| `POST /admin/students`        | `AdminController.java`   | —                     | Add a new student (MongoDB: `students`).                     |
| `GET /admin/students`         | `AdminController.java`   | —                     | Retrieve a list of all students (MongoDB: `students`).       |
| `PUT /admin/students/{id}`    | `AdminController.java`   | —                     | Update details of an existing student (MongoDB: `students`). |
| `DELETE /admin/students/{id}` | `AdminController.java`   | —                     | Remove a student from the system (MongoDB: `students`).      |

#### 📚 Course Management

| Endpoint                                          | Controller Class         | Service Class         | Description                               |
| ------------------------------------------------- | ----------------------- | --------------------- | ----------------------------------------- |
| `POST /admin/courses`                             | `AdminController.java`   | —                     | Create a new course (MongoDB: `courses`).                      |
| `GET /admin/courses`                              | `AdminController.java`   | —                     | Retrieve a list of all courses (MongoDB: `courses`).           |
| `PUT /admin/courses/{id}`                         | `AdminController.java`   | —                     | Update course details (MongoDB: `courses`).                    |
| `DELETE /admin/courses/{id}`                      | `AdminController.java`   | —                     | Delete a course (MongoDB: `courses`).                          |
| `POST /admin/courses/{id}/students`               | `AdminController.java`   | —                     | Assign students to a course (update `courses.students` array in MongoDB).              |
| `GET /admin/courses/{id}/students`                | `AdminController.java`   | —                     | Get list of students assigned to a course (MongoDB: `courses`).|
| `DELETE /admin/courses/{id}/students/{studentId}` | `AdminController.java`   | —                     | Remove a specific student from a course (MongoDB: `courses`).  |

---

### ✅ 3. **Teacher APIs**

#### 📝 Question Bank

| Endpoint                         | Controller Class           | Service Class           | Description                                        |
| -------------------------------- | ------------------------- | ----------------------- | -------------------------------------------------- |
| `POST /teacher/questions`        | `TeacherController.java`   | `QuestionService.java`  | Add a new question to the question bank (MongoDB: `questionTexts`).           |
| `GET /teacher/questions`         | `TeacherController.java`   | `QuestionService.java`  | Retrieve all questions created by the teacher (MongoDB: `questionTexts`).     |
| `GET /teacher/questions/{id}`    | `TeacherController.java`   | `QuestionService.java`  | Fetch a specific question by ID (MongoDB: `questionTexts`).                   |
| `PUT /teacher/questions/{id}`    | `TeacherController.java`   | `QuestionService.java`  | Update the content or metadata of a question (MongoDB: `questionTexts`).      |
| `DELETE /teacher/questions/{id}` | `TeacherController.java`   | `QuestionService.java`  | Delete a specific question from the question bank (MongoDB: `questionTexts`). |

#### 💡 Solution

| Endpoint                                | Controller Class           | Service Class         | Description                                    |
| --------------------------------------- | ------------------------- | --------------------- | ---------------------------------------------- |
| `POST /teacher/solutions`               | `TeacherController.java`   | —                     | Add a new solution (MongoDB: `solutionTexts`). |
| `GET /teacher/solutions/{questionId}`   | `TeacherController.java`   | —                     | Get solution for a question (MongoDB: `solutionTexts`). |

#### 📑 Assignment Management

| Endpoint                                 | Controller Class           | Service Class             | Description                                                      |
| ---------------------------------------- | ------------------------- | ------------------------- | ---------------------------------------------------------------- |
| `POST /teacher/assignments`              | `TeacherController.java`   | `AssignmentService.java`  | Create a new assignment (MongoDB: `assignments`).                |
| `GET /teacher/assignments`               | `TeacherController.java`   | `AssignmentService.java`  | List all assignments (MongoDB: `assignments`).                   |
| `GET /teacher/assignments/{id}`          | `TeacherController.java`   | `AssignmentService.java`  | Get assignment details (MongoDB: `assignments`).                 |
| `PUT /teacher/assignments/{id}`          | `TeacherController.java`   | `AssignmentService.java`  | Update assignment details (MongoDB: `assignments`).              |
| `DELETE /teacher/assignments/{id}`       | `TeacherController.java`   | `AssignmentService.java`  | Delete an assignment (MongoDB: `assignments`).                   |

---

All endpoints operate on MongoDB collections. All IDs are MongoDB ObjectIds. No SQL/relational tables are used anywhere in the API or backend. All backend logic is implemented in Java Spring Boot.