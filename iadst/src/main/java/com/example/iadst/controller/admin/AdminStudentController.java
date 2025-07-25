package com.example.iadst.controller.admin;

import com.example.iadst.models.Students;
import com.example.iadst.repos.StudentRepo;
import com.example.iadst.services.StudentsService;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Validated
@CrossOrigin( origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/admin/students")
public class AdminStudentController {

    @Autowired
    private  StudentRepo studentRepo;

    @Autowired
    private StudentsService studentsService;


    @GetMapping(path = "/all")
    public ResponseEntity<List<Students>> getAllStudents(){
        return ResponseEntity.status(HttpStatus.OK).body(studentRepo.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Students> getStudentByID(@PathVariable String id){
        // System.out.println(id);
        Students stu = studentRepo.findById(new ObjectId(id)).get();
        // System.out.println(stu.toString());
        return ResponseEntity.status(HttpStatus.OK).body(stu);
    }

    @PostMapping(path = "/")
    public ResponseEntity<?> addStudent(@Valid @RequestBody Students item){
        // System.out.println(item.toString());

        if(studentRepo.existsByEmail(item.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(java.util.Map.of("error", "Already Student Exist"));
        }

        Students result =  studentRepo.save(item);

        // System.out.println(result.toString());

        return  ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Students> removeStudent(@PathVariable String id){
        if(!studentRepo.existsById(new ObjectId(id))){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Students("NO Student with id-"+ id + "is Found"));
        }

        Students element = studentRepo.findById(new ObjectId(id)).get();
        studentRepo.deleteById(new ObjectId(id));
        element.setMessage("deleted Successfully");

        return  new ResponseEntity<>(element, HttpStatus.OK);

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Students> updateStudentDetail(@PathVariable String id, @RequestBody HashMap<String, String> modifiedItems){
        if(!studentRepo.existsById(new ObjectId(id))){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Students("NO Student with id-"+ id + "is Found"));
        }

        Students element = studentRepo.findById(new ObjectId(id)).get();
        List<Students> result = modifiedItems
                .entrySet()
                .stream()
                .map((item)->studentsService.ModifyContent(item.getKey(),item.getValue(),id))
                .toList();

        return  ResponseEntity.status(HttpStatus.OK).body(result.getLast());

    }
}
