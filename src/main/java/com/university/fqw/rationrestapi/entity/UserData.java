package com.university.fqw.rationrestapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    private Gender gender;
    private int weight;
    private int height;
    private int age;
    private Activity activity;
}
