package org.ignite.renderertools.cameras;

import org.ignite.mathf.Matrix4f;
import org.ignite.mathf.Transform;
import org.ignite.mathf.Vector3;

public abstract class Camera {

    public Transform transform;
    protected ProjectionType projectionType;

    protected float near;
    protected float far;

    protected Matrix4f projectionMatrix;
    protected Matrix4f viewMatrix;

    public Camera(){
        transform = new Transform();
    }

    public abstract void setPosition(Vector3 position);

    public Matrix4f getViewProjectionMatrix() {
        return projectionMatrix.multiply(viewMatrix);
    }

    public static Camera create(ProjectionType type){
        return switch (type) {
            case PERSPECTIVE -> new PerspectiveCamera();
            case ORTHOGRAPHIC -> new OrthographicCamera();
            default -> null;
        };
    }
}
