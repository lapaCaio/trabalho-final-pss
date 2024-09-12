package lp.caio;

import com.formdev.flatlaf.FlatDarkLaf;
import lp.caio.presenter.PresenterLogin;
import lp.caio.services.ServicoAutenticacao;
import lp.caio.view.LoginView;
import lp.caio.view.MainPanel;
import lp.caio.view.MessageView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        ServicoAutenticacao servicoAutenticacao = new ServicoAutenticacao();
        PresenterLogin presenterLogin = new PresenterLogin(servicoAutenticacao);
        LoginView telaLogin = new LoginView();
        MainPanel telaMain = new MainPanel();
        MessageView telaMensagem = new MessageView();

        presenterLogin.addTela(telaMain); //setar tamanho mínimo
        presenterLogin.addTela(telaLogin);
        //presenter.addTela(telaMensagem);
    }
}