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

    public Boolean insertTeacher(Teachers item){
        try {
            Query query;
            query = new Query(Criteria.where("email").is(item.getEmail()));
            if(mongoTemplate.exists(query,Teachers.class)){
                System.out.println("\nTeacher Exist\n");
                return Boolean.TRUE;
            }else{
                System.out.println("Teacher is not there");
                return Boolean.FALSE;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int test(String k, String v, String id){
        if (v == null){
            return  1;
        }

        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update().set(k,v);

        mongoTemplate.updateFirst(query, update, Teachers.class);

        System.out.println(k + "---"+ v);
        return k.length() + v.length();
    }
}
