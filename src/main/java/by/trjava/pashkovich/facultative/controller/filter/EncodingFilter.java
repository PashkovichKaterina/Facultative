package by.trjava.pashkovich.facultative.controller.filter;

import by.trjava.pashkovich.facultative.constants.Variable;

import javax.servlet.*;
import java.io.IOException;

/**
 * Filter is used to change the standard encoding of the http request to the necessary.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see Filter
 * @since JDK1.0
 */
public class EncodingFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter(Variable.ENCODING);
        if (encoding == null) {
            encoding = Variable.UTF8;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestEncoding = servletRequest.getCharacterEncoding();
        if (!encoding.equals(requestEncoding)) {
            servletRequest.setCharacterEncoding(encoding);
            servletResponse.setCharacterEncoding(encoding);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        encoding = null;
    }
}
