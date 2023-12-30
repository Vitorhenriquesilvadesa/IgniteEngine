package org.ignite.renderertools.cameras;

import org.ignite.mathf.Matrix4f;
import org.ignite.mathf.Vector3;

public class OrthographicCamera extends Camera {
    private final float left, right, bottom, top;

    public OrthographicCamera(float left, float right, float bottom, float top) {
        this.left = left;
        this.right = right;
        this.bottom = bottom;
        this.top = top;

        this.projectionMatrix = calculateOrthographicMatrix(left, right, bottom, top);
    }

    public OrthographicCamera() {
        this.left = -1;
        this.right = 1;
        this.bottom = -1;
        this.top = 1;

        this.projectionMatrix = calculateOrthographicMatrix(left, right, bottom, top);
    }

    private Matrix4f calculateOrthographicMatrix(float left, float right, float bottom, float top) {
        Matrix4f ortho = new Matrix4f();

        ortho.set(0, 0, 2.0f / (right - left));
        ortho.set(1, 1, 2.0f / (top - bottom));
        ortho.set(2, 2, -1.0f);

        ortho.set(0, 3, -(right + left) / (right - left));
        ortho.set(1, 3, -(top + bottom) / (top - bottom));

        return ortho;
    }

    @Override
    public void setPosition(Vector3 position) {
        this.transform.position = position;
        updateViewMatrix();
    }

    private void updateViewMatrix() {
        Matrix4f translation = new Matrix4f();
        translation.setIdentity();
        translation.translate(new Vector3(-this.transform.position.x,
                -this.transform.position.y, -this.transform.position.z));

        this.viewMatrix = translation;
    }
}
