package lp.caio.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainPanel extends JFrame implements Observer {

    private ArrayList<Observer> telas = new ArrayList<>();
    private JDesktopPane desktopPane;

    public MainPanel() {
        // Definir título da janela
        setTitle("Trabalho Final PSS - Caio Lapa, Tiago Artem, Sylvio Veras");

        // Configurar a janela para abrir maximizada
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Definir o tamanho mínimo da janela
        setMinimumSize(new Dimension(800, 600)); // Ajuste conforme necessário

        // Inicializa o JDesktopPane e configura o fundo
        desktopPane = new JDesktopPane();
        //desktopPane.setBackground(new Color(229, 229, 229)); // Fundo cinza claro
        getContentPane().add(desktopPane, BorderLayout.CENTER);

        // Adicionar janelas internas
        adicionarTelaInterna(new LoginView());
        adicionarTelaInterna(new MessageView());
        adicionarTelaInterna(new SendMessageView());
        adicionarTelaInterna(new ChangePasswordView());
        adicionarTelaInterna(new UserListingView());
        adicionarTelaInterna(new UserRegisterView());
        adicionarTelaInterna(new UserAuthorizationView());

        // Tornar a janela visível
        setVisible(true);
    }

    public void adicionarTelaInterna(JInternalFrame tela) {
        // Personalizar a aparência das janelas internas
        //tela.setBorder(BorderFactory.createLineBorder(new Color(0, 122, 204), 2)); // Borda azul
        //tela.setBackground(new Color(255, 255, 255)); // Fundo branco para as janelas internas
        desktopPane.add(tela);
        telas.add((Observer) tela);
        tela.setVisible(true);
    }

    public static void main(String[] args) {
        // Criar e mostrar a tela
        SwingUtilities.invokeLater(MainPanel::new);
    }

    @Override
    public void atualizar() {
        // Implementação do método atualizar
        for (Observer tela : telas) {
            tela.atualizar();
        }
    }
}
