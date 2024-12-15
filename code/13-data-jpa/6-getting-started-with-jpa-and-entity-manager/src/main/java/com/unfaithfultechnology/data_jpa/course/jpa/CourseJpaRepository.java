package com.unfaithfultechnology.data_jpa.course.jpa;

import com.unfaithfultechnology.data_jpa.course.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CourseJpaRepository {

    //@Autowired
    @PersistenceContext
    private EntityManager entityManager;


    public void insert(Course course){
        entityManager.merge(course);
    }

    public void delete(long id){
        Course course = entityManager.find(Course.class,id);
        entityManager.remove(course);
    }

    public Course find(long id){
        return entityManager.find(Course.class,id);
    }


}
