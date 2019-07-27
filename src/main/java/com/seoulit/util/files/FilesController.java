package com.seoulit.util.files;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping
public class FilesController {

    @Resource
    FileUtil fileUtil;

    @Resource
    FilesDAO filesDAO;

    @GetMapping(value="/api/files")
    @ResponseBody
    public ResponseEntity<?> getFiles() {

        List<FilesTO> list = filesDAO.selectFiles();

        return ResponseEntity.status(HttpStatus.OK).body(list);

    }

    @PostMapping(value="/api/fileupload")
    @ResponseBody
    public ResponseEntity<?> fileUpload(MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {

            List<FilesTO> list = fileUtil.parseFileInfo(multipartHttpServletRequest);

            if(!CollectionUtils.isEmpty(list)){
                filesDAO.insertFileList(list);
            }

            return ResponseEntity.ok().build();
        
    }

  
    @PostMapping(value="/api/filedownload")
    @ResponseBody
    public void fileDownload(@RequestBody Map<String,Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String storedFileName = (String) param.get("storedFileName");
        String originalFileName = (String) param.get("originalFileName");
        String fileUrl = (String) param.get("fileUrl");
        File file = new File(fileUrl, storedFileName);
 
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
     
        //User-Agent : 어떤 운영체제로  어떤 브라우저를 서버( 홈페이지 )에 접근하는지 확인함
        String header = request.getHeader("User-Agent");
        String fileName;
        
        if ((header.contains("MSIE")) || (header.contains("Trident")) || (header.contains("Edge"))) {
            //인터넷 익스플로러 10이하 버전, 11버전, 엣지에서 인코딩 
            fileName = URLEncoder.encode(originalFileName, "UTF-8");
        } else {
            //나머지 브라우저에서 인코딩
            fileName = new String(originalFileName.getBytes("UTF-8"), "iso-8859-1");
        }
        //형식을 모르는 파일첨부용 contentType
        response.setContentType("application/octet-stream");
        //다운로드와 다운로드될 파일이름
        response.setHeader("Content-Disposition", "attachment; filename=\""+ fileName + "\"");

        response.setHeader("filename", URLEncoder.encode(fileName, "UTF-8"));
        //파일복사
        FileCopyUtils.copy(in, response.getOutputStream());
        in.close();
        response.getOutputStream().flush();
        response.getOutputStream().close();
      
        
    }

    @PostMapping(value="/api/filedelete")
    @ResponseBody
    public ResponseEntity<?> fileDelete(@RequestBody Map<String,Object> param) throws Exception {

        String storedFileName = (String) param.get("storedFileName");
        String fileUrl = (String) param.get("fileUrl");

        File file = new File(fileUrl, storedFileName);
        if(file.exists()) {
        file.delete();
        }
        return ResponseEntity.ok().build();
    }
    
}