package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.CourseEntity;
import com.example.demo.repo.CourseRepo;

@Controller
public class CourseController {

    @Autowired
    private CourseRepo courseRepo;
    

    @GetMapping("/courses")
    public String courses(Model model){

        model.addAttribute(
                "courses",
                courseRepo.findAll()
        );

        return "course/courses";
    }

    @GetMapping("/admin/courses")
    public String courseList(Model model){

        model.addAttribute(
                "courses",
                courseRepo.findAll()
        );

        return "Admin/course-list";
    }

    @GetMapping("/admin/course/add")
    public String addCourse(Model model) {

        model.addAttribute("course", new CourseEntity());

        return "Admin/course-form";
    }

    @PostMapping("/admin/course/save")
    public String saveCourse(@ModelAttribute CourseEntity course){
        courseRepo.save(course);
        return "redirect:/admin/courses";
    }

    @GetMapping("/admin/course/edit/{id}")
    public String editCourse(@PathVariable Long id, Model model){
        model.addAttribute(
            "course",
            courseRepo.findById(id).orElse(null)
        );
        return "Admin/course-form";
    }

    @GetMapping("/admin/course/delete/{id}")
    public String deleteCourse(@PathVariable Long id){
        courseRepo.deleteById(id);
        return "redirect:/admin/courses";
    }
}