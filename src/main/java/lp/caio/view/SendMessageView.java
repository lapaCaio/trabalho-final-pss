package lp.caio.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SendMessageView extends JInternalFrame implements Observer {

    private JTextField recipientField;
    private JTextArea messageArea;
    private JButton sendButton;

    public SendMessageView() {
        super("Enviar Mensagem", false, false, true, true);
        initializeComponents();
        setupLayout();
        setSize(500, 350);
        setVisible(true);
    }

    private void initializeComponents() {
        // Campo de texto para o destinatário da notificação
        recipientField = new JTextField(20);
        recipientField.setFont(new Font("Arial", Font.PLAIN, 14));
        recipientField.setBorder(createTextFieldBorder());

        // Área de texto para a mensagem da notificação
        messageArea = new JTextArea(10, 30);
        messageArea.setFont(new Font("Arial", Font.PLAIN, 14));
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setBorder(createTextAreaBorder());

        // Botão de enviar
        sendButton = new JButton("Enviar");
        sendButton.setFont(new Font("Arial", Font.BOLD, 14));
        sendButton.setBackground(new Color(0, 122, 204));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.setBorderPainted(false);
        sendButton.setPreferredSize(new Dimension(120, 40));
        sendButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                sendButton.setBackground(new Color(0, 103, 216)); // Cor mais escura ao passar o mouse
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sendButton.setBackground(new Color(0, 123, 255)); // Cor original
            }
        });
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendNotification();
            }
        });
    }

    private void setupLayout() {
        // Layout principal usando BoxLayout
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel para o destinatário
        JPanel recipientPanel = new JPanel(new BorderLayout());
        recipientPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Destinatário", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 12)));
        recipientPanel.add(recipientField, BorderLayout.CENTER);

        // Painel para a mensagem
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Mensagem", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 12)));
        messagePanel.add(new JScrollPane(messageArea), BorderLayout.CENTER);

        // Painel para o botão de enviar
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        buttonPanel.add(sendButton);

        // Adiciona os painéis ao frame
        add(recipientPanel);
        add(messagePanel);
        add(buttonPanel);
    }

    private Border createTextFieldBorder() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );
    }

    private Border createTextAreaBorder() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );
    }

    private void sendNotification() {
        String recipient = recipientField.getText();
        String message = messageArea.getText();

        if (recipient.isEmpty() || message.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            // Lógica para enviar a notificação
            // Exemplo: Notificação enviada com sucesso
            JOptionPane.showMessageDialog(this, "Mensagem enviada para " + recipient + " com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            // Limpar campos após o envio
            recipientField.setText("");
            messageArea.setText("");
        }
    }

    @Override
    public void atualizar() {
        // Implementar a lógica de atualização da tela
        System.out.println("SendMessageView foi atualizado");
    }
}
