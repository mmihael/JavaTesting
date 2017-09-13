package test.reflection;

import lombok.Data;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by mmamula on 21.7.2017.
 */
@Data
public class ReflectionTest {

    private int id;
    private Date date;

    public void setIdAndDate(int id, Date date) {
        this.id = id;
        this.date = date;
    }

    public void getStackTrace() {
        System.out.println(ExceptionUtils.getStackTrace(new Exception()));
        //new Exception("test").printStackTrace();
    }

    public static void test() {

        ReflectionTest r = new ReflectionTest();

        /*Arrays.stream(r.getClass().getDeclaredFields()).forEach(field -> {
            System.out.println(field.getName());
            System.out.println(field.getType());

            try {
                System.out.println(field.get(r));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            System.out.println();
        });*/

        Arrays.stream(r.getClass().getDeclaredMethods()).forEach(method -> {
            System.out.println(method.getName());
            System.out.println(method.getReturnType());
            Arrays.stream(method.getParameters()).forEach(parameter -> {
                System.out.println(parameter.getName());
                System.out.println(parameter.getParameterizedType());
                System.out.println(parameter.getType());
            });
            System.out.println();
        });

    }

}
