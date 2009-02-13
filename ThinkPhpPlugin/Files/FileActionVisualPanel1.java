/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ThinkPhpPlugin.Files;

import ThinkPhpPlugin.Entrance;
import ThinkPhpPlugin.ThinkphpCreateProject;
import ThinkPhpPlugin.ThinkphpFile;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.openide.WizardDescriptor;

public final class FileActionVisualPanel1 extends JPanel  implements DocumentListener {

    //private Project project;
    public static final String PROP_ACTION_NAME = "Empty";
    String p_name="";
    String p_path="";
    private FileActionWizardPanel1 panel;

    /** Creates new form FileActionVisualPanel1 */
    public FileActionVisualPanel1(FileActionWizardPanel1 panel) {
        initComponents();

        this.panel = panel;
        ThinkphpCreateProject CreateProject=new ThinkphpCreateProject();
        //this.project=OpenProjects.getDefault().getOpenProjects()[0];
        p_name=CreateProject.GetProjectName();
        p_path=CreateProject.GetProjectDir();
        String entrance_fold=(String) entrance_folds.getSelectedItem();
        project_name.setText(p_name);
        create_file.setText(p_path+"/"+entrance_fold+"/Lib/Action/"+PROP_ACTION_NAME+"Action.class.php");
        Action_name.getDocument().addDocumentListener(this);

        Entrance en=new Entrance();
        ArrayList<String> list=en.GetEntrance();
        for(int i=0;i<list.size();i++)
        {
            entrance_folds.addItem(list.get(i));
        }
    }

    @Override
    public String getName() {
        return "名称和位置";
    }

    @Override
    public void addNotify() {
        super.addNotify();
        //same problem as in 31086, initial focus on Cancel button
        Action_name.requestFocus();
    }

    void read(WizardDescriptor wizardDescriptor) {
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    void store(WizardDescriptor d) {
        
        String action_name=Action_name.getText().trim()+"Action";
        String model_name=Action_name.getText().trim();
        String entrance_fold=(String) entrance_folds.getSelectedItem();
        //String create_fun=create_function.getText();
        String file_name = p_path+File.separatorChar+entrance_fold+File.separatorChar+"Lib"+File.separatorChar+"Action"+File.separatorChar+action_name+".class.php";
        d.putProperty("file_name", file_name);
        d.putProperty("create_model", create_model.isSelected()?"1":"0");
        d.putProperty("create_display", create_display.isSelected()?"1":"0");
        d.putProperty("create_function", create_function.getText());
        d.putProperty("action_name", action_name);
        d.putProperty("entrance_fold", entrance_fold);
        d.putProperty("model_name",model_name);
    }

    boolean valid(WizardDescriptor wizardDescriptor) {
        if(Action_name.getText().length()==0)
        {
            wizardDescriptor.putProperty("WizardPanel_errorMessage",
                    "控制器名称不能为空。");
            return false;
        }
        
        if (ThinkphpFile.ExistFile(create_file.getText())) {
            wizardDescriptor.putProperty("WizardPanel_errorMessage",
                    "控制器名称已经存在。");
            return false; 
        }
        
        wizardDescriptor.putProperty("WizardPanel_errorMessage","");
        return true;
    }

    void validate(WizardDescriptor wizardDescriptor) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        action_name_label = new javax.swing.JLabel();
        Action_name = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        project_name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        create_file = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        entrance_folds = new javax.swing.JComboBox();
        create_model = new javax.swing.JCheckBox();
        create_display = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        create_function = new javax.swing.JTextArea();

        setName(""); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(action_name_label, org.openide.util.NbBundle.getMessage(FileActionVisualPanel1.class, "FileActionVisualPanel1.action_name_label.text")); // NOI18N

        Action_name.setText(org.openide.util.NbBundle.getMessage(FileActionVisualPanel1.class, "FileActionVisualPanel1.Action_name.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(FileActionVisualPanel1.class, "FileActionVisualPanel1.jLabel1.text")); // NOI18N

        project_name.setText(org.openide.util.NbBundle.getMessage(FileActionVisualPanel1.class, "FileActionVisualPanel1.project_name.text")); // NOI18N
        project_name.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(FileActionVisualPanel1.class, "FileActionVisualPanel1.jLabel2.text")); // NOI18N

        create_file.setText(org.openide.util.NbBundle.getMessage(FileActionVisualPanel1.class, "FileActionVisualPanel1.create_file.text")); // NOI18N
        create_file.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(FileActionVisualPanel1.class, "FileActionVisualPanel1.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(FileActionVisualPanel1.class, "FileActionVisualPanel1.jLabel4.text")); // NOI18N

        entrance_folds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entrance_foldsActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(create_model, org.openide.util.NbBundle.getMessage(FileActionVisualPanel1.class, "FileActionVisualPanel1.create_model.text")); // NOI18N

        create_display.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(create_display, org.openide.util.NbBundle.getMessage(FileActionVisualPanel1.class, "FileActionVisualPanel1.create_display.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(FileActionVisualPanel1.class, "FileActionVisualPanel1.jLabel5.text")); // NOI18N

        create_function.setColumns(20);
        create_function.setRows(5);
        create_function.setText(org.openide.util.NbBundle.getMessage(FileActionVisualPanel1.class, "FileActionVisualPanel1.create_function.text")); // NOI18N
        jScrollPane1.setViewportView(create_function);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(action_name_label)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(create_model)
                        .addGap(18, 18, 18)
                        .addComponent(create_display))
                    .addComponent(project_name, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                    .addComponent(entrance_folds, javax.swing.GroupLayout.Alignment.TRAILING, 0, 525, Short.MAX_VALUE)
                    .addComponent(Action_name, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                    .addComponent(create_file, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(action_name_label)
                    .addComponent(Action_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(entrance_folds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(project_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(create_file, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(create_model)
                    .addComponent(create_display))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void entrance_foldsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entrance_foldsActionPerformed
        // TODO add your handling code here:
        String action_name=Action_name.getText();
        String entrance_fold=(String) entrance_folds.getSelectedItem();
        String file_name = p_path+File.separatorChar+entrance_fold+File.separatorChar+"Lib"+File.separatorChar+"Action"+File.separatorChar+action_name+"Action.class.php";
        create_file.setText(file_name);
    }//GEN-LAST:event_entrance_foldsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Action_name;
    private javax.swing.JLabel action_name_label;
    private javax.swing.JCheckBox create_display;
    private javax.swing.JTextField create_file;
    private javax.swing.JTextArea create_function;
    private javax.swing.JCheckBox create_model;
    private javax.swing.JComboBox entrance_folds;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField project_name;
    // End of variables declaration//GEN-END:variables

    // Implementation of DocumentListener --------------------------------------
    public void changedUpdate(DocumentEvent e) {
        updateTexts(e);
    }

    public void insertUpdate(DocumentEvent e) {
        updateTexts(e);
    }

    public void removeUpdate(DocumentEvent e) {
        updateTexts(e);
    }

    /** Handles changes in the Project name and project directory, */
    private void updateTexts(DocumentEvent e) {
        String action_name=Action_name.getText();
        String entrance_fold=(String) entrance_folds.getSelectedItem();
        String file_name = p_path+File.separatorChar+entrance_fold+File.separatorChar+"Lib"+File.separatorChar+"Action"+File.separatorChar+action_name+"Action.class.php";
        create_file.setText(file_name);

        //String create_fun=create_function.getText();
       //String [] tmp=create_fun.split("\n");
       // System.out.println(tmp[0]);
        //System.out.println(create_fun);
        panel.fireChangeEvent(); // Notify that the panel changed
    }
}
