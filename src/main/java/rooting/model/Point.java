package rooting.model;

import java.util.Objects;

/**
 * @author agavrikov
 * @date 26.01.19
 */
public class Point {

    private static final double EARTH_COEF = 1.511582261992642;
    private static final double EPS = 1e-5;

    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Point(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double degToRad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double radToDeg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public double dist(Point point) {
        double theta = longitude - point.getLongitude();
        double dist =
            Math.sin(degToRad(latitude)) * Math.sin(degToRad(point.getLatitude())) +
            Math.cos(degToRad(latitude)) * Math.cos(degToRad(point.getLatitude())) * Math.cos(degToRad(theta));
        dist = Math.acos(dist);
        dist = radToDeg(dist);
        return dist * 60 * 1.1515 * 1.609344 * EARTH_COEF;
    }

    @Override
    public String toString() {
        return "Point{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Point)) {
            return false;
        }
        Point point = (Point) o;
        return Math.abs(point.getLatitude() - latitude) < EPS && Math.abs(point.getLongitude() - longitude) < EPS;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.round(latitude * Math.pow(10, EPS)), Math.round(longitude * Math.pow(10, EPS)));
    }
}
