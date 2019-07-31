package com.hearkensummertask.hearkensummertask.controller;

import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Controller
public class converttest {
    @RequestMapping("/convert")
    public void test() throws Exception {
        String docxFilePath = "D:\\新建word文档.doc";
        String htmlPath = "D:\\转化成的html.html";

        WordprocessingMLPackage wmp = WordprocessingMLPackage.load(new File(docxFilePath));
        Docx4J.toHTML(wmp, "html/resources", "resources", new FileOutputStream(new File(htmlPath)));
    }


}
