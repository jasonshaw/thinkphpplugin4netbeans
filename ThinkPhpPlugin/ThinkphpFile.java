/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ThinkPhpPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import org.openide.cookies.OpenCookie;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
/**
 *插件中的文件操作
 * @author Administrator
 */
public class ThinkphpFile {
    
    //写入文件，并打开
    public static void WriteFile(String txt,String path)
    {
        try
        {
            OutputStreamWriter out=new OutputStreamWriter(new FileOutputStream(path),"UTF-8");
            out.write(txt);
            out.flush();
            out.close();
        }
        catch (Exception e)
        {
            //Exceptions.printStackTrace(e);
            return;
        }

        //打开创建好的文档，显示在TopComponent上面
        ThinkphpCreateProject pro=new ThinkphpCreateProject();
        File file=new File(path);
        FileObject fob = FileUtil.toFileObject(FileUtil.normalizeFile(file));
        if (fob != null) {
            try {
                DataObject dob = DataObject.find(fob);
                OpenCookie oc = (OpenCookie) dob.getCookie (OpenCookie.class);
                if (oc != null) {
                    oc.open();
                }
            } catch (Exception ex) {
               // Exceptions.printStackTrace(ex);
                return;
            }
        }
    }

    public static boolean ExistFile(String path)
    {
        File file=new File(path);
        return file.exists();
    }
}
