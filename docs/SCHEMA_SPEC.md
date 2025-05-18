# Specifications of Schemas

Here’s your **complete, corrected, and optimized schema** for both **MySQL (Relational DB)** and **MongoDB (NoSQL)**.

---

## 🟦 MySQL Schema (Relational DB)

```sql
-- TEACHERS
CREATE TABLE Teachers (
    teacherId    INT              AUTO_INCREMENT PRIMARY KEY,
    teacherName  VARCHAR(255)     NOT NULL,
    teacherEmail VARCHAR(255)     UNIQUE NOT NULL
);

-- STUDENTS
CREATE TABLE Students (
    studentRoll  VARCHAR(100)     PRIMARY KEY,
    studentName  VARCHAR(255)     NOT NULL,
    studentEmail VARCHAR(255)     UNIQUE NOT NULL
);

-- QUESTIONS (metadata only)
CREATE TABLE Questions (
    questionId   INT              AUTO_INCREMENT PRIMARY KEY,
    mongoId      VARCHAR(24)      NOT NULL UNIQUE,
    topicName    VARCHAR(255),
    difficulty   ENUM('easy','medium','hard'),
    marks        INT              NOT NULL DEFAULT 0,
    sourceName   TEXT,
    sourceHref   TEXT,
    teacherId    INT              NOT NULL,
    INDEX (mongoId),
    INDEX (topicName),
    INDEX (difficulty),
    FOREIGN KEY (teacherId) REFERENCES Teachers(teacherId) ON DELETE CASCADE
);

-- SOLUTIONS (metadata only)
CREATE TABLE Solutions (
    solutionId   INT              AUTO_INCREMENT PRIMARY KEY,
    questionId   INT              NOT NULL,
    teacherId    INT              NOT NULL,
    mongoId      VARCHAR(24)      NOT NULL UNIQUE,
    INDEX (mongoId),
    FOREIGN KEY (questionId) REFERENCES Questions(questionId) ON DELETE CASCADE,
    FOREIGN KEY (teacherId)  REFERENCES Teachers(teacherId) ON DELETE CASCADE
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
---

## 🟩 MongoDB Collections (Document Models)

### **courses**

```json
{
  "_id": ObjectId("..."),
  "courseName": "Course 101",
  "teacherId": 1212,  // MySQL: Teachers.teacherId
  "students": ["studentRoll1", "studentRoll2", "studentRoll3"],  // MySQL: Students.studentRoll
  "assignments": [ObjectId("..."), ObjectId("...")],
  "createdAt": ISODate("2025-02-01T00:00:00Z"),
  "updatedAt": ISODate("2025-02-01T00:00:00Z")
}
```

### **assignments**

```json
{
  "_id": ObjectId("..."),
  "name": "Assignment 1",
  "description": "This is Assignment 1",
  "dueDate": ISODate("2025-02-13T00:00:00Z"),
  "courseId": ObjectId("..."),
  "difficultyDistribution": {
    "easy": 5,
    "medium": 3,
    "hard": 2
  },
  "totalMarks": 50,
  "students": [
    {
      "studentRoll": "studentRoll789",
      "assignedQuestions": [
        ObjectId("..."), ObjectId("..."), ObjectId("...")
      ]
    },
    {
      "studentRoll": "studentRoll890",
      "assignedQuestions": [
        ObjectId("..."), ObjectId("..."), ObjectId("...")
      ]
    }
  ],
  "createdAt": ISODate("2025-02-01T00:00:00Z"),
  "updatedAt": ISODate("2025-02-01T00:00:00Z")
}
```

### **questionTexts**

```json
{
  "_id": ObjectId("..."),
  "description": "Add two numbers",
  "inputFormat": "Two integers A and B",
  "outputFormat": "Single integer sum",
  "constraints": ["1 <= A, B <= 1000"],
  "sampleInputs": ["1 2"],
  "sampleOutputs": ["3"],
  "version": 1,
  "createdAt": ISODate("2025-02-01T00:00:00Z"),
  "updatedAt": ISODate("2025-02-01T00:00:00Z")
}
```

### **solutionTexts**

```json
{
  "_id": ObjectId("..."),
  "questionId": ObjectId("..."),  // Ref: questionTexts._id
  "answerCode": "def add(a, b): return a + b",
  "explanation": "This Python function adds two integers.",
  "createdAt": ISODate("2025-02-01T00:00:00Z"),
  "updatedAt": ISODate("2025-02-01T00:00:00Z")
}
```