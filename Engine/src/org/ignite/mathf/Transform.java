package org.ignite.mathf;

public class Transform {

    public Vector3 position;
    public Vector3 rotation;
    public Vector3 scale;

    public Transform() {
        position = Vector3.zero();
        rotation = Vector3.zero();
        scale = Vector3.zero();
    }

    public Transform(Vector3 position, Vector3 rotation, Vector3 scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public Transform(Vector3 position, Vector3 rotation) {
        this.position = position;
        this.rotation = rotation;
        this.scale = new Vector3(1, 1, 1);
    }

    public Transform(Vector3 position) {
        this.position = position;
        this.rotation = Vector3.zero();
        this.scale = new Vector3(1, 1, 1);
    }

    public Transform(Vector3 position, float rotationX, float rotationY, float rotationZ) {
        this.position = position;
        this.rotation = new Vector3(rotationX, rotationY, rotationZ);
        this.scale = new Vector3(1, 1, 1);
    }

    public Matrix4f transformationMatrix() {
        Matrix4f matrix = new Matrix4f();

        matrix.translate(position);
        matrix.rotate(rotation);
        matrix.scale(scale);

        return matrix;
    }
}
