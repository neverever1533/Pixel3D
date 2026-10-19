package cn.imaginary.toolkit;

import cn.imaginary.toolkit.model.Model3D;
import cn.imaginary.toolkit.model.wavefront.Object3D;
import cn.imaginary.toolkit.model.voxel.Voxel3D;

import javaev.io.FileTool;
import javaxev.vecmath.Point3DPlus;

import java.awt.image.BufferedImage;
import java.io.File;

public class ModelTool {
    private ImageTool imageTool = new ImageTool();

    private Model3D model3D = new Model3D();

    private Object3D object3D = new Object3D();

    private Voxel3D voxel3D = new Voxel3D();

    private FileTool fileTool = new FileTool();

    private Point3DPlus[] array;

    private int[][] getArrays(BufferedImage image) {
        return imageTool.getArrays(image);
    }

    private BufferedImage read(File imageFile) {
        return imageTool.read(imageFile);
    }

    public void read(BufferedImage imageXZ, BufferedImage imageYZ, BufferedImage imageXY) {
        read(getArrays((imageXZ)), getArrays(imageYZ), getArrays(imageXY));
    }

    public void read(File imageFileXZ, File imageFileYZ, File imageFileXY) {
        read(read(imageFileXZ), read(imageFileYZ), read(imageFileXY));
    }

    public void read(int[][] arrayXZ, int[][] arrayYZ, int[][] arrayXY) {
        setPointArray(model3D.read(arrayXZ, arrayYZ, arrayXY));
    }

    public Point3DPlus[] getPointArray() {
        return array;
    }

    public void setPointArray(Point3DPlus[] array) {
        this.array = array;
    }

    public void toObject(File file) {
        Point3DPlus[] array = getPointArray();
        object3D.setPointArray(array);
        fileTool.writeString(file, object3D.toString());
//        System.out.println("mt58:" + array.length);
    }

    public void toVoxel(File file) {
    }
}
