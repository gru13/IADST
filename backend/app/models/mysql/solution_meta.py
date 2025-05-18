# models/mysql/solution_meta.py
# SQLAlchemy model for SolutionMeta table

"""
SQLAlchemy model for SolutionMeta metadata.
"""

from sqlalchemy.orm import relationship
from sqlalchemy import Column, Integer, String, ForeignKey
from app.models.mysql.base import Base


class SolutionMeta(Base):
    __tablename__ = "solutionMeta"
    solutionId = Column(
        name='solutionId', type_=Integer, primary_key=True, autoincrement=True
    )
    
    questionId = Column(
        ForeignKey('question_meta.questionId', ondelete='CASCADE'), 
        name="questionId", type_=Integer, nullable=False, index=True
    )
    
    teacherId = Column(
        ForeignKey('teachers.teacherId', ondelete='CASCADE'),
        name='teacherId', type_=Integer, nullable=False
    )
    
    mongoId = Column(
        name="mongoId", type_=String(24), nullable=False, index=True
    )