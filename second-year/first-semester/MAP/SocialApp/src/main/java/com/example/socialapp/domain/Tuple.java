package com.example.socialapp.domain;

import java.io.Serializable;

public record Tuple<T1, T2>(T1 first, T2 second) implements Serializable {
}
