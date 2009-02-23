/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.google.code.thinkphpplugin4netbeans.rorview.nodes;

import com.google.code.thinkphpplugin4netbeans.rorview.Core.ThinkphpNode;
import org.netbeans.api.project.Project;
import org.openide.loaders.DataObjectNotFoundException;

/**
 *
 * @author Administrator
 */
public class CoreNode extends ThinkphpNode{
    String foldername;
    public CoreNode(Project proj,String foldername) throws DataObjectNotFoundException {
        super(proj,foldername);
        this.foldername=foldername;
    }

    @Override
    public String getDisplayName() {
        return "核心库";
    }
}
