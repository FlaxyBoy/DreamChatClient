package cv.bloody.ua.dream.chat.client.packet.client;

import cv.bloody.ua.dream.chat.client.packet.chat.ChatColor;
import cv.bloody.ua.dream.chat.client.packet.chat.UserGroup;
import org.json.JSONObject;

public class PacketOutPrivateMessage extends ClientPacket {

    private final JSONObject message;

    public PacketOutPrivateMessage(JSONObject message) {
        super(PacketIDEnum.PRIVATE_MESSAGE_PACKET.ordinal());
        this.message = message;
    }


    @Override
    public void handle() throws Exception {
        String displayName = message.getString("display_name");
        if(displayName.contains("@")) displayName = message.getString("sender");
        System.out.println(ChatColor.ANSI_YELLOW + "(" + displayName + " >> " + " YOU) " + UserGroup.valueOf(message.getString("group")) + " " + ChatColor.ANSI_RESET + "| " +
                displayName + ChatColor.ANSI_RESET + ": " + ChatColor.ANSI_YELLOW
                + message.getString("message") + ChatColor.ANSI_RESET);
    }
}
