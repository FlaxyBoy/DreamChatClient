package cv.bloody.ua.dream.chat.client.packet.chat;

public interface Message {

    String getSender();

    String getDisplayName();

    UserGroup getUserGroup();

    String getMessage();

    long getDate();
}
