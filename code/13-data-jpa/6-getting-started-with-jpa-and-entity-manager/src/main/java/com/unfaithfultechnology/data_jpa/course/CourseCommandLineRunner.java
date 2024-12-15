package com.unfaithfultechnology.data_jpa.course;

import com.unfaithfultechnology.data_jpa.course.Course;
import com.unfaithfultechnology.data_jpa.course.jdbc.CourseJdbcRepository;
import com.unfaithfultechnology.data_jpa.course.jpa.CourseJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//
// Interface CommandLineRunner
//
// It used to indicate that a bean should run
//

@Component
public class CourseCommandLineRunner implements CommandLineRunner {

    //@Autowired
    //private CourseJdbcRepository repository;

    @Autowired
    private CourseJpaRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.insert(new Course(1, "Learn AWS Now!", "in28minutes"));
        repository.insert(new Course(2, "Learn Azure Now!", "in28minutes"));
        repository.insert(new Course(3, "Learn DevOps Now!", "in28minutes"));

        repository.delete(1);

        System.out.println("Course id 2 -> " + repository.find(2));
        System.out.println("Course id 3 -> " + repository.find(3));
    }
}
