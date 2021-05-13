package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Student> students = new ArrayList<>();
    private int age;
    private String name;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        Student student = null;
        for (Student st : students) {
            if (st.getAverageGrade() == averageGrade) {
                student = st;
                break;
            }
        }
        return student;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        Student student = null;
        double maxGrade = 0;
        for (Student st : students) {
            if (st.getAverageGrade() > maxGrade) {
                student = st;
                maxGrade = st.getAverageGrade();
            }
        }
        return student;
    }

    public Student getStudentWithMinAverageGrade() {
        //TODO:
        Student student = null;
        double minGrade = students.get(0).getAverageGrade();
        for (Student st : students) {
            if (st.getAverageGrade() < minGrade) {
                student = st;
                minGrade = st.getAverageGrade();
            }
        }
        return student;
    }

    public void expel(Student student) {
        students.remove(student);
    }
}