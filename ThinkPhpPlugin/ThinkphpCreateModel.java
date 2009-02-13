/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ThinkPhpPlugin;

/**
 *
 * @author Administrator
 */
public class ThinkphpCreateModel {

    //目前只实现最简单的功能，将来还要添加数据库的自动获取功能
    public void CreateModel(String entrance_fold, String model_name) {
        ThinkphpCreateProject project=new ThinkphpCreateProject();
        String path=project.GetProjectDir()+"/"+entrance_fold+"/Lib/Model/"+model_name+"Model.class.php";
        String model_source="<?php\r" +
                "class "+model_name+" extends Model{\r" +
                "}\r" +
                "?>";
        ThinkphpFile.WriteFile(model_source, path);
    }
}
