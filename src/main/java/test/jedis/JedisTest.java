package test.jedis;

import org.apache.commons.io.IOUtils;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * Created by mmamula on 15.3.2017.
 */
public class JedisTest {

    public static void evalShaTest() {
        try (Jedis j = new Jedis("192.168.33.66")) {
            j.select(1);
            String sha = j.scriptLoad("return 123;");
            System.out.println(sha);
            System.out.println(j.evalsha(sha));
            System.out.println(j.evalsha("fasdfsdafsdaf"));
        }
    }

    public static void delTest() {

        Jedis j = new Jedis("192.168.33.66");
        j.select(10);
        for (int i = 0; i < 1000000; i++) {
            j.hset("test", "test-" + i, "random string");
        }
        j.close();

    }

    public static void hgetTest() {

        Jedis j = new Jedis("192.168.33.66");
        j.select(1);
        j.hset("test", "1", "111");
        System.out.println(j.hget("s", "2") == null);

    }



    public void test() {

        try {
            try {
                throw new RuntimeException("asfd");
            } catch (Exception e) {
                System.out.println(2);
            }
        } catch (Exception e) {
            System.out.println(1);
        }

//        MessageDigest md = null;



//        try {
//            String fileName = "test.txt";
//            System.out.println(fileName.substring(0, fileName.lastIndexOf('.')));
////            System.out.println(getClass().getClassLoader().getResource("lua").getFile());
//            File file = new File(getClass().getClassLoader().getResource("lua").getFile());
//            Arrays.stream(file.listFiles()).forEach(e -> System.out.println(e.getAbsolutePath()));
////            URL el;
////            while ((el = getClass().getClassLoader().getResources("lua").get()) != null) {
////                System.out.println(el);
////            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    
    public static void eval() {
        Jedis j = new Jedis("192.168.33.66");
        j.select(1);

        Object test = null;
        try {
            test = j.eval(IOUtils.toString(new FileInputStream("src\\main\\resources\\lua\\test.lua")),0, "2");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(test instanceof String);
        System.out.println(test);
    }



}
