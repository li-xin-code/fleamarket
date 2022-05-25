package com.lixin.servlet.common;

import com.lixin.common.utils.SystemUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.Function;

/**
 * 响应页面请求
 * 初始化阶段扫描 {web/page/}目录加载页面文件
 * 文件名和路径存入viewMap
 *
 * @author lx
 * @date 2022/4/5
 */
@WebServlet(name = "viewServlet", urlPatterns = "/view/*")
public class ViewServlet extends ScanFileServlet {
    private static final long serialVersionUID = 1458049632137078049L;

    private final static String DIR = "/page/";
    private final String SUFFIX = ".html";
    private final Set<String> views = new HashSet<>(10);

    private final String VIEW = "";
    private final String LIST = "list";
    private final Map<String, Function<List<String>, Boolean>> dispatcher = new HashMap<>();

    @Override
    public void init() {
        dispatcher.put(VIEW, list -> list.size() == 1 && views.contains(list.get(0)));
        dispatcher.put(LIST, list -> list.size() == 1 && LIST.equals(list.get(0)));
        List<String> scan = scan(DIR);
        scan.forEach(item -> {
            if (item.endsWith(SUFFIX)) {
                views.add(item.substring(0, item.indexOf(".")));
            }
        });
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String uri = req.getRequestURI();
        List<String> parse = parse(uri);
        if (dispatcher.get(VIEW).apply(parse)) {
            String view = parse.get(0);
            InputStream in = getServletContext().getResourceAsStream(DIR + view + SUFFIX);
            OutputStream out = resp.getOutputStream();
            SystemUtils.inputToOutPut(in, out);
        }
        if (dispatcher.get(LIST).apply(parse)) {
            PrintWriter writer = resp.getWriter();
            writer.print(views);
        }
    }


    private List<String> parse(String uri) {
        String prefix = "/view/";
        String substring = uri.substring(uri.indexOf(prefix) + prefix.length());
        String[] split = substring.split("/");
        return Arrays.asList(split);
    }


}
