package javaev.util;

import javaxev.vecmath.Point3DPlus;

import java.util.ArrayList;
import java.util.Arrays;

public class ListTool {
    private static int type_contains = 0;
    private static int type_index = 1;
    private static int type_index_last = 0;

    public static boolean contains(ArrayList<Point3DPlus> arrayList, Point3DPlus Point3DPlus) {
        return (boolean) check(arrayList, Point3DPlus, type_contains);
    }

    public static int indexOf(ArrayList<Point3DPlus> arrayList, Point3DPlus Point3DPlus) {
        return (int) check(arrayList, Point3DPlus, type_index);
    }

    private static Object check(ArrayList<Point3DPlus> arrayList, Point3DPlus Point3DPlus, int type) {
        if (null != arrayList && null != Point3DPlus) {
            for (int i = 0; i < arrayList.size(); i++) {
                Point3DPlus p = arrayList.get(i);
                if (p.x == Point3DPlus.x && p.y == Point3DPlus.y && p.z == Point3DPlus.z) {
                    if (type == type_contains) {
                        return true;
                    } else if (type == type_index) {
                        return i;
                    }
                }
            }
        }
        if (type == type_contains) {
            return false;
        } else if (type == type_index) {
            return -1;
        }
        return -1;
    }

    public static Point3DPlus[] toArray(ArrayList<Point3DPlus> arrayList) {
        if (null != arrayList) {
            Point3DPlus[] array = new Point3DPlus[arrayList.size()];
            arrayList.toArray(array);
            return array;
        }
        return null;
    }

    public static ArrayList<Point3DPlus> toList(Point3DPlus[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }
}
