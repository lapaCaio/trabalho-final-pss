package lp.caio.services;

import lp.caio.model.User;

public class ServicoAutenticacao {

    private User admin;
    public ServicoAutenticacao() {
        //inicialmente não há admin cadastrado
        this.admin = null;
    }
    // Exemplo simplificado, substitua pela lógica de autenticação real
    public boolean autenticar(User user) {
        return "user".equals(user.getName()) && "password".equals(user.getPassword());
    }

    public boolean isAdmin(User user) {
        return admin != null && admin.equals(user);
    }
}

