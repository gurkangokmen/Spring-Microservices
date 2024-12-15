package com.unfaithfultechnology.data_jpa.course.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJdbcRepository {

    @Autowired
    private JdbcTemplate springJdbcTemplate;

    // TEXT BLOCK
    private static String INSERT_QUERY = """
            insert into course (id,name,author) 
            values (1,'Learn AWS','in28minutes');
            """;
    public void insert(){
        springJdbcTemplate.update(INSERT_QUERY);
    }
}
