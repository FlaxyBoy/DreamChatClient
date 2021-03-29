package cv.bloody.ua.dream.chat.client;

import cv.bloody.ua.dream.chat.client.network.MainHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


public class Network {

    private SocketChannel channel;

    public Network() {
        new Thread(() -> {
            EventLoopGroup group = new NioEventLoopGroup();
            try {
                Bootstrap b = new Bootstrap();
                b.group(group)
                        .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel socketChannel) throws Exception {
                                channel = socketChannel;
                                channel.pipeline().addLast(new StringDecoder() , new StringEncoder() , new MainHandler());
                            }
                        });
                ChannelFuture future = b.connect("mc.dreamnw.ru" , 8888).sync();
                future.channel().closeFuture().sync();
            }catch (Exception e) {
                e.printStackTrace();
            } finally {
                group.shutdownGracefully();
            }
        }).start();
    }

    public SocketChannel getChannel() {
        return channel;
    }
}
