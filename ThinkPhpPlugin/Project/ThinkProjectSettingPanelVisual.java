/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ThinkProjectSettingPanelVisual.java
 *
 * Created on 2009-2-6, 21:33:03
 */

package ThinkPhpPlugin.Project;

import ThinkPhpPlugin.Setting.ThinkphpSettingPanel;
import java.util.prefs.Preferences;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.openide.WizardDescriptor;

/**
 *
 * @author Administrator
 */
public class ThinkProjectSettingPanelVisual extends javax.swing.JPanel  implements DocumentListener {

    private ThinkProjectSettingWizardPanel panel;
    /** Creates new form ThinkProjectSettingPanelVisual */
    public ThinkProjectSettingPanelVisual(ThinkProjectSettingWizardPanel panel) {
        initComponents();
        this.panel = panel;
        
        // 监听器
        log_file_size.getDocument().addDocumentListener(this);
        limit_refresh_time.getDocument().addDocumentListener(this);
        default_module.getDocument().addDocumentListener(this);
        default_action.getDocument().addDocumentListener(this);
        url_suffix.getDocument().addDocumentListener(this);

        //装载配置信息
        Preferences root=Preferences.userNodeForPackage(ThinkphpSettingPanel.class);
        route_mode.setSelectedIndex(root.getInt("route_mode", 0));
        hide_index.setSelected(root.getBoolean("hide_index", false));
        url_suffix.setText(root.get("hide_suffix", ".html"));
        web_log.setSelected(root.getBoolean("web_log_on", false));
        log_file_size.setText(root.get("web_log_size", ""));

        debug_mode.setSelected(root.getBoolean("debug_mode", false));
        limit_refresh_on.setSelected(root.getBoolean("limit_refresh_on", false));
        limit_refresh_time.setText(root.get("limit_refresh_time", "3"));
        default_module.setText(root.get("default_module", "Index"));
        default_action.setText(root.get("default_action", "index"));
    }


    void read(WizardDescriptor wizardDescriptor) {
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    void store(WizardDescriptor d) {
        Preferences root=Preferences.userNodeForPackage(ThinkphpSettingPanel.class);
        root.putInt("route_mode", route_mode.getSelectedIndex());
        root.putBoolean("hide_index", hide_index.isSelected());
        root.put("hide_suffix", url_suffix.getText());
        root.putBoolean("web_log_on", web_log.isSelected());
        root.put("web_log_size", log_file_size.getText());
        root.putBoolean("debug_mode", debug_mode.isSelected());
        root.putBoolean("limit_refresh_on", limit_refresh_on.isSelected());
        root.put("limit_refresh_time", limit_refresh_time.getText());
        root.put("default_module", default_module.getText());
        root.put("default_action", default_action.getText());

        
        //设置调试模式
        /*String str_debug_mode = debug_mode.isSelected()?"true":"false";
        d.putProperty("debug_mode", str_debug_mode);
        //设置路由模式
        d.putProperty("route_mode",route_mode.getSelectedIndex());
        //设置记录日志
        if(web_log.isSelected())
        {
            d.putProperty("web_log","true");
            d.putProperty("log_file_size",log_file_size.getText());
        }
        //防刷新
        if(limit_refresh_on.isSelected())
        {
            d.putProperty("limit_refresh_on","true");
            d.putProperty("limit_refresh_time",limit_refresh_time.getText());
        }
        d.putProperty("default_module", default_module.getText());
        d.putProperty("default_action", default_action.getText());

        //隐藏入口
        String str_hide_index = hide_index.isSelected()?"true":"false";
        d.putProperty("hide_index", str_hide_index);

        //伪静态
        d.putProperty("url_suffix", url_suffix.getText());*/
    }

    boolean valid(WizardDescriptor wizardDescriptor) {
        //选中了 记录日志 选项
        if(web_log.isSelected())
        {
            if(log_file_size.getText().length()==0)
            {
                log_file_size.setFocusable(true);
                wizardDescriptor.putProperty("WizardPanel_errorMessage",
                    "刷新时间不能为空！");
                return false;
            }
        }
        
        //选中了 防刷新 选项
        if(limit_refresh_on.isSelected())
        {
            if(limit_refresh_time.getText().length()==0)
            {
                limit_refresh_time.setFocusable(true);
                wizardDescriptor.putProperty("WizardPanel_errorMessage",
                    "刷新时间不能为空！");
                return false; 
            }
        }

        //默认模块和默认操作不能为空
        if(default_module.getText().length()==0)
        {
            default_module.setFocusable(true);
            wizardDescriptor.putProperty("WizardPanel_errorMessage",
                "默认模块不能为空！");
            return false;
        }

        if(default_action.getText().length()==0)
        {
            default_action.setFocusable(true);
            wizardDescriptor.putProperty("WizardPanel_errorMessage",
                "默认操作不能为空！");
            return false;
        }
        wizardDescriptor.putProperty("WizardPanel_errorMessage",
                "");
        return true;
    }

    void validate(WizardDescriptor wizardDescriptor) {
        //throw new UnsupportedOperationException("Not yet implemented");
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        route_mode_label = new javax.swing.JLabel();
        debug_mode = new javax.swing.JCheckBox();
        route_mode = new javax.swing.JComboBox();
        web_log_label = new javax.swing.JLabel();
        web_log = new javax.swing.JCheckBox();
        web_log_total_label = new javax.swing.JLabel();
        log_file_size = new javax.swing.JTextField();
        limit_refresh_on_label = new javax.swing.JLabel();
        limit_refresh_on = new javax.swing.JCheckBox();
        limit_refresh_time_label = new javax.swing.JLabel();
        limit_refresh_time = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        default_module_label = new javax.swing.JLabel();
        default_module = new javax.swing.JTextField();
        default_action_label = new javax.swing.JLabel();
        default_action = new javax.swing.JTextField();
        hide_enter_label = new javax.swing.JLabel();
        hide_index = new javax.swing.JCheckBox();
        url_suffix_label = new javax.swing.JLabel();
        url_suffix = new javax.swing.JTextField();
        debug_mode_label = new javax.swing.JLabel();

        route_mode_label.setText(org.openide.util.NbBundle.getMessage(ThinkProjectSettingPanelVisual.class, "ThinkProjectSettingPanelVisual.route_mode_label.text")); // NOI18N

        debug_mode.setSelected(true);
        debug_mode.setText(org.openide.util.NbBundle.getMessage(ThinkProjectSettingPanelVisual.class, "ThinkProjectSettingPanelVisual.debug_mode.text")); // NOI18N

        route_mode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "普通模式", "PATHINFO模式", "REWRITE模式", "兼容模式" }));
        route_mode.setSelectedIndex(1);

        web_log_label.setText(org.openide.util.NbBundle.getMessage(ThinkProjectSettingPanelVisual.class, "ThinkProjectSettingPanelVisual.web_log_label.text")); // NOI18N

        web_log.setText(org.openide.util.NbBundle.getMessage(ThinkProjectSettingPanelVisual.class, "ThinkProjectSettingPanelVisual.web_log.text")); // NOI18N

        web_log_total_label.setText(org.openide.util.NbBundle.getMessage(ThinkProjectSettingPanelVisual.class, "ThinkProjectSettingPanelVisual.web_log_total_label.text")); // NOI18N

        log_file_size.setText(org.openide.util.NbBundle.getMessage(ThinkProjectSettingPanelVisual.class, "ThinkProjectSettingPanelVisual.log_file_size.text")); // NOI18N

        limit_refresh_on_label.setText(org.openide.util.NbBundle.getMessage(ThinkProjectSettingPanelVisual.class, "ThinkProjectSettingPanelVisual.limit_refresh_on_label.text")); // NOI18N

        limit_refresh_on.setSelected(true);
        limit_refresh_on.setText(org.openide.util.NbBundle.getMessage(ThinkProjectSettingPanelVisual.class, "ThinkProjectSettingPanelVisual.limit_refresh_on.text")); // NOI18N

        limit_refresh_time_label.setText(org.openide.util.NbBundle.getMessage(ThinkProjectSettingPanelVisual.class, "ThinkProjectSettingPanelVisual.limit_refresh_time_label.text")); // NOI18N

        limit_refresh_time.setText(org.openide.util.NbBundle.getMessage(ThinkProjectSettingPanelVisual.class, "ThinkProjectSettingPanelVisual.limit_refresh_time.text")); // NOI18N

        jLabel1.setText(org.openide.util.NbBundle.getMessage(ThinkProjectSettingPanelVisual.class, "ThinkProjectSettingPanelVisual.jLabel1.text")); // NOI18N

        default_module_label.setText(org.openide.util.NbBundle.getMessage(ThinkProjectSettingPanelVisual.class, "ThinkProjectSettingPanelVisual.default_module_label.text")); // NOI18N

        default_module.setText(org.openide.util.NbBundle.getMessage(ThinkProjectSettingPanelVisual.class, "ThinkProjectSettingPanelVisual.default_module.text")); // NOI18N

        default_action_label.setText(org.openide.util.NbBundle.getMessage(ThinkProjectSettingPanelVisual.class, "ThinkProjectSettingPanelVisual.default_action_label.text")); // NOI18N

        default_action.setText(org.openide.util.NbBundle.getMessage(ThinkProjectSettingPanelVisual.class, "ThinkProjectSettingPanelVisual.default_action.text")); // NOI18N

        hide_enter_label.setText(org.openide.util.NbBundle.getMessage(ThinkProjectSettingPanelVisual.class, "ThinkProjectSettingPanelVisual.hide_enter_label.text")); // NOI18N

        hide_index.setText(org.openide.util.NbBundle.getMessage(ThinkProjectSettingPanelVisual.class, "ThinkProjectSettingPanelVisual.hide_index.text")); // NOI18N

        url_suffix_label.setText(org.openide.util.NbBundle.getMessage(ThinkProjectSettingPanelVisual.class, "ThinkProjectSettingPanelVisual.url_suffix_label.text")); // NOI18N

        url_suffix.setText(org.openide.util.NbBundle.getMessage(ThinkProjectSettingPanelVisual.class, "ThinkProjectSettingPanelVisual.url_suffix.text")); // NOI18N

        debug_mode_label.setText(org.openide.util.NbBundle.getMessage(ThinkProjectSettingPanelVisual.class, "ThinkProjectSettingPanelVisual.debug_mode_label.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(default_module_label)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(default_module))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(debug_mode_label)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(debug_mode))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(web_log_label)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(web_log))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(limit_refresh_on_label)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(limit_refresh_on)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(hide_enter_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hide_index)))
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(url_suffix_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(url_suffix))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(default_action_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(default_action))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(route_mode_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(route_mode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(web_log_total_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(log_file_size))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(limit_refresh_time_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(limit_refresh_time)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(route_mode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(route_mode_label)
                    .addComponent(debug_mode_label)
                    .addComponent(debug_mode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(web_log_label)
                    .addComponent(web_log)
                    .addComponent(web_log_total_label)
                    .addComponent(log_file_size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(limit_refresh_on_label)
                    .addComponent(limit_refresh_on)
                    .addComponent(limit_refresh_time_label)
                    .addComponent(limit_refresh_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(default_module_label)
                    .addComponent(default_module, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(default_action_label)
                    .addComponent(default_action, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hide_enter_label)
                    .addComponent(hide_index)
                    .addComponent(url_suffix_label)
                    .addComponent(url_suffix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

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
        panel.fireChangeEvent(); // Notify that the panel changed
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox debug_mode;
    private javax.swing.JLabel debug_mode_label;
    private javax.swing.JTextField default_action;
    private javax.swing.JLabel default_action_label;
    private javax.swing.JTextField default_module;
    private javax.swing.JLabel default_module_label;
    private javax.swing.JLabel hide_enter_label;
    private javax.swing.JCheckBox hide_index;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JCheckBox limit_refresh_on;
    private javax.swing.JLabel limit_refresh_on_label;
    private javax.swing.JTextField limit_refresh_time;
    private javax.swing.JLabel limit_refresh_time_label;
    private javax.swing.JTextField log_file_size;
    private javax.swing.JComboBox route_mode;
    private javax.swing.JLabel route_mode_label;
    private javax.swing.JTextField url_suffix;
    private javax.swing.JLabel url_suffix_label;
    private javax.swing.JCheckBox web_log;
    private javax.swing.JLabel web_log_label;
    private javax.swing.JLabel web_log_total_label;
    // End of variables declaration//GEN-END:variables

}
