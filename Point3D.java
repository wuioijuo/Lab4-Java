/*№2.3
Начало	отсчета.
Создайте	 метод,	 принимающий	 Коробку	 из	 задачи	 3.1.1,	 и	 кладет	 в	 неё	 трехмерную	 точку	
координат	 (из	задачи	2.1.5)	с	произвольными	значениями.	Метод	должен	позволять	передавать	
Коробку	с	более	чем	одним	видом	параметризации. */


public class Point3D extends Point {
    private int z;

    public Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "{" + getX() + ";" + getY() + ";" + z + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Point3D other = (Point3D) obj;
        return getX() == other.getX()
                && getY() == other.getY()
                && z == other.z;
    }

    @Override
    public Point3D clone() {
        return new Point3D(getX(), getY(), z);
    }
}