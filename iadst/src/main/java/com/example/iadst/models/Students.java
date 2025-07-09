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
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "students")
public class students {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String rollNumber;

    @NotEmpty
    private String name;

    public students() {}

    public students(ObjectId id, String rollNumber, String name, String email) {
        this.id = id;
        this.rollNumber = rollNumber;
        this.name = name;
        Email = email;
    }

    @NotBlank(message = "Email can't be empty")
    @Email(message = "This is not a valid email")
    private String Email;


    public String getId() {
        return id.toString();
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        return "students{" +
                "id=" + id +
                ", rollNumber='" + rollNumber + '\'' +
                ", name='" + name + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }
}
