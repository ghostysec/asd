package ml.dima_dencep.bettermcbot.methods;

import io.netty.channel.Channel;
import java.util.function.BiConsumer;
import ml.dima_dencep.bettermcbot.ProxyLoader;

public interface Method extends BiConsumer<Channel, ProxyLoader.Proxy> {
}
