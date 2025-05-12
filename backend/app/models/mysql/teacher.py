# models/mysql/teacher.py
"""
SQLAlchemy model for Teacher table.
"""
from sqlalchemy import Column, Integer, String 
from sqlalchemy.orm import declarative_base

Base = declarative_base()


class Teacher(Base):
    __tablename__ = "teachers" 
    
    teacherID: Integer = Column(
        name="teacherId", type_=Integer, primary_key=True, autoincrement=True
    )
    teacherName: String(255) = Column(
        name="teacherName", type_=String(50), nullable=False
    )
    teacherEmail: String(255) = Column(
        name="teacherEmail", type_=String(50), unique=True, nullable=False,
    )
