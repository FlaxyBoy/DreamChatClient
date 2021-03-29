package cv.bloody.ua.dream.chat.client.packet.chat;


public enum UserGroup {
    USER("Користувач" , ChatColor.ANSI_YELLOW + " U"),
    MODERATOR("Модератор" , ChatColor.ANSI_BLUE + " MOD"),
    ADMINISTRATOR("Адміністратор" , ChatColor.ANSI_BLUE + " ADM");

    private final String displayName;
    private final String prefix;

    private UserGroup(String displayName , String prefix) {
        this.displayName = displayName;
        this.prefix = prefix;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPrefix() {
        return prefix;
    }
}
