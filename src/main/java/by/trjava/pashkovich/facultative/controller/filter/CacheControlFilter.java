package by.trjava.pashkovich.facultative.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Filter is used to prevent pages from being cached.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see Filter
 * @since JDK1.0
 */
public class CacheControlFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setDateHeader("Last-Modified", new Date().getTime());
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0, post-check=0, pre-check=0");
        resp.setHeader("Pragma", "no-cache");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
