package com.epam.ua.wozzya.web;

import com.epam.ua.wozzya.web.command.Command;
import com.epam.ua.wozzya.web.command.CommandContainer;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final Logger log = Logger.getLogger(Controller.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }
    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        log.debug("starting processing request");
        String commandName = req.getParameter("command");
        log.trace("Request parameter command: " + commandName);

        Command command = CommandContainer.get(commandName);
        log.trace("Got command from container " + command);

        String forward = command.execute(req, resp);
        log.trace("Forward address: " + forward);

        log.debug("finished processing request, forwarding to: " + forward);

        if(forward != null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(forward);
            dispatcher.forward(req, resp);
        }
    }
}
