# mysql.py
"""
SQLAlchemy engine & SessionLocal for MySQL (exposed on 8000).
"""
from sqlalchemy import create_engine, text
from sqlalchemy.orm import sessionmaker
from dotenv import load_dotenv
import os
# Import all models so their tables are registered
from app.models.mysql.teacher import Base as TeacherBase
from app.models.mysql.student import Base as StudentBase
from app.models.mysql.question_meta import Base as QuestionMetaBase
from app.models.mysql.solution_meta import Base as SolutionMetaBase
from app.models.mysql.teacher import Teacher
from app.models.mysql.question_meta import Questions
from app.models.mysql.solution_meta import SolutionMeta

# Load .env file
load_dotenv()

# Access variables
MYSQL_USER = os.getenv("MYSQL_USER")
MYSQL_PASSWORD = os.getenv("MYSQL_PASSWORD")
MYSQL_PORT = os.getenv("MYSQL_PORT")
MYSQL_DATABASE = os.getenv("MYSQL_DATABASE")

# SQLAlchemy Database URL
DATABASE_URL = (
    f"mysql+pymysql://{MYSQL_USER}:{MYSQL_PASSWORD}@localhost:{MYSQL_PORT}/{MYSQL_DATABASE}"
)

engine = create_engine(DATABASE_URL, pool_pre_ping=True)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

# Create all tables for all models
for model_base in [TeacherBase, StudentBase, QuestionMetaBase, SolutionMetaBase]:
    model_base.metadata.create_all(bind=engine)

print("Database URL:", DATABASE_URL)

def get_mysql_db():
    """Dependency to get a database session."""
    mysql_db = SessionLocal()
    try:
        yield mysql_db
    finally:
        mysql_db.close()
        
if __name__ == "__main__":
    # Test the connection
    try:
        with engine.connect() as connection:
            print("Database connection successful!")

        # Properly use the generator
        db_gen = get_mysql_db()    # get the generator
        db = next(db_gen)          # get the actual session

        try:
            print(db.execute(text("SHOW TABLES")).fetchall())
        finally:
            db_gen.close()         # ensure proper cleanup

    except Exception as e:
        print(f"Database connection failed: {e}")