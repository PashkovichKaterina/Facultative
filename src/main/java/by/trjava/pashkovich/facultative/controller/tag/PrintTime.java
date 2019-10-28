package by.trjava.pashkovich.facultative.controller.tag;

import by.trjava.pashkovich.facultative.util.CustomFormatForDate;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Date;

/**
 * Tag is used to display the time in a format convenient for the user.
 *
 * @author Katsiaryna Pashkovich
 * @version 1.0
 * @see TagSupport
 * @since JDK1.0
 */
public class PrintTime extends TagSupport {
    private Date date;

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            if (date != null) {
                pageContext.getOut().write(CustomFormatForDate.getUseClientTimeFormat(date));
            }
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
