package cv.bloody.ua.dream.chat.client.packet.client;

import cv.bloody.ua.dream.chat.client.App;
import org.json.JSONObject;

public class AuthorizeFailedPacket extends ClientPacket {

    private final Reason reason;

    public AuthorizeFailedPacket(Reason reason) {
        super(PacketIDEnum.AUTH_FAILED_PACKET.ordinal());
        this.reason = reason;
    }

    @Override
    public void handle() throws Exception {
        System.out.println("Invalid authorization by reason " + reason.name());
        App.state.set(0);
    }


    public static enum Reason {
        UNKNOWN_USER,
        FAILED_PASSWORD,
        USER_IS_LOGINED,
        SERVER_PROBLEM;
    }

}
