# Specifications of Schemas

## 🟦 **MySQL Schema (Relational DB)**

<!--
MySQL is used for user management and metadata. It stores teachers, students, and metadata for questions, solutions, courses, and assignments. The 'mongoId' fields in MySQL tables reference the corresponding documents in MongoDB collections, enabling a hybrid relational-document data model.

ID Mapping:
- Teachers: 'teacherId' (MySQL) is referenced in MongoDB 'courses.teacherId'.
- Students: 'studentRoll' (MySQL) should match 'studentRoll' in MongoDB collections.
- Questions/Solutions/Courses/Assignments: 'mongoId' fields in MySQL reference the '_id' of MongoDB documents.

Foreign keys in MySQL ensure referential integrity for relational data.
-->

```sql
-- TEACHERS
CREATE TABLE Teachers (
    teacherId    INT              AUTO_INCREMENT PRIMARY KEY,
    teacherName  VARCHAR(255)     NOT NULL,
    teacherEmail VARCHAR(255)     UNIQUE NOT NULL,
    createdAt    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- STUDENTS
CREATE TABLE Students (
    studentRoll  VARCHAR(100)     PRIMARY KEY,
    studentName  VARCHAR(255)     NOT NULL,
    studentEmail VARCHAR(255)     UNIQUE NOT NULL,
    createdAt    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- QUESTIONS (metadata only)
CREATE TABLE Questions (
    questionId   INT              AUTO_INCREMENT PRIMARY KEY,
    mongoId      CHAR(24)         NOT NULL,
    topicName    VARCHAR(255),
    difficulty   ENUM('easy','medium','hard'),
    marks        INT              NOT NULL DEFAULT 0,
    sourceName   VARCHAR(255),
    sourceHref   VARCHAR(255),
    createdAt    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX (mongoId),
    INDEX (topicName),
    INDEX (difficulty)
);

-- SOLUTIONS (metadata only)
CREATE TABLE Solutions (
    solutionId   INT              AUTO_INCREMENT PRIMARY KEY,
    questionId   INT              NOT NULL,
    teacherId    INT              NOT NULL,
    mongoId      CHAR(24)         NOT NULL,
    createdAt    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (questionId) REFERENCES Questions(questionId) ON DELETE CASCADE,
    FOREIGN KEY (teacherId)  REFERENCES Teachers(teacherId) ON DELETE CASCADE,
    INDEX (mongoId)
);

-- COURSES
CREATE TABLE Courses (
    courseId     INT              AUTO_INCREMENT PRIMARY KEY,
    courseName   VARCHAR(255)     NOT NULL,
    teacherId    INT              NOT NULL,
    mongoId      CHAR(24)         NOT NULL,
    createdAt    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (teacherId) REFERENCES Teachers(teacherId) ON DELETE CASCADE,
    INDEX (courseId),
    INDEX (mongoId)
);

-- ASSIGNMENTS (metadata only)
CREATE TABLE Assignments (
    assignmentId   INT            AUTO_INCREMENT PRIMARY KEY,
    assignmentName VARCHAR(255)   NOT NULL,
    assignmentDesc TEXT            NOT NULL,
    courseId       INT            NOT NULL,
    dueDate        DATETIME       NULL,
    mongoId        CHAR(24)       NOT NULL,
    createdAt      TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt      TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (courseId) REFERENCES Courses(courseId) ON DELETE CASCADE,
    INDEX (dueDate),
    INDEX (mongoId)
);

-- STUDENT–ASSIGNMENT MAPPINGS
CREATE TABLE AssignmentSubmissions (
    submissionId    INT            AUTO_INCREMENT PRIMARY KEY,
    assignmentId    INT            NOT NULL,
    studentRoll     VARCHAR(100)   NOT NULL,
    questionMongoId CHAR(24)       NOT NULL,
    createdAt       TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    INDEX (assignmentId),
    INDEX (studentRoll),
    FOREIGN KEY (assignmentId) REFERENCES Assignments(assignmentId) ON DELETE CASCADE,
    FOREIGN KEY (studentRoll)    REFERENCES Students(studentRoll) ON DELETE CASCADE
);
```

---

## 🟩 **MongoDB Collections**

<!--
MongoDB is used for storing document-based data, such as course-student mappings, assignment details, question and solution content. The '_id' fields in MongoDB are referenced by 'mongoId' fields in MySQL for cross-database connectivity.

- 'courses' collection: Stores course details, including teacher, students, and assignments.
- 'assignments' collection: Stores assignment metadata, difficulty distribution, and per-student assigned questions.
- 'questionTexts' collection: Stores the actual question content.
- 'solutionTexts' collection: Stores the actual solution content.

Data Flow:
- Teachers create courses (MySQL), which are detailed in MongoDB ('courses').
- Courses have assignments (MySQL metadata, MongoDB for details and mapping).
- Assignments are linked to students and questions in MongoDB.
- Questions and solutions have metadata in MySQL, but their content is in MongoDB.
- Students are assigned assignments and questions via MongoDB collections.
-->

### `courses` Collection

This collection stores course details, including references to students and assignments.

```json
{
  "_id": "ObjectId('...')",
  "courseName": "Course 101",
  "teacherId": "1212", // Reference to the teacher (could be an ObjectId from Teachers collection)
  "students": ["studentRoll1", "studentRoll2", "studentRoll3"],
  "assignments": ["ObjectId('...')", "ObjectId('...')"]
}
```

### `assignments` Collection

Stores assignment metadata and references the questions related to the assignment.

```json
{
  "_id": "ObjectId('...')",
  "difficultyDistribution": {
    // The difficulty level breakdown
    "easy": 5,
    "medium": 3,
    "hard": 2
  },
  "totalMarks": 50, // Total marks for the assignment
  "students": [
    // List of students assigned this assignment
    {
      "studentRoll": "studentRoll789",
      "assignedQuestions": [
        "ObjectId('...')", // Question 1 (Easy)
        "ObjectId('...')", // Question 2 (Medium)
        "ObjectId('...')" // Question 3 (Hard)
      ]
    },
    {
      "studentRoll": "studentRoll890",
      "assignedQuestions": [
        "ObjectId('...')", // Question 1 (Medium)
        "ObjectId('...')", // Question 2 (Hard)
        "ObjectId('...')" // Question 3 (Easy)
      ]
    }
  ]
}
```

---

### `questionTexts` Collection

Stores question details like description, input/output format, and constraints.

```json
{
  "_id": "ObjectId('...')",
  "description": "Add two numbers",
  "inputFormat": "Two integers A and B",
  "outputFormat": "Single integer sum",
  "constraints": ["1 <= A, B <= 1000"],
  "sampleInputs": ["1 2"],
  "sampleOutputs": ["3"]
}
```

### `solutionTexts` Collection

Stores solution details like code and explanation for the questions.

```json
{
  "_id": "ObjectId('...')",
  "questionId": "5a1d4d4",
  "answerCode": "def add(a, b): return a + b",
  "explanation": "This Python function adds two integers."
}
```
