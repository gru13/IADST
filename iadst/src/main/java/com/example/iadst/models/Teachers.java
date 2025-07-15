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
@Document(collection = "teachers")
public class Teachers {
    @Id
    private ObjectId id;

    @NotBlank
    @Indexed(unique = true)
    private String facultyId;

    @NotBlank
    private String name;

    @Email(message = "This is not a valid email")
    @Indexed
    private String email;

    @Pattern(
            regexp = "^(\\+91|91|0)?[6-9]\\d{9}$",
            message = "Invalid Indian phone number"
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
