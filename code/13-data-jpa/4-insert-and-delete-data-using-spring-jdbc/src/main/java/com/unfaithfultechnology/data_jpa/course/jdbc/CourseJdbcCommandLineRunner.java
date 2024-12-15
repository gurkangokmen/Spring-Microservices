package com.unfaithfultechnology.data_jpa.course.jdbc;

import com.unfaithfultechnology.data_jpa.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//
// Interface CommandLineRunner
//
// It used to indicate that a bean should run
//

@Component
public class CourseJdbcCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CourseJdbcRepository courseJdbcRepository;

    @Override
    public void run(String... args) throws Exception {
        courseJdbcRepository.insert(new Course(1, "Learn AWS Now!", "in28minutes"));
        courseJdbcRepository.insert(new Course(2, "Learn Azure Now!", "in28minutes"));
        courseJdbcRepository.insert(new Course(3, "Learn DevOps Now!", "in28minutes"));

        courseJdbcRepository.delete(1);
    }
}
