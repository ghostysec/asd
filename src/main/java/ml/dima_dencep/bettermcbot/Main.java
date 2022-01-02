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

      Integer cpuselect;
      String serverhp;
      if (args.length != 5) {
         System.out.println("You server <IP:PORT>:");
         serverhp = in.nextLine();

         System.out.println("Server protocol (https://wiki.vg/Protocol_version_numbers):");
         protcolID = Integer.parseInt(in.nextLine());

         System.out.println("Method:");
         methodID = in.nextLine();

         System.out.println("Time (secons):");
         duration = Integer.parseInt(in.nextLine());

         System.out.println("Target cpu (-1 for max):");
         cpuselect = Integer.parseInt(in.nextLine());
      } else {
         serverhp = args[0];
         protcolID = Integer.parseInt(args[1]);
         methodID = args[2];
         duration = Integer.parseInt(args[3]);
         cpuselect = Integer.parseInt(args[4]);
      }

      proxies = new ProxyGen(new File("proxies.txt"), args).load();

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
      method = Methods.getMethod(methodID);
      System.out.println("Running metod: " + method.toString());
      NettyBootstrap.start();
   }
}
