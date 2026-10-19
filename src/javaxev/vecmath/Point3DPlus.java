package javaxev.vecmath;

public class Point3DPlus {
    public int x;
    public int y;
    public int z;

    private double xd;
    private double yd;
    private double zd;

    public Point3DPlus() {
    }

    public Point3DPlus(double x, double y, double z) {
        setLocation(x, y, z);
    }

    public double getX() {
        return xd;
    }

    public double getY() {
        return yd;
    }

    public double getZ() {
        return zd;
    }

    private void setLocation(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setLocation(double x, double y, double z) {
        xd = x;
        yd = y;
        zd = z;
        setLocation((int) x, (int) y, (int) z);
    }

    public void move(double x, double y, double z) {
        xd += x;
        yd += y;
        zd += z;
        setLocation((int) xd, (int) yd, (int) zd);
    }

    public String toString() {
        return getClass().getName() + "[" + getX() + ", " + getY() + ", " + getZ() + "]";
    }
}
