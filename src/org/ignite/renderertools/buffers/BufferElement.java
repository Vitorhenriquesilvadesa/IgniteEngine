package org.ignite.renderertools.buffers;

import static org.ignite.core.macros.Macros.*;

import org.ignite.renderertools.buffers.exceptions.InvalidShaderDataTypeException;

public class BufferElement {

    public String name;
    public ShaderDataType type;
    public int offset;
    public int size;
    public boolean normalized;

    public BufferElement() {
    }

    public BufferElement(ShaderDataType type, String name, boolean normalized) {
        this.name = name;
        this.offset = 0;
        this.size = shaderDataTypeSize(type);
        this.type = type;
        this.normalized = normalized;
    }

    public BufferElement(ShaderDataType type, String name) {
        this.name = name;
        this.offset = 0;
        this.size = shaderDataTypeSize(type);
        this.type = type;
        this.normalized = false;
    }

    public int getComponentCount() {
        switch (type) {
            case Float:
                return 1;
            case Float2:
                return 2;
            case Float3:
                return 3;
            case Float4:
                return 4;
            case Int:
                return 1;
            case Int2:
                return 2;
            case Int3:
                return 3;
            case Int4:
                return 4;
            case Mat3:
                return 3 * 3;
            case Mat4:
                return 4 * 4;
            case Boolean:
                return 1;
            default:
                break;
        }

        _assert(new InvalidShaderDataTypeException("The specified shader data type is not supported."), false);
        return 0;
    }

    public static int shaderDataTypeSize(ShaderDataType type) {

        switch (type) {

            case Float:
                return 4 * 1;

            case Float2:
                return 4 * 2;

            case Float3:
                return 4 * 3;

            case Float4:
                return 4 * 4;

            case Mat3:
                return 4 * 3 * 3;

            case Mat4:
                return 4 * 4 * 4;

            case Int:
                return 4 * 1;

            case Int2:
                return 4 * 2;

            case Int3:
                return 4 * 3;

            case Int4:
                return 4 * 4;

            case Boolean:
                return 1;

            default:
                break;
        }

        _assert(new InvalidShaderDataTypeException("The specified shader data type is not supported."), false);
        return 0;
    }

}