public enum BotState {

    LANG(1),FEEDBACK(2),PHONE(3),CODE(4),NAME(5),PASSPORT(6),COMMON(7),REGION(8),CERTIFICATE(9),SETTINGS(10),SETTING_PHONE(11),SETTING_LANG(12),SETTING_REGION(13),EVENTS(14),AGENTINFO(15);

    private int state;

    BotState(int state){
        this.state=state;
    }

    public int getState() {
        return state;
    }

}
