package org.everest.mvc.infrastructure.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Servlet Filter implementation class CharacterEncodingFilter
 */
@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {

    private String encoding = null;
    private boolean ignore = true;
    private Logger logger = LoggerFactory.getLogger(CharacterEncodingFilter.class);

    public void destroy() {
        encoding = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        logger.info("Character Encoding: {}", encoding);
        chain.doFilter(request, response);
    }


    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding = "UTF-8";
        String value = filterConfig.getInitParameter("ignore");
        this.ignore = ((value == null) || value.equalsIgnoreCase("true") || value.equalsIgnoreCase("yes"));
    }

}
