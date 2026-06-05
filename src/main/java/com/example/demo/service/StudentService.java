package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repo.StudentRepo;

@Service
public class StudentService {

    @Autowired
    private StudentRepo repo;

    public Student save(Student student){
        return repo.save(student);
    }

    public Student login(String email,String password){
        Student student = repo.findByEmail(email);

        if(student != null &&
           student.getPassword().equals(password)){
            return student;
        }

        return null;
    }
}
