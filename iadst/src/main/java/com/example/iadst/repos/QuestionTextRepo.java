package com.example.iadst.repos;

import com.example.iadst.models.QuestionTexts;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionTextRepo extends MongoRepository<QuestionTexts, ObjectId> {
}
