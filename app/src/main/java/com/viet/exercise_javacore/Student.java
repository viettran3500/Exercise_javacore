package com.viet.exercise_javacore;

public class Student implements Comparable<Student> {

    private String name;
    private int yearOfBird;
    private String phoneNumber;
    private String specialized;

    public Student(String name, int yearOfBird, String phoneNumber, String specialized) {
        this.name = name;
        this.yearOfBird = yearOfBird;
        this.phoneNumber = phoneNumber;
        this.specialized = specialized;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBird() {
        return yearOfBird;
    }

    public void setYearOfBird(int yearOfBird) {
        this.yearOfBird = yearOfBird;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSpecialized() {
        return specialized;
    }

    public void setSpecialized(String specialized) {
        this.specialized = specialized;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", yearOfBird=" + yearOfBird +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", specialized='" + specialized + '\'' +
                '}';
    }

    @Override
    public int compareTo(Student student) {
        return this.getYearOfBird() - student.getYearOfBird();
    }
}
