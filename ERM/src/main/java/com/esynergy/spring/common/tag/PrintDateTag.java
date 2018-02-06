package com.esynergy.spring.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class PrintDateTag extends TagSupport{
    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter writer = pageContext.getOut();
            writer.print("<u>Hello From Tag</u>"); //  <u>Hello From Tag</u>... it will display on JSP page
        } catch (IOException ex) {System.out.println(ex);}
        return SKIP_BODY;
    }
}
