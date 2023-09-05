package org.ignite.renderertools.shader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.ignite.core.app.Application.ClientLog;
import static org.ignite.core.macros.debug.Macros.*;

public class Shader {

    private int rendererID;

    public Shader(String vertPath, String fragPath) {

        this.rendererID = glCreateProgram();
        int program = this.rendererID;

        String vertSource = parseShader(vertPath);
        String fragSource = parseShader(fragPath);
        int vertexShader = glCreateShader(GL_VERTEX_SHADER);
        int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);

        glShaderSource(vertexShader, vertSource);
        glShaderSource(fragmentShader, fragSource);

        glCompileShader(vertexShader);

        int success = glGetShaderi(vertexShader, GL_COMPILE_STATUS);

        if (success == GL_FALSE) {
            String error = glGetShaderInfoLog(vertexShader);
            ClientLog.error(error);
            _assert("Vertex Shader compilation failed!", false);
        }

        /*
         * 
         */

        glCompileShader(fragmentShader);

        success = glGetShaderi(fragmentShader, GL_COMPILE_STATUS);

        if (success == GL_FALSE) {
            String error = glGetShaderInfoLog(fragmentShader);
            ClientLog.error(error);
            _assert("Fragment Shader compilation failed!", false);
        }

        glAttachShader(program, vertexShader);
        glAttachShader(program, fragmentShader);

        glLinkProgram(program);

        int isLinked = glGetProgrami(program, GL_LINK_STATUS);

        if (isLinked == GL_FALSE) {
            String error = glGetProgramInfoLog(program);
            ClientLog.error(error);
            _assert("Shader Program link failed!", false);
        }

        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);
        glDetachShader(program, vertexShader);
        glDetachShader(program, fragmentShader);

        // ClientLog.debug(vertSource);
        // ClientLog.debug(fragSource);
    }

    public void bind() {
        glUseProgram(this.rendererID);
    }

    public void unbind() {
        glUseProgram(0);
    }

    private String parseShader(String filePath) {

        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(workingDir + filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            reader.close();
            return sb.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
