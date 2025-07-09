package com.example.iadst.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignedStudent {
    private ObjectId studentId;
    private List<ObjectId> questionIds;
}
