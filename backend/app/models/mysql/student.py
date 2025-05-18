# models/mysql/student.py
"""
SQLAlchemy model for Student table.
"""
from sqlalchemy import Column, Integer, String
from app.models.mysql.base import Base

class Student(Base):
    __tablename__ = "students" 
    
    studentRoll = Column(
        name="studentRoll", type_=String(100), primary_key=True, index=True
    )
    studentName = Column(
        name="studentName", type_=String(50), nullable=False
    )
    studentEmail = Column(
        name="studentEmail", type_=String(50), nullable=False, unique=True
    )
