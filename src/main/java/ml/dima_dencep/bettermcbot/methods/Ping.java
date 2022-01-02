package ml.dima_dencep.bettermcbot.methods;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import ml.dima_dencep.bettermcbot.Main;
import ml.dima_dencep.bettermcbot.NettyBootstrap;
import ml.dima_dencep.bettermcbot.ProxyLoader;
import ml.dima_dencep.bettermcbot.minecraftutils.Handshake;
import ml.dima_dencep.bettermcbot.minecraftutils.PingPacket;

public class Ping implements Method {
   private byte[] handshakebytes;

   public Ping() {
      this.handshakebytes = (new Handshake(Main.protcolID, Main.srvRecord, Main.port, 1)).getWrappedPacket();
   }

   public void accept(Channel channel, ProxyLoader.Proxy proxy) {
      channel.writeAndFlush(Unpooled.buffer().writeBytes(this.handshakebytes));
      channel.writeAndFlush(Unpooled.buffer().writeBytes(new byte[]{1, 0}));
      channel.writeAndFlush(Unpooled.buffer().writeBytes((new PingPacket(System.currentTimeMillis())).getWrappedPacket()));
      ++NettyBootstrap.integer;
      ++NettyBootstrap.totalConnections;
      channel.close();
   }
}
