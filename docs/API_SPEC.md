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
| `POST /admin/teachers/`        | `AdminTeacherController.java`   | —                     | Add a new teacher to the system (MongoDB: `teachers`).       |
| `GET /admin/teachers/all`         | `AdminTeacherController.java`   | —                     | Retrieve a list of all teachers (MongoDB: `teachers`).       |
| `GET /admin/teachers/{id}`    | `AdminTeacherController.java`   | —                     | Retrieve a teacher by ID (MongoDB: `teachers`).              |
| `PUT /admin/teachers/{id}`    | `AdminTeacherController.java`   | —                     | Update details of an existing teacher (MongoDB: `teachers`). |
| `DELETE /admin/teachers/{id}` | `AdminTeacherController.java`   | —                     | Remove a teacher from the system (MongoDB: `teachers`).      |

#### 👨‍🎓 Student Management

| Endpoint                      | Controller Class         | Service Class         | Description                            |
| ----------------------------- | ----------------------- | --------------------- | -------------------------------------- |
| `POST /admin/students/`        | `AdminStudentController.java`   | —                     | Add a new student (MongoDB: `students`).                     |
| `GET /admin/students/all`         | `AdminStudentController.java`   | —                     | Retrieve a list of all students (MongoDB: `students`).       |
| `GET /admin/students/{id}`    | `AdminStudentController.java`   | —                     | Retrieve a student by ID (MongoDB: `students`).              |
| `PUT /admin/students/{id}`    | `AdminStudentController.java`   | —                     | Update details of an existing student (MongoDB: `students`). |
| `DELETE /admin/students/{id}` | `AdminStudentController.java`   | —                     | Remove a student from the system (MongoDB: `students`).      |

#### 📚 Course Management

| Endpoint                                          | Controller Class         | Service Class         | Description                               |
| ------------------------------------------------- | ----------------------- | --------------------- | ----------------------------------------- |
| `POST /admin/course/`                             | `AdminCourseController.java`   | —                     | Create a new course (MongoDB: `courses`).                      |
| `GET /admin/course/all`                              | `AdminCourseController.java`   | —                     | Retrieve a list of all courses (MongoDB: `courses`).           |
| `GET /admin/course/{id}`                         | `AdminCourseController.java`   | —                     | Retrieve a course by ID (MongoDB: `courses`).                  |
| `PUT /admin/course/{id}`                         | `AdminCourseController.java`   | —                     | Update course details (MongoDB: `courses`).                    |
| `DELETE /admin/course/{id}`                      | `AdminCourseController.java`   | —                     | Delete a course (MongoDB: `courses`).                          |
| `POST /admin/course/{id}/students/{studentId}`               | `AdminCourseController.java`   | —                     | Assign students to a course (update `courses.students` array in MongoDB).              |
| `GET /admin/course/{id}/courses`                | `AdminCourseController.java`   | —                     | Get list of students assigned to a course (MongoDB: `courses`).|
| `DELETE /admin/course/{id}/students/{studentId}` | `AdminCourseController.java`   | —                     | Remove a specific student from a course (MongoDB: `courses`).  |

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

| Endpoint                                  | Controller Class           | Service Class         | Description                                              |
| ----------------------------------------- | ------------------------- | --------------------- | -------------------------------------------------------- |
| `POST /teacher/solutions`                 | `TeacherController.java`   | —                     | Add a new solution (MongoDB: `solutionTexts`).           |
| `GET /teacher/solutions/{questionId}`     | `TeacherController.java`   | —                     | Get solution for a question (MongoDB: `solutionTexts`).  |
| `PUT /teacher/solutions/{solutionId}`     | `TeacherController.java`   | —                     | Update a solution by ID (MongoDB: `solutionTexts`).      |
| `DELETE /teacher/solutions/{solutionId}`  | `TeacherController.java`   | —                     | Delete a solution by ID (MongoDB: `solutionTexts`).      |

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