package com.lixin.servlet.img;

import com.alibaba.fastjson2.JSONObject;
import com.lixin.common.exception.NotExpectedException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import static com.lixin.common.utils.SystemUtils.*;

/**
 * @author lixin
 */
@WebServlet(name = "uploadServlet", urlPatterns = "/upload")
@MultipartConfig(location = UPLOAD_PATH,
        maxFileSize = MAX_FILE_SIZE,
        maxRequestSize = MAX_REQUEST_SIZE)
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1815231200874094764L;

    @Override
    public void init() {
        File file = new File(UPLOAD_PATH);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            if (!mkdirs) {
                throw new NotExpectedException(UPLOAD_PATH + "mkdirs fail.");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part file = req.getPart("file");
        if (file == null) {
            throw new NotExpectedException("file is null.");
        }
        if (!CONTENT_TYPE_MAP.containsValue(file.getContentType().toLowerCase())) {
            throw new NotExpectedException("Unsupported file type.");
        }
        String fileName = file.getSubmittedFileName();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String newFileName = uuid + suffix;
        file.write(newFileName);
        PrintWriter writer = resp.getWriter();
        JSONObject result = new JSONObject();
        result.put("path", ImageServlet.URL_PATTERNS + newFileName);
        writer.print(result);
    }

}
