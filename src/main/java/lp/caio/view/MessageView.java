package lp.caio.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MessageView extends JInternalFrame implements Observer {

    private JList<String> receivedMessagesList;
    private JList<String> readMessagesList;
    private DefaultListModel<String> receivedMessagesModel;
    private DefaultListModel<String> readMessagesModel;

    public MessageView() {
        super("Mensagens", true, false, true, true);
        initializeComponents();
        setupLayout();
        setSize(500, 400);
        setVisible(true);
    }

    private void initializeComponents() {
        // Modelos de lista para mensagens recebidas e lidas
        receivedMessagesModel = new DefaultListModel<>();
        readMessagesModel = new DefaultListModel<>();

        // Listas para as mensagens
        receivedMessagesList = new JList<>(receivedMessagesModel);
        readMessagesList = new JList<>(readMessagesModel);

        // Estiliza as listas
        receivedMessagesList.setBorder(createListBorder("Mensagens Recebidas"));
        readMessagesList.setBorder(createListBorder("Mensagens Lidas"));
    }

    private void setupLayout() {
        // Usando JTabbedPane para separar as categorias
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Roboto", Font.BOLD, 12));
        tabbedPane.setTabPlacement(JTabbedPane.TOP);

        // Adicionando as listas às abas
        tabbedPane.addTab("Recebidas", new JScrollPane(receivedMessagesList));
        tabbedPane.addTab("Lidas", new JScrollPane(readMessagesList));

        // Adicionando o tabbedPane ao layout da JInternalFrame
        add(tabbedPane, BorderLayout.CENTER);

        // Adicionando painel de ferramentas
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        toolbar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JButton markAsReadButton = new JButton("Marcar como lida");
        JButton deleteButton = new JButton("Excluir");

        // Estiliza os botões
        markAsReadButton.setFont(new Font("Roboto", Font.BOLD, 14));
        markAsReadButton.setBackground(new Color(76, 140, 80)); // Verde
        markAsReadButton.setForeground(Color.WHITE);
        markAsReadButton.setBorderPainted(false);
        markAsReadButton.setFocusPainted(false);
        markAsReadButton.setPreferredSize(new Dimension(200, 36)); // Aumenta a largura

        markAsReadButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                markAsReadButton.setBackground(new Color(76, 120, 80)); // Cor mais escura ao passar o mouse
            }

            @Override
            public void mouseExited(MouseEvent e) {
                markAsReadButton.setBackground(new Color(76, 140, 80)); // Cor original
            }
        });


        deleteButton.setFont(new Font("Roboto", Font.BOLD, 14));
        deleteButton.setBackground(new Color(244, 67, 54)); // Vermelho
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.setBorderPainted(false);
        deleteButton.setPreferredSize(new Dimension(150, 36)); // Largura padrão

        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                deleteButton.setBackground(new Color(200, 67, 54)); // Cor mais escura ao passar o mouse
            }

            @Override
            public void mouseExited(MouseEvent e) {
                deleteButton.setBackground(new Color(244, 67, 54)); // Cor original
            }
        });


        toolbar.add(markAsReadButton);
        toolbar.add(deleteButton);

        add(toolbar, BorderLayout.NORTH);
    }

    private Border createListBorder(String title) {
        return BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                title,
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Roboto", Font.BOLD, 12)
        );
    }

    @Override
    public void atualizar() {
        // Implementar a lógica de atualização da tela
        System.out.println("MessageView foi atualizado");
    }
}
