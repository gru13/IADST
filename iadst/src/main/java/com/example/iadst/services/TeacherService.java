package com.example.iadst.services;

import com.example.iadst.models.Teachers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TeacherService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Teachers updateParameter(String key, String value, String id) {
        // Check for duplicate facultyId
        if (key.equals("facultyId")) {
            Query existingQuery = new Query(Criteria.where("facultyId").is(value)
                    .and("id").ne(id));
            if (mongoTemplate.exists(existingQuery, Teachers.class)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, 
                    "Teacher with faculty ID " + value + " already exists");
            }
        }

        // Check for duplicate email
        if (key.equals("email")) {
            Query existingQuery = new Query(Criteria.where("email").is(value)
                    .and("id").ne(id));
            if (mongoTemplate.exists(existingQuery, Teachers.class)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, 
                    "Teacher with email " + value + " already exists");
            }
        }

        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update().set(key, value);

        mongoTemplate.updateFirst(query, update, Teachers.class);
        Teachers updatedItem = mongoTemplate.findOne(query, Teachers.class);

        if (updatedItem == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
                "Teacher with id " + id + " not found");
        }

        return updatedItem;
    }
}
