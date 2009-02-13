/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ThinkPhpPlugin;

import ThinkPhpPlugin.Setting.ThinkphpSettingPanel;
import java.io.File;
import java.util.prefs.Preferences;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ui.OpenProjects;
import org.openide.filesystems.FileUtil;

/**
 *
 * @author Administrator
 */
public class ThinkphpCreateProject {

    //写入口文件
    public void CreateEntrance(String entrance_file,String entrance_fold,String ProjectDir) 
    {
        String entrancefilepath=ProjectDir+"/"+entrance_file;
        String entrancedirpath=ProjectDir+"/"+entrance_fold+"/";
        //如果已经存在该入口文件及文件夹，则不创建，直接返回
       if(!ThinkphpFile.ExistFile(entrancefilepath) && !ThinkphpFile.ExistFile(entrancedirpath))
        {
            //建立文件夹
            File entrancefoad=FileUtil.normalizeFile(new File(entrancedirpath));
            entrancefoad.mkdir();
            String str_dir[]={"Cache","Common","Conf","Data","Html","Lang","Lib","Logs","PlugIns","Temp","Tpl",
            "Tpl/default","Tpl/default/Index","Tpl/default/Index/index","Tpl/default/Public","Lib/Action","Lib/Model"};
            for(int i=0;i<str_dir.length;i++){
                File tmpdir = FileUtil.normalizeFile(new File(entrancedirpath+str_dir[i]));
                tmpdir.mkdirs();
            }

            //装载配置文件
            Preferences root=Preferences.userNodeForPackage(ThinkphpSettingPanel.class);
            String db_type=String.valueOf(root.getInt("db_type", 0));
            String db_host=root.get("db_host", "localhost");
            String db_user=root.get("db_user", "root");
            String db_pwd=root.get("db_pwd", "");
            String db_port=root.get("db_port", "3306");
            String db_name=root.get("db_name", "test");
            String db_prefix=root.get("db_prefix", "think_");
            String default_module=root.get("default_module", "Index");
            String default_action=root.get("default_action", "index");
            String debug_mode=root.get("debug_mode", "false");
            String limit_refresh_on=root.get("limit_refresh_on", "false");
            String limit_refresh_time=root.get("limit_refresh_time", "3");
            String log_file_size=root.get("web_log_size", "1000000");
            String web_log=root.get("web_log_on", "true");
            String route_mode=root.get("route_mode", "1");
            String url_suffix=root.get("hide_suffix", ".html");
            boolean hide_index=root.getBoolean("hide_index", false);

            //隐藏index.php文件
            if(hide_index)
            {
                route_mode="2";
                String htaccess_file="<IfModule mod_rewrite.c>\r"+
                                     "RewriteEngine on\r"+
                                     "RewriteCond %{REQUEST_FILENAME} !-d\r"+
                                     "RewriteCond %{REQUEST_FILENAME} !-f\r"+
                                     "RewriteRule ^(.*)$ "+entrance_file+"/$1 [QSA,PT,L]\r"+
                                     "</IfModule>";
                ThinkphpFile.WriteFile(htaccess_file,ProjectDir+"/.htaccess");
            }

            //写默认Action
            String default_action_file="<?php\r" +
                    "class " +default_module+"Action extends Action{\r"+
                    "   function " +default_action+"(){\r"+
                    "   }\r" +
                    "}\r" +
                    "?>";

            ThinkphpFile.WriteFile(default_action_file,entrancedirpath+"Lib/Action/"+default_module+"Action.class.php");

            //写入口文件
            String indexfile="<?php\r" +
                    "define('THINK_PATH','./ThinkPHP');\r" +
                    "define('APP_PATH','./"+entrance_fold+"');\r" +
                    "define('APP_NAME','"+entrance_fold+"');\r" +
                    "require(THINK_PATH.'/ThinkPHP.php');\r" +
                    "$app=new App();\r" +
                    "$app->run();\r" +
                    "?>";
            ThinkphpFile.WriteFile(indexfile,entrancefilepath);

            //写入配置文件
            String configfile="<?php\r" +
                    "return array(\r" +
                    "'DB_TYPE'=>'" +db_type+"',\r"+
                    "'DB_HOST'=>'" +db_host+"',\r"+
                    "'DB_USER'=>'" +db_user+"',\r"+
                    "'DB_PWD'=>'" +db_pwd+"',\r"+
                    "'DB_NAME'=>'" +db_name+"',\r"+
                    "'DB_PORT'=>'" +db_port+"',\r"+
                    "'DB_PREFIX'=>'" +db_prefix+"',\r"+
                    "'DEBUG_MODE'=>" +debug_mode+",\r"+
                    "'URL_MODEL'=>" +route_mode+",\r"+
                    "'HTML_URL_SUFFIX'=>'" +url_suffix+"',\r"+
                    "'WEB_LOG_RECORD'=>" +web_log+",\r"+
                    "'LOG_FILE_SIZE'=>" +log_file_size+",\r"+
                    "'LIMIT_RESFLESH_ON'=>" +limit_refresh_on+",\r"+
                    "'LIMIT_REFLESH_TIMES'=>" +limit_refresh_time+",\r"+
                    "'DEFAULT_MODULE'=>'" +default_module+"',\r"+
                    "'DEFAULT_ACTION'=>'" +default_action+"',\r"+
                    ");\r" +
                    "?>";
            ThinkphpFile.WriteFile(configfile,entrancedirpath+"Conf/config.php");
        }
    }

    public void WriteProjectConfig(String project_path,String index_file,String project_url) 
    {
        String private_path=project_path+"/nbproject/private";
        if(!ThinkphpFile.ExistFile(private_path))
        {
            //建立文件夹
            File entrancefoad=FileUtil.normalizeFile(new File(private_path));
            entrancefoad.mkdir();

            String private_properties_source="copy.src.files=false\r"+
                                             "copy.src.target=\r"+
                                             "index.file="+index_file+"\r"+
                                             "run.as=LOCAL\r"+
                                             "url="+project_url;
            String private_properties=private_path+"/private.properties";
            ThinkphpFile.WriteFile(private_properties_source,private_properties);
        }
    }

    //此函数只能在打开项目之后才能用
    public String GetProjectDir()
    {
        //Project project=OpenProjects.getDefault().getOpenProjects()[0];
        Project project=OpenProjects.getDefault().getMainProject();
        return project.getProjectDirectory().getPath();
    }

    //此函数只能在打开项目之后才能用
    public String GetProjectName()
    {
        //Project project=OpenProjects.getDefault().getOpenProjects()[0];
        Project project=OpenProjects.getDefault().getMainProject();
        return project.getProjectDirectory().getName();
    }
}
