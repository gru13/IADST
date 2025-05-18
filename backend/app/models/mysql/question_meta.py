# models/mysql/question_meta.py
# SQLAlchemy model for QuestionMeta table

"""
SQLAlchemy model for QuestionMeta metadata related to courses.
"""
from sqlalchemy import Column, Integer, String, Text, ForeignKey, Enum
from sqlalchemy.orm import relationship
from enum import Enum as PyEnum
from app.models.mysql.teacher import Teacher
from app.models.mysql.base import Base


class DifficultyEnum(PyEnum):
    EASY = "easy"
    MEDIUM = "medium"
    HARD = "hard"


class Questions(Base):
    __tablename__ = "question_meta" 
    
    questionId = Column(
        name="questionId", type_=Integer, primary_key=True, autoincrement=True
    )
    mongoId = Column(
        name="mongoId", type_=String(24), nullable=False, index=True
    )
    topicName = Column(
        name="teacherName", type_=String(50), nullable=False, index=True
    )
    difficulty = Column(
        Enum(DifficultyEnum),
        name="difficulty",
        nullable=False,
        index=True
    )
    marks = Column(
        Integer,
        nullable=False,
        server_default="0"
    )
    sourceName = Column(
        name="sourceName", type_=Text, nullable=False
    )
    sourceLink = Column(
        name="sourceLink", type_=Text, nullable=False
    )
    teacherId = Column(
        ForeignKey('teachers.teacherId', ondelete='CASCADE'),
        name="teacherId", type_=Integer,  nullable=False, index=True
    )
    
    teacher = relationship("Teacher", back_populates="questionMeta")
