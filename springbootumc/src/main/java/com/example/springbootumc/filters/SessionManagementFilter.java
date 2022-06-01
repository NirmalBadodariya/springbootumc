//package com.example.springbootumc.filters;
//
//import java.io.IOException;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//public class SessionManagementFilter implements Filter {
//
//    public void destroy() {
//        // TODO Auto-generated method stub
//    }
//
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletResponse res = (HttpServletResponse) response;
//        HttpServletRequest req = (HttpServletRequest) request;
//
//        HttpSession session = req.getSession(false);
//
//        res.setHeader("Cache-Control", "no-chache,no-store,must-revalidate");
//        res.setHeader("Pragma", "no-chache");
//        res.setDateHeader("Expires", 0);
//        if (session == null) {
//            System.out.println("seseeion null");
//            res.sendRedirect("index");
//        } else {
//            System.out.println("removed cache ");
//
//            chain.doFilter(request, response);
//        }
//    }
//
//    public void init(FilterConfig fConfig) throws ServletException {
//
//    }
//
//}