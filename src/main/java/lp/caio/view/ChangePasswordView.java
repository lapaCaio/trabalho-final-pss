package lp.caio.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChangePasswordView extends JInternalFrame implements Observer {

    private JPasswordField currentPasswordField;
    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;
    private JButton changePasswordButton;

    public ChangePasswordView() {
        super("Trocar Senha", true, false, true, true);
        initializeComponents();
        setupLayout();
        setSize(500, 300);
        setVisible(true);
        //setBackground(new Color(240, 240, 240)); // Fundo cinza claro
        //getContentPane().setBackground(new Color(240, 240, 240)); // Define o fundo do conteúdo como cinza claro
    }

    private Border createTextFieldBorder() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );
    }


    private void initializeComponents() {
        // Campos de senha com bordas e altura aumentada
        currentPasswordField = new JPasswordField(30);
        currentPasswordField.setFont(new Font("Roboto", Font.PLAIN, 12));
        currentPasswordField.setBorder(createTextFieldBorder());

        newPasswordField = new JPasswordField(30);
        newPasswordField.setFont(new Font("Roboto", Font.PLAIN, 12));
        newPasswordField.setBorder(createTextFieldBorder());

        confirmPasswordField = new JPasswordField(30);
        confirmPasswordField.setFont(new Font("Roboto", Font.PLAIN, 12));
        confirmPasswordField.setBorder(createTextFieldBorder());

        // Botão de trocar senha estilizado com altura aumentada
        changePasswordButton = new JButton("Trocar Senha");
        changePasswordButton.setFont(new Font("Roboto", Font.BOLD, 14));
        changePasswordButton.setBackground(new Color(0, 123, 255));
        changePasswordButton.setForeground(Color.WHITE);
        changePasswordButton.setFocusPainted(false);
        changePasswordButton.setBorderPainted(false);
        changePasswordButton.setPreferredSize(new Dimension(200, 50)); // Largura e altura

        // Adiciona um efeito de destaque ao passar o mouse sobre o botão
        changePasswordButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                changePasswordButton.setBackground(new Color(0, 103, 216)); // Cor mais escura ao passar o mouse
            }

            @Override
            public void mouseExited(MouseEvent e) {
                changePasswordButton.setBackground(new Color(0, 123, 255)); // Cor original
            }
        });

        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePassword();
            }
        });
    }

    private void setupLayout() {
        // Layout principal com GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label e campo para senha atual
        JLabel currentPasswordLabel = new JLabel("Senha Atual:");
        currentPasswordLabel.setFont(new Font("Roboto", Font.BOLD, 12)); // Fonte Roboto 12 Bold
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(currentPasswordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(currentPasswordField, gbc);

        // Label e campo para nova senha
        JLabel newPasswordLabel = new JLabel("Nova Senha:");
        newPasswordLabel.setFont(new Font("Roboto", Font.BOLD, 12)); // Fonte Roboto 12 Bold
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(newPasswordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(newPasswordField, gbc);

        // Label e campo para confirmação da nova senha
        JLabel confirmPasswordLabel = new JLabel("Confirmar Senha:");
        confirmPasswordLabel.setFont(new Font("Roboto", Font.BOLD, 12)); // Fonte Roboto 12 Bold
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(confirmPasswordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(confirmPasswordField, gbc);

        // Botão de trocar senha
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(changePasswordButton, gbc);
    }

    private void changePassword() {
        // Obter as senhas dos campos
        String currentPassword = new String(currentPasswordField.getPassword());
        String newPassword = new String(newPasswordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        // Verificar se os campos estão preenchidos
        if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar se a nova senha e a confirmação coincidem
        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "As novas senhas não coincidem.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Lógica de troca de senha
        // Exemplo: Senha alterada com sucesso
        JOptionPane.showMessageDialog(this, "Senha alterada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        // Limpar os campos após a troca de senha
        currentPasswordField.setText("");
        newPasswordField.setText("");
        confirmPasswordField.setText("");
    }

    @Override
    public void atualizar() {
        // Método necessário para implementar Observer, mas não utilizado neste contexto
    }
}
