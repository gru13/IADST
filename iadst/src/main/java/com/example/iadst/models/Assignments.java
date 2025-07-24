package com.example.iadst.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
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

    @NotNull(message = "Course ID is required")
    private ObjectId courseId;

    @NotBlank(message = "Topic cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9\\s-]{3,100}$", message = "Topic must be 3-100 characters and contain only letters, numbers, spaces, and hyphens")
    private String topic;

    @NotNull(message = "Question distribution is required")
    @Valid
    private HashMap<String,Integer> questionDistribution;

    @NotNull(message = "Assigned students list cannot be null")
    @Valid
    private List<AssignedStudent> assignedTo = new ArrayList<>();

    @NotNull(message = "Creator ID is required")
    private ObjectId createdBy;
}



