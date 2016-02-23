package com.fstn;

/**
 * @author dge on 10/12/2015.
 */

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class AccessControlAllowOriginFilter implements javax.servlet.Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        ((HttpServletResponse)resp).addHeader(
                "Access-Control-Allow-Origin", "*"

        );

        ((HttpServletResponse)resp).addHeader(
                "Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE"
        );

        ((HttpServletResponse)resp).addHeader(
                "Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization"
        );

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }


}
