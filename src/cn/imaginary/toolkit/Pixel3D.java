package cn.imaginary.toolkit;

import javaxev.vecmath.Point3DPlus;

import java.awt.image.BufferedImage;
import java.io.File;

public class Pixel3D {
    private ModelTool modelTool = new ModelTool();

    public void read(BufferedImage imageXZ, BufferedImage imageYZ, BufferedImage imageXY) {
        modelTool.read(imageXZ, imageYZ, imageXY);
    }

    public void read(File imageFileXZ, File imageFileYZ, File imageFileXY) {
        modelTool.read(imageFileXZ, imageFileYZ, imageFileXY);
    }

    public Point3DPlus[] getPointArray() {
        return modelTool.getPointArray();
    }

    public void setPointArray(Point3DPlus[] array) {
        modelTool.setPointArray(array);
    }

    public void toObject(File file) {
        modelTool.toObject(file);
    }

    public void toVoxel(File file) {
        modelTool.toVoxel(file);
    }
}
