package lp.caio.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginView extends JInternalFrame implements Observer {
    private JTextField txtUserName;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblUserName;
    private JLabel lblPassword;

    public LoginView() {
        super("Login", true, false, true, true);
        initComponents();
        configuraJanela();
    }

    private void initComponents() {
        // Inicializa os componentes de interface gráfica
        lblUserName = new JLabel("Nome do usuário: ");
        lblUserName.setFont(new Font("Roboto", Font.BOLD, 12));

        lblPassword = new JLabel("Senha: ");
        lblPassword.setFont(new Font("Roboto", Font.BOLD, 12));

        txtUserName = new JTextField(30); // Aumenta a largura do campo
        txtUserName.setFont(new Font("Roboto", Font.PLAIN, 12));
        txtUserName.setBorder(createTextFieldBorder());

        txtPassword = new JPasswordField(30); // Aumenta a largura do campo
        txtPassword.setFont(new Font("Roboto", Font.PLAIN, 12));
        txtPassword.setBorder(createTextFieldBorder());

        btnLogin = new JButton("Acessar");
        btnLogin.setFont(new Font("Roboto", Font.BOLD, 14));
        btnLogin.setBackground(new Color(0, 122, 204));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setFocusPainted(false);
        btnLogin.setPreferredSize(new Dimension(60, 36)); // Diminuir a largura do botão

        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnLogin.setBackground(new Color(0, 103, 216)); // Cor mais escura ao passar o mouse
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnLogin.setBackground(new Color(0, 123, 255)); // Cor original
            }
        });
        // Cria um painel para organizar os componentes
        JPanel painel = new JPanel();
        painel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Adiciona os componentes ao painel usando GridBagConstraints
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        painel.add(lblUserName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        painel.add(txtUserName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        painel.add(lblPassword, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        painel.add(txtPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;  // O botão "Acessar" deve ocupar duas células para centralizar
        gbc.anchor = GridBagConstraints.CENTER;  // Centraliza o botão "Acessar"
        painel.add(btnLogin, gbc);

        // Adiciona o painel ao JInternalFrame
        add(painel);
    }

    private Border createTextFieldBorder() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );
    }

    private void configuraJanela() {
        setSize(600, 250); // Aumenta o tamanho da janela para acomodar os campos maiores
        setVisible(true);
        //setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 1), "Login", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Roboto", Font.BOLD, 16)));
        //VER ISSO AQUI PQ DEIXA A TELA MUITO BONITA
        setResizable(false);
        toFront(); // Traz a janela para frente
    }

    public JTextField getTxtUserName() {
        return txtUserName;
    }

    public JPasswordField getTxtSenha() {
        return txtPassword;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    @Override
    public void atualizar() {
        // Implementar a lógica de atualização da tela
        System.out.println("LoginView foi atualizado");
    }
}
