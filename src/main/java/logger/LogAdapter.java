/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logger;

/**
 *
 * @author tiago
 */
public class LogAdapter {
     private Logger logger;

    public LogAdapter(String format) {
        switch (format.toLowerCase()) {
            case "csv":
                logger = new CSVLogger();
                break;
            case "json":
                logger = new JSONLogger();
                break;
            default:
                throw new IllegalArgumentException("Formato de log não suportado: " + format);
        }
    }

    public void log(String operacao, String nome, String usuario, String mensagemFalha) {
        logger.log(operacao, nome, usuario, mensagemFalha);
    }
}
