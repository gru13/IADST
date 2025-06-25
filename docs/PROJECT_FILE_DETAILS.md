# Detailed Project File/Directory Descriptions (Spring Boot + React)

This document provides a comprehensive explanation of each file and directory in both the backend (Java Spring Boot) and frontend (React) structures.

---

## Backend: Java Spring Boot

### File/Directory Details

#### `Application.java`
- Main entry point for the Spring Boot application. Contains the `@SpringBootApplication` annotation and the `main()` method to bootstrap the app.

#### `config/`
- **MongoConfig.java**: Configures MongoDB connection, custom converters, and repository scanning for Spring Data MongoDB. Handles DB URI, credentials, and advanced Mongo settings.

#### `controller/`
- **AuthController.java**: REST controller for authentication endpoints (`/auth/login`, `/auth/logout`). Handles user login, logout, and token issuance.
- **AdminController.java**: REST controller for admin operations. Manages CRUD for teachers, students, courses, and assignment setup. Handles endpoints like `/admin/teachers`, `/admin/courses`, etc.
- **TeacherController.java**: REST controller for teacher operations. Manages question bank, assignment creation, preview, and distribution. Handles endpoints like `/teacher/questions`, `/teacher/assignments`.

#### `service/`
- **AuthService.java**: Contains authentication logic, user validation, password hashing, JWT/token management, and session handling.
- **AdminService.java**: Implements business logic for admin operations, including user/course CRUD, validation, and assignment of teachers/students to courses.
- **TeacherService.java**: Implements business logic for teacher operations, such as managing questions and assignments.
- **QuestionService.java**: Handles CRUD and advanced logic for questions, including filtering, searching, and validation.
- **AssignmentService.java**: Handles assignment creation, pseudo-random question selection, preview, regeneration, and assignment distribution logic.
- **EmailPdfService.java**: Generates PDF documents for assignments and sends them to students via email. Integrates with PDF libraries and mail APIs.

#### `model/`
- **Teacher.java**: Java class (POJO) representing the `teachers` MongoDB document. Contains fields like id, name, email, timestamps, and relevant annotations.
- **Student.java**: Java class representing the `students` MongoDB document. Contains fields like id, rollNumber, name, email, timestamps.
- **Course.java**: Java class representing the `courses` MongoDB document. Contains fields for course name, teacherId, student list, and timestamps.
- **QuestionText.java**: Java class for the `questionTexts` MongoDB document. Contains question metadata, text, difficulty, and references to creator.
- **SolutionText.java**: Java class for the `solutionTexts` MongoDB document. Contains solution code, explanation, and references to question/creator.
- **Assignment.java**: Java class for the `assignments` MongoDB document. Contains assignment metadata, question distribution, per-student assignments, and timestamps.

#### `util/`
- **Randomizer.java**: Utility for deterministic, seed-based random selection of questions for assignments. Ensures reproducibility.
- **PdfGenerator.java**: Utility for generating PDF documents for assignments, using libraries like iText or Apache PDFBox.
- **EmailSender.java**: Utility for sending emails via SMTP or external APIs (e.g., SendGrid, Mailgun). Handles attachments and templates.

#### `src/main/resources/`
- **application.properties**: Spring Boot configuration file. Stores DB URI, server port, mail settings, and other environment-specific configs.
- **static/**: Static web resources (images, JS, CSS) served by Spring Boot if needed.
- **templates/**: Thymeleaf or other template files for server-side rendering or email templates.

#### `src/test/java/com/example/app/`
- **QuickstartApplicationTests.java**: Example test class for application context and integration tests. Can be expanded for unit and integration testing.

#### Project Root
- **pom.xml**: Maven build file. Manages Java dependencies, plugins, build lifecycle, and project metadata.
- **Dockerfile**: Instructions to build the backend Docker image for deployment.

---

## Frontend: React

### File/Directory Details

#### `public/`
- **index.html**: HTML entrypoint for the React app. Contains the root `<div id="root">` where the React app is mounted.

#### `src/components/`
- **admin/**: Contains admin-specific UI components:
  - **TeacherForm.jsx**: Form for creating/editing teacher records.
  - **StudentForm.jsx**: Form for creating/editing student records.
  - **CourseForm.jsx**: Form for creating/editing course records.
  - **TeacherList.jsx**: Table/list of teachers with actions (edit/delete).
  - **StudentList.jsx**: Table/list of students with actions.
  - **CourseList.jsx**: Table/list of courses and their memberships.
- **teacher/**: Contains teacher-specific UI components:
  - **QuestionForm.jsx**: Form for creating/editing questions.
  - **QuestionList.jsx**: List and filter questions.
  - **AssignmentWizard.jsx**: Multi-step assignment creation wizard.
  - **AssignmentPreview.jsx**: Preview of assignments per student.
  - **AssignmentList.jsx**: List of assignments.
  - **AssignmentDetails.jsx**: Detailed view of a specific assignment.

#### `src/pages/`
- **Login.jsx**: Login page for admin/teacher authentication.
- **AdminDashboard.jsx**: Admin home page with stats and navigation.
- **TeacherDashboard.jsx**: Teacher home page with overview and quick actions.

#### `src/services/`
- **auth.api.js**: API wrapper for authentication endpoints (`/auth`). Handles login/logout requests, token storage, and error handling.
  - Functions: `login(credentials)`, `logout()`, `refreshToken()`, `getCurrentUser()`
- **admin.api.js**: API wrapper for admin endpoints (`/admin`). Includes functions for CRUD operations on teachers, students, and courses, as well as assigning users to courses.
  - Functions: `createTeacher(data)`, `getTeachers()`, `updateTeacher(id, data)`, `deleteTeacher(id)`, `createStudent(data)`, `getStudents()`, `updateStudent(id, data)`, `deleteStudent(id)`, `createCourse(data)`, `getCourses()`, `updateCourse(id, data)`, `deleteCourse(id)`, `assignTeacherToCourse(courseId, teacherId)`, `assignStudentsToCourse(courseId, studentIds)`
- **teacher.api.js**: API wrapper for teacher endpoints (`/teacher`). Includes functions for managing questions, assignments, and fetching assignment previews.
  - Functions: `createQuestion(data)`, `getQuestions()`, `updateQuestion(id, data)`, `deleteQuestion(id)`, `createAssignment(data)`, `getAssignments()`, `updateAssignment(id, data)`, `deleteAssignment(id)`, `getAssignmentPreview(assignmentId)`

#### `src/contexts/`
- **AuthContext.jsx**: React Context for authentication state. Provides user, token, login, and logout methods to the app. Handles token refresh, user persistence, and context updates on login/logout.
  - Functions: `login(credentials)`, `logout()`, `refreshToken()`, `setUser(user)`, `isAuthenticated()`

#### `src/utils/`
- **validators.js**: Contains form validation rules and helpers. Used for validating user input in forms (e.g., email, password, required fields).
  - Functions: `validateEmail(email)`, `validatePassword(password)`, `validateRequired(value)`, `validateForm(formData)`
- **dateFormatter.js**: Utility for formatting dates in the UI. Supports custom date formats and localization.
  - Functions: `formatDate(date, format)`, `toLocalDate(date)`, `toIsoString(date)`
- **apiClient.js**: Configured Axios instance for making HTTP requests with base URL and interceptors. Handles request/response interceptors for auth tokens and error handling.
  - Functions: `get(url, config)`, `post(url, data, config)`, `put(url, data, config)`, `delete(url, config)`, `setAuthToken(token)`

#### `src/App.jsx`
- Main React component. Sets up routing and context providers for the app. Handles protected routes, layout, and global error boundaries.
  - Functions: `renderRoutes()`, `handleError(error)`, `render()`

#### `src/index.jsx`
- Entry point for the React app. Renders `<App />` inside the root div and wraps it in `AuthContext`. Initializes global styles and sets up React StrictMode.
  - Functions: `renderApp()`

#### Project Root
- **package.json**: Manages frontend dependencies, scripts, and project metadata. Defines build, start, test, and lint scripts. Lists all npm/yarn dependencies and project info.
- **.env**: (If present) Stores environment variables for the frontend, such as API base URLs and feature flags.
- **README.md**: (If present) Provides setup, usage, and deployment instructions for the frontend.

---

## Additional Notes

- All backend and frontend code is modularized for maintainability and scalability.
- Backend follows RESTful API design and uses DTOs for request/response payloads.
- Frontend uses React functional components and hooks for state management.
- Error handling, loading states, and user feedback are implemented throughout the UI.
- Both backend and frontend are containerized for easy deployment with Docker.
- Code is organized to support team collaboration and future feature expansion.

