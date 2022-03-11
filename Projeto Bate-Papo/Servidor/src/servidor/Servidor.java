package servidor;

import java.awt.Font;
import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Servidor {
    private static JFrame tela_status = new JFrame();

    public Servidor(JFrame telaServidor, int campo) {
        Servidor.tela_status = telaServidor = new JFrame();
    }
    public static void main(String[] args) throws IOException, NullPointerException {
        ServerSocket server = new ServerSocket(5021);
        Socket socket;
        ArrayList<PrintStream> clientes = new ArrayList<>();
        try {
            int num_clientes_conectados;
            while (true) {
                socket = server.accept();
                clientes.add(new PrintStream(socket.getOutputStream())); // guarda o endereço do cliente
                Mensagem mensagem = new Mensagem(socket, clientes);
                num_clientes_conectados = clientes.size();
                JTextField num_clientes = setupComponents(num_clientes_conectados);
                startStatus(tela_status, num_clientes);
            }
        } catch (IOException ex) {
           
        }
    }

    private static JTextField setupComponents(int numero_clientes) {
        JTextField campo_clientes = new JTextField("N° de clientes conectados: " + numero_clientes);
        campo_clientes.setSize(tela_status.getPreferredSize().width, tela_status.getPreferredSize().height);
        campo_clientes.setHorizontalAlignment(JTextField.CENTER);
        campo_clientes.setToolTipText("Número de conexões que foram estabelecidas com o servidor");
        campo_clientes.setEditable(false);
        campo_clientes.setVisible(true);
        campo_clientes.setFont(new Font("Tahoma", Font.BOLD, 10));
        return campo_clientes;
    }

    private static void startStatus(JFrame tela, JTextField num_clientes) {
        tela.setSize(num_clientes.getPreferredSize().width + 20, 60);
        tela.setResizable(false);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.add(num_clientes);
        tela.setLocation(950, 139);
        tela.setVisible(true);
    }

}
