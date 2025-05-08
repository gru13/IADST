## 🟦 **MySQL Schema (Relational DB)**

```sql
-- TEACHERS
CREATE TABLE Teachers (
    teacherId INT AUTO_INCREMENT PRIMARY KEY,
    teacherName VARCHAR(255) NOT NULL,
    teacherEmail VARCHAR(255) UNIQUE NOT NULL
);

-- STUDENTS
CREATE TABLE Students (
    studentId INT AUTO_INCREMENT PRIMARY KEY,
    studentName VARCHAR(255) NOT NULL,
    studentEmail VARCHAR(255) UNIQUE NOT NULL
);

-- QUESTIONS (only metadata here)
CREATE TABLE Questions (
    questionId INT AUTO_INCREMENT PRIMARY KEY,
    mongoId VARCHAR(255) NOT NULL, -- references MongoDB questionTexts._id
    topicName VARCHAR(255),
    difficulty VARCHAR(255),
    marks INT,
    sourceName VARCHAR(255),
    sourceHref VARCHAR(255)
);

-- SOLUTIONS (metadata only)
CREATE TABLE Solutions (
    solutionId INT AUTO_INCREMENT PRIMARY KEY,
    questionId INT,
    teacherId INT,
    mongoId VARCHAR(255) NOT NULL, -- references MongoDB solutionTexts._id
    FOREIGN KEY (questionId) REFERENCES Questions(questionId) ON DELETE CASCADE,
    FOREIGN KEY (teacherId) REFERENCES Teachers(teacherId) ON DELETE CASCADE
);

-- CLASSES (in MongoDB now, but just for reference in case)
CREATE TABLE Classes (
    classId INT AUTO_INCREMENT PRIMARY KEY,
    teacherId INT,
    FOREIGN KEY (teacherId) REFERENCES Teachers(teacherId) ON DELETE CASCADE
);

-- ASSIGNMENTS (metadata only in MySQL)
CREATE TABLE Assignments (
    assignmentId INT AUTO_INCREMENT PRIMARY KEY,
    classId INT,
    dueDate TIMESTAMP,
    FOREIGN KEY (classId) REFERENCES Classes(classId) ON DELETE CASCADE
);
```

---

## 🟩 **MongoDB Collections**

### `classes` Collection

This collection stores class details, including references to students and assignments.

```json
{
  "_id": "ObjectId('...')",
  "className": "Class 101",
  "teacherId": "teacherId_value",  // Reference to the teacher (could be an ObjectId from Teachers collection)
  "students": [
    "studentId1",
    "studentId2",
    "studentId3"
  ],
  "assignments": [
    "assignmentId1",
    "assignmentId2"
  ]
}
```

### `assignments` Collection

Stores assignment metadata and references the questions related to the assignment.

```json
{
  "_id": "ObjectId('...')",
  "assignmentId": "123456",  // Optional field for tracking purposes
  "teacherId": "teacherId123",  // Teacher who created the assignment
  "classId": "classId456",  // Class associated with the assignment
  "topic": "Mathematics",  // The subject or topic of the assignment
  "dueDate": "2025-05-30T23:59:59Z",  // Due date for the assignment
  "difficultyDistribution": {  // The difficulty level breakdown
    "easy": 5,
    "medium": 3,
    "hard": 2
  },
  "totalMarks": 50,  // Total marks for the assignment
  "questionIds": [  // List of question IDs selected for the assignment
    "ObjectId('...')", 
    "ObjectId('...')", 
    "ObjectId('...')"
  ],
  "students": [  // List of students assigned this assignment
    {
      "studentId": "studentId789",
      "assignedQuestions": [
        "ObjectId('...')",  // Question 1 (Easy)
        "ObjectId('...')",  // Question 2 (Medium)
        "ObjectId('...')"   // Question 3 (Hard)
      ]
    },
    {
      "studentId": "studentId890",
      "assignedQuestions": [
        "ObjectId('...')",  // Question 1 (Medium)
        "ObjectId('...')",  // Question 2 (Hard)
        "ObjectId('...')"   // Question 3 (Easy)
      ]
    }
  ]
}

```

#### Explanation of Fields:

1. **\_id**: MongoDB's automatically generated unique identifier for each document in the collection.
2. **assignmentId**: A unique ID for the assignment (optional but useful for tracking).
3. **teacherId**: The ID of the teacher who created the assignment.
4. **classId**: The ID of the class associated with the assignment.
5. **topic**: The subject or topic for the assignment (e.g., Mathematics, Programming, etc.).
6. **dueDate**: The date and time by which the assignment is due, in ISO 8601 format.
7. **difficultyDistribution**: A breakdown of the number of easy, medium, and hard questions in the assignment. This is used to define how many questions of each difficulty level should be assigned.
8. **totalMarks**: The total marks for the assignment, calculated based on the difficulty levels of the questions (easy = 5, medium = 10, hard = 20).
9. **questionIds**: A list of IDs referring to questions selected for this assignment.
10. **students**: A list of students who have been assigned this assignment, with each student having:

    * **studentId**: The ID of the student.
    * **assignedQuestions**: A list of question IDs that have been assigned to the student.

##### Example of Marks Calculation:

* **Easy Question**: 5 marks
* **Medium Question**: 10 marks
* **Hard Question**: 20 marks

For each student:

* If a student has 1 easy, 1 medium, and 1 hard question, their total marks will be:
  `(1 * 5) + (1 * 10) + (1 * 20) = 35 marks`

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
  "answerCode": "def add(a, b): return a + b",
  "explanation": "This Python function adds two integers."
}
```

---

### ✅ **Final Summary**

| **Component**                               | **Stored In** |
| ------------------------------------------- | ------------- |
| Users (teachers, students), assignments     | MySQL         |
| Class-student mapping                       | MongoDB       |
| Assignment metadata and question details    | MongoDB       |
| Question content (description, constraints) | MongoDB       |
| Solution metadata (who answered what)       | MySQL         |
| Solution content (code, explanation)        | MongoDB       |



