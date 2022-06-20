package malpica;

import javax.swing.ImageIcon;

public class Intro extends javax.swing.JFrame {

    public Intro() {

        // Atributos del frame Intro..
        initComponents();
        setLayout(null);
        setSize(640, 315);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/images/logos/logo.png")).getImage());

        // Arranque del cronómetro..
        cronometro.start();
    }

    // Cronómetro..
    Thread cronometro = new Thread() {

        @Override
        public void run() {

            int hor = 0, min = 0, seg = 0;

            for (;;) {

                try {

                    seg++;

                    if (seg > 59) {

                        seg = 0;
                        min++;
                    }

                    if (min > 59) {

                        seg = 0;
                        min = 0;
                        hor++;
                    }

                    if (seg == 6) {

                        dispose();

                        new Login().setVisible(true);

                        cronometro.stop();
                    }

                    // Suspensión del cronómetro..
                    Thread.sleep(1000);

                } catch (InterruptedException e) {

                }
            }
        }
    };

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondoIntro = new javax.swing.JPanel();
        gifIntro = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Malpica Soft");
        setUndecorated(true);
        getContentPane().setLayout(null);

        fondoIntro.setBackground(new java.awt.Color(0, 0, 0));
        fondoIntro.setPreferredSize(new java.awt.Dimension(596, 321));
        fondoIntro.setLayout(null);

        gifIntro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logos/logo-gif.gif"))); // NOI18N
        fondoIntro.add(gifIntro);
        gifIntro.setBounds(160, 8, 300, 300);

        getContentPane().add(fondoIntro);
        fondoIntro.setBounds(0, 0, 640, 315);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Intro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Intro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Intro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Intro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Intro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel fondoIntro;
    private javax.swing.JLabel gifIntro;
    // End of variables declaration//GEN-END:variables
}
