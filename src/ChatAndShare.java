import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
       
public class ChatAndShare extends javax.swing.JFrame {
        private Peer peer;
        private Peer sendFileToPeer;
        private static final String TERMINATE = "Exit"; 
        private String folderLocator;
	static String myName; 
	static volatile boolean finished = false; 
        InetAddress myIPAddress = null;
        MulticastSocket multiCastSocket= null;
        int myGroupPort, receiptionPort;
        int receiverPort;
        OnMessageQuery messageByPeer;
    public ChatAndShare() {
        initComponents();
        findTheDirectory();
        messageByPeer = new OnMessageQuery(){
            @Override
            public void onAnyMessageQuery(String message1){
                msg_area.append("\n"+message1);
            }
        };
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        ipAdd = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        portNumber = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        msg_area = new javax.swing.JTextArea();
        mssg = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        receivingPort = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Receivers_Port = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Group IP: ");

        ipAdd.setText("234.0.0.1");

        jLabel2.setText("Port :");

        portNumber.setText("8759");
        portNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portNumberActionPerformed(evt);
            }
        });

        msg_area.setColumns(20);
        msg_area.setRows(5);
        jScrollPane1.setViewportView(msg_area);

        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Join");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Name:");

        username.setText("Nabs");
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });

        jLabel4.setText("Receiption Port: ");

        receivingPort.setText("4988");
        receivingPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receivingPortActionPerformed(evt);
            }
        });

        jLabel5.setText("Receiver's Port: ");

        jMenu1.setText("File");

        jMenuItem1.setText("Open");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ipAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Receivers_Port, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel4)
                                .addGap(13, 13, 13))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(receivingPort, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(portNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(mssg)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ipAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(receivingPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Receivers_Port))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mssg, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    if(ipAdd.getText().equals("") || portNumber.getText().equals("") || username.getText().equals("") || receivingPort.getText().equals("")){
        JOptionPane.showMessageDialog(this, "Any field cannot be empty");
    }
    else{
        try{
        myIPAddress = InetAddress.getByName(ipAdd.getText());
        myGroupPort =Integer.parseInt(portNumber.getText());
        multiCastSocket = new MulticastSocket(myGroupPort);
        myName = username.getText();
        receiptionPort =  Integer.parseInt(receivingPort.getText()); 
        multiCastSocket.joinGroup(myIPAddress);
        jButton2.setEnabled(false);
        msg_area.append("Welcome "+myName);
        try{
            peer = new Peer(multiCastSocket,myIPAddress,myGroupPort,messageByPeer);
           sendFileToPeer = new Peer(receiptionPort, messageByPeer,folderLocator);
       }
       catch(Exception e){
           System.out.println("Error : "+ e);
       }
        msg_area.append("\nStart typing msg now...\n");
              }
       catch(IOException e){
           System.out.println("Error...");
       }
 
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    //method to create folder for storing files
    public void findTheDirectory(){
        ClassLoader ldr = ChatAndShare.class.getClassLoader();
        URL url =  ldr.getResource("ChatAndShare.class");
        String directory1 = url.toString();
        String className = "ChatAndShare.class";
        
        folderLocator = directory1.substring(6, directory1.length()-className.length())+"documents/";
        folderLocator = folderLocator.replaceAll("%20", " ");
        File folderPath = new File(folderLocator);
        folderPath.mkdir();
    }
    //method for multi casting the message
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            String message1= mssg.getText();
            mssg.setText("");
            msg_area.append("\nme: "+message1);
            if(message1.equalsIgnoreCase(ChatAndShare.TERMINATE)) 
		{ 
		finished = true; 
                multiCastSocket.leaveGroup(myIPAddress); 
		multiCastSocket.close(); 
		
		}
		message1 = myName + ": " + message1;
		byte[] buffer = message1.getBytes(); 
		DatagramPacket datagram = new
                DatagramPacket(buffer,buffer.length,myIPAddress,myGroupPort); 
                multiCastSocket.send(datagram);
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(rootPane, "Connection Closed!");
        }       
    }//GEN-LAST:event_jButton1ActionPerformed
    
    //method for browsing file and sending file
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

    if(Receivers_Port.getText().equals("")){
           JOptionPane.showMessageDialog(this, "Enter Receiver's Port Number");
       }
       else{
        receiverPort = Integer.parseInt(Receivers_Port.getText());    
       BufferedOutputStream bos=  null;
       BufferedInputStream bis = null;
       Socket socket = null;
       DataOutputStream os= null;
        int returnVal = fileChooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            try{
                socket = new Socket("127.0.0.1", receiverPort);
                os = new DataOutputStream(socket.getOutputStream());
                os.writeUTF(myName);
                os.writeUTF(file.getName());
                os.writeInt((int)file.length());
            FileInputStream selectedFile = new FileInputStream(file);
            bis = new BufferedInputStream(selectedFile);
            bos = new BufferedOutputStream(socket.getOutputStream());
            int b;
            while((b = bis.read()) != -1){
                bos.write(b);
            }
            bos.flush();
            msg_area.append("\nFile "+file.getName()+" Send To Port "+receiverPort);
            selectedFile.close();
            }
                 catch(IOException e){
                System.out.println("Error: "+ e);
            }
            try{//if statement for derefrencing possible null pointer
                if(bis != null && bos != null && socket !=null && os != null){
                os.close();
                socket.close();
                bos.close();
                bis.close();
            }
            }
            catch(IOException e){
                System.out.println("Error : "+e);
            }
            catch(NullPointerException e){
                System.out.println("Error "+e);
            }
       }   
    }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void receivingPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receivingPortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_receivingPortActionPerformed

    private void portNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_portNumberActionPerformed

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatAndShare.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatAndShare.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatAndShare.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatAndShare.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatAndShare().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Receivers_Port;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JTextField ipAdd;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea msg_area;
    private javax.swing.JTextField mssg;
    private javax.swing.JTextField portNumber;
    private javax.swing.JTextField receivingPort;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
