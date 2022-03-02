package ml.dima_dencep.bettermcbot;

import java.util.HashMap;

import ml.dima_dencep.bettermcbot.methods.*;

public class Methods {
   public static final HashMap<String, Method> METHODS = new HashMap();

   public static Method getByID(int i) {
      return (Method)METHODS.getOrDefault(i, (c, p) -> {
         c.close();
         System.err.println("invalid method id: " + i);
      });
   }

   private static void registerMethod(String name, Method m) {
      if (METHODS.containsKey(name)) {
         throw new IllegalStateException("Method with id " + name + " already exists");
      } else {
         METHODS.put(name, m);
      }
   }

   public static void setupMethods() {
      registerMethod("bigpacket", new BigPacket());
      registerMethod("botjoiner", new BotJoiner());
      registerMethod("doublejoin", new DoubleJoin());
      registerMethod("emptypacket", new EmptyPacket());
      registerMethod("gayspam", new GaySpam());
      registerMethod("handshake", new HandshakeMethod());
      registerMethod("invaliddata", new InvalidData());
      registerMethod("invalidnames", new InvalidNames());
      registerMethod("spoof", new IPSpoof());
      registerMethod("join", new Join());
      registerMethod("legacyping", new LegacyPing());
      registerMethod("legitnamejoin", new LegitNameKiller());
      registerMethod("pingjoin", new LoginPingMulticrasher());
      registerMethod("longhost", new LongHost());
      registerMethod("nullping", new NullPing());
      registerMethod("ping", new Ping());
      registerMethod("query", new QueryFlood());
      registerMethod("randompacket", new RandomBytes());
      registerMethod("bighandshake", new BigHandshake());
      registerMethod("unexpectedpacket", new PlayPacket());
      registerMethod("memory", new Memory());
   }

   public static Method getMethod(String methodID) {
      return (Method)METHODS.getOrDefault(methodID, new Join());
   }
}
