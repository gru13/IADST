package com.example.iadst.models;


//{
//    "_id": ObjectId("..."),
//    "facultyId": "TCH12345",
//    "name": "John Doe",
//    "email": "john.doe@example.com"
//}


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "faculties")
public class Teachers {
    @Id
    private ObjectId id;

    @NotBlank(message = "Faculty ID cannot be blank")
    @Pattern(regexp = "^[A-Z]{3}\\d{5}$", message = "Faculty ID must be 3 uppercase letters followed by 5 digits")
    @Indexed(unique = true)
    private String facultyId;

    @NotBlank(message = "Name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z\\s]{2,50}$", message = "Name must be 2-50 characters long and contain only letters and spaces")
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


    public String getId() {
        if(id == null){
            return "null";
        }
        return id.toString();
    }

    public Teachers(String message){
        this.message = message;
    }
}
