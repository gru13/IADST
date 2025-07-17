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


    @NotBlank(message = "Roll number cannot be blank")
    @Pattern(regexp = "^[A-Z]{2}\\d{7}$", message = "Roll number must be 2 uppercase letters followed by 7 digits")
    @Indexed(unique = true)
    private String rollNumber;

    @NotBlank(message = "Name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z\\s]{2,50}$", message = "Name must be 2-50 characters long and contain only letters")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please provide a valid email address")
    @Indexed(unique = true)
    private String email;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(
            regexp = "^(\\+91|91|0)?[6-9]\\d{9}$",
            message = "Please provide a valid Indian phone number"
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
