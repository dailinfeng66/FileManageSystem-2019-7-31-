package com.hearkensummertask.hearkensummertask.dao;

import com.hearkensummertask.hearkensummertask.bean.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,String> {
    User findByUserid(String userid);

}
