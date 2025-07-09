package com.example.iadst.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;

//{
//    "_id": ObjectId("..."),
//    "courseId": ObjectId("..."), // Ref: Courses._id
//    "title": "Assignment 1",
//    "topic": "Sorting",
//    "questionDistribution": { "easy": 2, "medium": 2, "hard": 1 },
//    "assignedTo": [
//        {
//        "studentId": ObjectId("..."),
//        "questionIds": [ObjectId("..."), ObjectId("...")]
//        }
//    ],
//    "createdBy": ObjectId("..."), // Ref: Teachers._id
//}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "assignments")
public class Assignments {

    @Id
    private ObjectId id;
    private ObjectId courseId;
    private String topic;
    private HashMap<String,Integer> questionDistribution;

    private List<AssignedStudent> assignedTo;

    private ObjectId createdBy;
}



