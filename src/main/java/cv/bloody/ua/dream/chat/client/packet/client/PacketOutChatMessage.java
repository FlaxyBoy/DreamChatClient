package cv.bloody.ua.dream.chat.client.packet.client;

import cv.bloody.ua.dream.chat.client.packet.chat.ChatColor;
import cv.bloody.ua.dream.chat.client.packet.chat.Message;
import cv.bloody.ua.dream.chat.client.packet.chat.UserGroup;
import org.json.JSONObject;

public class PacketOutChatMessage extends ClientPacket {

    private final JSONObject message;

    public PacketOutChatMessage(JSONObject message) {
        super(PacketIDEnum.MESSAGE_PACKET.ordinal());
        this.message = message;
    }


    @Override
    public void handle() throws Exception {
        String displayName = message.getString("display_name");
        if(displayName.contains("@")) displayName = message.getString("sender");
        System.out.println(UserGroup.valueOf(message.getString("group")).getPrefix() + " " + ChatColor.ANSI_RESET + "| " +
                displayName + ChatColor.ANSI_RESET + ": " + ChatColor.ANSI_YELLOW
                + message.getString("message") + ChatColor.ANSI_RESET);
    }
}
