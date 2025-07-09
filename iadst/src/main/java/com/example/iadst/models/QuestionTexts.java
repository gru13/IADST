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


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "questionTexts")
public class QuestionTexts {
    @Id
    private ObjectId id;
    private String topic;
    private String difficulty;
    private int marks;
    private String source;
    private String description;
    private String inputFormat;
    private String outputFormat;
    private String constraints;
    private String sampleInput;
    private String sampleOutput;
    private ObjectId createdBy;


    @Override
    public String toString() {
        return "QuestionTexts{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", marks=" + marks +
                ", source='" + source + '\'' +
                ", description='" + description + '\'' +
                ", inputFormat='" + inputFormat + '\'' +
                ", outputFormat='" + outputFormat + '\'' +
                ", constraints='" + constraints + '\'' +
                ", sampleInput='" + sampleInput + '\'' +
                ", sampleOutput='" + sampleOutput + '\'' +
                ", createdBy=" + createdBy +
                '}';
    }


    public String getCreatedBy() {
        return createdBy.toString();
    }

    public String getId() {
        return id.toString();
    }
}

