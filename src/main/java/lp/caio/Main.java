package lp.caio;

import com.formdev.flatlaf.FlatDarkLaf;
import database.DataBaseUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
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

          try (Connection conn = DriverManager.getConnection(DataBaseUtils.URL)) {
          
              DataBaseUtils.createTables(conn);
    
            
        } catch (SQLException e) {
            System.out.println("Erro ao inicializar o banco de dados: " + e.getMessage());
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