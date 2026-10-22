package cn.imaginary.toolkit;

import cn.imaginary.toolkit.model.Model3D;
import cn.imaginary.toolkit.model.wavefront.Object3D;

import javaev.io.FileTool;
import javaxev.vecmath.Point3DPlus;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Pixel3D {
    private String name = "Pixel3D";
    private String version = "v0.8.1";
    private String tag_sharp = "#";
    private String tag_space = " ";

    private FileTool fileTool = new FileTool();

    private Object3D object3D = new Object3D();

    private Model3D model3D = new Model3D();

    public void read(BufferedImage imageXZ, BufferedImage imageYZ, BufferedImage imageXY) {
        model3D.read(imageXZ, imageYZ, imageXY);
    }

    public void read(File imageFileXZ, File imageFileYZ, File imageFileXY) {
        model3D.read(imageFileXZ, imageFileYZ, imageFileXY);
    }

    public void setRightMode(boolean isRightMode) {
        model3D.setRightMode(isRightMode);
    }

    public void toObject(File file) {
        toObject(file, false);
    }

    public void toFullObject(File file) {
        toObject(file, true);
    }

    private void toObject(File file, boolean isVoxelMode) {
        ArrayList<Point3DPlus> arrayList = model3D.getPointList();
        object3D.setInfo(tag_sharp + tag_space + name + tag_space + version);
        int size = arrayList.size();
        arrayList = model3D.update(arrayList, isVoxelMode);
        System.out.println("p3d50 toString:is voxel mode:" + isVoxelMode + "/size:" + arrayList.size() + "/" + size);
        object3D.setPointList(arrayList);
        object3D.setRightMode(model3D.isRightMode());
        object3D.setVoxelMode(isVoxelMode);
        object3D.setName(fileTool.getPrefix(file.getName()));
        fileTool.writeString(file, object3D.toString());
//        System.out.println("p3d53:" + array.length);
    }

    public void toVoxel(File file) {
    }
}
