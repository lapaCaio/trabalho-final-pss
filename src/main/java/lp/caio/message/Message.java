package lp.caio.message;

public class Message {

    private String message;
    private boolean lida;

    public Message(String message){
        this.message = message;
        this.lida = false;
    }


    public String getMessage() {
        return message;
    }

    public boolean isLida() {
        return lida;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }

}
