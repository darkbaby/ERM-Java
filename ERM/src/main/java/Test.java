import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.print.attribute.HashAttributeSet;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.esynergy.erm.model.ob.Bank;
import com.esynergy.erm.service.BankService;


public class Test {

	 
	public static void main(String[] arg) throws Exception {
		String path = System.getProperty("user.dir");
		System.out.println(path);
		System.out.println("end");
        File logFile = new File("envi/pjs.log");
        System.out.println(logFile.getName());

	}
	
}
 
