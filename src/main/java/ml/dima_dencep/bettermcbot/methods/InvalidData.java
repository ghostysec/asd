package ml.dima_dencep.bettermcbot.methods;

import io.netty.channel.Channel;
import ml.dima_dencep.bettermcbot.Main;
import ml.dima_dencep.bettermcbot.NettyBootstrap;
import ml.dima_dencep.bettermcbot.minecraftutils.Handshake;
import ml.dima_dencep.bettermcbot.ProxyLoader;

public class InvalidData implements Method {
   private Handshake handshake;

   public InvalidData() {
      this.handshake = new Handshake(Main.protcolID, Main.srvRecord, Main.port, 2);
   }

   public void accept(Channel channel, ProxyLoader.Proxy proxy) {
      channel.writeAndFlush(this.handshake);
      ++NettyBootstrap.integer;
      ++NettyBootstrap.totalConnections;
      channel.close();
   }
}
