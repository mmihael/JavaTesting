package test.jetty;

/**
 * Created by mmamula on 28.7.2017.
 */
public interface JettyTestMBean {
    public void setQueryTarget(String s);
    public String getQueryTarget();
    public String testPrint(String s);
}
