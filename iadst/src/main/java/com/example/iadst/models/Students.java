package com.example.iadst.models;

//{
//    "_id": ObjectId("..."),
//    "rollNumber": "CS2023001",
//    "name": "Jane Smith",
//    "email": "jane.smith@example.com",
//}

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "students")
public class Students {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String rollNumber;

    @NotEmpty
    private String name;

    @NotBlank(message = "Email can't be empty")
    @Email(message = "This is not a valid email")
    private String email;

    @Pattern(
            regexp = "^(\\+91|91|0)?[6-9]\\d{9}$",
            message = "Invalid Indian phone number"
    )
    private String phoneNo;


    @Transient
    private String message;

    public Students(String message){
        this.message = message;
    }

    public String getId() {
        if (this.id == null){
            return "null";
        }
        return id.toString();
    }
}
