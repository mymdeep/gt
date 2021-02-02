package com.example.demo.controller;

import com.example.demo.bean.OperatingAccount;
import com.example.demo.services.OperatingAccountServices;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebFilter(filterName = "LoginFilter",
    urlPatterns = "*.html", /*通配符（*）表示对所有的web资源进行拦截*/
    initParams = {
        @WebInitParam(name = "charset", value = "utf-8")    /*这里可以放一些初始化的参数*/
    })

public class LoginFilter implements Filter {
    @Resource
    private OperatingAccountServices operatingAccountServices;

    private String filterName;
    private String charset;

    @Override
    public void init(FilterConfig config) {
        /*初始化方法  接收一个FilterConfig类型的参数 该参数是对Filter的一些配置*/
        filterName = config.getFilterName();
        charset = config.getInitParameter("charset");
        System.out.println("过滤器初始化：" + filterName);
        System.out.println("字符集编码：" + charset);
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁：" + filterName);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
        throws ServletException, IOException {
        /*过滤方法 主要是对request和response进行一些处理，然后交给下一个过滤器或Servlet处理*/
        req.setCharacterEncoding(charset);
        resp.setCharacterEncoding(charset);

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String url = request.getRequestURI();
        System.out.println("请求的url：" + url);

        /*处理请求页面*/
        int idx = url.lastIndexOf("/");
        String endWith = url.substring(idx + 1);

        /*判断请求页面*/
        if (!endWith.equals("login.html")) {
            System.out.println("不是登录页面，进行拦截处理");
            if (!isLogin(request)) {
                System.out.println("未登录或者账号密码错误，跳转到登录界面");
                response.setContentType("text/html; charset=UTF-8"); // 转码
                PrintWriter out = response.getWriter();
                out.flush();
                out.println("<script>");
                out.println("alert('未登录！');");
                out.println("window.location.href='login.html';");
                out.println("</script>");
                out.close();
            } else {
                System.out.println("已登录");
                chain.doFilter(req, resp);
            }
        } else {
            System.out.println("是登录页面，不进行拦截处理");
            chain.doFilter(req, resp);
        }
    }

    private boolean isLogin(HttpServletRequest request) {
        String username = "";
        String password = "";

        Cookie[] cookies = request.getCookies();    // 获取一个cookies数据，方便后续操作
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) { // 遍历cookies，寻找指定内容
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                } else if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }
        }

        // 通过匹配cookie中的username和password，来判断是否登录过
        if (username.equals("") || password.equals("")) {
            return false;
        } else {
            OperatingAccount oa = new OperatingAccount();
            oa.setUsername(username);
            Map<String, Object> map = this.operatingAccountServices.getOperatingAccountByUsername(oa);
            List<OperatingAccount> list = (List<OperatingAccount>) map.get("list");
            OperatingAccount o = list.get(0);
            if (username.equals(o.getUsername())) { // 账号正确
                if (password.equals(o.getPassword())) { // 密码正确
                    return true;    // 返回true，代表已登录
                }
            }
            return false;   // 账号不存在或者密码错误，则认为未登录
        }
    }
}
