#version 330 core

in vec4 v_Color;
layout(location = 0) out vec4 color;

void main(){
    color = vec4(0.2, 0.3, 0.8, 1.0);
}