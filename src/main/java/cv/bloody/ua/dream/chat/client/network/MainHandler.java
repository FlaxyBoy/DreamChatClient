package cv.bloody.ua.dream.chat.client.network;

import cv.bloody.ua.dream.chat.client.packet.client.ClientPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MainHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            ClientPacket packet = NetworkManager.decode((String) msg);
            packet.handle();
        }catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }
}
