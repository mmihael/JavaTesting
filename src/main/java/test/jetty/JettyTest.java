package test.jetty;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mmamula on 26.7.2017.
 */
public class JettyTest implements JettyTestMBean {

    public AtomicInteger i = new AtomicInteger(0);

    String queryTarget = "filter";

    public void test() {

        Server s = new Server(3333);

        ServletHandler servletHandler = new ServletHandler();

        s.setHandler(servletHandler);

        ServletHolder servletHolder = new ServletHolder(new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                System.out.println(i.incrementAndGet());
                resp.setContentType("text/html;charset=utf-8");
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println("<h1>Hello World</h1>");
            }
        });

        servletHandler.addFilterWithMapping(
                new FilterHolder(new Filter() {
                    @Override
                    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                        System.out.println("Filter 1");
                        String test = servletRequest.getParameter(queryTarget);
                        System.out.println(test);
                        if (test != null) {
                            servletResponse.setContentType("text/html;charset=utf-8");
                            servletResponse.getWriter().println("Response from filter 1");
                        } else {
                            filterChain.doFilter(servletRequest, servletResponse);
                        }
                    }
                    @Override public void init(FilterConfig filterConfig) throws ServletException {}
                    @Override public void destroy() {}
                }),
                "/*",
                EnumSet.of(DispatcherType.REQUEST)
        );

        servletHandler.addFilterWithMapping(
                new FilterHolder(new Filter() {
                    @Override
                    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                        System.out.println("Filter 2");
                        HttpServletRequest req = (HttpServletRequest) servletRequest;
                        String test = ((HttpServletRequest) servletRequest).getRequestURI();
                        System.out.println(test);
                        if (test != null && test.equals("/123")) {
                            servletResponse.setContentType("text/html;charset=utf-8");
                            servletResponse.getWriter().println("Response from filter 2");
                        } else {
                            filterChain.doFilter(servletRequest, servletResponse);
                        }
                    }
                    @Override public void init(FilterConfig filterConfig) throws ServletException {}
                    @Override public void destroy() {}
                }),
                "/*",
                EnumSet.of(DispatcherType.REQUEST)
        );

        servletHandler.addServletWithMapping(servletHolder, "/*");
//        servletHandler.addFilter();

//        servletHandler.setHandler(new AbstractHandler() {
//            @Override
//            public void handle(String s, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
//                System.out.println(i.incrementAndGet());
//                httpServletResponse.setContentType("text/html;charset=utf-8");
//                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
//                request.setHandled(true);
//                httpServletResponse.getWriter().println("<h1>Hello World</h1>");
//            }
//        });

        try {
            s.start();
            s.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void mBeanTest() {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();

        ObjectName helloName = null;


        JettyTest t = new JettyTest();


        try {
            // Uniquely identify the MBeans and register them with the platform MBeanServer
            helloName = new ObjectName("test.jetty:name=JettyTest");
            server.registerMBean(t, helloName);
        } catch(Exception e) {
            e.printStackTrace();
        }



        t.test();
    }

    @Override
    public void setQueryTarget(String s) {
        this.queryTarget = s;
    }

    @Override
    public String getQueryTarget() { return this.queryTarget; }

    public String testPrint(String s) { return "TEST" + s; }
}
