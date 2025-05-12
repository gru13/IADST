# models/mysql/student.py
"""
SQLAlchemy model for Student table.
"""
from sqlalchemy import Column, Integer, String
from sqlalchemy.orm import declarative_base

Base = declarative_base()

class Student(Base):
    __tablename__ = "students" 
    
    studentRoll: Integer = Column(
        name="studentRoll", type_=String(100), primary_key=True, autoincrement=True
    )
    studentName: String(255) = Column(
        name="studentName", type_=String(50), nullable=False
    )
    studentEmail: String(255) = Column(
        name="studentEmail", type_=String(50), nullable=False, unique=True
    )
