/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.google.code.thinkphpplugin4netbeans.rorview.nodes;

import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ui.support.NodeFactory;
import org.netbeans.spi.project.ui.support.NodeFactorySupport;
import org.netbeans.spi.project.ui.support.NodeList;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Exceptions;

/**
 *
 * @author Administrator
 */
public class CoreNodeFactory implements NodeFactory{

    Project proj;
    
    public NodeList<?> createNodes(Project pro) {
        this.proj=pro;
        if(this.proj.getProjectDirectory().getFileObject("ThinkPHP")==null)
        {
            System.out.println("无法找到ThinkPHP目录");
            return NodeFactorySupport.fixedNodeList();
        }
        try {
            CoreNode nd = new CoreNode(proj,"ThinkPHP");
            return NodeFactorySupport.fixedNodeList(nd);
        } catch (DataObjectNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
