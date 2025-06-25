# Project Structure

├── backend                                # Java Spring Boot backend
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com/example/app
│   │   │   │       ├── Application.java                # Spring Boot entrypoint
│   │   │   │       ├── config
│   │   │   │       │   └── MongoConfig.java            # MongoDB configuration (Spring Data MongoDB)
│   │   │   │       ├── controller
│   │   │   │       │   ├── AuthController.java         # `/auth/login`, `/auth/logout` endpoints
│   │   │   │       │   ├── AdminController.java        # `/admin/*` endpoints for user/course management
│   │   │   │       │   └── TeacherController.java      # `/teacher/*` endpoints for question & assignment flows
│   │   │   │       ├── service
│   │   │   │       │   ├── AuthService.java            # Handles authentication logic
│   │   │   │       │   ├── AdminService.java           # CRUD for teachers, students, courses
│   │   │   │       │   ├── TeacherService.java         # Question & assignment operations
│   │   │   │       │   ├── QuestionService.java        # High-level question CRUD logic
│   │   │   │       │   ├── AssignmentService.java      # Assignment logic: pseudo-random selection, preview, regenerate
│   │   │   │       │   └── EmailPdfService.java        # PDF generation and email dispatch routines
│   │   │   │       ├── model
│   │   │   │       │   ├── Teacher.java                # `teachers` document schema
│   │   │   │       │   ├── Student.java                # `students` document schema
│   │   │   │       │   ├── QuestionText.java           # `questionTexts` document schema
│   │   │   │       │   ├── SolutionText.java           # `solutionTexts` document schema
│   │   │   │       │   ├── Course.java                 # `courses` document schema
│   │   │   │       │   └── Assignment.java             # `assignments` document schema
│   │   │   │       ├── util
│   │   │   │       │   ├── Randomizer.java             # Deterministic seed-based picker
│   │   │   │       │   ├── PdfGenerator.java           # PDF layout & formatting utilities
│   │   │   │       │   └── EmailSender.java            # SMTP or external API email helper
│   │   │   └── resources
│   │   │       ├── application.properties              # Spring Boot config (DB URI, etc.)
│   │   │       └── ...
│   ├── pom.xml                                        # Java dependencies (Maven)
│   └── Dockerfile                                     # Docker image build instructions
│
├── frontend                                           # React single-page application
│   ├── public
│   │   └── index.html                                 # HTML entrypoint with <div id="root">
│   ├── src
│   │   ├── components                                # Reusable UI components
│   │   │   ├── admin                                 # Admin-role widgets
│   │   │   │   ├── TeacherForm.jsx                   # Create/edit teacher form
│   │   │   │   ├── StudentForm.jsx                   # Create/edit student form
│   │   │   │   ├── CourseForm.jsx                    # Create/edit course form
│   │   │   │   ├── TeacherList.jsx                   # Table of teachers with actions
│   │   │   │   ├── StudentList.jsx                   # Table of students with actions
│   │   │   │   └── CourseList.jsx                    # Table of courses and memberships
│   │   │   └── teacher                               # Teacher-role widgets
│   │   │       ├── QuestionForm.jsx                  # Create/edit question form
│   │   │       ├── QuestionList.jsx                  # List/filter questions
│   │   │       ├── AssignmentWizard.jsx              # Multi-step assignment creator
│   │   │       ├── AssignmentPreview.jsx             # Student vs. questions preview
│   │   │       ├── AssignmentList.jsx                # List of assignments
│   │   │       └── AssignmentDetails.jsx             # Detailed assignment view
│   │   ├── pages                                    # Routeable top-level pages
│   │   │   ├── Login.jsx                            # Authentication page
│   │   │   ├── AdminDashboard.jsx                   # Admin home with stats & links
│   │   │   └── TeacherDashboard.jsx                 # Teacher home with overview
│   │   ├── services                                 # API call wrappers
│   │   │   ├── auth.api.js                          # `/auth` requests
│   │   │   ├── admin.api.js                         # `/admin` requests
│   │   │   └── teacher.api.js                       # `/teacher` requests
│   │   ├── contexts                                 # React Contexts for shared state
│   │   │   └── AuthContext.jsx                      # Provides user, token, login(), logout()
│   │   ├── utils                                    # Front-end helpers
│   │   │   ├── validators.js                        # Form validation rules
│   │   │   ├── dateFormatter.js                     # Date formatting helpers
│   │   │   └── apiClient.js                         # Configured Axios instance
│   │   ├── App.jsx                                  # Router setup & context providers
│   │   └── index.jsx                                # ReactDOM.render, wraps <App /> in <AuthContext>
│   └── package.json                                 # JS dependencies and scripts
│
├── docs                                             # Project documentation
│   ├── PROJECT_STRUCTURE.md                         # Project Structure
│   ├── PROJECT_OVERVIEW.md                          # Project Overview
│   ├── SCHEMA_SPEC.md                               # specifications of schemas
│   └── API_SPEC.md                                  # Endpoints specifications
│
├── docker-data/
│   └── mongo/                                       # MongoDB data persisted here (host:8001)
│
├── docker-compose.yml
│
└── README.md                                        # Overview, setup, and usage instructions
