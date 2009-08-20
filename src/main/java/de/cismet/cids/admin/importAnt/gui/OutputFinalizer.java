/*
 * OutputIntermedTable.java
 *
 * Created on 19. November 2003, 19:17
 */

package de.cismet.cids.admin.importAnt.gui;
import de.cismet.cids.admin.importAnt.*;
import javax.swing.ListSelectionModel;
import javax.swing.table.*;
import javax.swing.JProgressBar;
/**
 *
 * @author  hell
 */
public class OutputFinalizer extends javax.swing.JInternalFrame {
    
    /** Creates new form OutputIntermedTable */
    public OutputFinalizer() {
        this(new Finalizer());
        //tblFinalizer.setC
    }
    
    
    public OutputFinalizer(Finalizer fin) {
        initComponents();
        
        //tblFinalizer.setFont(new java.awt.Font("Dialog", 0, 10));
        setTitle("Status");
        this.tblFinalizer.setModel(fin);
        this.tblFinalizer.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblFinalizer.getColumnModel().getColumn(1).setCellRenderer(new ProgressRenderer());
        this.txtLogs.setForeground(java.awt.Color.BLUE);
        pack();
        
    }
    
    
    public void setLog(String log) {
        txtLogs.setText(log);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jTabbedPane1 = new javax.swing.JTabbedPane();
        scpStatus = new javax.swing.JScrollPane();
        tblFinalizer = new javax.swing.JTable();
        scpLogs = new javax.swing.JScrollPane();
        txtLogs = new javax.swing.JTextArea();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/de/cismet/cids/admin/res/catchup_rls.gif")));
        scpStatus.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(0, 0, 0, 0)));
        scpStatus.setPreferredSize(new java.awt.Dimension(300, 140));
        tblFinalizer.setFocusable(false);
        tblFinalizer.setRowSelectionAllowed(false);
        scpStatus.setViewportView(tblFinalizer);

        jTabbedPane1.addTab("Status", scpStatus);

        scpLogs.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(0, 0, 0, 0)));
        txtLogs.setBackground(new java.awt.Color(204, 204, 204));
        txtLogs.setEditable(false);
        scpLogs.setViewportView(txtLogs);

        jTabbedPane1.addTab("Logs", scpLogs);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        pack();
    }//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JScrollPane scpLogs;
    private javax.swing.JScrollPane scpStatus;
    private javax.swing.JTable tblFinalizer;
    private javax.swing.JTextArea txtLogs;
    // End of variables declaration//GEN-END:variables
    
}

class ProgressRenderer extends JProgressBar implements TableCellRenderer {
    public ProgressRenderer() {
    }
    
    public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return (JProgressBar)value;
    }    
    
    
}
