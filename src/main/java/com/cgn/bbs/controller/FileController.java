package com.cgn.bbs.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cgn.bbs.beans.Files;
import com.cgn.bbs.common.Log;
import com.cgn.bbs.services.inte.IAllService;
import com.cgn.bbs.util.Utils;

@Controller
public class FileController {
	@Autowired
	private Log log;
	@Autowired
	private IAllService allService;
	/*
     * 获取file.html页面
     */
    /*@RequestMapping("file")
    public String file(){
        return "/file";
    }*/
    
    /**
     * 实现文件上传
     * */
    @RequestMapping("/upload")
    @ResponseBody 
    public String fileUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request){
    	System.out.println("即将上传文件---------->>>>>");
    	try {
	        if(file.isEmpty()){
	            return "false";
	        }
	        String fileName = file.getOriginalFilename();
	        int size = (int) file.getSize();
	        System.out.println(fileName + "-->" + size);
	        
	        String path = request.getSession().getServletContext().getRealPath("fileDir")+File.separator+UUID.randomUUID().toString()+File.separator+UUID.randomUUID().toString();
	        String usePath=path.substring(path.indexOf("fileDir")-1)+File.separator+fileName;
	        //        String path = "C:/bbsFile/"+UUID.randomUUID().toString()+"/"+UUID.randomUUID().toString();
	        String filePath=path + "/" + fileName;
	        File dest = new File(filePath);
//	        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
//	            System.out.println(dest.getParentFile().mkdir());
//	        }
	        mkdir(dest);
	            file.transferTo(dest); //保存文件
	            System.out.println("文件地址:"+filePath);
	            System.out.println("项目应用地址:"+usePath);usePath=usePath.replace("\\", "/");
	            String fileId=allService.savefile(usePath);
	            return fileId+"***"+usePath;
        } catch (Exception e) {
        	log.Warning(Log.WARNING, "FileController中fileUpload上传文件异常："+Utils.getStackTrace(e), e);
            e.printStackTrace();
            return "false";
        }
    }
    private void mkdir(File file){
    	while(!file.getParentFile().mkdir()){
    		mkdir(file.getParentFile());
     }
    }
    /**
     * 获取multifile.html页面
     */
    @RequestMapping("multifile")
    public String multifile(){
        return "/multifile";
    }
    
    /**
     * 实现多文件上传
     * */
    @RequestMapping(value="multifileUpload",method=RequestMethod.POST) 
//　　public @ResponseBody String multifileUpload(@RequestParam("fileName")List<MultipartFile> files)
    public @ResponseBody String multifileUpload(HttpServletRequest request){
        
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("fileName");
        
        if(files.isEmpty()){
            return "false";
        }

        String path = "F:/test" ;
        
        for(MultipartFile file:files){
            String fileName = file.getOriginalFilename();
            int size = (int) file.getSize();
            System.out.println(fileName + "-->" + size);
            
            if(file.isEmpty()){
                return "false";
            }else{        
                File dest = new File(path + "/" + fileName);
                if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                }catch (Exception e) {
                    e.printStackTrace();
                    return "false";
                } 
            }
        }
        return "true";
    }
    
    @RequestMapping("/download")
    public String downLoad(int fileId,HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException{
    	Files files=allService.getFileById(fileId);
    	String filename=files.getFilePath().substring(files.getFilePath().lastIndexOf("/")+1);
//        String filePath = "C:/开发/workspace_eclipse/EmploymentForum/src/main/webapp" ;
//        String clazzFilePath = Class.class.getClass().getResource("/").getPath();
        String filePath=request.getSession().getServletContext().getRealPath("");
//        String filePath=new String(clazzFilePath.getBytes(),"UTF-8");
        System.out.println(filePath);
        File file = new File(filePath + files.getFilePath());
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
            
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;
            
            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file); 
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("----------file download" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
