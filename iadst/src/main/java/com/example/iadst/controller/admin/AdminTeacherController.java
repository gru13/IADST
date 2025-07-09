package com.example.iadst.controller;


import com.example.iadst.models.Teachers;
import com.example.iadst.repos.TeacherRepo;
import com.example.iadst.services.TeacherService;
import io.swagger.v3.oas.models.security.SecurityScheme;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//[
//        {
//        "facultyId": "TCH10016",
//        "name": "Paul Anderson",
//        "email": "paul.anderson@example.com"
//        },
//        {
//        "facultyId": "TCH10017",
//        "name": "Queenie Roberts",
//        "email": "queenie.roberts@example.com"
//        }
//        ]


import javax.print.DocFlavor;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    TeacherRepo teacherRepo;

    @Autowired
    TeacherService teacherService;

    @GetMapping("/teachers/{id}")
    public ResponseEntity<Teachers> getOneTeacher(@PathVariable String id){
        Teachers teacher = teacherRepo.findById(new ObjectId(id)).get();
        return ResponseEntity.status(HttpStatus.OK).body(teacher);
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<Teachers>> getTeachers(){
        List<Teachers> teachersList = teacherRepo.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(teachersList);
    }

    @PostMapping("/teachers")
    public ResponseEntity<Teachers> addTeacher(@Valid @RequestBody Teachers item){

        if(teacherService.insertTeacher(item)){
            System.out.println("Item Exist");
            item.setMessage("Element is already exist");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(item);
        }

        Teachers insertedItem = teacherRepo.save(item);
        insertedItem.setMessage("Successfully inserted");
        return ResponseEntity.status(HttpStatus.CREATED).body(insertedItem);
    }

    @PutMapping("/teachers/{id}")
    public  ResponseEntity<Teachers> modifyParameter(@PathVariable String id, @RequestBody   HashMap<String, String> modifiedContent){
        Teachers element = teacherRepo.findById(new ObjectId(id)).get();
        List<Teachers> result = modifiedContent.entrySet().stream().map(entity -> teacherService.updateParameter(entity.getKey(), entity.getValue(), id)).toList();
        System.out.println(result);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/teachers/{id}")
    public  ResponseEntity<Teachers> deleteTeacher(@PathVariable String id){
        Teachers elemtn = teacherRepo.findById(new ObjectId(id)).get();
        teacherRepo.deleteById(new ObjectId(id));

        return ResponseEntity.status(HttpStatus.OK).body(elemtn);
    }


}
