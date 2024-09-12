package lp.caio.model;

public class Admin extends Estado {

    public Admin(User user) {
        super(user);
    }


    //@override nos métodos


    @Override
    public void criarUser() {
        super.criarUser();
    }

    @Override
    public void aprovarUser() {
        super.aprovarUser();
    }

    @Override
    public void deletarUser() {
        super.deletarUser();
    }
}
