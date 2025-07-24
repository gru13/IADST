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
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "Topic cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9\\s-]{3,100}$", message = "Topic must be 3-100 characters and contain only letters, numbers, spaces, and hyphens")
    private String topic;

    @NotNull(message = "Difficulty level is required")
    private Difficulty difficulty;

    @Min(value = 1, message = "Marks must be at least 1")
    @Max(value = 100, message = "Marks cannot exceed 100")
    private int marks;

    @NotBlank(message = "Source cannot be blank")
    private String source;

    @NotBlank(message = "Description cannot be blank")
    @Size(min = 10, max = 2000, message = "Description must be between 10 and 2000 characters")
    private String description;

    @NotBlank(message = "Input format cannot be blank")
    private String inputFormat;

    @NotBlank(message = "Output format cannot be blank")
    private String outputFormat;

    @NotBlank(message = "Constraints cannot be blank")
    private String constraints;

    @NotBlank(message = "Sample input cannot be blank")
    private String sampleInput;

    @NotBlank(message = "Sample output cannot be blank")
    private String sampleOutput;

    @NotNull(message = "Creator ID is required")
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

