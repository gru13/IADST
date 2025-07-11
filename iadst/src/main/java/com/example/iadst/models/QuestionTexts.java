package com.example.iadst.models;


//{
//    "_id": ObjectId("..."),
//    "topic": "Sorting Algorithms",
//    "difficulty": "easy",
//    "marks": 5,
//    "source": "Custom",
//    "description": "Write a function to sort an array...",
//    "inputFormat": "First line contains n...",
//    "outputFormat": "Sorted array...",
//    "constraints": "1 <= n <= 1000",
//    "sampleInput": "5 4 3 2 1",
//    "sampleOutput": "1 2 3 4 5",
//    "createdBy": ObjectId("..."), // Ref: Teachers._id
//}


import com.example.iadst.enums.Difficulty;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "questionTexts")
public class QuestionTexts {
    @Id
    private ObjectId id;
    private String topic;
    private Difficulty difficulty;
    private int marks;
    private String source;
    private String description;
    private String inputFormat;
    private String outputFormat;
    private String constraints;
    private String sampleInput;
    private String sampleOutput;
    private ObjectId createdBy;

    @Transient
    String message;

    public String getDifficulty() {
        if (this.difficulty == null) {
            return "null";
        }
        return this.difficulty.name();
    }

    public String getCreatedBy() {
        if (this.createdBy == null) {
            return "null";
        }
        return createdBy.toString();
    }

    public String getId() {
        if (this.id == null) {
            return "null";
        }
        return id.toString();
    }
}

