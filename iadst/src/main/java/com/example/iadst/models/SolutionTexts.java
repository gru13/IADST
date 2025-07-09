package com.example.iadst.models;

//{
//    "_id": ObjectId("..."),
//    "questionId": ObjectId("..."), // Ref: QuestionTexts._id
//    "solution": "def sort_array(arr): ...",
//    "createdBy": ObjectId("..."), // Ref: Teachers._id
//}


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "solutionTexts")
public class SolutionTexts {

    @Id
    private ObjectId id;

    private ObjectId questionId;

    @NotBlank(message = "Solution must not be blank")
    private String  solution;
    private ObjectId createdBy;

    public String getId() {
        return id.toString();
    }

    @Override
    public String toString() {
        return "SolutionTexts{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", solution='" + solution + '\'' +
                ", createdBy=" + createdBy +
                '}';
    }
}
