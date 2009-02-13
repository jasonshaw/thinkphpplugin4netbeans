/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ThinkPhpPlugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.openide.util.Exceptions;

/**
 *
 * @author Administrator
 */
public class ThinkphpDebug {
    public String GetDebugUrl(String path,String fun)
    {
        if(IsValidate(path))
        {
            String hostUrl="";
            try{
                hostUrl=GetLocalHost();
            }
            catch(Exception ex)
            {
                return "";
            }

            return hostUrl+GetEntranceIndex(path)+"/"+GetAction(path)+"/"+fun;
        }
        return "";
    }
    //获取Action名字
    public String GetAction(String path)
    {
        //仅在windows机器上运行
        File file=new File(path);
        if(!file.exists())
        {
            return "";
        }
        String filename=file.getName();
        String arr[]=filename.split("Action");
        if(arr.length<=1)
            return "";
        
        return arr[0];
    }

    //是否是合法的Action
    private boolean IsValidate(String path)
    {
        if(!path.trim().endsWith("Action.class.php"))
            return false;
        if(path.trim().split("Lib").length<=1)
            return false;
        if(path.trim().split("Action").length<=2)
            return false;
        return true;
    }

    public String GetPath(String path)
    {
        File file=new File(path);
        File parent=file.getParentFile().getParentFile().getParentFile();
        //System.out.println(parent.getName());
        return parent.getName();
        
    }

    private String GetEntranceIndex(String path)
    {
        try {
            String enterFold=GetPath(path);
            Entrance entrance = new Entrance();
            HashMap map = entrance.GetEntranceFile();
            String enterFile=(String) map.get(enterFold);
            if(enterFile==null )
                return "";
            return enterFile;
        } catch (FileNotFoundException ex) {
            //Exceptions.printStackTrace(ex);
            return "index.php";
        } catch (IOException ex) {
            //Exceptions.printStackTrace(ex);
            return "index.php";
        }
    }

    private String GetLocalHost() throws FileNotFoundException, IOException
    {
        BufferedReader read = null;
        ThinkphpCreateProject project = new ThinkphpCreateProject();
        File file = new File(project.GetProjectDir() + "/nbproject/private/private.properties");
        read = new BufferedReader(new FileReader(file));
        String line=read.readLine();
        while(line!=null && line.trim().length()!=0)
        {
            String [] arr=line.split("=");
            if(arr.length>=2)
            {
                if(arr[0].trim().toLowerCase().equals("url"))
                {
                    read.close();
                    return arr[1].trim().toLowerCase();
                }
            }
            line=read.readLine();
        }
        
        return "";
    }
}
