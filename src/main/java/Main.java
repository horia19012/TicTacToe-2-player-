
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Main extends javax.swing.JFrame {


    public Main() {
        initComponents();
        
        JFrame f=new JFrame();   
        FIRST_PLAYER_NAME = JOptionPane.showInputDialog(f,"Enter first player name : ");
        SECOND_PLAYER_NAME = JOptionPane.showInputDialog(f,"Enter second player name : ");
        
        playerPlaying(this);   
    }
    
    public static final byte default_weight = 0;
   
    public static byte weightAt_0_0 = default_weight;
    public static byte weightAt_0_1 = default_weight;
    public static byte weightAt_0_2 = default_weight;
    
    public static byte weightAt_1_0 = default_weight;
    public static byte weightAt_1_1 = default_weight;
    public static byte weightAt_1_2 = default_weight;
    
    public static byte weightAt_2_0 = default_weight;
    public static byte weightAt_2_1 = default_weight;
    public static byte weightAt_2_2 = default_weight;
    
    public static byte[][] scoreState_0 = {{weightAt_0_0, weightAt_0_1, weightAt_0_2},
                             {weightAt_0_0, weightAt_0_1, weightAt_0_2},
                             {weightAt_0_0, weightAt_0_1, weightAt_0_2}};

    public static byte[][] scoreState_1 = {{weightAt_0_0, weightAt_0_1, weightAt_0_2},
                             {weightAt_0_0, weightAt_0_1, weightAt_0_2},
                             {weightAt_0_0, weightAt_0_1, weightAt_0_2}};

    private static final void resetScore(Main obj){
        
        for(int i = 0;i <= 2;i ++){
            for(int j = 0;j <= 2;j ++){
                scoreState_0[i][j] = default_weight;
                scoreState_1[i][j] = default_weight;
            }
        }
        String playerPlayedName = "";
        playerPlayedName = getPlayedText().equals(FIRST_STATE)? FIRST_PLAYER_NAME:SECOND_PLAYER_NAME;

        obj.lbl_0.setText("Player "+playerPlayedName+ " played.");
      //  obj.lbl_1.setText(Arrays.toString(scoreState_0[0])+"\n\n"+ Arrays.toString(scoreState_0[1]) + "\n\n "+ Arrays.toString(scoreState_0[2]));
      //  obj.lbl_2.setText(Arrays.toString(scoreState_1[0])+ "\n "+Arrays.toString(scoreState_1[1])+ Arrays.toString(scoreState_1[2]));
      
        scoreUpdate(obj);
    }
    public static byte[][] getCurrentMatrix(){
    
        byte[][] scoreState = {{weightAt_0_0, weightAt_0_1, weightAt_0_2},
                                 {weightAt_1_0, weightAt_1_1, weightAt_1_2},
                                 {weightAt_2_0, weightAt_2_1, weightAt_2_2}};
        return scoreState;
    }
    static int draw = 0;
    static  byte[] c = {1,1,1};

    static byte[] diatmpr = {0,0,0};
    static byte[] diatmpl = {0,0,0};
    static byte[] htmp = {0,0,0};
    static byte[] vtmp = {0,0,0};
   
    public static boolean getMatrixResult(final byte[][] a){
	      
        final Object[] onesMatrix = {c};
        
        for(int i=0;i <=2;i++){

           for(int j=0;j <=2;j++){
             
        	   if(i == j) {
        		diatmpr[i] = a[i][j]; 
        	   }
        	   htmp[j] = a[i][j];
        	   vtmp[j] = a[j][i];
        	   
           }
           diatmpl[i] = a[i][(a.length - (i+1))];
           
           Object[] compareTo3 = {htmp};
           Object[] compareTo4 = {vtmp};
           if(Arrays.deepEquals(onesMatrix, compareTo3) || Arrays.deepEquals(onesMatrix, compareTo4))
               return true;
        }
        Object[] compareTo = {diatmpr};
        Object[] compareTo2 = {diatmpl};
        
        if(Arrays.deepEquals(onesMatrix, compareTo) || Arrays.deepEquals(onesMatrix, compareTo2))
            return true;
        return false;
     
    }    
    
    public static boolean getMatrixResult(byte[][] a, byte[][] b){
    
        Object[] objArray1 = {a};
        Object[] objArray2 = {b};
        
        return Arrays.deepEquals(objArray1, objArray2);
    }
    
    public static boolean getWiningState(String c){
        byte[][] scoreState;
        
       if(getPlayedText() == FIRST_STATE){
            scoreState = scoreState_0;
        }
        else{
            scoreState = scoreState_1;
        }
       
        return getMatrixResult(scoreState);
    }
    
    public void checkGameState(String c){
        
         if(getWiningState(c)){
            String playerPlayedName = "";
            playerPlayedName = getPlayedText().equals(FIRST_STATE)? FIRST_PLAYER_NAME:SECOND_PLAYER_NAME;

            JOptionPane optionPane = new JOptionPane("Player "+ playerPlayedName +" won.",JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = optionPane.createDialog("GAME OVER.");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
            
            if(playerPlayedName.equals(FIRST_PLAYER_NAME)){
                FIRST_STATE_SCORE++;
            }
            else{
                SECOND_STATE_SCORE++;
            }
            restGame();
         }
         else if(draw == 9){
            JOptionPane optionPane = new JOptionPane("Game draw.",JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = optionPane.createDialog("GAME OVER.");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
            
         }
    }
    
    public static final String FIRST_STATE = "X";
    public static final String SECOND_STATE = "O";
    
    public static String FIRST_PLAYER_NAME = "Pramod";
    public static String SECOND_PLAYER_NAME = "Aftab";
    
    public static int FIRST_STATE_SCORE = 0;
    public static int SECOND_STATE_SCORE = 0;
    
    
    public static byte playerPlayed = 0;
    
    public void playerPlayed(Main obj){
    
        String playerPlayedName = "";
        playerPlayedName = getPlayedText().equals(FIRST_STATE)? FIRST_PLAYER_NAME:SECOND_PLAYER_NAME;
            
        obj.lbl_0.setText("Player "+playerPlayedName+ " played.");
        //obj.lbl_1.setText(Arrays.toString(scoreState_0[0])+"\n\n"+ Arrays.toString(scoreState_0[1]) + "\n\n "+ Arrays.toString(scoreState_0[2]));
        //obj.lbl_2.setText(Arrays.toString(scoreState_1[0])+ "\n "+Arrays.toString(scoreState_1[1])+ Arrays.toString(scoreState_1[2]));
      
        scoreUpdate(obj);
      
        this.checkGameState(obj.lbl_0.getText());
    }
    
      private static void scoreUpdate(Main obj){
        obj.lbl_1.setText(FIRST_PLAYER_NAME + " : "+FIRST_STATE_SCORE);
        obj.lbl_2.setText(SECOND_PLAYER_NAME + " : "+SECOND_STATE_SCORE);
    }
            
    public static void playerPlaying(Main obj){
      /*  JOptionPane optionPane = new JOptionPane("Playing",JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog("Warning!");
        dialog.setAlwaysOnTop(true); // to show top of all other application
        dialog.setVisible(true); // to visible the 
        */
        
        if(playerPlayed == 0){
            playerPlayed = 1;
        }
        else{
            playerPlayed = 0;
        }
    }
    
    public static String getPlayedText(){
        if(playerPlayed == 0)
            return SECOND_STATE;
        else
            return FIRST_STATE;
    }
    
    public static void setPlayerWeight(int i, int j, String c){
        
        if(c.equals(SECOND_STATE)){
            scoreState_1[i][j] = 1;
        }
        else{
            scoreState_0[i][j] = 1;
        }
    }
    
    private static void setFontSize(JButton toBtn){
        toBtn.setFont(new Font("Arial", Font.PLAIN, 150));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_0_0 = new javax.swing.JButton();
        btn_0_1 = new javax.swing.JButton();
        btn_0_2 = new javax.swing.JButton();
        btn_1_0 = new javax.swing.JButton();
        btn_1_1 = new javax.swing.JButton();
        btn_1_2 = new javax.swing.JButton();
        btn_2_0 = new javax.swing.JButton();
        btn_2_1 = new javax.swing.JButton();
        btn_2_2 = new javax.swing.JButton();
        lbl_0 = new javax.swing.JLabel();
        lbl_1 = new javax.swing.JLabel();
        lbl_2 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridLayout(4, 3, 1, 1));

        btn_0_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_0_0ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_0_0);

        btn_0_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_0_1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_0_1);

        btn_0_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_0_2ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_0_2);

        btn_1_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_1_0ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_1_0);

        btn_1_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_1_1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_1_1);

        btn_1_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_1_2ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_1_2);

        btn_2_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_2_0ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_2_0);

        btn_2_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_2_1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_2_1);

        btn_2_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_2_2ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_2_2);
        getContentPane().add(lbl_0);

        lbl_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_1MouseClicked(evt);
            }
        });
        getContentPane().add(lbl_1);
        getContentPane().add(lbl_2);

        jMenu3.setText("Reset");
        jMenu3.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                jMenu3MenuSelected(evt);
            }
        });
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });
        jMenuBar2.add(jMenu3);

        setJMenuBar(jMenuBar2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_0_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_0_0ActionPerformed
        draw++;
        playerPlaying(this);
        btn_0_0.setText(getPlayedText());
        setPlayerWeight(0,0,btn_0_0.getText());
        btn_0_0.setEnabled(false);
        playerPlayed(this);
    }//GEN-LAST:event_btn_0_0ActionPerformed

    private void btn_0_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_0_1ActionPerformed
        draw++;
        playerPlaying(this);
        btn_0_1.setText(getPlayedText());
        setPlayerWeight(0,1,btn_0_1.getText());
        btn_0_1.setEnabled(false);
        playerPlayed(this);
    }//GEN-LAST:event_btn_0_1ActionPerformed

    private void btn_0_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_0_2ActionPerformed
        draw++;
        playerPlaying(this);
        btn_0_2.setText(getPlayedText());
        setPlayerWeight(0,2, btn_0_2.getText());
        btn_0_2.setEnabled(false);
        playerPlayed(this);
    }//GEN-LAST:event_btn_0_2ActionPerformed

    private void btn_1_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_1_0ActionPerformed
        draw++;
        playerPlaying(this);
        btn_1_0.setText(getPlayedText());
        setPlayerWeight(1,0, btn_1_0.getText());
        btn_1_0.setEnabled(false);
        playerPlayed(this);  
    }//GEN-LAST:event_btn_1_0ActionPerformed

    private void btn_1_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_1_1ActionPerformed
        draw++;
        playerPlaying(this);
        btn_1_1.setText(getPlayedText());
        setPlayerWeight(1,1,btn_1_1.getText());
        btn_1_1.setEnabled(false);
        playerPlayed(this);
    }//GEN-LAST:event_btn_1_1ActionPerformed

    private void btn_1_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_1_2ActionPerformed
        draw++;
        playerPlaying(this);  
        btn_1_2.setText(getPlayedText());
        setPlayerWeight(1,2,btn_1_2.getText());
        btn_1_2.setEnabled(false);
        playerPlayed(this);
    }//GEN-LAST:event_btn_1_2ActionPerformed

    private void btn_2_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_2_0ActionPerformed
        draw++;
        playerPlaying(this);
        btn_2_0.setText(getPlayedText());
        setPlayerWeight(2,0,btn_2_0.getText());
        btn_2_0.setEnabled(false);
        playerPlayed(this);  
    }//GEN-LAST:event_btn_2_0ActionPerformed

    private void btn_2_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_2_1ActionPerformed
        draw++;
        playerPlaying(this);  
        btn_2_1.setText(getPlayedText());
        setPlayerWeight(2,1,btn_2_1.getText());
        btn_2_1.setEnabled(false);
        playerPlayed(this);
    }//GEN-LAST:event_btn_2_1ActionPerformed

    private void btn_2_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_2_2ActionPerformed
        draw++;
        playerPlaying(this);
        btn_2_2.setText(getPlayedText());
        setPlayerWeight(2,2,btn_2_2.getText());
        btn_2_2.setEnabled(false);
        playerPlayed(this);  
    }//GEN-LAST:event_btn_2_2ActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        System.out.print("Clicked...............");
    }//GEN-LAST:event_formMouseClicked

    private void lbl_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_1MouseClicked
    
    }//GEN-LAST:event_lbl_1MouseClicked

    private void jMenu3MenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jMenu3MenuSelected
        restGame();

    }//GEN-LAST:event_jMenu3MenuSelected

    private void restGame(){
      /*  JOptionPane optionPane = new JOptionPane("Reset",JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog("Warning!");
        dialog.setAlwaysOnTop(true); // to show top of all other application
        dialog.setVisible(true); // to visible the 
*/
        resetScore(this);
        resetbtn(btn_0_0);
        resetbtn(btn_0_1);
        resetbtn(btn_0_2);

        resetbtn(btn_1_0);
        resetbtn(btn_1_1);
        resetbtn(btn_1_2);

        resetbtn(btn_2_0);
        resetbtn(btn_2_1);
        resetbtn(btn_2_2);
        
      //  FIRST_STATE_SCORE = 0;
      //  SECOND_STATE_SCORE = 0;
    }
    
    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu3ActionPerformed
    
    private void resetbtn(JButton jbobj){
        jbobj.setEnabled(true);
        jbobj.setText("");
    }
   

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            Main mainObj = new Main();
            
            public void run() {
                
                System.out.print("So its started...........");
                
                
                mainObj.setVisible(true);
                mainObj.setExtendedState(MAXIMIZED_BOTH);
                
                setFontSize(mainObj.btn_0_0);
                setFontSize(mainObj.btn_0_1);
                setFontSize(mainObj.btn_0_2);
                
                setFontSize(mainObj.btn_1_0);
                setFontSize(mainObj.btn_1_1);
                setFontSize(mainObj.btn_1_2);
                
                setFontSize(mainObj.btn_2_0);
                setFontSize(mainObj.btn_2_1);
                setFontSize(mainObj.btn_2_2);
        
               // mainObj.setFont(new Font("Arial", Font.PLAIN, 150));

             
            }
        });
    }
    
    private javax.swing.JButton btn_0_0;
    private javax.swing.JButton btn_0_1;
    private javax.swing.JButton btn_0_2;
    private javax.swing.JButton btn_1_0;
    private javax.swing.JButton btn_1_1;
    private javax.swing.JButton btn_1_2;
    private javax.swing.JButton btn_2_0;
    private javax.swing.JButton btn_2_1;
    private javax.swing.JButton btn_2_2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JLabel lbl_0;
    private javax.swing.JLabel lbl_1;
    private javax.swing.JLabel lbl_2;


}
