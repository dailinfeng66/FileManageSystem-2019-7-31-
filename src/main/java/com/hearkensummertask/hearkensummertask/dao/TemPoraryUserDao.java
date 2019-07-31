package com.hearkensummertask.hearkensummertask.dao;

import com.hearkensummertask.hearkensummertask.bean.TemporaryUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemPoraryUserDao extends JpaRepository<TemporaryUser,String> {
    TemporaryUser findByUserid(String userid);
}
