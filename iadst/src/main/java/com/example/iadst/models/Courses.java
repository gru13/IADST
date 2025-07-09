package com.example.iadst.models;

//{
//    "_id": ObjectId("..."),
//    "courseName": "Course 101",
//    "teacherId": ObjectId("..."),  // Ref: Teachers._id, assigned by Admin
//    "Students": [ObjectId("..."), ObjectId("..."), ObjectId("...")],  // Ref: Students._id, assigned by Admin
//}

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
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

    @NotBlank(message = "Course name must not be blank")
    private String courseName;
    private ObjectId teacherId;
    private List<ObjectId> students;

    public String getId() {
        return id.toString();
    }

    public String getTeacherId(){
        return teacherId.toString();
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
