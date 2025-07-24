package com.example.iadst.repos;

import com.example.iadst.models.Courses;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepo extends MongoRepository<Courses, ObjectId> {
    boolean existsByCourseName(String courseName);

}
