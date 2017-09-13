package test.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.Properties;

/**
 * Created by mmamula on 26.6.2017.
 */
public class VelocityTest {

    public static void test() {

        VelocityEngine ve = new VelocityEngine();
        Properties p = new Properties();
        p.setProperty("file.resource.loader.path", "C:\\Users\\mmamula\\IdeaProjects\\test\\src\\main\\resources");
        ve.init(p);

        Template t = ve.getTemplate("test.vm");

        VelocityContext vc = new VelocityContext();
        vc.put("test", 123);
        vc.put("pushMessages", "[ {} ]");
        vc.put("timeZoneId", 1);
        vc.put("pushSettings", new Object());
        vc.put("newline", "\n");

        StringWriter sw = new StringWriter();
        t.merge(vc, sw);
        
        System.out.println(sw.toString());


    }

}
