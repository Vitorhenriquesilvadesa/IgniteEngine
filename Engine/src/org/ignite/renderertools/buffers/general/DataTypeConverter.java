package org.ignite.renderertools.buffers.general;

import static org.ignite.general.macros.debug.Macros.*;
import static org.lwjgl.opengl.GL45.*;

import org.ignite.renderertools.buffers.exceptions.InvalidShaderDataTypeException;

public final class DataTypeConverter {

    public static int shaderDataTypeToOpenGLBaseType(ShaderDataType type) {

        switch (type) {
            case Boolean:
                return GL_BOOL;
            case Float:
                return GL_FLOAT;
            case Float2:
                return GL_FLOAT;
            case Float3:
                return GL_FLOAT;
            case Float4:
                return GL_FLOAT;
            case Int:
                return GL_INT;
            case Int2:
                return GL_INT;
            case Int3:
                return GL_INT;
            case Int4:
                return GL_INT;
            case Mat3:
                return GL_FLOAT;
            case Mat4:
                return GL_FLOAT;
            default:
                break;
        }

        _assert(new InvalidShaderDataTypeException("The specified shader data type is not supported."), false);
        return 0;
    }
}
