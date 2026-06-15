package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entity.CourseEntity;
import com.example.demo.repo.CourseRepo;

@Component
public class CourseDataLoader implements CommandLineRunner {

    private final CourseRepo courseRepo;

    public CourseDataLoader(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }
    private void saveCourse(String name,
            String code,
            Double admissionFee,
            Double semesterFee,
            Double totalFee,
            String duration) {

CourseEntity course = new CourseEntity();

course.setCourseName(name);
course.setCourseCode(code);
course.setAdmissionFee(admissionFee);
course.setSemesterFee(semesterFee);
course.setTotalFee(totalFee);
course.setDuration(duration);

courseRepo.save(course);
    }
    @Override
    public void run(String... args) {

        if(courseRepo.count() == 0) {

            // ================= BCA =================

            saveCourse("BCA Full Stack Development", "BCA001", 10000.0, 27500.0, 175000.0, "3 Years");
            saveCourse("BCA AI & Machine Learning", "BCA002", 10000.0, 30000.0, 190000.0, "3 Years");
            saveCourse("BCA Cloud Computing", "BCA003", 10000.0, 29000.0, 185000.0, "3 Years");
            saveCourse("BCA Mobile App Development", "BCA004", 10000.0, 28000.0, 180000.0, "3 Years");
            saveCourse("BCA Cyber Security", "BCA005", 10000.0, 31000.0, 195000.0, "3 Years");
            saveCourse("BCA Data Science", "BCA006", 10000.0, 30000.0, 190000.0, "3 Years");

            // ================= BBA =================

            saveCourse("BBA Finance", "BBA001", 10000.0, 25000.0, 160000.0, "3 Years");
            saveCourse("BBA Marketing", "BBA002", 10000.0, 24000.0, 155000.0, "3 Years");
            saveCourse("BBA HR Management", "BBA0003", 10000.0, 23000.0, 150000.0, "3 Years");
            saveCourse("BBA Business Analytics", "BBA004", 10000.0, 26000.0, 165000.0, "3 Years");
            saveCourse("BBA International Business", "BBA005", 10000.0, 25500.0, 162000.0, "3 Years");
            saveCourse("BBA Entrepreneurship", "BBA006", 10000.0, 25000.0, 160000.0, "3 Years");

            // ================= BA =================

            saveCourse("BA English", "BA001", 5000.0, 17500.0, 110000.0, "3 Years");
            saveCourse("BA History", "BA002", 5000.0, 17000.0, 107000.0, "3 Years");
            saveCourse("BA Political Science", "BA003", 5000.0, 18000.0, 113000.0, "3 Years");
            saveCourse("BA Psychology", "BA004", 5000.0, 19000.0, 119000.0, "3 Years");
            saveCourse("BA Sociology", "BA005", 5000.0, 16500.0, 104000.0, "3 Years");
            saveCourse("BA Economics", "BA006", 5000.0, 18500.0, 116000.0, "3 Years");

            // ================= BCOM =================

            saveCourse("BCom Accounting", "BCOM001", 8000.0, 18000.0, 116000.0, "3 Years");
            saveCourse("BCom Banking", "BCOM002", 8000.0, 19000.0, 122000.0, "3 Years");
            saveCourse("BCom Taxation", "BCOM003", 8000.0, 18500.0, 119000.0, "3 Years");
            saveCourse("BCom Finance", "BCOM004", 8000.0, 20000.0, 128000.0, "3 Years");
            saveCourse("BCom Business Management", "BCOM005", 8000.0, 21000.0, 134000.0, "3 Years");
            saveCourse("BCom E-Commerce", "BCOM006", 8000.0, 22000.0, 140000.0, "3 Years");

            // ================= BSC =================

            saveCourse("BSc Physics", "BSC001", 8000.0, 18000.0, 116000.0, "3 Years");
            saveCourse("BSc Chemistry", "BSC002", 8000.0, 19000.0, 122000.0, "3 Years");
            saveCourse("BSc Biotechnology", "BSC003", 8000.0, 21000.0, 134000.0, "3 Years");
            saveCourse("BSc Computer Science", "BSC004", 8000.0, 22000.0, 140000.0, "3 Years");
            saveCourse("BSc Mathematics", "BSC005", 8000.0, 18000.0, 116000.0, "3 Years");
            saveCourse("BSc Information Technology", "BSC006", 8000.0, 23000.0, 146000.0, "3 Years");

            // ================= BTECH =================

            saveCourse("BTech Artificial Intelligence", "BTECH001", 15000.0, 60000.0, 495000.0, "4 Years");
            saveCourse("BTech Cyber Security", "BTECH002", 15000.0, 55000.0, 455000.0, "4 Years");
            saveCourse("BTech Data Science", "BTECH003", 15000.0, 57500.0, 475000.0, "4 Years");
            saveCourse("BTech Computer Science", "BTECH004", 15000.0, 50000.0, 415000.0, "4 Years");
            saveCourse("BTech Mechanical Engineering", "BTECH005", 15000.0, 45000.0, 375000.0, "4 Years");
            saveCourse("BTech Civil Engineering", "BTECH006", 15000.0, 42500.0, 355000.0, "4 Years");
            saveCourse("BTech Electrical Engineering", "BTECH007", 15000.0, 47500.0, 395000.0, "4 Years");
            saveCourse("BTech Electronics Engineering", "BTECH008", 15000.0, 52500.0, 435000.0, "4 Years");

            // ================= DIPLOMA =================

            saveCourse("Diploma Computer Science", "DIP001", 5000.0, 12000.0, 77000.0, "3 Years");
            saveCourse("Diploma Mechanical", "DIP0002", 5000.0, 11500.0, 74000.0, "3 Years");
            saveCourse("Diploma Civil", "DIP003", 5000.0, 11000.0, 71000.0, "3 Years");
            saveCourse("Diploma Electrical", "DIP004", 5000.0, 13000.0, 83000.0, "3 Years");
            saveCourse("Diploma Electronics", "DIP005", 5000.0, 12500.0, 80000.0, "3 Years");
            saveCourse("Diploma Automobile", "DIP006", 5000.0, 13500.0, 86000.0, "3 Years");

            // ================= MA =================

            saveCourse("MA English", "MA001", 10000.0, 22500.0, 100000.0, "2 Years");
            saveCourse("MA History", "MA002", 10000.0, 21000.0, 94000.0, "2 Years");
            saveCourse("MA Political Science", "MA003", 10000.0, 23000.0, 102000.0, "2 Years");
            saveCourse("MA Sociology", "MA004", 10000.0, 20000.0, 90000.0, "2 Years");
            saveCourse("MA Psychology", "MA005", 10000.0, 25000.0, 110000.0, "2 Years");
            saveCourse("MA Hindi", "MA006", 10000.0, 19000.0, 86000.0, "2 Years");

            // ================= MBA =================

            saveCourse("MBA Marketing", "MBA001", 15000.0, 60000.0, 255000.0, "2 Years");
            saveCourse("MBA Finance", "MBA002", 15000.0, 65000.0, 275000.0, "2 Years");
            saveCourse("MBA Human Resource", "MBA003", 15000.0, 55000.0, 235000.0, "2 Years");
            saveCourse("MBA Business Analytics", "MBA004", 15000.0, 70000.0, 295000.0, "2 Years");
            saveCourse("MBA International Business", "MBA005", 15000.0, 67500.0, 285000.0, "2 Years");
            saveCourse("MBA Entrepreneurship", "MBA006", 15000.0, 62500.0, 265000.0, "2 Years");

         // ================= MCA =================

            saveCourse("MCA Advanced Java", "MCA001", 12000.0, 42500.0, 182000.0, "2 Years");
            saveCourse("MCA AI Engineering", "MCA002", 12000.0, 47500.0, 202000.0, "2 Years");
            saveCourse("MCA Cyber Security", "MCA003", 12000.0, 45000.0, 192000.0, "2 Years");
            saveCourse("MCA Software Architecture", "MCA004", 12000.0, 44000.0, 188000.0, "2 Years");
            saveCourse("MCA Cloud Computing", "MCA005", 12000.0, 46000.0, 196000.0, "2 Years");
            saveCourse("MCA Data Science", "MCA006", 12000.0, 46500.0, 198000.0, "2 Years");

            // ================= MSC =================

            saveCourse("MSc Physics", "MSC001", 10000.0, 25000.0, 110000.0, "2 Years");
            saveCourse("MSc Chemistry", "MSC002", 10000.0, 29000.0, 126000.0, "2 Years");
            saveCourse("MSc Mathematics", "MSC003", 10000.0, 27500.0, 120000.0, "2 Years");
            saveCourse("MSc Computer Science", "MSC004", 10000.0, 35000.0, 150000.0, "2 Years");
            saveCourse("MSc Biotechnology", "MSC005", 10000.0, 34000.0, 146000.0, "2 Years");
            saveCourse("MSc Environmental Science", "MSC006", 10000.0, 26000.0, 114000.0, "2 Years");

            // ================= MTECH =================

            saveCourse("MTech Artificial Intelligence", "MTECH002", 20000.0, 75000.0, 320000.0, "2 Years");
            saveCourse("MTech Computer Science", "MTECH002", 20000.0, 70000.0, 300000.0, "2 Years");
            saveCourse("MTech Data Science", "MTECH003", 20000.0, 72500.0, 310000.0, "2 Years");
            saveCourse("MTech Cyber Security", "MTECH004", 20000.0, 67500.0, 290000.0, "2 Years");
            saveCourse("MTech Mechanical Engineering", "MTECH005", 20000.0, 60000.0, 260000.0, "2 Years");
            saveCourse("MTech Civil Engineering", "MTECH006", 20000.0, 55000.0, 240000.0, "2 Years");
        }
    }}