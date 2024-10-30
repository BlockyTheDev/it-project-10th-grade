/*
 * School Project - Tetris Game
 * Copyright (C) 2023 - present BlockyTheDev <https://github.com/BlockyTheDev>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
import java.text.SimpleDateFormat
import java.util.Date
import org.gradle.api.attributes.java.TargetJvmVersion
import org.cadixdev.gradle.licenser.LicenseExtension

plugins {
    java
    idea
    application
    alias(libs.plugins.licenser)
}

val projectVersion: String by project
val projectGroupId: String by project

group = projectGroupId
version = projectVersion

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.jetbrainsAnnotations)
}

configurations.all {
    attributes.attribute(TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE, 21)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    withSourcesJar()
    withJavadocJar()
}

tasks {
    withType<ProcessResources> {
        // Note: https://github.com/gradle/gradle/issues/861
        outputs.upToDateWhen { false }

        filesMatching("VERSION_INFO.txt") {
            expand("variable_version" to project.version)
        }
    }

    withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(21)
    }

    withType<Javadoc> {
        with(options as StandardJavadocDocletOptions) {
            encoding(Charsets.UTF_8.name())
            addStringOption("Xdoclint:none", "-quiet")
            // addStringOption("Xdoclint:reference,syntax,html,missing", "-quiet")
            keyWords()
            linkSource()
            use()
        }

        jar {
            from("LICENSE") {
                rename { "${it}_${project.base.archivesName.get()}" }
            }
        }
    }

    jar {
        manifest {
            attributes(
                "Project-Name" to project.name,
                "Project-Version" to version,
                "Build-Datetime" to SimpleDateFormat("dd.MM.yyyy HH:mm:ss Z (z)").format(Date()),
                "Build-Toolchain" to "${java.toolchain.languageVersion.get()} (${java.toolchain.vendor.get()})",
                "System-Java-VM" to "${System.getProperty("java.version")} - ${System.getProperty("java.vm.version")} (${System.getProperty("java.vm.vendor")})",
                "Build-OS" to "${System.getProperty("os.name")} ${System.getProperty("os.version")} ${System.getProperty("os.arch")}"
            )
        }
    }

    application {
        mainClass.set("${projectGroupId}.Main")
    }
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

configure<LicenseExtension> {
    newLine(false)
    header(rootProject.file("HEADER"))

    properties {
        set("year", "2023 - present")
        set("name", "BlockyTheDev <https://github.com/BlockyTheDev>")
    }

    tasks {
        create("gradle") {
            @Suppress("UnstableAPIUsage") // needed at that location
            files.from("build.gradle.kts", "settings.gradle.kts", "gradle.properties")
        }
    }
}
