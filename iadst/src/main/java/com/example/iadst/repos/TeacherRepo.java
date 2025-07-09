package com.example.iadst.repos;

import com.example.iadst.models.Teachers;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeacherRepo extends MongoRepository<Teachers, ObjectId> {
    boolean existsByEmail( String email);
}
