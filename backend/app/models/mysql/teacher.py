# models/mysql/teacher.py
"""
SQLAlchemy model for Teacher table.
"""
from sqlalchemy import Column, Integer, String 

from app.models.mysql.base import Base


class Teacher(Base):
    __tablename__ = "teachers" 
    
    teacherID = Column(
        name="teacherId", type_=Integer, primary_key=True, autoincrement=True
    )
    teacherName = Column(
        name="teacherName", type_=String(50), nullable=False
    )
    teacherEmail = Column(
        name="teacherEmail", type_=String(50), unique=True, nullable=False,
    )