# Specifications of Schemas

Here’s your **complete, corrected, and optimized schema** for **MongoDB (NoSQL)** only.

---

## 🟩 **MongoDB Collections**

MongoDB is used for storing all data, including course-student mappings, assignment details, question and solution content. All backend logic is implemented in Java Spring Boot.

- `teachers` collection: Stores teacher details. (Created by Admin)
- `students` collection: Stores student details. (Created by Admin)
- `courses` collection: Stores course details, including teacher, students, and assignments. (Created by Admin; teacher and students are assigned to courses by Admin)
- `assignments` collection: Stores assignment metadata, difficulty distribution, and per-student assigned questions.
- `questionTexts` collection: Stores the actual question content.
- `solutionTexts` collection: Stores the actual solution content.

Data Flow:
- Admin creates teachers, students, and courses (all documents in MongoDB).
- Admin assigns teacher and students to courses (updates `courses.teacherId` and `courses.students` array in MongoDB).
- Teachers create questions and assignments (documents in `questionTexts` and `assignments`).
- Assignments are linked to students and questions in MongoDB.
- Questions and solutions have all metadata and content in MongoDB.
- Students are assigned assignments and questions via MongoDB collections.

---

## 🟩 MongoDB Collections (Document Models)

### **teachers**

```json
{
  "_id": ObjectId("..."),
  "facultyId": "TCH12345",
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

### **students**

```json
{
  "_id": ObjectId("..."),
  "rollNumber": "CS2023001",
  "name": "Jane Smith",
  "email": "jane.smith@example.com",
}
```

### **courses**

```json
{
  "_id": ObjectId("..."),
  "courseName": "Course 101",
  "teacherId": ObjectId("..."),  // Ref: teachers._id, assigned by Admin
  "students": [ObjectId("..."), ObjectId("..."), ObjectId("...")],  // Ref: students._id, assigned by Admin
}
```

### **questionTexts**

```json
{
  "_id": ObjectId("..."),
  "topic": "Sorting Algorithms",
  "difficulty": "easy",
  "marks": 5,
  "source": "Custom",
  "description": "Write a function to sort an array...",
  "inputFormat": "First line contains n...",
  "outputFormat": "Sorted array...",
  "constraints": "1 <= n <= 1000",
  "sampleInput": "5 4 3 2 1",
  "sampleOutput": "1 2 3 4 5",
  "createdBy": ObjectId("..."), // Ref: teachers._id
}
```

### **solutionTexts**

```json
{
  "_id": ObjectId("..."),
  "questionId": ObjectId("..."), // Ref: questionTexts._id
  "solution": "def sort_array(arr): ...",
  "createdBy": ObjectId("..."), // Ref: teachers._id
}
```

### **assignments**

```json
{
  "_id": ObjectId("..."),
  "courseId": ObjectId("..."), // Ref: courses._id
  "title": "Assignment 1",
  "topic": "Sorting",
  "questionDistribution": { "easy": 2, "medium": 2, "hard": 1 },
  "assignedTo": [
    {
      "studentId": ObjectId("..."),
      "questionIds": [ObjectId("..."), ObjectId("...")]
    }
  ],
  "createdBy": ObjectId("..."), // Ref: teachers._id
}
```

---

All relationships are managed via ObjectId references between collections. No SQL/relational tables are used. All CRUD and lookup operations are performed on MongoDB collections only.