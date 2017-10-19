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

import java.util.ResourceBundle;

/**
 * @author Tilen Faganel
 * @since 2.4.0
 */
public class MojoConstants {

    public static final String MAVEN_JAR_PLUGIN_VERSION = ResourceBundle.getBundle("META-INF/kumuluzee/plugin-versions").getString("maven-jar-plugin.version");
    public static final String MAVEN_RESOURCE_PLUGIN_VERSION = ResourceBundle.getBundle("META-INF/kumuluzee/plugin-versions").getString("maven-resources-plugin.version");
    public static final String MAVEN_DEPENDENCY_PLUGIN_VERSION = ResourceBundle.getBundle("META-INF/kumuluzee/plugin-versions").getString("maven-dependency-plugin.version");
    public static final String DOWNLOAD_MAVEN_PLUGIN_VERSION = ResourceBundle.getBundle("META-INF/kumuluzee/plugin-versions").getString("download-maven-plugin.version");
    public static final String REPLACER_PLUGIN_VERSION = ResourceBundle.getBundle("META-INF/kumuluzee/plugin-versions").getString("replacer-plugin.version");
    public static final String SWAGGER_UI_VERSION = ResourceBundle.getBundle("META-INF/kumuluzee/plugin-versions").getString("swagger-ui.version");
}
