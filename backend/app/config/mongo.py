# mongo.py

"""
Motor (async) client & MongoDB collection handles (exposed on 8001).
"""

from motor.motor_asyncio import AsyncIOMotorClient
from fastapi import Depends
import os

MONGO_HOST = os.getenv("MONGO_HOST", "localhost")
MONGO_PORT = int(os.getenv("MONGO_PORT", 8001))
MONGO_DB = os.getenv("MONGO_DB", "iadst")

MONGO_URI = f"mongodb://{MONGO_HOST}:{MONGO_PORT}"

client = AsyncIOMotorClient(MONGO_URI)
db = client[MONGO_DB]

# Expose collection handles
question_texts = db["questionTexts"]
solution_texts = db["solutionTexts"]
courses = db["courses"]
assignments = db["assignments"]

# Dependency for FastAPI routes
async def get_mongo_db():
    return db

