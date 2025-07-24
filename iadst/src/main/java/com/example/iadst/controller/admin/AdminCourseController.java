package com.example.iadst.controller.admin;


import com.example.iadst.models.Courses;
import com.example.iadst.repos.CourseRepo;
import com.example.iadst.services.CourseService;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin( origins = "http://localhost:5173")
@RestController
@RequestMapping("/admin/course")
@Validated
public class AdminCourseController {

    @Autowired
    CourseRepo courseRepo;

    @Autowired
    CourseService courseService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<Courses>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(courseRepo.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Courses> getCourseByID(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(courseRepo.findById(new ObjectId(id)).get());
    }

    @PostMapping(path = "/")
    public ResponseEntity<?> postAll(@Valid @RequestBody Courses item) {
        if (courseRepo.existsByCourseName(item.getCourseName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("error", "Course with this name already exists"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(courseRepo.save(item));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Courses> removeStudent(@PathVariable String id){
        if(!courseRepo.existsById(new ObjectId(id))){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Courses("NO Student with id-"+ id + "is Found"));
        }

        Courses element = courseRepo.findById(new ObjectId(id)).get();
        courseRepo.deleteById(new ObjectId(id));
        element.setMessage("deleted Successfully");

        return  ResponseEntity.status(HttpStatus.OK).body(element);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Courses> updateStudentDetail(@PathVariable String id, @RequestBody HashMap<String, String> modifiedItems){
        System.out.println(id);

        if(!courseRepo.existsById(new ObjectId(id))){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Courses("NO Student with id-"+ id + "is Found"));
        }

        Courses element = courseRepo.findById(new ObjectId(id)).get();
        List<Courses> result = modifiedItems
                .entrySet()
                .stream()
                .map((item)->courseService.ModifyContent(item.getKey(),item.getValue(),id))
                .toList();

        return  ResponseEntity.status(HttpStatus.OK).body(result.getLast());
    }

    @GetMapping(path = "/{id}/courses")
    public ResponseEntity<List<String>> studentsInCourse(@PathVariable String id){
        List<String> students = courseRepo.findById(new ObjectId(id)).get().getStudents();
        if (students == null){
            return  ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }

    @DeleteMapping(path = "/{id}/students/{studentId}")
    public ResponseEntity<Courses> removeStudentInCourse(@PathVariable("id") String id, @PathVariable("studentId") String studendId){
        Courses courseElement = courseRepo.findById(new ObjectId(id)).get();
        System.out.println(courseElement.toString());
        String removeStudentId = courseElement.removeStudent(studendId);
        if(removeStudentId == null){
            courseElement.setMessage("Element Not Found");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(courseElement);
        }
        courseRepo.save(courseElement);
        System.out.println(courseElement.getStudents());
        courseElement.setMessage("Successfully remove Student with Id ("+studendId+")");
        return ResponseEntity.status(HttpStatus.OK).body(courseElement);
    }

    @PostMapping(path = "/{id}/students/{studentId}")
    public ResponseEntity<Courses> addStudentInCourse(@PathVariable("id") String id, @PathVariable("studentId") String studendId){
        Courses courseElement = courseRepo.findById(new ObjectId(id)).get();
        System.out.println(courseElement.toString());
        courseElement.addStudent(studendId);
        courseRepo.save(courseElement);
        return ResponseEntity.status(HttpStatus.OK).body(courseElement);
    }
}
