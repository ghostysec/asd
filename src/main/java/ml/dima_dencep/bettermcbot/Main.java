package ml.dima_dencep.bettermcbot;

import java.io.File;
import java.net.InetAddress;
import java.util.Random;
import java.util.Scanner;

import ml.dima_dencep.bettermcbot.methods.Method;
import ml.dima_dencep.bettermcbot.minecraftutils.ServerAddress;

public class Main {
   public static String origIP;
   public static String srvRecord;
   public static InetAddress resolved;
   public static int port;
   public static int protcolID;
   public static int protocolLength;
   public static boolean genProxy;
   public static String methodID;
   public static Method method;
   public static int duration;
   public static int targetCPS;
   public static int nettyThreads;
   public static int loopThreads;
   public static String string;
   public static ProxyLoader proxies;

   public static void main(String[] args) throws Throwable {
      System.out.println("Refactored by dima_dencep");
      System.out.println("Repo: http://github.com/dimadencep/BetterMcBot");
      System.out.println("Starting Better Mc Bot...");
      Scanner in = new Scanner(System.in);

      int cpuselect;
      String serverhp;
      int protcol;
      String method;
      boolean proxyGen;
      int dura;

      System.out.println("You server <IP:PORT>:");
      serverhp = in.nextLine();

      System.out.println("Server protocol (https://wiki.vg/Protocol_version_numbers):");
      protcol = Integer.parseInt(in.nextLine());

      System.out.println("Method:");
      method = in.nextLine();

      System.out.println("Time (secons):");
      dura = Integer.parseInt(in.nextLine());

      System.out.println("Target cpu (-1 for max):");
      cpuselect = Integer.parseInt(in.nextLine());

      System.out.println("Gen new proxy?:");
      proxyGen = Boolean.parseBoolean(in.nextLine());

      start(serverhp, protcol, method, dura, cpuselect, proxyGen);
   }

   public static void start(String serverhp, int protcol, String method, int dura, int cpuselect, boolean proxy) throws Throwable {
      duration = dura;
      protcolID = protcol;
      methodID = method;
      genProxy = proxy;

      proxies = new ProxyGen(new File("proxies.txt")).load();

      try {
         System.out.println("Resolving IP...");
         ServerAddress sa = ServerAddress.getAddrss(serverhp);
         srvRecord = sa.getIP();
         port = sa.getPort();
         resolved = InetAddress.getByName(srvRecord);
         System.out.println("Resolved IP: " + resolved.getHostAddress());
         origIP = serverhp.split(":")[0];
         targetCPS = cpuselect + (int)Math.ceil((double)cpuselect / 100 * (50 + (double)cpuselect / 5000));
         nettyThreads = targetCPS == -1 ? 256 : (int)Math.ceil(6.4E-4D * (double)targetCPS);
         loopThreads = targetCPS == -1 ? 3 : (int)Math.ceil(1.999960000799984E-5D * (double)targetCPS);
         protocolLength = protcolID > 128 ? 3 : 2;
         Random r = new Random();

         for(int i = 1; i < 65536; ++i) {
            string = String.valueOf(string) + String.valueOf((char)(r.nextInt(125) + 1));
         }

      } catch (Exception var4) {
         var4.printStackTrace();
         Thread.sleep(5000L);
         return;
      }

      Methods.setupMethods();
      Main.method = Methods.getMethod(methodID);
      System.out.println("Running metod: " + Main.method.getClass().getName());
      NettyBootstrap.start();
   }
}
