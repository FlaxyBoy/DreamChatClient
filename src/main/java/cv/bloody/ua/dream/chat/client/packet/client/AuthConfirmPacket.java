package cv.bloody.ua.dream.chat.client.packet.client;

import cv.bloody.ua.dream.chat.client.App;

public class AuthConfirmPacket extends ClientPacket{
    public AuthConfirmPacket() {
        super(PacketIDEnum.AUTH_CONFIRM_PACKET.ordinal());
    }

    @Override
    public void handle() throws Exception {
        App.state.set(2);
    }
}
