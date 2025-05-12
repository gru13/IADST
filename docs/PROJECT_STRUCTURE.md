├── backend                                # Python FastAPI backend
│   ├── app  
│   │   ├── __init__.py                    # Makes `app` a Python package
│   │   ├── main.py                        # FastAPI entrypoint: initializes app, routers, middleware
│   │   │  
│   │   ├── config                         # Database connection configurations
│   │   │   ├── mysql.py                   # SQLAlchemy engine & SessionLocal for MySQL (exposed on 8000)
│   │   │   └── mongo.py                   # Motor (async) client & MongoDB collection handles (exposed on 8001)
│   │   │  
│   │   ├── routers                        # Route definitions mapping URLs to controllers
│   │   │   ├── auth.py                    # `/auth/login`, `/auth/logout` endpoints
│   │   │   ├── admin.py                   # `/admin/*` endpoints for user/course management
│   │   │   └── teacher.py                 # `/teacher/*` endpoints for question & assignment flows
│   │   │  
│   │   ├── controllers                    # Business‑logic layer called by routers
│   │   │   ├── auth_controller.py         # Handles authentication logic
│   │   │   ├── admin_controller.py        # CRUD for teachers, students, courses
│   │   │   └── teacher_controller.py      # Question & assignment operations
│   │   │  
│   │   ├── models                         # Data schemas and ORM/ODM definitions
│   │   │   ├── mysql                      # SQLAlchemy models for relational tables
│   │   │   │   ├── teacher.py             # `Teacher` table model
│   │   │   │   ├── student.py             # `Student` table model
│   │   │   │   ├── question_meta.py       # `QuestionMeta` metadata model
│   │   │   │   ├── solution_meta.py       # `SolutionMeta` metadata model
│   │   │   │   └── assignment_meta.py     # `AssignmentMeta` metadata model
│   │   │   │  
│   │   │   └── mongo                      # Pydantic or ODMantic schemas for NoSQL collections
│   │   │       ├── question_text.py       # `questionTexts` document schema
│   │   │       ├── solution_text.py       # `solutionTexts` document schema
│   │   │       ├── course_doc.py          # `courses` document schema
│   │   │       └── assignment_doc.py      # `assignments` document schema
│   │   │  
│   │   ├── services                       # Core application logic (reusable functions)
│   │   │   ├── auth_service.py            # Token creation/validation
│   │   │   ├── question_service.py        # High‑level question CRUD logic
│   │   │   ├── assignment_service.py      # Pseudo‑random selection, preview, regenerate
│   │   │   └── email_pdf_service.py       # PDF generation and email dispatch routines
│   │   │  
│   │   └── utils                          # Helper modules for pure functions
│   │       ├── randomizer.py              # Deterministic seed‑based picker
│   │       ├── pdf_generator.py           # PDF layout & formatting utilities
│   │       └── email_sender.py            # SMTP or external API email helper
│   │  
│   ├── requirements.txt                   # Python package dependencies
│   └── Dockerfile                         # Docker image build instructions
│  
├── frontend                               # React single‑page application
│   ├── public  
│   │   └── index.html                     # HTML entrypoint with `<div id="root">`
│   │  
│   ├── src  
│   │   ├── components                     # Reusable UI components
│   │   │   ├── admin                      # Admin‑role widgets
│   │   │   │   ├── TeacherForm.jsx        # Create/edit teacher form
│   │   │   │   ├── StudentForm.jsx        # Create/edit student form
│   │   │   │   ├── CourseForm.jsx         # Create/edit course form
│   │   │   │   ├── TeacherList.jsx        # Table of teachers with actions
│   │   │   │   ├── StudentList.jsx        # Table of students with actions
│   │   │   │   └── CourseList.jsx         # Table of courses and memberships
│   │   │   │  
│   │   │   └── teacher                    # Teacher‑role widgets
│   │   │       ├── QuestionForm.jsx       # Create/edit question form
│   │   │       ├── QuestionList.jsx       # List/filter questions
│   │   │       ├── AssignmentWizard.jsx   # Multi‑step assignment creator
│   │   │       ├── AssignmentPreview.jsx  # Student vs. questions preview
│   │   │       ├── AssignmentList.jsx     # List of assignments
│   │   │       └── AssignmentDetails.jsx  # Detailed assignment view
│   │   │  
│   │   ├── pages                          # Routeable top‑level pages
│   │   │   ├── Login.jsx                  # Authentication page
│   │   │   ├── AdminDashboard.jsx         # Admin home with stats & links
│   │   │   └── TeacherDashboard.jsx       # Teacher home with overview
│   │   │  
│   │   ├── services                       # API call wrappers
│   │   │   ├── auth.api.js                # `/auth` requests
│   │   │   ├── admin.api.js               # `/admin` requests
│   │   │   └── teacher.api.js             # `/teacher` requests
│   │   │  
│   │   ├── contexts                       # React Contexts for shared state
│   │   │   └── AuthContext.jsx            # Provides `user`, `token`, `login()`, `logout()`
│   │   │  
│   │   ├── utils                          # Front‑end helpers
│   │   │   ├── validators.js              # Form validation rules
│   │   │   ├── dateFormatter.js           # Date formatting helpers
│   │   │   └── apiClient.js               # Configured Axios instance
│   │   │  
│   │   ├── App.jsx                        # Router setup & context providers
│   │   └── index.jsx                      # ReactDOM.render, wraps `<App />` in `<AuthContext>`
│   │  
│   └── package.json                       # JS dependencies and scripts
│  
├── docs                                   # Project documentation
│   ├── PROJECT_STRUCTURE.md               # Project Structure
│   │   # Project Structure
│   ├── PROJECT_OVERVIEW.md                # Project Overview
│   ├── SCHEMA_SPEC.md                     # specifications of schemas
│   └── API_SPEC.md                        # Endpoints specifications 
│
├── docker-data/
│   ├── mysql/                             # MySQL data persisted here (host:8000)
│   └── mongo/                             # MongoDB data persisted here (host:8001)
│
├── docker-compose.yml
│
└── README.md                              # Overview, setup, and usage instructions
