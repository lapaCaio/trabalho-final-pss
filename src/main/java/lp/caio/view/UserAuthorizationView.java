package lp.caio.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class UserAuthorizationView extends JInternalFrame implements Observer {

    private JList<String> pendingUsersList;
    private DefaultListModel<String> pendingUsersModel;
    private JButton authorizeButton;
    private JButton rejectButton;

    public UserAuthorizationView() {
        super("Autorizar Usuários", true, false, true, true);
        initializeComponents();
        setupLayout();
        setSize(400, 300);
        setVisible(true);
    }

    private void initializeComponents() {
        // Modelo de lista para os usuários pendentes
        pendingUsersModel = new DefaultListModel<>();

        // Lista para exibir os usuários pendentes
        pendingUsersList = new JList<>(pendingUsersModel);
        pendingUsersList.setFont(new Font("Roboto", Font.PLAIN, 14)); // Usando Roboto
        pendingUsersList.setBorder(createListBorder());

        // Botões para autorizar ou rejeitar usuários
        authorizeButton = new JButton("Autorizar");
        authorizeButton.setFocusPainted(false);
        authorizeButton.setBorderPainted(false);

        rejectButton = new JButton("Rejeitar");
        rejectButton.setFocusPainted(false);
        rejectButton.setBorderPainted(false);

        // Estilo dos botões
        styleButton(authorizeButton, new Color(76, 153, 0)); // Cor azul para autorizar
        authorizeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                authorizeButton.setBackground(new Color(76, 120, 80)); // Cor mais escura ao passar o mouse
            }

            @Override
            public void mouseExited(MouseEvent e) {
                authorizeButton.setBackground(new Color(76, 153, 0)); // Cor original
            }
        });
        styleButton(rejectButton, new Color(204, 0, 0)); // Cor vermelha para rejeitar
        rejectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                rejectButton.setBackground(new Color(184, 0, 0)); // Cor mais escura ao passar o mouse
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rejectButton.setBackground(new Color(204, 0, 0)); // Cor original
            }
        });
        // Ações dos botões
        authorizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authorizeSelectedUser();
            }
        });

        rejectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rejectSelectedUser();
            }
        });
    }

    private void setupLayout() {
        // Layout principal com BorderLayout
        setLayout(new BorderLayout());

        // Adicionando a lista de usuários pendentes dentro de um JScrollPane
        JScrollPane scrollPane = new JScrollPane(pendingUsersList);
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Usuários Pendentes", TitledBorder.LEFT, TitledBorder.TOP, new Font("Roboto", Font.BOLD, 12)));
        add(scrollPane, BorderLayout.CENTER);

        // Painel para os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        buttonPanel.add(authorizeButton);
        buttonPanel.add(rejectButton);

        // Adicionando o painel de botões ao layout
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Método para definir a lista de usuários pendentes
    public void setPendingUsers(List<String> users) {
        pendingUsersModel.clear();
        for (String user : users) {
            pendingUsersModel.addElement(user);
        }
    }

    // Método para autorizar o usuário selecionado
    private void authorizeSelectedUser() {
        String selectedUser = pendingUsersList.getSelectedValue();
        if (selectedUser != null) {
            // Lógica de autorização do usuário
            JOptionPane.showMessageDialog(this, "Usuário " + selectedUser + " autorizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            pendingUsersModel.removeElement(selectedUser);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um usuário para autorizar.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para rejeitar o usuário selecionado
    private void rejectSelectedUser() {
        String selectedUser = pendingUsersList.getSelectedValue();
        if (selectedUser != null) {
            // Lógica de rejeição do usuário
            JOptionPane.showMessageDialog(this, "Usuário " + selectedUser + " rejeitado.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            pendingUsersModel.removeElement(selectedUser);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um usuário para rejeitar.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void styleButton(JButton button, Color backgroundColor) {
        button.setFont(new Font("Roboto", Font.BOLD, 14)); // Usando Roboto
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 40));
    }

    private Border createListBorder() {
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
