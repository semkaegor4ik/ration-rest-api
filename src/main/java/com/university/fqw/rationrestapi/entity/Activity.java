package com.university.fqw.rationrestapi.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Activity {
    MINIMUM(1),
    LOW(1.3),
    NORMAL(1.6),
    HIGH(1.7),
    EXTRA(1.9);

    private final double coefficient;
}
