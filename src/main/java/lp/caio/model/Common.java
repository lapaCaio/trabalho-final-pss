package lp.caio.model;

public class Common extends Estado {

    private boolean aprovado;

    public Common(User user) {
        super(user);
        aprovado = false;
    }

    //dar @Override nos métodos


    @Override
    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }
}
