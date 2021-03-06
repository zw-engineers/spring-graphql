package com.graphql.demo.domain;

import java.util.Objects;

public class Degree {
    private String degree;

    public Degree(String degree) {
        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Degree degree1 = (Degree) o;
        return Objects.equals(degree, degree1.degree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(degree);
    }

    @Override
    public String toString() {
        return "Degree{" +
                "degree='" + degree + '\'' +
                '}';
    }
}
