package com.example.iadst.models;

//{
//    "_id": ObjectId("..."),
//    "courseName": "Course 101",
//    "teacherId": ObjectId("..."),  // Ref: teachers._id, assigned by Admin
//    "students": [ObjectId("..."), ObjectId("..."), ObjectId("...")],  // Ref: students._id, assigned by Admin
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
public class courses {

    @Id
    private ObjectId id;

    @NotBlank
    private String coursesName;
    private ObjectId teacherId;
    private List<ObjectId> students;

    public String getId() {
        return id.toString();
    }

    @Override
    public String toString() {
        return "courses{" +
                "id=" + id +
                ", coursesName='" + coursesName + '\'' +
                ", teacherId=" + teacherId +
                ", students=" + students +
                '}';
    }
}
