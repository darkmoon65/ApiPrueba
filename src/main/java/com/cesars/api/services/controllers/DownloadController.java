package com.cesars.api.services.controllers;
import com.cesars.api.services.models.MorthyModel;
import com.cesars.api.services.services.ConsultaService;

import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DownloadController {
    @Autowired
    ConsultaService consultaService;

    @RequestMapping(value ="/zip", method = RequestMethod.GET)
    public void downLoadSearchFiles(HttpServletResponse response)
    {
        MorthyModel ejemplo = new MorthyModel();
        ejemplo.setName("character");
        ejemplo.setId(473);
        String data = consultaService.getData(ejemplo);

        Map<String, String> jsonList= new HashMap<>();
        jsonList.put("F1", data);      

        try{
            response.setHeader("Pragma", "public");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Content-type", "application-download");
            response.setHeader("Content-Disposition", "attachment; filename=ZipDataMorthyApi"+new SimpleDateFormat("yyyyMMdd").format(new Date())+".zip");
            response.setHeader("Content-Transfer-Encoding", "binary");
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ZipOutputStream zipOutputStream = new ZipOutputStream(baos);
            for (Entry<String, String> entry : jsonList.entrySet()) {
                ZipEntry e = new ZipEntry(entry.getKey()+new SimpleDateFormat("yyyyMMdd").format(new Date())+"."+"json");
                e.setSize(entry.getValue().length());
                e.setTime(System.currentTimeMillis());
                zipOutputStream.putNextEntry(e);
                InputStream is = new ByteArrayInputStream(entry.getValue().getBytes());
                org.apache.tomcat.util.http.fileupload.IOUtils.copy(is, zipOutputStream);
                is.close();
                zipOutputStream.closeEntry();
            }

            zipOutputStream.close();
            byte[] zipBytes = baos.toByteArray();
            OutputStream outStream = response.getOutputStream();
            outStream.write(zipBytes);
            outStream.close();
            response.flushBuffer();
        }

        catch(Exception e){
        }

    }
}
