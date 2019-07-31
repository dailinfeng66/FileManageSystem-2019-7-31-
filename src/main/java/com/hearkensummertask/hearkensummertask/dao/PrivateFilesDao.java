package com.hearkensummertask.hearkensummertask.dao;

import com.hearkensummertask.hearkensummertask.bean.PrivateFiles;
import com.hearkensummertask.hearkensummertask.bean.PublicFiles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrivateFilesDao extends JpaRepository<PrivateFiles,Integer> {

    List<PrivateFiles> findAllByOwner(String owner);
    PrivateFiles findByFileid(Integer id);
    PrivateFiles findByFilename(String name);
}
