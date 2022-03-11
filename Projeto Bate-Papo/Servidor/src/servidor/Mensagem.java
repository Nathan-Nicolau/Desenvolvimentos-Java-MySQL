package servidor;

import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;

public class Mensagem {
  private final Socket socket;
  private ArrayList<PrintStream> clientes;
  public Thread t;

  public Mensagem(Socket socket, ArrayList<PrintStream> clientes) throws IOException {
    this.socket = socket;
    this.clientes = clientes; 
    Thread();
  }

  private void Thread() throws IOException {
    t = new Thread(new Runnable() {
      @Override
      public void run() {
        String mensagem = "";
        try {
          InputStreamReader in = new InputStreamReader(socket.getInputStream());
          BufferedReader out = new BufferedReader(in);
          while ((mensagem = out.readLine()) != null) {
            enviarMensagem(mensagem);    
              if(clientes.size() == 0){
                in.close();
                out.close();
                fechar(clientes.size());
              }
          }
        } catch (IOException ex) {
          ex.getStackTrace();
        }
      }
    });  
    t.start();
  }

  public int fechar(int value){
    if(value == 0){
        t.stop();
        System.exit(value);       
    }  
    return value;
   
   }
  private void enviarMensagem(String mensagem) {
    for (int i = 0; i < clientes.size(); i++) {
      clientes.get(i).println(mensagem);
      clientes.get(i).flush();
    }
  }

  public void setClientes(ArrayList<PrintStream> clientes) {
    this.clientes = clientes;
  }  
}
