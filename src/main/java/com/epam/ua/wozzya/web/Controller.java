package com.epam.ua.wozzya.web;

import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api")
public class Controller extends HttpServlet {
    private static final Logger log = Logger.getLogger(Controller.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("got get request from session [" + getSessionId(req) + "], delegating to process");
        process(req, resp);
        log.info("served get request from session [" + getSessionId(req) +"]");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("got post request ["+ getSessionId(req)  +"], delegating to process");
        process(req, resp);
        log.info("served get request [" + getSessionId(req) + "]");
    }
    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println("hello");
    }

    private String getSessionId(HttpServletRequest req) {
        return req.getSession().getId();
    }
}
