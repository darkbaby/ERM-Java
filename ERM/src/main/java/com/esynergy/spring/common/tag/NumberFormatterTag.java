package com.esynergy.spring.common.tag;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.esynergy.erm.common.util.ICommonContains;
import com.esynergy.erm.web.action.IPageContains;
public class NumberFormatterTag extends SimpleTagSupport {

	private String number;

	public NumberFormatterTag() {
	}


	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public void doTag() throws JspException, IOException {
		try {
			double amount = Double.parseDouble(number);
 			String formattedNumber = ICommonContains.EXCHANGE_RATE_FORMAT.format(amount);
			getJspContext().getOut().write(formattedNumber);
		} catch (Exception e) {
			e.printStackTrace();
			// stop page from loading further by throwing SkipPageException
			throw new SkipPageException("Exception in formatting " + number
					+ " with format ");
		}
	}

}
