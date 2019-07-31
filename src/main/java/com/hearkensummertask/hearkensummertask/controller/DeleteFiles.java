package com.hearkensummertask.hearkensummertask.controller;

import com.hearkensummertask.hearkensummertask.service.DeleteFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 删除    文件
 */
@Controller
public class DeleteFiles {
    @Autowired
    DeleteFilesService deleteFiles;

    @RequestMapping("/deletepublicfile")
    public String deletepublicfile(@RequestParam("fid") Integer fid) {

        deleteFiles.deletePublicFiles(fid);


        return "treedata";
    }


    @RequestMapping("/deleteprivatefile")
    public String deleteprivatefile(@RequestParam("fid") Integer fid) {
        deleteFiles.deletePrivateFiles(fid);
        return "treedata";
    }


}
