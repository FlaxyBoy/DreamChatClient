package cv.bloody.ua.dream.chat.client.packet.server;

import org.json.JSONObject;

public class AuthPacket extends ServerPacket {

    private final String login;
    private final String password;

    public AuthPacket(String login , String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public JSONObject toJSON() {
        return new JSONObject().put("packet_id" , 1).put("login" , login).put("password" , password);
    }
}
