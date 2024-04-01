package MenuPrincipal;

import javax.swing.JOptionPane;

public class MenuPrincipalModificarPartida extends javax.swing.JFrame {
    
    MenuPrincipal mp = new MenuPrincipal();
    
    int valor = (Integer.parseInt(mp.PRODUCTOSmodificar[0][3]));
    
    public MenuPrincipalModificarPartida() {
        initComponents();
        this.setLocationRelativeTo(null);
        mp.EnviarPRODUCTOSaModificar();
        mp.EnviarRow();
        CargarElementos();
    }
    
    private void CargarElementos(){
        jTextArea_Descripcion.setText(mp.PRODUCTOS[mp.ROWXD][2]);
        jTextField_Serie.setText(mp.PRODUCTOS[mp.ROWXD][1]);
        jTextField_Cantidad.setText(mp.PRODUCTOS[mp.ROWXD][7]);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Fondo = new javax.swing.JPanel();
        jLabel_RegistroProd = new javax.swing.JLabel();
        jLabel_Producto1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_Descripcion = new javax.swing.JTextArea();
        jLabel_Producto2 = new javax.swing.JLabel();
        jTextField_Serie = new javax.swing.JTextField();
        jLabel_Producto3 = new javax.swing.JLabel();
        jButton_Menos = new javax.swing.JButton();
        jTextField_Cantidad = new javax.swing.JTextField();
        jButton_Mas = new javax.swing.JButton();
        jButton_Aceptar = new javax.swing.JButton();
        jButton_Cancelar = new javax.swing.JButton();

        setLocation(new java.awt.Point(0, 0));

        jPanel_Fondo.setBackground(new java.awt.Color(243, 255, 242));
        jPanel_Fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_RegistroProd.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_RegistroProd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_RegistroProd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_RegistroProd.setText("Modificar partida");
        jPanel_Fondo.add(jLabel_RegistroProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 6, 179, 45));

        jLabel_Producto1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_Producto1.setText("PRODUCTO:");
        jPanel_Fondo.add(jLabel_Producto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 57, 88, 53));

        jTextArea_Descripcion.setColumns(20);
        jTextArea_Descripcion.setLineWrap(true);
        jTextArea_Descripcion.setRows(5);
        jTextArea_Descripcion.setEnabled(false);
        jScrollPane1.setViewportView(jTextArea_Descripcion);

        jPanel_Fondo.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 57, 240, 53));

        jLabel_Producto2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_Producto2.setText("SERIE:");
        jPanel_Fondo.add(jLabel_Producto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 122, 88, 36));

        jTextField_Serie.setEditable(false);
        jTextField_Serie.setEnabled(false);
        jPanel_Fondo.add(jTextField_Serie, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 122, 240, 36));

        jLabel_Producto3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel_Producto3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Producto3.setText("CANTIDAD");
        jPanel_Fondo.add(jLabel_Producto3, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 170, 120, 32));

        jButton_Menos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton_Menos.setText("-");
        jButton_Menos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_MenosMouseClicked(evt);
            }
        });
        jPanel_Fondo.add(jButton_Menos, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 45, 70));

        jTextField_Cantidad.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextField_Cantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Cantidad.setText("0");
        jTextField_Cantidad.setEnabled(false);
        jPanel_Fondo.add(jTextField_Cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 208, 120, 71));

        jButton_Mas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton_Mas.setText("+");
        jButton_Mas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_MasMouseClicked(evt);
            }
        });
        jPanel_Fondo.add(jButton_Mas, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, 45, 70));

        jButton_Aceptar.setText("Aceptar");
        jButton_Aceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_AceptarMouseClicked(evt);
            }
        });
        jPanel_Fondo.add(jButton_Aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 90, 40));

        jButton_Cancelar.setText("Cancelar");
        jPanel_Fondo.add(jButton_Cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, 90, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Fondo, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Fondo, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_MenosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_MenosMouseClicked
        valor--;
        jTextField_Cantidad.setText(""+valor);
    }//GEN-LAST:event_jButton_MenosMouseClicked

    private void jButton_MasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_MasMouseClicked
        valor++;
        jTextField_Cantidad.setText(""+valor);
    }//GEN-LAST:event_jButton_MasMouseClicked

    private void jButton_AceptarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_AceptarMouseClicked
        mp.PRODUCTOS[mp.ROWXD][7] = ""+jTextField_Cantidad.getText();
        mp.PRODUCTOSenviar[mp.ROWXD][1] = mp.PRODUCTOS[mp.ROWXD][7];
        
        
        mp.PRODUCTOS[mp.ROWXD][8] = ""+(Float.parseFloat(mp.PRODUCTOS[mp.ROWXD][5]) * Float.parseFloat(mp.PRODUCTOS[mp.ROWXD][7])); 
        mp.PRODUCTOSenviar[mp.ROWXD][2] = mp.PRODUCTOS[mp.ROWXD][8];
        
        JOptionPane.showMessageDialog(null, "Registro añadido con éxito");
        dispose();
    }//GEN-LAST:event_jButton_AceptarMouseClicked

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(MenuPrincipalModificarPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalModificarPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalModificarPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalModificarPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipalModificarPartida().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Aceptar;
    private javax.swing.JButton jButton_Cancelar;
    private javax.swing.JButton jButton_Mas;
    private javax.swing.JButton jButton_Menos;
    private javax.swing.JLabel jLabel_Producto1;
    private javax.swing.JLabel jLabel_Producto2;
    private javax.swing.JLabel jLabel_Producto3;
    private javax.swing.JLabel jLabel_RegistroProd;
    private javax.swing.JPanel jPanel_Fondo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea_Descripcion;
    private javax.swing.JTextField jTextField_Cantidad;
    private javax.swing.JTextField jTextField_Serie;
    // End of variables declaration//GEN-END:variables
}
