package cn.imaginary.toolkit.model.wavefront;

import javaev.util.ListTool;
import javaxev.vecmath.Point3DPlus;

import java.util.ArrayList;

public class Object3D {
    private String tag_cube = "cube_";
    private String tag_line_separator = System.lineSeparator();
    private String tag_space = " ";
    private String tag_null = "null";
    private String tag_none = "";

    private String tag_vertices = "v";
    private String tag_vertex_texture = "vt";
    private String tag_vertex_normals = "vn";
    private String tag_vertices_parameter_space = "vp";

    private String tag_degree = "deg";
    private String tag_basis_matrix = "bmat";
    private String tag_step_size = "step";
    private String tag_curve_or_surface_type = "cstype";

    private String tag_point = "p";
    private String tag_line = "l";
    private String tag_face = "f";
    private String tag_curve = "curv";
    private String tag_curve_2d = "curv2";
    private String tag_surface = "surf";

    private String tag_parameter_values = "parm";
    private String tag_outter_trimming_loop = "trim";
    private String tag_inner_trimming_loop = "hole";
    private String tag_special_curve = "scrv";
    private String tag_special_point = "sp";
    private String tag_end_statement = "end";

    private String tag_connect = "con";

    private String tag_group_name = "g";
    private String tag_group_smoothing = "s";
    private String tag_group_merging = "mg";
    private String tag_object_name = "o";

    private String tag_bevel_interpolation = "bevel";
    private String tag_color_interpolation = "c_interp";
    private String tag_dissolve_interpolation = "d_interp";
    private String tag_level_of_detail = "lod";
    private String tag_material_name = "usemtl";
    private String tag_material_library = "mtllib";
    private String tag_shadow_casting = "shadow_obj";
    private String tag_ray_tracing = "trace_obj";
    private String tag_curve_approximation_technique = "ctech";
    private String tag_surface_approximation_technique = "stech";

    private Point3DPlus[] array;

    public Point3DPlus[] getPointArray() {
        return array;
    }

    public void setPointArray(Point3DPlus[] array) {
        this.array = array;
    }

    private void addVertices(ArrayList<Point3DPlus> arrayList, StringBuilder stringBuilder) {
        if (null != arrayList) {
            for (int i = 0; i < arrayList.size(); i++) {
                addVertices(arrayList.get(i), stringBuilder);
            }
        }
    }

    private void addVertices(double x, double y, double z, StringBuilder stringBuilder) {
        if (null != stringBuilder) {
            addString(stringBuilder, tag_vertices, x, y, z, null);
        }
    }

    private void addVertices(Point3DPlus point3D, StringBuilder stringBuilder) {
        if (null != point3D) {
            addVertices(point3D.getX(), point3D.getY(), point3D.getZ(), stringBuilder);
        }
    }

    private void addFace(ArrayList<Point3DPlus> arrayList, StringBuilder stringBuilder) {
        if (null != arrayList) {
            for (int i = 0; i < arrayList.size(); i++) {
                addFace(arrayList, arrayList.get(i), stringBuilder);
            }
        }
    }

    private void addFace(ArrayList<Point3DPlus> arrayList, Point3DPlus point3D, StringBuilder stringBuilder) {
        if (null != point3D) {
            addFace(arrayList, point3D.getX(), point3D.getY(), point3D.getZ(), stringBuilder);
        }
    }

    private void addFace(ArrayList<Point3DPlus> arrayList, double x, double y, double z, StringBuilder stringBuilder) {
        if (null != stringBuilder) {
            Point3DPlus p_1 = new Point3DPlus(x, y, z);
            int index_1 = ListTool.indexOf(arrayList, p_1);
            Point3DPlus p_2 = new Point3DPlus(x + 1, y, z);
            int index_2 = ListTool.indexOf(arrayList, p_2);
            Point3DPlus p_3 = new Point3DPlus(x + 1, y - 1, z);
            int index_3 = ListTool.indexOf(arrayList, p_3);
            Point3DPlus p_4 = new Point3DPlus(x, y - 1, z);
            int index_4 = ListTool.indexOf(arrayList, p_4);

            Point3DPlus p_5 = new Point3DPlus(x, y, z - 1);
            int index_5 = ListTool.indexOf(arrayList, p_5);
            Point3DPlus p_6 = new Point3DPlus(x + 1, y, z - 1);
            int index_6 = ListTool.indexOf(arrayList, p_6);
            Point3DPlus p_7 = new Point3DPlus(x + 1, y - 1, z - 1);
            int index_7 = ListTool.indexOf(arrayList, p_7);
            Point3DPlus p_8 = new Point3DPlus(x, y - 1, z - 1);
            int index_8 = ListTool.indexOf(arrayList, p_8);
            if (index_1 == -1 || index_2 == -1 || index_3 == -1 || index_4 == -1 || index_5 == -1 || index_6 == -1 || index_7 == -1 || index_8 == -1) {
                return;
            } else {
                index_1++;
                index_2++;
                index_3++;
                index_4++;
                index_5++;
                index_6++;
                index_7++;
                index_8++;
            }
//            addString(stringBuilder, tag_group_name, tag_cube + index_1, null, null, null);
            addString(stringBuilder, tag_face, index_1, index_2, index_3, index_4);
            addString(stringBuilder, tag_face, index_5, index_6, index_7, index_8);
            addString(stringBuilder, tag_face, index_1, index_2, index_6, index_5);
            addString(stringBuilder, tag_face, index_4, index_3, index_7, index_8);
            addString(stringBuilder, tag_face, index_1, index_5, index_8, index_4);
            addString(stringBuilder, tag_face, index_2, index_3, index_7, index_6);
            stringBuilder.append(tag_line_separator);
        }
    }

    private void addString(StringBuilder stringBuilder, String prefix, Object item_1, Object item_2, Object item_3) {
        addString(stringBuilder, prefix, item_1, item_2, item_3, null);
    }

    private void addString(StringBuilder stringBuilder, String prefix, Object item_1, Object item_2, Object item_3, Object item_4) {
        if (null != stringBuilder && null != prefix) {
            stringBuilder.append(prefix);
            stringBuilder.append(tag_space);
            if (null != item_1) {
                stringBuilder.append(item_1);
                stringBuilder.append(tag_space);
            }
            if (null != item_2) {
                stringBuilder.append(item_2);
                stringBuilder.append(tag_space);
            }
            if (null != item_3) {
                stringBuilder.append(item_3);
                stringBuilder.append(tag_space);
            }
            if (null != item_4) {
                stringBuilder.append(item_4);
                stringBuilder.append(tag_space);
            }
            stringBuilder.append(tag_line_separator);
        }
    }

    public String toString() {
        ArrayList<Point3DPlus> arrayList = ListTool.toList(getPointArray());
        StringBuilder stringBuilder = new StringBuilder();
        addVertices(arrayList, stringBuilder);
        stringBuilder.append(tag_line_separator);
//        System.out.println("o3d232:" + arrayList.size() + "/" + arrayList.get(0) + "/" + arrayList.get(1));
        addFace(arrayList, stringBuilder);
        return stringBuilder.toString();
    }
}
