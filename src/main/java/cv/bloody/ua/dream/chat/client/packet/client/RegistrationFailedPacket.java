package cv.bloody.ua.dream.chat.client.packet.client;

import cv.bloody.ua.dream.chat.client.App;

public class RegistrationFailedPacket extends ClientPacket{

    private final Reason reason;

    public RegistrationFailedPacket(Reason reason) {
        super(PacketIDEnum.REGISTER_FAILED_PACKET.ordinal());
        this.reason = reason;
    }

    @Override
    public void handle() throws Exception {
        System.out.println("Invalid registratoin by reason " + reason.name());
        App.state.set(0);
    }

    public static enum Reason {
        WRONG_FORMAT_LOGIN,
        TO_MANY_ADDRES,
        LOGIN_IS_OCCUPIED,
        SERVER_PROBLEM;
    }
}
