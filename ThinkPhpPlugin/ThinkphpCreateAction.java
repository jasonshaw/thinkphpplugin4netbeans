/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ThinkPhpPlugin;

import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class ThinkphpCreateAction {
    //创建Action
    public void CreateAction(String action_name,String file_name,HashMap config)
    {
        if(!ThinkphpFile.ExistFile(file_name))
        {
            String create_model=config.get("create_model").toString();
            String create_display=config.get("create_display").toString();
            String create_function=config.get("create_function").toString();
            String entrance_fold=config.get("entrance_fold").toString();
            String model_name=config.get("model_name").toString();
            //开始
            String action_file="<?php\n" +
                    "class " +action_name+" extends Action{\r";
            //添加操作
            String functions[]=create_function.split("\n");
            for(int i=0;i<functions.length;i++)
            {
                if(functions[i].trim().length()!=0)
                {
                    action_file=action_file+"  function "+functions[i]+"(){\r";
                    if(create_display.equals("1"))
                    {
                        action_file=action_file+"    $this->display();\r";
                    }
                    action_file=action_file+"  }\r";
                }
            }
            //结束
            action_file=action_file+"}\r" +
                    "?>";
            try {
                ThinkphpFile.WriteFile(action_file, file_name);
            } catch (Exception ex) {
                //Exceptions.printStackTrace(ex);
            }

            if(create_model.equals("1"))
            {
                ThinkphpCreateModel createmodel=new ThinkphpCreateModel();
                createmodel.CreateModel(entrance_fold,model_name);
            }
        }
    }
}
