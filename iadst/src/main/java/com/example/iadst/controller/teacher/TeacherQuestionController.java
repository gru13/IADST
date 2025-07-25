package com.example.iadst.controller.teacher;


import com.example.iadst.models.QuestionTexts;
import com.example.iadst.repos.QuestionTextRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teacher/questions")
public class TeacherQuestionController {
    @Autowired
    QuestionTextRepo questionTextRepo;

    @GetMapping("/all")
    public ResponseEntity<List<QuestionTexts>> getALLQuestion(){
        return ResponseEntity.status(HttpStatus.OK).body(questionTextRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionTexts> getCourseByID(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(questionTextRepo.findById(new ObjectId(id)).get());
    }

}
