package com.example.iadst.repos;

import com.example.iadst.models.Students;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepo extends MongoRepository<Students, ObjectId> {

    boolean existsByEmail(String email);

}
