package cn.imaginary.toolkit.model;

import javaev.util.ListTool;
import javaxev.vecmath.Point3DPlus;

import java.util.ArrayList;

public class Model3D {
    public Point3DPlus[] read(int[][] arrayXZ, int[][] arrayYZ, int[][] arrayXY) {
        if (!(null == arrayXZ && null == arrayYZ && null == arrayXY)) {
            int length = 0;
            int[][] arrXZ = null;
            if (null != arrayXZ) {
                length = arrayXZ.length;
                arrXZ = new int[length][length];
                for (int x = 0; x < length; x++) {
                    for (int z = 0; z < length; z++) {
                        arrXZ[x][z] = arrayXZ[x][length - 1 - z];
                    }
                }
            }
            int[][] arrYZ = null;
            if (null != arrayYZ) {
                length = arrayYZ.length;
                arrYZ = new int[length][length];
                for (int y = 0; y < length; y++) {
                    for (int z = 0; z < length; z++) {
                        arrYZ[y][z] = arrayYZ[length - 1 - y][length - 1 - z];
                    }
                }
            }
            int[][] arrXY = null;
            if (null != arrayXY) {
                length = arrayXY.length;
                arrXY = new int[length][length];
                for (int x = 0; x < length; x++) {
                    for (int y = 0; y < length; y++) {
                        arrXY[x][y] = arrayXY[x][length - 1 - y];
                    }
                }
            }
            ArrayList<Point3DPlus> arrayList = new ArrayList<>();
            for (int x = 0; x < length; x++) {
                for (int y = 0; y < length; y++) {
                    for (int z = 0; z < length; z++) {
                        Point3DPlus p = new Point3DPlus(x, y, z);
                        arrayList.add(p);
                        if ((null != arrXZ && arrXZ[x][z] == 0) || (null != arrYZ && arrYZ[y][z] == 0) || (null != arrXY && arrXY[x][y] == 0)) {
                            arrayList.remove(p);
                        }
                    }
                }
            }
            return ListTool.toArray(arrayList);
        }
        return null;
    }
}
