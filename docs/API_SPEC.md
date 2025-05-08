## ✅ 1. **Authentication APIs**

(For login and session management)

* `POST /auth/login` – Login for teacher/admin
* `POST /auth/logout` – Logout (if session-based)

---

## ✅ 2. **Admin APIs**

(Used only by the admin via admin panel)

### 👤 **Teacher Management**

* `POST /admin/teachers` – Create teacher
* `GET /admin/teachers` – List all teachers
* `PUT /admin/teachers/{teacherId}` – Update teacher
* `DELETE /admin/teachers/{teacherId}` – Delete teacher

### 👨‍🎓 **Student Management**

* `POST /admin/students` – Create student
* `GET /admin/students` – List all students
* `PUT /admin/students/{studentId}` – Update student
* `DELETE /admin/students/{studentId}` – Delete student

### 📚 **Class Management**

* `POST /admin/classes` – Create class
* `GET /admin/classes` – List all classes
* `PUT /admin/classes/{classId}` – Update class (e.g., assign teacher)
* `DELETE /admin/classes/{classId}` – Delete class

### 👥 **Assign Students to Class**

* `POST /admin/classes/{classId}/students` – Assign students to a class (accepts list of studentIds)
* `GET /admin/classes/{classId}/students` – List assigned students in a class
* `DELETE /admin/classes/{classId}/students/{studentId}` – Remove a student from class

---

## ✅ 3. **Teacher APIs**

(Used by teachers for CRUD on questions and assignments)

### 📝 **Question Bank Management**

* `POST /teacher/questions` – Create a question (metadata in MySQL, body in MongoDB)
* `GET /teacher/questions` – List all questions (filter by topic, difficulty, etc.)
* `GET /teacher/questions/{questionId}` – Get full question (metadata + body)
* `PUT /teacher/questions/{questionId}` – Update question details
* `DELETE /teacher/questions/{questionId}` – Delete a question

### 💡 **Solution Management**

* `POST /teacher/questions/{questionId}/solution` – Add or update a solution for a question
* `GET /teacher/questions/{questionId}/solution` – Get solution for a question

---

## ✅ 4. **Assignment APIs**

(Used by teachers for assignment management)

### 📄 **Assignment CRUD**

* `POST /teacher/assignments` – Create an assignment (with topic, difficulty distribution, and number of questions)
* `GET /teacher/assignments` – List all assignments
* `GET /teacher/assignments/{assignmentId}` – Get metadata of a specific assignment
* `DELETE /teacher/assignments/{assignmentId}` – Delete an assignment

### 🎲 **Random Question Generation**

* `POST /teacher/assignments/{assignmentId}/generate` – Pseudo-randomly assign questions for each student using classId, studentId, teacherId, etc.
* `GET /teacher/assignments/{assignmentId}/preview` – Show preview table with students and their questions
* `POST /teacher/assignments/{assignmentId}/regenerate` – Regenerate question mapping for the assignment

### 📨 **PDF Generation & Email Sending**

* `POST /teacher/assignments/{assignmentId}/generate-pdfs` – Generate PDF assignments for each student
* `POST /teacher/assignments/{assignmentId}/send-emails` – Send generated assignment PDFs to students via email using a faculty email template

---

## ✅ 5. **Utility APIs**

### 📚 **Source Name Retrieval**

* `GET /sources` – List all available source names for questions.

### 📝 **Topic Retrieval**

* `GET /topics` – List of available topics for questions.

### ⚖️ **Difficulty Retrieval**

* `GET /difficulties` – Enum list of difficulties (easy, medium, hard)

### 👨‍🏫 **Teacher Classes**

* `GET /teacher/{teacherId}/classes` – List classes owned by a teacher.

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