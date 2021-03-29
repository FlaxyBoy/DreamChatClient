package cv.bloody.ua.dream.chat.client.packet.server;

import org.json.JSONObject;

public class RegistrationPacket extends ServerPacket {

    private final String login;
    private final String password;

    public RegistrationPacket(String login , String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public JSONObject toJSON() {
        return new JSONObject().put("packet_id" , 2).put("login" , login).put("password" , password);
    }
}
