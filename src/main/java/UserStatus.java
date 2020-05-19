public enum UserStatus {
    FISRT(1),//ввел смс
    SECOND(2),//вел все даные необходимые для регитсрации
    THIRD(3),//согласован
    FOURTH(4);//не согласован
    private int status;
    UserStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return status;
    }
}
