package com.example.iadst.services;


import com.example.iadst.models.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StudentsService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public  Students ModifyContent(String k, String v, String id){
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update().set(k,v);

        mongoTemplate.updateFirst(query, update, Students.class);
        Students UpdatedItem = mongoTemplate.findOne(query, Students.class);

        // if (UpdatedItem != null) {
        //     System.out.println(UpdatedItem.toString());
        // }
        return UpdatedItem;
    }

}
