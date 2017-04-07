package com.zum.util;

import com.zum.domain.Board;
import com.zum.domain.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.UUID;

/**
 * Created by joeylee on 2017-04-07.
 */
public class FileUploadUtil {

    static Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);

    private static final String ATTACH_PATH = "/upload/";


    public static Image saveMultipartFile(MultipartHttpServletRequest multipartRequest,
                                         Board board) throws IOException {

        HttpSession session = multipartRequest.getSession();
        String root = session.getServletContext().getRealPath("/");
        String filePath = root + ATTACH_PATH;

        File dir = new File(filePath);
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }


        MultipartFile mpf = multipartRequest.getFile("upload");

        if (!mpf.isEmpty()) {

            String genId = UUID.randomUUID().toString();

            String originalFilename = mpf.getOriginalFilename();

            String exc = getFileExtension(mpf);

            String fileFullPath = filePath + genId + "." + exc; //파일 전체 경로

            String fileName = ATTACH_PATH  + genId  + "." + exc;

            long fileSize = mpf.getSize();

            mpf.transferTo(new File(fileFullPath)); //파일저장

            Image image = new Image();
            image.insertImage(originalFilename, fileName, fileSize, board);

            return image;

        }

        return null;
    }




    private static String getFileExtension(MultipartFile mpf) {

        String originalFilename = mpf.getOriginalFilename(); //파일명

        String exc = originalFilename.substring(
                originalFilename.lastIndexOf(".") + 1, originalFilename.length());

        return exc;
    }






}
