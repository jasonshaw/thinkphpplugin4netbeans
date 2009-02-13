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

/**
 *
 * @author Administrator
 */
public class Entrance {

    public ArrayList<String> GetEntrance()
    {
        ArrayList<String> list=new ArrayList<String>();
        ThinkphpCreateProject project=new ThinkphpCreateProject();
        String projectDir=project.GetProjectDir();
        File file=new File(projectDir);
        File subFile[]=file.listFiles();
        for(int i=0;i<subFile.length;i++)
        {
            //如果是.或者..，或者文件的话，跳过
            if(subFile[i].getName().equals(".") || subFile[i].getName().equals("..") || subFile[i].isFile() || 
                    subFile[i].getName().equals("ThinkPHP") || subFile[i].getName().equals("nbproject") || subFile[i].getName().equals("Public"))
                continue;
            String libPath=subFile[i].getPath()+"/Lib";
            String tplPath=subFile[i].getPath()+"/Tpl";
            File libFile=new File(libPath);
            File tplFile=new File(tplPath);
            if(libFile.exists() && tplFile.exists())
            {
                list.add(subFile[i].getName());
            }
        }
        return list;
    }

    //整个插件时间复杂度最高的一个函数，好在入口在thinkphp中并不多，否则真的是一个噩梦
    //这里采用即时计算的方法，其实也可以把结果缓存起来，这样效率应该会好点
    public HashMap GetEntranceFile() throws FileNotFoundException, IOException
    {
        BufferedReader read = null;
        //先获得入口目录
        ArrayList<String> dirlist=GetEntrance();
        //再获取文件，并分析是否是入口目录对应的入口文件，如果是，保存
        HashMap map=new HashMap();
        //ArrayList<String> list=new ArrayList<String>();
        ThinkphpCreateProject project=new ThinkphpCreateProject();
        String projectDir=project.GetProjectDir();
        File file=new File(projectDir);
        File subFile[]=file.listFiles();
        boolean found=false;
        for(int i=0;i<subFile.length;i++)
        {
            //如果是.或者..，或者文件的话，跳过
            if(subFile[i].getName().equals(".") || subFile[i].getName().equals("..") || subFile[i].isDirectory())
                continue;
            if(!subFile[i].getAbsolutePath().endsWith(".php")){
                continue;
            }
            read = new BufferedReader(new FileReader(subFile[i]));
            String line=read.readLine();
            while(line!=null && line.length()!=0)
            {
                //对该行进行匹配
                for(int j=0;j<dirlist.size();j++){
                    String [] tmp=line.split(dirlist.get(j).toString());
                    //找到，确认是入口文件
                    if(tmp.length>=2)
                    {
                        found=true;
                        //list.add(subFile[i].getName());
                        //使用hash表来存储结果, key是入口文件名，value是目录名
                        map.put(dirlist.get(j).toString(),subFile[i].getName());
                        break;
                    }
                }
                if(found)
                {
                    read.close();
                    break;
                }
                line=read.readLine();
            }
            found=false;
        }
        return map;
    }

    public boolean IsExist(String entrance_name)
    {
        ArrayList<String> list=this.GetEntrance();
        for(int i=0;i<list.size();i++)
        {
            //找到
            if(list.get(i).equals(entrance_name))
                return true;
        }
        return false;
    }

}
