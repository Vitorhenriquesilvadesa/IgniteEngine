package org.ignite.mathf;

import org.ignite.annotations.Define;

@Define("IGNITE_API")
public class Vector3 {

    public float x;
    public float y;
    public float z;

    public Vector3(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private Vector3(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vector3(Vector3 other){
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    public float length(){
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public static Vector3 sum(Vector3 a, Vector3 b){
        return new Vector3(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    public static float dot(Vector3 a, Vector3 b){
        return (a.x * b.x + a.y * b.y + a.z * b.z);
    }

    public static Vector3 multiply(Vector3 a, Vector3 b){
        return new Vector3(a.x * b.x, b.y * b.y, a.z * b.z);
    }

    public static Vector3 zero(){
        return new Vector3();
    }

    public static Vector3 forward(){
        return new Vector3(0, 0, 1);
    }

    public static Vector3 back(){
        return new Vector3(0, 0, -1);
    }

    public static Vector3 left(){
        return new Vector3(-1, 0, 0);
    }

    public static Vector3 right(){
        return new Vector3(1, 0, 0);
    }

    public static Vector3 up(){
        return new Vector3(0, 1, 0);
    }

    public static Vector3 down(){
        return new Vector3(0, -1, 0);
    }

    @Override
    public String toString(){
        return "Vector3 { " + this.x + ", " + this.y + ", " + this.z + " }";
    }
}
