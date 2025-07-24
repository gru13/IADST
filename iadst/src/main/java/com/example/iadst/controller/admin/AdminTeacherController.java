package com.example.iadst.controller.admin;


import com.example.iadst.exception.ResourceNotFoundException;
import com.example.iadst.models.Students;
import com.example.iadst.models.Teachers;
import com.example.iadst.repos.TeacherRepo;
import com.example.iadst.services.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/teachers")
@Validated
@Tag(name = "Teacher Management", description = "APIs for managing teachers")
public class AdminTeacherController {

    private final TeacherRepo teacherRepo;
    private final TeacherService teacherService;

    @Autowired
    public AdminTeacherController(TeacherRepo teacherRepo, TeacherService teacherService) {
        this.teacherRepo = teacherRepo;
        this.teacherService = teacherService;
    }

    @GetMapping("/all")
    @Operation(summary = "Get all teachers", description = "Retrieves a list of all teachers")
    public ResponseEntity<List<Teachers>> getTeachers() {
        List<Teachers> teachersList = teacherRepo.findAll();
        return ResponseEntity.ok(teachersList);
    }

    @GetMapping("/all/list/name")
    @Operation(description ="List of Names"  , summary = "get the list of String contains faculty Names")
    public  ResponseEntity<List<String>> getAllTeacherNameList(){
        List<Teachers> teachersList = teacherRepo.findAll();
        List<String> teacherrNamelist = teachersList.stream().map(Teachers::getName).toList();
        return ResponseEntity.status(HttpStatus.OK).body(teacherrNamelist);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get teacher by ID", description = "Retrieves a teacher by their ID")
    public ResponseEntity<Teachers> getOneTeacher(@PathVariable String id) {
        return teacherRepo.findById(new ObjectId(id))
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new ResourceNotFoundException("Teacher", "id", id));
    }

    @PostMapping
    @Operation(summary = "Add new teacher", description = "Creates a new teacher")
    public ResponseEntity<?> addTeacher(@Valid @RequestBody Teachers teacher) {
        if (teacherRepo.existsByEmail(teacher.getEmail())) {
            System.out.println("Teacher Email exist");
            Map<String, Object> response = new HashMap<>();
            response.put("timestamp", new Date());
            response.put("status", HttpStatus.CONFLICT.value());
            response.put("error", "Email already registered");
            response.put("message", "Teacher with email " + teacher.getEmail() + " already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        if (teacherRepo.existsByFacultyId(teacher.getFacultyId())) {
            System.out.println("Teacher FacultyId exist");
            Map<String, Object> response = new HashMap<>();
            response.put("timestamp", new Date());
            response.put("status", HttpStatus.CONFLICT.value());
            response.put("error", "Faculty ID already registered");
            response.put("message", "Teacher with faculty ID " + teacher.getFacultyId() + " already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        Teachers insertedItem = teacherRepo.save(teacher);
        insertedItem.setMessage("Successfully inserted");

        return ResponseEntity.status(HttpStatus.CREATED).body(insertedItem);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update teacher", description = "Updates an existing teacher's information")
    public ResponseEntity<Teachers> modifyParameter(@PathVariable String id, @RequestBody HashMap<String, String> modifiedContent) {
        if(!teacherRepo.existsById(new ObjectId(id))){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Teachers("No Teacher with id " + id + " was found"));
        }

        List<Teachers> result = modifiedContent.entrySet().stream()
            .map(entity -> teacherService.updateParameter(entity.getKey(), entity.getValue(), id))
            .toList();
        
        return ResponseEntity.status(HttpStatus.OK).body(result.getLast());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete teacher", description = "Deletes a teacher by their ID")
    public ResponseEntity<Teachers> deleteTeacher(@PathVariable String id) {
        if(!teacherRepo.existsById(new ObjectId(id))){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Teachers("No Teacher with id " + id + " was found"));
        }

        Teachers element = teacherRepo.findById(new ObjectId(id)).get();
        teacherRepo.deleteById(new ObjectId(id));
        element.setMessage("Teacher deleted successfully");

        return ResponseEntity.status(HttpStatus.OK).body(element);
    }

}
