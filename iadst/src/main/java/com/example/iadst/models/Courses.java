package com.example.iadst.models;

//{
//    "_id": ObjectId("..."),
//    "courseName": "Course 101",
//    "teacherId": ObjectId("..."),  // Ref: Teachers._id, assigned by Admin
//    "Students": [ObjectId("..."), ObjectId("..."), ObjectId("...")],  // Ref: Students._id, assigned by Admin
//}

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "courses")
public class Courses {

    @Id
    private ObjectId id;

    @NotBlank(message = "Course name cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9\\s-]{3,50}$", message = "Course name must be 3-50 characters and contain only letters, numbers, spaces, and hyphens")
    @Indexed(unique = true)  
    private String courseName;
    
    @NotNull(message = "Teacher ID is required")
    private ObjectId teacherId;
    
    @NotNull(message = "Students list cannot be null")
    private List<ObjectId> students = new ArrayList<>();

    @Transient
    private  String message;

    public  Courses(String message){
        this.message = message;
    }

    public String getId() {
        if(this.id == null){
            return "null";
        }
        return this.id.toString();
    }

    public String getTeacherID(){
        if(this.teacherId == null){
            return "null";
        }
        return this.teacherId.toString();
    }

    public List<String> getStudents(){
        return this.students.stream().map(a->a.toString()).toList();
    }

    public String removeStudent(String id){
        int index = students.indexOf(new ObjectId(id));
        if(index == -1){
            return null;
        }
        return this.students.remove(index).toString();
    }

    public void addStudent(String studentId){
        this.students.addLast(new ObjectId(studentId));
    }

    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", teacherId=" + teacherId +
                ", Students=" + students +
                '}';
    }
}
