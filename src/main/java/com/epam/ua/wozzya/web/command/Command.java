package com.epam.ua.wozzya.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Command {

    String execute(HttpServletRequest req, HttpServletResponse resp);
}
