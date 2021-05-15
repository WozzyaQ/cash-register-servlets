package com.epam.ua.wozzya.web.filter;

import com.epam.ua.wozzya.model.dao.entity.enumeration.Role;
import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;
import java.util.*;

public class CommandAccessFilter implements Filter {
    private static final Logger log = Logger.getLogger(CommandAccessFilter.class);
    private static final String COMMON_ACTIONS = "COMMON";
    private static final String UNCONTROLLABLE_ACTIONS = "NO_CONTROL";

    private static final Map<Role, List<String>> roleRelatedCommands = new EnumMap<>(Role.class);
    private static final List<String> commonActions = new ArrayList<>();
    private static final List<String> uncontrollableActions = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug(CommandAccessFilter.class.getName() + "  initialization started");

        initRoleRelatedAccessMap(filterConfig);
        log.trace("AccessMap: " + roleRelatedCommands);

        commonActions.addAll(extractActionListFromConfigByKey(filterConfig, COMMON_ACTIONS));
        log.trace("Commons: " + commonActions);

        uncontrollableActions.addAll(extractActionListFromConfigByKey(filterConfig, UNCONTROLLABLE_ACTIONS));
        log.trace("No-control: " + uncontrollableActions);


        log.debug("CommandAccess");

        log.debug(CommandAccessFilter.class.getName() + "  initialization finished");
    }

    private void initRoleRelatedAccessMap(FilterConfig filterConfig) {
        for (Role role : Role.values()) {
            List<String> actions = extractActionListFromConfigByKey(filterConfig, role.getName());
            roleRelatedCommands.put(role, actions);
        }
    }

    private List<String> extractActionListFromConfigByKey(FilterConfig filterConfig, String key) {
        return asList(filterConfig.getInitParameter(key));
    }

    private List<String> asList(String str) {
        List<String> commands = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(str);

        while (tokenizer.hasMoreTokens()) {
            commands.add(tokenizer.nextToken());
        }
        return commands;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {
        log.debug(CommandAccessFilter.class.getName() + " destroying");
    }
}
