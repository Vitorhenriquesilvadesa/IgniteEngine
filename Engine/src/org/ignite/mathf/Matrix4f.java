package org.ignite.mathf;

import java.nio.FloatBuffer;
import java.util.Arrays;

public class Matrix4f {
    private final float[][] matrix;

    public Matrix4f() {
        matrix = new float[4][4];
        setIdentity();
    }

    public void set(int row, int col, float value) {
        matrix[row][col] = value;
    }

    public float get(int row, int col) {
        return matrix[row][col];
    }

    public void setMatrix(float[][] newMatrix) {
        if (newMatrix.length != 4 || newMatrix[0].length != 4) {
            throw new IllegalArgumentException("Matrix must be 4x4");
        }
        for (int i = 0; i < 4; i++) {
            System.arraycopy(newMatrix[i], 0, matrix[i], 0, 4);
        }
    }

    public void setIdentity() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i][j] = (i == j) ? 1.0f : 0.0f;
            }
        }
    }

    public Matrix4f multiply(Matrix4f other) {
        Matrix4f result = new Matrix4f();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                float sum = 0.0f;
                for (int k = 0; k < 4; k++) {
                    sum += matrix[i][k] * other.matrix[k][j];
                }
                result.matrix[i][j] = sum;
            }
        }
        return result;
    }

    public void add(Matrix4f other) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i][j] += other.matrix[i][j];
            }
        }
    }

    public void subtract(Matrix4f other) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i][j] -= other.matrix[i][j];
            }
        }
    }

    public void transpose() {
        float[][] temp = new float[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp[i][j] = matrix[j][i];
            }
        }
        setMatrix(temp);
    }

    public void scale(Vector3 scale) {
        Matrix4f scaleMatrix = new Matrix4f();
        scaleMatrix.set(0, 0, scale.x);
        scaleMatrix.set(1, 1, scale.y);
        scaleMatrix.set(2, 2, scale.z);
        this.setMatrix(scaleMatrix.multiply(this).matrix);
    }

    public void translate(Vector3 translation) {
        Matrix4f translationMatrix = new Matrix4f();
        translationMatrix.set(0, 3, translation.x);
        translationMatrix.set(1, 3, translation.y);
        translationMatrix.set(2, 3, translation.z);
        this.setMatrix(translationMatrix.multiply(this).matrix);
    }

    public void rotate(Vector3 angles) {
        float angleX = angles.x;
        float angleY = angles.y;
        float angleZ = angles.z;

        Matrix4f rotationX = new Matrix4f();
        Matrix4f rotationY = new Matrix4f();
        Matrix4f rotationZ = new Matrix4f();

        float cosX = (float) Math.cos(angleX);
        float sinX = (float) Math.sin(angleX);
        float[][] matrixX = new float[][] {
                {1, 0, 0, 0},
                {0, cosX, -sinX, 0},
                {0, sinX, cosX, 0},
                {0, 0, 0, 1}
        };
        rotationX.setMatrix(multiplyMatrix(matrixX).matrix);

        float cosY = (float) Math.cos(angleY);
        float sinY = (float) Math.sin(angleY);
        float[][] matrixY = new float[][] {
                {cosY, 0, sinY, 0},
                {0, 1, 0, 0},
                {-sinY, 0, cosY, 0},
                {0, 0, 0, 1}
        };
        rotationY.setMatrix(multiplyMatrix(matrixY).matrix);

        float cosZ = (float) Math.cos(angleZ);
        float sinZ = (float) Math.sin(angleZ);
        float[][] matrixZ = new float[][] {
                {cosZ, -sinZ, 0, 0},
                {sinZ, cosZ, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        rotationZ.setMatrix(multiplyMatrix(matrixZ).matrix);

        Matrix4f rotation = rotationX.multiply(rotationY).multiply(rotationZ);
        this.setMatrix(rotation.multiply(this).matrix);
    }

    private Matrix4f multiplyMatrix(float[][] otherMatrix) {
        Matrix4f result = new Matrix4f();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                float sum = 0.0f;
                for (int k = 0; k < 4; k++) {
                    sum += this.matrix[i][k] * otherMatrix[k][j];
                }
                result.matrix[i][j] = sum;
            }
        }
        return result;
    }

    public float determinant() {
        float[][] mat = this.matrix;

        float term0 = mat[0][0] * mat[1][1] - mat[0][1] * mat[1][0];
        float term1 = mat[0][0] * mat[1][2] - mat[0][2] * mat[1][0];
        float term2 = mat[0][0] * mat[1][3] - mat[0][3] * mat[1][0];
        float term3 = mat[0][1] * mat[1][2] - mat[0][2] * mat[1][1];
        float term4 = mat[0][1] * mat[1][3] - mat[0][3] * mat[1][1];
        float term5 = mat[0][2] * mat[1][3] - mat[0][3] * mat[1][2];
        float term6 = mat[2][0] * mat[3][1] - mat[2][1] * mat[3][0];
        float term7 = mat[2][0] * mat[3][2] - mat[2][2] * mat[3][0];
        float term8 = mat[2][0] * mat[3][3] - mat[2][3] * mat[3][0];
        float term9 = mat[2][1] * mat[3][2] - mat[2][2] * mat[3][1];
        float term10 = mat[2][1] * mat[3][3] - mat[2][3] * mat[3][1];
        float term11 = mat[2][2] * mat[3][3] - mat[2][3] * mat[3][2];

        return term0 * term11 - term1 * term10 + term2 * term9 + term3 * term8 - term4 * term7 + term5 * term6;
    }

    public FloatBuffer toFloatBuffer(){
        FloatBuffer buffer = FloatBuffer.allocate(16);
        for (float[] floats : matrix) {
            buffer.put(floats);
        }
        buffer.flip();
        return buffer;
    }
}
