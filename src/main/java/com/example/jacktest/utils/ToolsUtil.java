package com.example.jacktest.utils;

import java.io.IOException;
import java.sql.Timestamp;

import com.example.jacktest.dao.PagingVariables;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ToolsUtil {
    
    public Timestamp currentDateInTimestamp();

    public String timestampToString(Timestamp timeValue);

    public PagingVariables returnPagingVariables(Page page, Pageable pageable);

    public String uploadFile(MultipartFile filedata, String uploadPath) throws IOException;

    public boolean deleteFileIfExists(String savedFileName, String filePath) throws IOException;

}