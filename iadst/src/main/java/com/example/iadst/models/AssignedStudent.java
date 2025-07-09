package com.example.iadst.models;

import org.bson.types.ObjectId;

import java.util.List;

public class AssignedStudent {
    private ObjectId studentId;
    private List<ObjectId> questionIds;
}
