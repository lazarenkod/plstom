package com.pl.plugins.core.menu;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 29.09.2008
 * Time: 17:49:04
 */
public class Layer {
    private ArrayList<FolderNode> folders;

    public ArrayList<FolderNode> getFolders() {
        return folders;
    }

    public void setFolders(ArrayList<FolderNode> folders) {
        this.folders = folders;
    }

    public Layer() {
        folders=new ArrayList<FolderNode>();
    }
    public void addFolder(FolderNode folder)
    {
        folders.add(folder);
    }

    public String toString() {
        return "Layer{" +
                "folders=" + folders +
                '}';
    }
}
