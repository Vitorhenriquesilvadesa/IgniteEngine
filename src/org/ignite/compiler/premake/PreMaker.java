package org.ignite.compiler.premake;
// package com.vtko.eros.premake;

// import javax.tools.*;
// import java.io.*;
// import java.util.List;

// public class PreMaker {

// public static void main(String[] args) {
// // Caminho para o arquivo principal do projeto
// String projectFilePath = "/caminho/do/seu/projeto/Main.java";

// // Compilação do projeto
// boolean compilationSuccess = compileProject(projectFilePath);

// if (compilationSuccess) {
// // Execução do projeto
// executeProject();
// } else {
// System.out.println("Falha na compilação do projeto.");
// }
// }

// private static boolean compileProject(String projectFilePath) {
// JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
// DiagnosticCollector<JavaFileObject> diagnostics = new
// DiagnosticCollector<>();
// StandardJavaFileManager fileManager =
// compiler.getStandardFileManager(diagnostics, null, null);

// Iterable<? extends JavaFileObject> compilationUnits =
// fileManager.getJavaFileObjectsFromStrings(
// List.of(projectFilePath));

// JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager,
// diagnostics, null, null,
// compilationUnits);

// boolean success = task.call();

// try {
// fileManager.close();
// } catch (IOException e) {
// e.printStackTrace();
// }

// return success;
// }

// private static void executeProject() {
// try {
// Process process = Runtime.getRuntime().exec("java -cp /caminho/do/seu/projeto
// Main");
// BufferedReader reader = new BufferedReader(new
// InputStreamReader(process.getInputStream()));
// String line;
// while ((line = reader.readLine()) != null) {
// System.out.println(line);
// }
// } catch (IOException e) {
// e.printStackTrace();
// }
// }
// }