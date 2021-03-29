package cv.bloody.ua.dream.chat.client.packet.server;

import org.json.JSONObject;

public class PacketImChatMessage extends ServerPacket{

    private final String message;

    public PacketImChatMessage(String message) {
        this.message = message;
    }

    @Override
    public JSONObject toJSON() {
        return new JSONObject().put("packet_id" , 3).put("message" , message);
    }

}
