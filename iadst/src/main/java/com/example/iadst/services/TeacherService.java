package com.example.iadst.services;


import com.example.iadst.models.Teachers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;


@Service
public class TeacherService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Teachers updateParameter(String k, String v, String id){

        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update().set(k,v);

        mongoTemplate.updateFirst(query, update, Teachers.class);

        Teachers Updateditem = mongoTemplate.findOne(query, Teachers.class);

        if (Updateditem != null) {
            System.out.println(Updateditem.toString());
        }
        return Updateditem;
    }
}
