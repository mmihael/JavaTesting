package test;

import ch.ethz.ssh2.Connection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import com.twitter.hpack.Encoder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.jgroups.*;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Interval;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import test.concurrency.Concurrency;
import test.hibernate.Hibernate;
import test.httpclient.HttpApacheClient;
import test.jackson.Jackson;
import test.jdbc.Jdbc;
import test.jedis.JedisTest;
import test.jetty.JettyTest;
import test.lock.LockTest;
import test.mongo.MongoTest;
import test.reflection.ReflectionTest;
import test.velocity.VelocityTest;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.SocketPermission;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Test {

    public static void preAuthSnippets() throws Exception {
        /**
         * PREAUTH SNIPPETS
         * */
        String data = IOUtils.toString(new FileInputStream("test.txt"));
        String pattern = IOUtils.toString(new FileInputStream("pattern.txt"));

        List<String> s = Arrays.asList(data.split("\n"));

        for (String item : s) {
            String[] sA = item.split("\\|");
            System.out.println(Arrays.asList(sA));

            String a = pattern.replace("$url", sA[3].trim());
            a = a.replace("$username", sA[5].trim());
            a = a.replace("$password", sA[6].trim());
            IOUtils.write(a, new FileOutputStream("preauth-" + sA[1].trim() + "-4.groovy"));
        }
    }

//    public static WebElement waitForElement(SearchContext ctxt, By by) {
//        WebElement el;
//        long start = System.currentTimeMillis();
//        while (true) {
//            try {
//                el = ctxt.findElement(by);
//                break;
//            } catch (Exception e) {
//                System.out.println(e);
//                if (System.currentTimeMillis() - start > 60000) {
//                    throw new RuntimeException("Wait expired");
//                }
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e1) {
//                    System.out.println(e1.getMessage());
//                }
//            }
//        }
//        return el;
//    }
    
    @AllArgsConstructor
    @ToString
    static class C {
        public String name;
        public int id;
    }

    static interface Pro {
        public void proTest();
    }

  public static void main (String[] args) {

      /*File f = new File("/home/mmamula/IdeaProjects/test/src/main/resources/test.json");
      try {
          System.out.println(IOUtils.toString(new FileInputStream(f)));

      } catch (Exception e) {
          System.out.println(e.getMessage());
      }*/

      DateTime now = new DateTime().withDayOfMonth(1).withTime(0,0,0,0);
      System.out.println(now);


      /*Runtime r = Runtime.getRuntime();

      try {t
          Process p = r.exec("ls");
          System.out.println(IOUtils.toString(p.getInputStream()));
      } catch (Exception e) {
          e.printStackTrace();
      }*/



      // DateTime now = new DateTime("2017-3-31");
      /*DateTime now = new DateTime();
      DateTime t = now.minusMonths(1).withDayOfMonth(1);
      int month = t.getMonthOfYear();
      List<String> l = new ArrayList<>();

      DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");

      while (t.isBefore(now)) {
          System.out.println(fmt.print(t));
          t = t.plusDays(1);
      }*/

      /*System.out.println(t);
      System.out.println("Day of the month " + t.getDayOfMonth());
      System.out.println("Month of the year " + t.getMonthOfYear());*/

      //System.out.println(Pattern.compile("^\\d\\|\\d+$").matcher("5|3").find());
      //System.out.println(u);

      /*Console c = System.console();
      String username;
      String password;

      System.out.println("Username: ");
      username = c.readLine();
      System.out.println("Password: ");
      password = new String(c.readPassword());

      WebDriver driver = new ChromeDriver();

      driver.get("https://dm.ioinfobip.com/login");

      try {
          WebElement jUsername = driver.findElement(By.id("username"));
          WebElement jPassword = driver.findElement(By.id("password"));
          jUsername.sendKeys(username);
          jPassword.sendKeys(password);
          jPassword.submit();
          driver.get("https://dm.ioinfobip.com/statusPerApplication/applicationId/198/locationId/1/view");
          WebElement table = driver.findElement(By.id("grid"));
          List<WebElement> tr = table.findElements(By.cssSelector("tr.body"));
          System.out.println(tr.size());
          for (int i = 0; i < tr.size(); i++) {
//              (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.name("statusList[" + i + "].status")));
              WebElement chbox = waitForElement(tr.get(i), By.name("statusList[" + i + "].status"));
              System.out.println(chbox.findElement(By.xpath("./..")).findElement(By.cssSelector("a i")).getAttribute("class"));
              chbox.click();
              waitForElement(tr.get(i), By.cssSelector("[id^=upgrade] a")).click();
              WebElement canShutDown = waitForElement(driver, By.cssSelector("#canShutdown strong"));
              System.out.println(canShutDown.getAttribute("class"));
              System.out.println(canShutDown.getText());
              break;
          }
      } catch (org.openqa.selenium.NoSuchElementException e1) {
          System.out.println(e1);
      }*/

      /*try {
        JChannel channel = new JChannel();
        channel.connect("test");
          channel.setReceiver(new Receiver() {
              @Override
              public void viewAccepted(View view) {

              }

              @Override
              public void suspect(Address address) {

              }

              @Override
              public void block() {

              }

              @Override
              public void unblock() {

              }

              @Override
              public void receive(Message message) {
                System.out.println(message.getObject());
              }

              @Override
              public void getState(OutputStream outputStream) throws Exception {

              }

              @Override
              public void setState(InputStream inputStream) throws Exception {

              }
          });
          while (true) {
              Thread.sleep(2000);
              channel.send(new Message(null, null, args[0]));
          }

      } catch (Exception e) {
          e.printStackTrace();
      }*/


      /**
       *
       * JSON PART
      String jsonArray = "[{ \"num{ber\": 12 }, { \"number\": 12, \"str\": \"te\\\"{xt\\\\\", \"object\":{ \"number\": 12 } }]";
      System.out.println(jsonArray);
      List<String> objects = new ArrayList<>();

     int opend = 0, closed = 0;
     boolean opendQuote = false;
      int escapesInRow = 0;

      String buff = "";

      char lastToken = '\0';
      for (int i = 0; i < jsonArray.length(); i++) {
          char currentToken = jsonArray.charAt(i);

          if (!opendQuote && currentToken == '{') {
              opend++;
          } else if (!opendQuote && currentToken == '}') {
              closed++;
          } else if (currentToken == '\\') {
              escapesInRow++;
          } else if (currentToken == '"' && escapesInRow % 2 == 0) {
              opendQuote = !opendQuote;
          }

          if (lastToken == '\\' && currentToken != '\\') { escapesInRow = 0; }

          if (opend != 0) {
              buff += currentToken;
          }

          if (opend != 0 && opend == closed) {
              opend = 0;
              closed = 0;
              objects.add(buff);
              buff = "";
          }
          lastToken = currentToken;
      }

      objects.forEach((e) -> { System.out.println(e); });

      */

//      MongoTest.test();
//        System.out.println(b);


      //System.out.println(Arrays.asList(data.split("\n")));
//      System.out.println(i.next());


//      Calendar c = new GregorianCalendar();
//      c.setTimeInMillis(0);
//      Calendar d = new GregorianCalendar();
//
//      System.out.println(c.getTime());
//      System.out.println(c.toString());

//      ConcurrentHashMap<Integer, AtomicInteger> i = new ConcurrentHashMap<>();
//      i.put(1, new AtomicInteger(3));
//      AtomicInteger a = i.get(1);
//      a.addAndGet(3);

//      FileOutputStream fos = null;
//      ObjectOutputStream oos = null;
//      try {
//          fos = new FileOutputStream("C:\\Users\\mmamula\\IdeaProjects\\test\\dump.txt");
//          oos = new ObjectOutputStream(fos);
//          oos.writeObject(i);
//
//      } catch (Exception e) {
//          e.printStackTrace();
//      } finally {
//          if (fos != null) {
//              try {
//                  fos.close();
//              } catch (IOException e) {
//                  e.printStackTrace();
//              }
//          }
//          if (oos != null) {
//              try {
//                  oos.close();
//              } catch (IOException e) {
//                  e.printStackTrace();
//              }
//          }
//      }
     // ScheduledExecutorService syncExecutor = Executors.newScheduledThreadPool(1);

//      System.out.println(Integer.toString(12412).indexOf("1"));



      /*String x = "&";
      for (int i = 11; i < 26; i++) {
          x += "target=org.infobip.2.infobip-sms-service.P1-FR-" + i + ".traffic.delivery.rate&";
      }
      x += "target=org.infobip.2.infobip-sms-service.P1-FR-" + 29 + ".traffic.delivery.rate&";
      System.out.println(x);*/

      /*try (FileInputStream fis = new FileInputStream("C:\\Users\\mmamula\\IdeaProjects\\test\\dump.txt"); ObjectInputStream ois = new ObjectInputStream(fis)) {
          i = (ConcurrentHashMap<Integer, AtomicInteger>) ois.readObject();
      } catch (Exception e) {

      }

      System.out.println(i);*/


      /*List<Integer> l = Arrays.asList(1, 12, 234, 5235, 3, 5);

      Consumer<Integer> c = (i) -> { if (i == 3) { throw new RuntimeException("error"); } System.out.println(i); };

      l.forEach(i -> {

          try {
              c.accept(i);
          } catch (Exception e) {
              System.out.println(e.getMessage());
              throw e;
          }

      });*/

//      ScheduledExecutorService e = Executors.newScheduledThreadPool(1);
//
//      e.scheduleWithFixedDelay(() -> {
//          System.out.println(123);
//      }, 2, 2, TimeUnit.SECONDS);
//
//      System.out.println(e.isShutdown());
//      System.out.println(e.isTerminated());
//
//      try {
//          Thread.sleep(5000);
//      } catch (InterruptedException e1) {
//          System.out.println(e1);
//      }
//
//      e.shutdown();
//
//      System.out.println(e.isShutdown());
//      System.out.println(e.isTerminated());

//    JedisTest.evalShaTest();

//    HttpApacheClient.loginReq();

//    try {
//      System.out.println(IOUtils.toString(new FileInputStream("src\\main\\resources\\lua\\test.lua")));
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    JedisTest.eval();


//    Concurrency.workQueue();
    /*if (args.length == 0) {

      topic.addMessageListener(e -> {
        System.out.println(e.getMessageObject());
      });

    } else {
      int i = 0;
      while (true) {
        try {
          Thread.sleep(2000);
        } catch (Exception e){
          System.out.println(e.getMessage());
        }
        topic.publish("Event no: " + i++);
      }
    }*/

    /*List<Integer> l = Arrays.asList(1,2,34,5);

    for (Integer i : l) {
      try {
        if (i == 2) { throw new RuntimeException("Test"); }
      } catch (Exception e) {
        System.out.println(e.getMessage());
        continue;
      } finally {
        System.out.println("asdf");
      }
      System.out.println(i);
    }*/
//    Pattern pattern = Pattern.compile("\\[\\d+\\] (\\d+)");
//    Matcher matcher = pattern.matcher(" [1] 324 ");
//    if (matcher.find()) {
//      System.out.println(matcher.group(0));
//      System.out.println(matcher.group(1));
//    }


    /*List<Integer> l = Arrays.asList(1);
    int x = 870719594;
    System.out.println(l.get(l.size() - 1));
    String i = "13";*/

    //System.out.println((System.currentTimeMillis() / 1000) / 60);

    /*Pattern x = Pattern.compile("#\\w+");

    Matcher y = x.matcher("#NetID_NetworkName");
    //y.find();
    System.out.println(y.matches());*/

//System.out.println(new ArrayList<Integer>().getClass());
      /*String x = ";;as;;";

      System.out.println(Arrays.asList(x.split(";")).stream().filter(e -> !e.isEmpty()).collect(Collectors.toList()));
      System.out.println(x);*/

      /*String[] validEventSuffix = {"_FARM_UNREACHABLE", "_OFFLINE_MODEMS"};
      System.out.println(String.join("|", validEventSuffix));
      Pattern pattern = Pattern.compile("^SFManager_(.*)(?:_FARM_UNREACHABLE|_OFFLINE_MODEMS)$");
      Matcher matcher = pattern.matcher("SFManager_pu3pool_FARM_UNREACHABLE");

      if (matcher.find()) {
          System.out.println(matcher.group(0));
          System.out.println(matcher.group(1));
          System.out.println(matcher.groupCount());
      }*/

      /*List<String> l = Arrays.asList("test@infobip.com");

      String emails = "asdf";

      List<String> emailsList = Arrays.stream(emails.split(";")).map(String::trim).filter(e -> !e.equals("asdf")).collect(Collectors.toList());

      System.out.println(emailsList);*/

      /*Calendar c = new GregorianCalendar();
    c.add(Calendar.YEAR, -1);
      System.out.println(DateFormatUtils.format(c.getTimeInMillis(), "yyyy-MM-dd H:m:s"));*/

      /*String s = "SFManager_pu3pool_FARM_UNREACHABLE";

      System.out.println(StringUtils.endsWith(s, "_FARM_UNREACHABLE"));

      System.out.println(s.indexOf("_"));

      System.out.println(s.substring(10, s.length() - 17));*/

     /* Calendar calendar = new GregorianCalendar();
      calendar.set(Calendar.HOUR_OF_DAY, 0);
      calendar.set(Calendar.MINUTE, 0);
      calendar.set(Calendar.SECOND, 0);
      calendar.set(Calendar.MILLISECOND, 0);
      calendar.add(Calendar.DATE, 1);
      Format formatter = new SimpleDateFormat("dd/MM/yy H:m:s");
      System.out.println(Base64.getEncoder().encodeToString(("adriana.ajredini@infobip.com/token:UhGYjz3wgFBIK6eBJku0wrB0Zr53O7i025oZ3uSS").getBytes()));*/
//      Concurrency.thread();
//      Jackson.stream();
//      Jackson.tree();
//      Jackson.pojo();
//      Jackson.strToPojo();
//      Jackson.strTest();
//      Jdbc.insert();
//      Jdbc.insert();
//      Jdbc.select();
//      Hibernate.test();

  }

}
