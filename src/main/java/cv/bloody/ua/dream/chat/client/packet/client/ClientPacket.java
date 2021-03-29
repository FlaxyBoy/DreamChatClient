package cv.bloody.ua.dream.chat.client.packet.client;

public abstract class ClientPacket {

    public static enum PacketIDEnum {
        AUTH_FAILED_PACKET,
        REGISTER_FAILED_PACKET,
        AUTH_CONFIRM_PACKET,
        MESSAGE_PACKET,
        PRIVATE_MESSAGE_PACKET;
    }

    private final int id;

    public ClientPacket(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public abstract void handle() throws Exception;

}
