/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logger;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author tiago
 */
public class JSONLogger implements Logger{
    
       private static final String FILE_NAME = "log.json";

    @Override
    public void log(String operacao, String nome, String usuario, String mensagemFalha) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            LocalDateTime now = LocalDateTime.now();
            String data = now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String hora = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

            String logMessage = String.format("{ \"operacao\": \"%s\", \"nome\": \"%s\", \"data\": \"%s\", \"hora\": \"%s\", \"usuario\": \"%s\", \"mensagemFalha\": \"%s\" }\n",
                    operacao, nome, data, hora, usuario, mensagemFalha == null ? "" : mensagemFalha);
            writer.write(logMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
