package lp.caio.model;

public abstract class Estado{
    protected User user;

    public Estado (User user) {
        this.user = user;
    }

    private String getNomeEstado() {
        return this.getClass().getSimpleName().toLowerCase();
    }

    //IMPLEMENTAR TUDO DE USER AQUI TBM
    public void criarUser() {
        throw new RuntimeException("Não é possível criar usuário" + getNomeEstado());
    }

    public void aprovarUser() {
        throw new RuntimeException("Não é possível aprovar usuário" + getNomeEstado());
    }

    public void deletarUser() {
        throw new RuntimeException("Não é possível deletar usuário" + getNomeEstado());
    }

    public boolean isAprovado() {
        throw new RuntimeException("Não é possível ver status da aprovação" + getNomeEstado());
    }

    public void setAprovado() {
        throw new RuntimeException("Não é possível setar aprovação" + getNomeEstado());
    }
}
