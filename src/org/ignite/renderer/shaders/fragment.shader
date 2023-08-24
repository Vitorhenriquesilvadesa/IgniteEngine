#version 330 core

in vec4 color;
out vec4 fragColor;

void main(){
    fragColor = vec4(color.x + 0.5, color.y + 0.5, color.z + 0.5, 1.0);
}