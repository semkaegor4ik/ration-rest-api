package com.university.fqw.rationrestapi.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
public enum Gender {
    MALE(66, 13.7, 5, 6.8),
    FEMALE(655, 9.6, 1.8, 4.7);
    private final double startCoefficient;
    private final double weightCoefficient;
    private final double heightCoefficient;
    private final double ageCoefficient;

}
