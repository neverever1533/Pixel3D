package cn.imaginary.toolkit;

import javaev.io.FileTool;
import javaxev.vecmath.Point3DPlus;

import java.awt.image.BufferedImage;
import java.io.File;

public class Pixel3D {
    private String name = "Pixel3D";
    private String version = "v0.6.6";
    private String tag_sharp = "#";
    private String tag_space = " ";

    private ImageTool imageTool = new ImageTool();

    private ModelTool modelTool = new ModelTool();

    public void read(BufferedImage imageXZ, BufferedImage imageYZ, BufferedImage imageXY) {
        modelTool.setInfo(tag_sharp + tag_space + name + tag_space + version);
        modelTool.read(imageXZ, imageYZ, imageXY);
    }

    public void read(File imageFileXZ, File imageFileYZ, File imageFileXY) {
        read(imageTool.read(imageFileXZ), imageTool.read(imageFileYZ), imageTool.read(imageFileXY));
    }

    public Point3DPlus[] getPointArray() {
        return modelTool.getPointArray();
    }

    public void setPointArray(Point3DPlus[] array) {
        modelTool.setPointArray(array);
    }

    public void setRightMode(boolean isRightMode) {
        modelTool.setRightMode(isRightMode);
    }

    public void toObject(File file) {
        modelTool.toObject(file);
    }

    public void toFullObject(File file) {
        modelTool.toFullObject(file);
    }

    public void toVoxel(File file) {
        modelTool.toVoxel(file);
    }
}
