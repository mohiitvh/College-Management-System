package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.CourseEntity;

public interface CourseRepo extends JpaRepository<CourseEntity, Long> {

CourseEntity findByCourseName(String courseName);


}