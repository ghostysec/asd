package ml.dima_dencep.bettermcbot.methods;

import io.netty.channel.Channel;
import java.util.Random;

import ml.dima_dencep.bettermcbot.NettyBootstrap;
import ml.dima_dencep.bettermcbot.ProxyLoader;

public class Combined implements Method {
   private int at = 0;

   private String randomString(int len) {
      int leftLimit = 97;
      int rightLimit = 122;
      int targetStringLength = len;
      Random random = new Random();
      StringBuilder buffer = new StringBuilder(len);

      for(int i = 0; i < targetStringLength; ++i) {
         int randomLimitedInt = leftLimit + (int)(random.nextFloat() * (float)(rightLimit - leftLimit + 1));
         buffer.append((char)randomLimitedInt);
      }

      return buffer.toString();
   }

   public void accept(Channel channel, ProxyLoader.Proxy proxy) {
      switch(this.at) {
      case 0:
      default:
         ++NettyBootstrap.integer;
         ++NettyBootstrap.totalConnections;
         channel.close();
      }
   }
}
