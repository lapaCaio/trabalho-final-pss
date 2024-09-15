package lp.caio.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import lp.caio.model.User;
import lp.caio.services.ServicoAutenticacao;
import lp.caio.view.LoginView;
import lp.caio.view.Observer;

public class PresenterLogin {

    private List<Observer> telas;
    private LoginView view;
    private ServicoAutenticacao servicoAutenticacao;

    public PresenterLogin(ServicoAutenticacao servicoAutenticacao) {
        this.telas = new ArrayList<>();
        this.servicoAutenticacao = servicoAutenticacao;
    }

    public void configurarView(LoginView view) {
        this.view = view;

        if (this.view != null) {
            view.getBtnLogin().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        realizarLogin();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        }
    }

    private void realizarLogin() throws Exception {
        if (view == null) {
            showMessage("Erro na configuração da interface. Tente novamente mais tarde.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String userName = view.getTxtUserName().getText();
        String password = new String(view.getTxtSenha().getPassword());

        servicoAutenticacao.inicializarAdmin(userName, password); // Verifica e cria admin se necessário

        boolean loginSucess = servicoAutenticacao.autenticar(userName, password);

        if (loginSucess) {
            User user = servicoAutenticacao.getUser(userName); // Obter usuário para verificar se é admin
            if (user != null && servicoAutenticacao.isAdmin(user)) {
                showMessage("Bem-vindo, Administrador!", "Informação", JOptionPane.INFORMATION_MESSAGE);
            } else {
                showMessage("Bem-vindo, Usuário!", "Informação", JOptionPane.INFORMATION_MESSAGE);
            }
            notificarTelas(); // Notifica outras telas após login bem-sucedido
        } else {
            showMessage("Usuário ou senha inválidos", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showMessage(String mensagem, String titulo, int tipoMensagem) {
        if (view != null) {
            JOptionPane.showMessageDialog(view, mensagem, titulo, tipoMensagem);
        }
    }

    private void notificarTelas() {
        for (Observer observer : telas) {
            observer.atualizar();
        }
    }

    public List<Observer> getTelas() {
        return telas;
    }

    public void addTela(Observer tela) {
        telas.add(tela);
    }
}
