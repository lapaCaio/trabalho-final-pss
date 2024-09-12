package lp.caio.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

public class UserListingView extends JInternalFrame implements Observer {

    private JList<String> userList;
    private DefaultListModel<String> userListModel;

    public UserListingView() {
        super("Listagem de Usuários", true, false, true, true);
        initializeComponents();
        setupLayout();
        setSize(400, 300);
        setVisible(true);
    }

    private void initializeComponents() {
        // Modelo de lista para os usuários
        userListModel = new DefaultListModel<>();

        // Lista para exibir os usuários
        userList = new JList<>(userListModel);
        userList.setFont(new Font("Roboto", Font.PLAIN, 12));
        userList.setBorder(createListBorder());
    }

    private void setupLayout() {
        // Usando BorderLayout para dispor a lista no centro
        setLayout(new BorderLayout());

        // Adicionando a lista dentro de um JScrollPane
        JScrollPane scrollPane = new JScrollPane(userList);
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),
                "Usuários Cadastrados", TitledBorder.LEFT, TitledBorder.TOP, new Font("Roboto", Font.BOLD, 12)));

        // Adiciona o JScrollPane ao layout
        add(scrollPane, BorderLayout.CENTER);
    }

    // Método para atualizar a lista de usuários
    public void setUsers(List<String> users) {
        userListModel.clear();
        for (String user : users) {
            userListModel.addElement(user);
        }
    }

    @Override
    public void atualizar() {
        // Lógica de atualização quando o Observer for notificado
        // Exemplo de atualização de dados (a lista de usuários pode vir de um modelo)
        // this.setUsers(userService.getAllUsers());
    }

    private Border createListBorder() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );
    }
}
