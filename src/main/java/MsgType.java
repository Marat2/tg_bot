public enum MsgType {
    TEXT(1),FILE(2),CALLBACK(3),PHONE(4);
    private int incomingType;
    MsgType(int incomingType){
        this.incomingType = incomingType;
    }
    public int getType(){
        return incomingType;
    }
}
