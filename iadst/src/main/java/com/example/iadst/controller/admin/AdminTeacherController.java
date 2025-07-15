package com.example.iadst.controller.admin;


import com.example.iadst.models.Students;
import com.example.iadst.models.Teachers;
import com.example.iadst.repos.TeacherRepo;
import com.example.iadst.services.TeacherService;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin( origins = "http://localhost:5173")
@RestController
@RequestMapping("/admin/teachers")
public class AdminTeacherController {

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/all")
    public ResponseEntity<List<Teachers>> getTeachers() {
        List<Teachers> teachersList = teacherRepo.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(teachersList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teachers> getOneTeacher(@PathVariable String id) {
        Teachers teacher = teacherRepo.findById(new ObjectId(id)).get();
        return ResponseEntity.status(HttpStatus.OK).body(teacher);
    }


    @PostMapping("/")
    public ResponseEntity<Teachers> addTeacher(@Valid @RequestBody Teachers item) {

        System.out.println(item.toString());

        if (teacherRepo.existsByEmail(item.getEmail())){
            System.out.println("Item Exist");
            item.setMessage("Element is already exist");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(item);
        }

        Teachers insertedItem = teacherRepo.save(item);
        insertedItem.setMessage("Successfully inserted");

        return ResponseEntity.status(HttpStatus.CREATED).body(insertedItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teachers> modifyParameter(@PathVariable String id, @RequestBody HashMap<String, String> modifiedContent) {

        if(!teacherRepo.existsById(new ObjectId(id))){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Teachers("NO Student with id-"+ id + "is Found"));
        }

        Teachers element = teacherRepo.findById(new ObjectId(id)).get();
        List<Teachers> result = modifiedContent.entrySet().stream().map(entity -> teacherService.updateParameter(entity.getKey(), entity.getValue(), id)).toList();
        System.out.println(result.getLast());

        return ResponseEntity.status(HttpStatus.OK).body(result.getLast());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Teachers> deleteTeacher(@PathVariable String id) {
        if(!teacherRepo.existsById(new ObjectId(id))){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Teachers("NO Student with id-"+ id + "is Found"));
        }

        Teachers element = teacherRepo.findById(new ObjectId(id)).get();
        teacherRepo.deleteById(new ObjectId(id));
        element.setMessage("deleted Successfully");

        return ResponseEntity.status(HttpStatus.OK).body(element);
    }


}
