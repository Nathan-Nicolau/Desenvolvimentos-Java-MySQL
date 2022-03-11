package cliente;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.logging.*;
import javax.swing.*;
import static javax.swing.JOptionPane.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Chat extends javax.swing.JFrame {

    private Socket socket;
    private String nome;
    private BufferedReader out;
    private InputStreamReader in;
    private boolean rodar;
    private String horaConexao;
    private String horadaMensagem;

    public Chat(String nome) {
        this.nome = nome;
        rodar = true;
        try {
            socket = new Socket("localhost", 5021);
        } catch (IOException ex) {
            showMessageDialog(null, "Erro ao conectar!", "Erro", ERROR_MESSAGE);
            System.exit(0);
        }
        initComponents();
        setupComponents();
        Thread();
    }

    private void Thread() {
        setNomeCliente();
        Thread t = new Thread(new Runnable() {
            String msg;

            @Override
            public void run() {
                horaConexao = getHoraConexao();
                horadaMensagem = horaConexao; // em teoria, no momento em que a aplicação é iniciada,a primeira
                try {                         // mensagem será enviada no mesmo minuto.
                    in = new InputStreamReader(socket.getInputStream());
                    out = new BufferedReader(in);
                    while ((msg = out.readLine()) != null) {
                        horadaMensagem = setHoraMensagem();
                        msgRecebida.setText(msgRecebida.getText() + msg + "\n");
                        if (!rodar) {
                            break;
                        }
                    }
                } catch (IOException ex) {
                    try {
                        showMessageDialog(null, "O Servidor foi desconectado...\n"
                                + "       " + "saindo da aplicação", "Aviso", WARNING_MESSAGE);
                        in.close();
                        out.close();
                        System.exit(0);
                    } catch (IOException ex1) {
                        Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                } finally {
                    try {
                        in.close();
                        out.close();
                        socket.close();
                    } catch (IOException ex) {
                        showMessageDialog(null, "Erro ao Finalizar", "Erro", ERROR_MESSAGE);
                    }
                }
            }
        });
        t.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        msgRecebida = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        msgEnviar = new javax.swing.JTextArea();
        btnEnviar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        nome_cliente = new javax.swing.JTextField();
        info_saida = new javax.swing.JLabel();
        campo_hora = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setToolTipText("");
        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bate-Papo:", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jScrollPane1.setAutoscrolls(true);

        msgRecebida.setEditable(false);
        msgRecebida.setColumns(20);
        msgRecebida.setFont(new java.awt.Font("Myanmar Text", 1, 12)); // NOI18N
        msgRecebida.setRows(5);
        msgRecebida.setToolTipText("Aqui aparecem todas as mensagens ;)");
        msgRecebida.setAutoscrolls(false);
        msgRecebida.setSelectionColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(msgRecebida);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane2.setViewportBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mensagem:", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jScrollPane2.setAutoscrolls(true);

        msgEnviar.setColumns(20);
        msgEnviar.setFont(new java.awt.Font("Myanmar Text", 1, 11)); // NOI18N
        msgEnviar.setRows(5);
        msgEnviar.setToolTipText("Digite aqui sua mensagem...");
        msgEnviar.setAutoscrolls(false);
        msgEnviar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                msgEnviarKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(msgEnviar);

        btnEnviar.setBackground(new java.awt.Color(204, 204, 204));
        btnEnviar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnEnviar.setText("Enviar");
        btnEnviar.setToolTipText("Clique para enviar sua mensagem");
        btnEnviar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnEnviar.setOpaque(false);
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        btnSair.setBackground(new java.awt.Color(204, 204, 204));
        btnSair.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnSair.setText("Sair do Chat");
        btnSair.setToolTipText("Clique aqui para sair da aplicação...");
        btnSair.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        nome_cliente.setEditable(false);
        nome_cliente.setBackground(new java.awt.Color(230, 230, 230));
        nome_cliente.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        nome_cliente.setForeground(new java.awt.Color(153, 0, 0));
        nome_cliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nome_cliente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente:", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 9))); // NOI18N

        info_saida.setBackground(new java.awt.Color(204, 204, 255));
        info_saida.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        info_saida.setForeground(new java.awt.Color(0, 0, 204));
        info_saida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        info_saida.setText("?");
        info_saida.setToolTipText("Para fechar o SERVIDOR clique no X da janela ao lado");
        info_saida.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        info_saida.setOpaque(true);

        campo_hora.setEditable(false);
        campo_hora.setBackground(new java.awt.Color(230, 230, 230));
        campo_hora.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        campo_hora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campo_hora.setToolTipText("Horário da conexão");
        campo_hora.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hora:", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 9))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(nome_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campo_hora, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(232, 232, 232)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(info_saida, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campo_hora, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nome_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(info_saida)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                .addContainerGap())
        );

        nome_cliente.getAccessibleContext().setAccessibleName("Usuário");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

  private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
      String msg = nome + " escreveu as " + horadaMensagem + ": ";
      try {
          PrintStream ps = new PrintStream(socket.getOutputStream());
          msg += msgEnviar.getText();
          ps.println(msg);
          ps.flush();
          msgEnviar.setText("");
      } catch (IOException ex) {
          showMessageDialog(null, "Erro ao enviar a mensagem!", "Erro", ERROR_MESSAGE);
      }
  }//GEN-LAST:event_btnEnviarActionPerformed

  private void msgEnviarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_msgEnviarKeyPressed
      if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
          String msg = nome + " escreveu as " + horadaMensagem + ": ";
          try {
              PrintStream ps = new PrintStream(socket.getOutputStream());
              msg += msgEnviar.getText();
              ps.println(msg);
              ps.flush();
              msgEnviar.setText("");
          } catch (IOException ex) {
              showMessageDialog(null, "Erro ao enviar a mensagem!", "Erro", ERROR_MESSAGE);
          }
      }
  }//GEN-LAST:event_msgEnviarKeyPressed

  private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
      try {
          if (btnSair.isEnabled() == true) {
              int resposta = showConfirmDialog(null, "Deseja realmente sair? ", "Sair do OneMessage", JOptionPane.OK_CANCEL_OPTION);
              if (resposta == OK_OPTION) {
                  socket.close();
                  System.exit(0);
              } else {
                  showMessageDialog(null, "Voltando...", "Cancelando saída", JOptionPane.INFORMATION_MESSAGE);
              }
          }

      } catch (IOException ex) {
          ex.getStackTrace();
      }
  }//GEN-LAST:event_btnSairActionPerformed

    private void setNomeCliente() {
        nome_cliente.setToolTipText("Essa tela é do cliente " + nome);
        nome_cliente.setText(nome);
        nome_cliente.setFont(new Font("Tahoma", Font.BOLD, 10));
    }

    private void setupComponents() {
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("OneMessage");
    }

    private String getHoraConexao() {
        String horaDaConexao;
        LocalTime hora_atual = LocalTime.now(ZoneId.of("UTC-3"));
        DateTimeFormatter formatarHora = DateTimeFormatter.ofPattern("HH:mm");
        horaDaConexao = formatarHora.format(hora_atual);                                                                   
        campo_hora.setText(horaDaConexao);              
        return horaDaConexao;                           
    }

    private String setHoraMensagem() {
        while (true) {
            LocalTime hora_atual = LocalTime.now(ZoneId.of("UTC-3"));
            DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
            String horaMensagemFormatada = formatterHora.format(hora_atual);
            return horaMensagemFormatada;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnSair;
    public static javax.swing.JTextField campo_hora;
    private javax.swing.JLabel info_saida;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea msgEnviar;
    private javax.swing.JTextArea msgRecebida;
    public static javax.swing.JTextField nome_cliente;
    // End of variables declaration//GEN-END:variables
}
