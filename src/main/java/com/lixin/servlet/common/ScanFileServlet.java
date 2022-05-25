package com.lixin.servlet.common;

import com.lixin.common.exception.NotExpectedException;

import javax.servlet.http.HttpServlet;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lixin
 */
public abstract class ScanFileServlet extends HttpServlet {

    private static final long serialVersionUID = 8637749068362396167L;

    protected List<String> scan(String dir) {
        String path;
        URL url;
        try {
            url = getServletContext().getResource(dir);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new NotExpectedException("scan fail.");
        }
        if (url == null) {
            throw new NotExpectedException("scan fail.");
        }
        path = url.getFile();
        File file = new File(path);
        File[] files = file.listFiles(File::isFile);
        List<String> list = new ArrayList<>(10);
        assert files != null;
        Arrays.asList(files).forEach(f -> list.add(f.getName()));
        return list;
    }

}
