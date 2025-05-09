# Endpoints specifications

### ✅ 1. **Authentication APIs**

| Endpoint            | Router File       | Controller                 | Service           | Description                              |
| ------------------- | ----------------- | -------------------------- | ----------------- | ---------------------------------------- |
| `POST /auth/login`  | `routers/auth.py` | `auth_controller.login()`  | `auth_service.py` | Authenticates a user and returns tokens. |
| `POST /auth/logout` | `routers/auth.py` | `auth_controller.logout()` | —                 | Logs out a user and invalidates tokens.  |

---

### ✅ 2. **Admin APIs**

#### 👤 Teacher Management

| Endpoint                      | Router File        | Controller Function                 | Service Layer | Description                            |
| ----------------------------- | ------------------ | ----------------------------------- | ------------- | -------------------------------------- |
| `POST /admin/teachers`        | `routers/admin.py` | `admin_controller.create_teacher()` | —             | Add a new teacher to the system.       |
| `GET /admin/teachers`         | `routers/admin.py` | `admin_controller.get_teachers()`   | —             | Retrieve a list of all teachers.       |
| `PUT /admin/teachers/{id}`    | `routers/admin.py` | `admin_controller.update_teacher()` | —             | Update details of an existing teacher. |
| `DELETE /admin/teachers/{id}` | `routers/admin.py` | `admin_controller.delete_teacher()` | —             | Remove a teacher from the system.      |

#### 👨‍🎓 Student Management


| Endpoint                      | Router File        | Controller Function                 | Service Layer | Description                            |
| ----------------------------- | ------------------ | ----------------------------------- | ------------- | -------------------------------------- |
| `POST /admin/students`        | `routers/admin.py` | `admin_controller.create_student()` | —             | Add a new student.                     |
| `GET /admin/students`         | `routers/admin.py` | `admin_controller.get_students()`   | —             | Retrieve a list of all students.       |
| `PUT /admin/students/{id}`    | `routers/admin.py` | `admin_controller.update_student()` | —             | Update details of an existing student. |
| `DELETE /admin/students/{id}` | `routers/admin.py` | `admin_controller.delete_student()` | —             | Remove a student from the system.      |

#### 📚 Class Management

| Endpoint                                          | Router File        | Controller Function                     | Service Layer | Description                               |
| ------------------------------------------------- | ------------------ | --------------------------------------- | ------------- | ----------------------------------------- |
| `POST /admin/classes`                             | `routers/admin.py` | `admin_controller.create_class()`       | —             | Create a new class.                       |
| `GET /admin/classes`                              | `routers/admin.py` | `admin_controller.get_classes()`        | —             | Retrieve a list of all classes.           |
| `PUT /admin/classes/{id}`                         | `routers/admin.py` | `admin_controller.update_class()`       | —             | Update class details.                     |
| `DELETE /admin/classes/{id}`                      | `routers/admin.py` | `admin_controller.delete_class()`       | —             | Delete a class.                           |
| `POST /admin/classes/{id}/students`               | `routers/admin.py` | `admin_controller.assign_students()`    | —             | Assign students to a class.               |
| `GET /admin/classes/{id}/students`                | `routers/admin.py` | `admin_controller.get_class_students()` | —             | Get list of students assigned to a class. |
| `DELETE /admin/classes/{id}/students/{studentId}` | `routers/admin.py` | `admin_controller.remove_student()`     | —             | Remove a specific student from a class.   |

---

### ✅ 3. **Teacher APIs**

#### 📝 Question Bank

| Endpoint                         | Router File          | Controller                             | Service               | Description                                        |
| -------------------------------- | -------------------- | -------------------------------------- | --------------------- | -------------------------------------------------- |
| `POST /teacher/questions`        | `routers/teacher.py` | `teacher_controller.create_question()` | `question_service.py` | Add a new question to the question bank.           |
| `GET /teacher/questions`         | `routers/teacher.py` | `teacher_controller.list_questions()`  | `question_service.py` | Retrieve all questions created by the teacher.     |
| `GET /teacher/questions/{id}`    | `routers/teacher.py` | `teacher_controller.get_question()`    | `question_service.py` | Fetch a specific question by ID.                   |
| `PUT /teacher/questions/{id}`    | `routers/teacher.py` | `teacher_controller.update_question()` | `question_service.py` | Update the content or metadata of a question.      |
| `DELETE /teacher/questions/{id}` | `routers/teacher.py` | `teacher_controller.delete_question()` | `question_service.py` | Delete a specific question from the question bank. |

#### 💡 Solution

| Endpoint                                | Router File          | Controller Function                           | Service Layer | Description                                    |
| --------------------------------------- | -------------------- | --------------------------------------------- | ------------- | ---------------------------------------------- |
| `POST /teacher/questions/{id}/solution` | `routers/teacher.py` | `teacher_controller.add_or_update_solution()` | —             | Add or update the solution for a question.     |
| `GET /teacher/questions/{id}/solution`  | `routers/teacher.py` | `teacher_controller.get_solution()`           | —             | Retrieve the solution for a specific question. |

---

### ✅ 4. **Assignment APIs**

| Endpoint                                       | Router File          | Controller                               | Service                 | Description                                                       |
| ---------------------------------------------- | -------------------- | ---------------------------------------- | ----------------------- | ----------------------------------------------------------------- |
| `POST /teacher/assignments`                    | `routers/teacher.py` | `teacher_controller.create_assignment()` | `assignment_service.py` | Create a new assignment by selecting questions and class targets. |
| `GET /teacher/assignments`                     | `routers/teacher.py` | `teacher_controller.list_assignments()`  | `assignment_service.py` | Get a list of all created assignments.                            |
| `GET /teacher/assignments/{id}`                | `routers/teacher.py` | `teacher_controller.get_assignment()`    | —                       | Retrieve details of a specific assignment.                        |
| `DELETE /teacher/assignments/{id}`             | `routers/teacher.py` | `teacher_controller.delete_assignment()` | —                       | Delete a specific assignment.                                     |
| `POST /teacher/assignments/{id}/generate`      | `routers/teacher.py` | `teacher_controller.generate()`          | `assignment_service.py` | Generate student-wise question mappings for the assignment.       |
| `GET /teacher/assignments/{id}/preview`        | `routers/teacher.py` | `teacher_controller.preview()`           | `assignment_service.py` | Preview assignment data including mapping before finalizing.      |
| `POST /teacher/assignments/{id}/regenerate`    | `routers/teacher.py` | `teacher_controller.regenerate()`        | `assignment_service.py` | Re-generate assignment mappings if changes are needed.            |
| `POST /teacher/assignments/{id}/generate-pdfs` | `routers/teacher.py` | `teacher_controller.generate_pdfs()`     | `email_pdf_service.py`  | Generate PDFs of assignments per student.                         |
| `POST /teacher/assignments/{id}/send-emails`   | `routers/teacher.py` | `teacher_controller.send_emails()`       | `email_pdf_service.py`  | Email the assignment PDFs to students automatically.              |

---

### ✅ 5. **Utility APIs**

| Endpoint                    | Router File          | Controller Function                     | Service Layer | Description                                                |
| --------------------------- | -------------------- | --------------------------------------- | ------------- | ---------------------------------------------------------- |
| `GET /sources`              | `routers/teacher.py` | `teacher_controller.get_sources()`      | —             | Get list of sources  (leetcode, hackerrank, codechef).     |
| `GET /topics`               | `routers/teacher.py` | `teacher_controller.get_topics()`       | —             | Get list of predefined topics to tag questions.            |
| `GET /difficulties`         | `routers/teacher.py` | `teacher_controller.get_difficulties()` | —             | Get supported difficulty levels (easy, medium, hard).      |
| `GET /teacher/{id}/classes` | `routers/teacher.py` | `teacher_controller.get_my_classes()`   | —             | Get classes assigned to the logged-in teacher.             |

---

### 📌 **Summary**

| **Module**                              | **Endpoint**                                                                                                                                    |
| --------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------- |
| **Authentication**                      | `/auth/login`, `/auth/logout`                                                                                                                   |
| **Admin: Teacher Management**           | `/admin/teachers`, `/admin/teachers/{teacherId}`                                                                                                |
| **Admin: Student Management**           | `/admin/students`, `/admin/students/{studentId}`                                                                                                |
| **Admin: Class Management**             | `/admin/classes`, `/admin/classes/{classId}`                                                                                                    |
| **Admin: Assign Students to Class**     | `/admin/classes/{classId}/students`, `/admin/classes/{classId}/students/{studentId}`                                                            |
| **Teacher: Question Bank Management**   | `/teacher/questions`, `/teacher/questions/{questionId}`                                                                                         |
| **Teacher: Solution Management**        | `/teacher/questions/{questionId}/solution`                                                                                                      |
| **Teacher: Assignment CRUD**            | `/teacher/assignments`, `/teacher/assignments/{assignmentId}`                                                                                   |
| **Teacher: Random Question Generation** | `/teacher/assignments/{assignmentId}/generate`, `/teacher/assignments/{assignmentId}/preview`, `/teacher/assignments/{assignmentId}/regenerate` |
| **Teacher: PDF & Email Management**     | `/teacher/assignments/{assignmentId}/generate-pdfs`, `/teacher/assignments/{assignmentId}/send-emails`                                          |
| **Utility: Source Name Retrieval**      | `/sources`                                                                                                                                      |
| **Utility: Topic Retrieval**            | `/topics`                                                                                                                                       |
| **Utility: Difficulty Retrieval**       | `/difficulties`                                                                                                                                 |
| **Utility: Teacher Classes**            | `/teacher/{teacherId}/classes`                                                                                                                  |

---