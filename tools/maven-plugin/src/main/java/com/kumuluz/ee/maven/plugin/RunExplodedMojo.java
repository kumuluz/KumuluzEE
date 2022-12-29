/*
 *  Copyright (c) 2014-2017 Kumuluz and/or its affiliates
 *  and other contributors as indicated by the @author tags and
 *  the contributor list.
 *
 *  Licensed under the MIT License (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  https://opensource.org/licenses/MIT
 *
 *  The software is provided "AS IS", WITHOUT WARRANTY OF ANY KIND, express or
 *  implied, including but not limited to the warranties of merchantability,
 *  fitness for a particular purpose and noninfringement. in no event shall the
 *  authors or copyright holders be liable for any claim, damages or other
 *  liability, whether in an action of contract, tort or otherwise, arising from,
 *  out of or in connection with the software or the use or other dealings in the
 *  software. See the License for the specific language governing permissions and
 *  limitations under the License.
*/
package com.kumuluz.ee.maven.plugin;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.*;
import org.apache.maven.project.MavenProject;

import java.io.File;

import static org.twdata.maven.mojoexecutor.MojoExecutor.*;

/**
 * Run the application in an exploded class and dependency runtime.
 *
 * @author Benjamin Kastelic
 * @since 2.4.0
 */
@Mojo(
        name = "run",
        defaultPhase = LifecyclePhase.PACKAGE,
        requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME,
        requiresDependencyCollection = ResolutionScope.COMPILE_PLUS_RUNTIME
)
@Execute(phase = LifecyclePhase.PACKAGE)
public class RunExplodedMojo extends AbstractCopyDependenciesMojo {

    private static final String CLASSPATH_FORMAT = "target%1$sclasses%2$starget%1$sdependency%1$s*";

    @Override
    public void execute() throws MojoExecutionException {

        copyDependencies();

        executeMojo(
                plugin(
                        groupId("org.codehaus.mojo"),
                        artifactId("exec-maven-plugin"),
                        version("3.1.0")
                ),
                goal("exec"),
                configuration(
                        element(name("executable"), javaCmd()),
                        element(name("inheritIo"), "true"),
                        element(name("arguments"),
                                element(name("argument"), "-cp"),
                                element(name("argument"), String.format(CLASSPATH_FORMAT, File.separator, File.pathSeparator)),
                                element(name("argument"), "com.kumuluz.ee.EeApplication"))
                ),
                executionEnvironment(project, session, buildPluginManager)
        );
    }

    public static String javaCmd() {
        File javaCmd = fileForEnv("JAVACMD");

        if (javaCmd != null) {
            return javaCmd.getAbsolutePath();
        }

        File javaHome = fileForEnv("JAVA_HOME");
        if (javaHome != null) {

            String[] paths = {"jre/sh/java", "bin/java", "bin/java.exe"};

            for( String path : paths ) {
                File f = new File(javaHome, path);
                if (f.isFile() && f.canExecute()) {
                    return f.getAbsolutePath();
                }
            }
        }
        return "java";
    }

    private static File fileForEnv(String env) {
        String s = System.getenv(env);
        if (s == null || s.isEmpty()) {
            return null;
                } else {
            return new File(s);
                }
        }
    }
