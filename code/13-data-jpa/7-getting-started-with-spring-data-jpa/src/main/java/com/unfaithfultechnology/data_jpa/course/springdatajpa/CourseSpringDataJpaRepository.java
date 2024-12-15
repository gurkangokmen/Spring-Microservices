package com.unfaithfultechnology.data_jpa.course.springdatajpa;

import com.unfaithfultechnology.data_jpa.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseSpringDataJpaRepository extends JpaRepository<Course, Long> {
}
