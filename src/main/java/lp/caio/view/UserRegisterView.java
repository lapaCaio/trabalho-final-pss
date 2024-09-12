package lp.caio.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserRegisterView extends JInternalFrame implements Observer {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;

    public UserRegisterView() {
        super("Registrar Novo Usuário", true, false, true, true);
        initializeComponents();
        setupLayout();
        setSize(400, 250);
        setVisible(true);
        setBorder(BorderFactory.createEmptyBorder()); // Remove a borda padrão
    }

    private void initializeComponents() {
        // Campos de texto e senha
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        confirmPasswordField = new JPasswordField(20);

        // Adiciona borda aos campos de texto para estilo
        Border fieldBorder = createTextFieldBorder();
        usernameField.setBorder(fieldBorder);
        passwordField.setBorder(fieldBorder);
        confirmPasswordField.setBorder(fieldBorder);

        // Define a fonte para os campos de texto
        Font fieldFont = new Font("Roboto", Font.PLAIN, 12);
        usernameField.setFont(fieldFont);
        passwordField.setFont(fieldFont);
        confirmPasswordField.setFont(fieldFont);

        // Botão de registro
        registerButton = new JButton("Registrar");
        registerButton.setFont(new Font("Roboto", Font.BOLD, 14));
        registerButton.setBackground(new Color(0, 122, 204));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setBorderPainted(false);
        registerButton.setPreferredSize(new Dimension(120, 40));
        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                registerButton.setBackground(new Color(0, 103, 216)); // Cor mais escura ao passar o mouse
            }

            @Override
            public void mouseExited(MouseEvent e) {
                registerButton.setBackground(new Color(0, 123, 255)); // Cor original
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
    }

    private void setupLayout() {
        // Cria um painel com fundo cinza claro
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        //panel.setBackground(new Color(229, 229, 229)); // Fundo cinza claro

        // Layout principal com GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label e campo de texto para nome de usuário
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Nome de Usuário:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Campos de texto ocupam duas colunas
        panel.add(usernameField, gbc);

        // Label e campo de texto para senha
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Resetar gridwidth
        panel.add(new JLabel("Senha:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(passwordField, gbc);

        // Label e campo de texto para confirmação de senha
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Confirmar Senha:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(confirmPasswordField, gbc);

        // Botão de registrar
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(registerButton, gbc);

        // Adiciona o painel ao JInternalFrame
        add(panel);
    }

    private void registerUser() {
        // Obter os dados dos campos
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        // Verificar se os campos estão preenchidos
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar se as senhas coincidem
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "As senhas não coincidem.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Lógica de registro de usuário
        // Exemplo: Registro bem-sucedido
        JOptionPane.showMessageDialog(this, "Usuário registrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        // Limpar os campos após o registro
        usernameField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }

    private Border createTextFieldBorder() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );
    }

    @Override
    public void atualizar() {
        // Método necessário para implementar Observer, mas não utilizado neste contexto
    }
}
