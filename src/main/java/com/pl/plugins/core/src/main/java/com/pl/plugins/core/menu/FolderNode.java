package com.pl.plugins.core.menu;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Lazarenko.Dmitry
 * Date: 29.09.2008
 * Time: 17:31:53
 */
public class FolderNode
{
    private String name;
    private String displayName;
    private ArrayList<AtrNode> atrs;
    private ArrayList<FolderNode> childFolders;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public ArrayList<AtrNode> getAtrs() {
        return atrs;
    }

    public void setAtrs(ArrayList<AtrNode> atrs) {
        this.atrs = atrs;
    }

    public ArrayList<FolderNode> getChildFolders() {
        return childFolders;
    }

    public void setChildFolders(ArrayList<FolderNode> childFolders) {
        this.childFolders = childFolders;
    }

    public void addAtr(AtrNode atr)
    {
        atrs.add(atr);
    }
    public void addFolder(FolderNode folder)
    {
        childFolders.add(folder);
    }

    public String toString() {
        return "FolderNode{" +
                "name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", atrs=" + atrs +
                ", childFolders=" + childFolders +
                '}'+"\n";
    }

    public FolderNode() {
         atrs=new ArrayList<AtrNode>();
         childFolders=new ArrayList<FolderNode>();
    }
}

