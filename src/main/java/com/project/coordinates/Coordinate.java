package com.project.coordinates;

public class Coordinate implements Comparable<Coordinate> {

    private long id;
    private String x;
    private String y;
    private String z;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getX() {
        return x;
    }

    public void setX(final String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(final String y) {
        this.y = y;
    }

    public String getZ() {
        return z;
    }

    public void setZ(final String z) {
        this.z = z;
    }

    public int compareTo(final Coordinate toBeCompared) {
        return Long.compare(id, toBeCompared.id);
    }
}
