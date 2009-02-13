/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ThinkPhpPlugin.Action;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.windows.WindowManager;

public final class AboutAction implements ActionListener {
    
    public void actionPerformed(ActionEvent e) {
        //关于菜单
        AboutDialog about=new AboutDialog(WindowManager.getDefault().getMainWindow(),true);
        Rectangle rt=WindowManager.getDefault().getMainWindow().getBounds();
        about.setTitle("关于");
        about.setLocation((int)(rt.getWidth()-rt.getX())/2, (int)(rt.getHeight()-rt.getY())/2);
        about.show(true);

        /*ThinkphpCreateProject pro=new ThinkphpCreateProject();
        File file=new File(pro.GetProjectDir()+"/index.php");
        FileObject fob = FileUtil.toFileObject(FileUtil.normalizeFile(file));
        if (fob != null) {
            try {
                DataObject dob = DataObject.find(fob);
                OpenCookie oc = (OpenCookie) dob.getCookie (OpenCookie.class);
                if (oc != null) {
                    oc.open();
                }
            } catch (DataObjectNotFoundException ex) {
                Exceptions.printStackTrace(ex);
            }
        }*/
    }
}
