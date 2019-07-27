package com.seoulit.util.files;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FilesDAO {
    
    void insertFileList(List<FilesTO> list) throws Exception;

    List<FilesTO> selectFiles();

}