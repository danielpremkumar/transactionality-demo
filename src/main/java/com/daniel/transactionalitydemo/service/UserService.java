package com.daniel.transactionalitydemo.service;

import com.daniel.transactionalitydemo.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    static Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void insert(List<User> users) {
        for (User user : users) {
            logger.info("Inserting User with the Name: " + user.getName());
            jdbcTemplate.update("insert into USER(Name, Dept, Salary) values (?,?,?)",
                    preparedStatement -> {
                        preparedStatement.setString(1,user.getName());
                        preparedStatement.setString(2,user.getDept());
                        preparedStatement.setLong(3,user.getSalary());
                    });
        }
    }

    public List<User> getUsers() {
        logger.info("Retrieving all users....");
        return jdbcTemplate.query("select * from USER",
                (resultSet, i) -> new User( resultSet.getString("Name"), resultSet.getString("Dept"),  resultSet.getLong("Salary")));
    }
}
