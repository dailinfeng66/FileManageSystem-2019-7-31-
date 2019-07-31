package com.hearkensummertask.hearkensummertask.dao;

import com.hearkensummertask.hearkensummertask.bean.Jurisdiction;
import com.hearkensummertask.hearkensummertask.bean.PublicFiles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicFilesDao extends JpaRepository<PublicFiles,Integer> {
    PublicFiles findByFileid(Integer id);
    PublicFiles findByFilename(String name);
//    PublicFiles findByFilepath(String name);
}
