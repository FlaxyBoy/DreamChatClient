package cv.bloody.ua.dream.chat.client.network;

import cv.bloody.ua.dream.chat.client.packet.client.*;
import io.netty.channel.ChannelHandlerContext;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class NetworkManager {

    private static final Map<Integer , Function<JSONObject, ClientPacket>>
            jsonToServerPacketReleaser = new HashMap<>();

    public static ClientPacket decode(String text ) {
        JSONObject info = new JSONObject(text);
        if(!info.has("packet_id")) throw new IllegalStateException("packet not contains \"packet_id\"");
        int packetID = info.getInt("packet_id");
        Function<JSONObject , ClientPacket> function = jsonToServerPacketReleaser
                .getOrDefault(packetID , null);
        if(function == null) throw new IllegalStateException("illegal packet id");
        ClientPacket packet = function.apply(info);
        if(packet == null) throw new IllegalStateException("invalid packet state");
        return packet;
    }

    static {
        jsonToServerPacketReleaser.put(ClientPacket.PacketIDEnum.REGISTER_FAILED_PACKET.ordinal() , (jsonObject) -> {
            try {
                return new RegistrationFailedPacket(RegistrationFailedPacket.Reason.
                        valueOf(jsonObject.getString("reason")));
            }catch (Exception e) {
                return null;
            }
        });
        jsonToServerPacketReleaser.put(ClientPacket.PacketIDEnum.AUTH_FAILED_PACKET.ordinal() , (jsonObject) -> {
            try {
                return new AuthorizeFailedPacket(AuthorizeFailedPacket.Reason.
                        valueOf(jsonObject.getString("reason")));
            }catch (Exception e) {
                return null;
            }
        });
        jsonToServerPacketReleaser.put(ClientPacket.PacketIDEnum.AUTH_CONFIRM_PACKET.ordinal() , (jsonObject) -> {
            try {
                return new AuthConfirmPacket();
            }catch (Exception e) {
                return null;
            }
        });
        jsonToServerPacketReleaser.put(ClientPacket.PacketIDEnum.MESSAGE_PACKET.ordinal() , (jsonObject) -> {
            try {
                return new PacketOutChatMessage(jsonObject);
            }catch (Exception e) {
                return null;
            }
        });
        jsonToServerPacketReleaser.put(ClientPacket.PacketIDEnum.PRIVATE_MESSAGE_PACKET.ordinal() , (jsonObject) -> {
            try {
                return new PacketOutPrivateMessage(jsonObject);
            }catch (Exception e) {
                return null;
            }
        });
    }
}
