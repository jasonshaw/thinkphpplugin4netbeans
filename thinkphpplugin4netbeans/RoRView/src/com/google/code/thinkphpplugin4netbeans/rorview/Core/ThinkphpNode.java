/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.google.code.thinkphpplugin4netbeans.rorview.Core;

import java.awt.Image;
import org.netbeans.api.project.Project;
import org.openide.filesystems.Repository;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.FilterNode;
import org.openide.util.ImageUtilities;

/**
 * node的核心类，其他node都必须继承这个类
 * @author Administrator
 */
public class ThinkphpNode extends FilterNode{
    private static Image smallImage =
          ImageUtilities.loadImage("/com/google/code/thinkphpplugin4netbeans/rorview/images/logo.jpg"); // NOI18

    public ThinkphpNode(Project proj,String foldername) throws DataObjectNotFoundException {
        super(DataObject.find(proj.getProjectDirectory().getFileObject(foldername)).getNodeDelegate());
    }

    

    //Next, we add icons, for the default state, which is
    //closed, and the opened state; we will make them the same.
    //Icons in project logical views are
    //based on combinations--you must combine the node's own icon
    //with a distinguishing badge that is merged with it. Here we
    //first obtain the icon from a data folder, then we add our
    //badge to it by merging it via a NetBeans API utility method:

    public Image getIcon(int type) {
        DataFolder root = DataFolder.findFolder(Repository.getDefault().getDefaultFileSystem().getRoot());
        Image original = root.getNodeDelegate().getIcon(type);
        return ImageUtilities.mergeImages(original, smallImage, 7, 7);
    }

    public Image getOpenedIcon(int type) {
        DataFolder root = DataFolder.findFolder(Repository.getDefault().getDefaultFileSystem().getRoot());
        Image original = root.getNodeDelegate().getIcon(type);
        return ImageUtilities.mergeImages(original, smallImage, 7, 7);
    }
}
