package lp.caio.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import lp.caio.message.Message;
import lp.caio.services.ServicoAutenticacao;
import lp.caio.view.LoginView;
import lp.caio.model.User;
import lp.caio.view.Observer;

public class PresenterLogin {

    private List<Observer> telas;
    private LoginView view;
    private ServicoAutenticacao servicoAutenticacao;

    // Construtor
    public PresenterLogin(ServicoAutenticacao servicoAutenticacao) {
        this.telas = new ArrayList<>();
        this.servicoAutenticacao = servicoAutenticacao;
    }

    // Método para configurar a view e associar eventos
    public void configurarView(LoginView view) {
        this.view = view;

        if (this.view != null) {
            // Configurar o evento do botão "Acessar"
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
            // Se a view for null, não é possível realizar o login
            showMessage("Erro na configuração da interface. Tente novamente mais tarde.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String userName = view.getTxtUserName().getText();
        String password = new String(view.getTxtSenha().getPassword()); // Usar `new String` para obter a senha em texto claro

        List<Message> messages = new ArrayList<>();
        User user = new User(1, userName, password, LocalDate.now(), 2, 8, "admin", messages, true);

        boolean loginSucess = servicoAutenticacao.autenticar(user);

        if (loginSucess) {
            if (servicoAutenticacao.isAdmin(user)) {
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
