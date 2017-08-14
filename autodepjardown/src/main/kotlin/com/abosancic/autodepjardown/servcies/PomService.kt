package com.abosancic.autodepjardown.servcies

import org.apache.commons.io.FileUtils
import org.springframework.stereotype.Component
import java.io.File

@Component
class PomService {

    fun createPomFile(groupId: String, artifactId: String, version: String): Unit {
        var pomTmp: String = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "\txsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "\t<modelVersion>4.0.0</modelVersion>\n" +
                "\n" +
                "\t<groupId>com.abosancic</groupId>\n" +
                "\t<artifactId>down</artifactId>\n" +
                "\t<version>0.0.1-SNAPSHOT</version>\n" +
                "\t<packaging>jar</packaging>\n" +
                "\n" +
                "\t<name>down</name>\n" +
                "\n" +
                "\t<properties>\n" +
                "\t\t<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>\n" +
                "\t\t<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>\n" +
                "\t\t<java.version>1.8</java.version>\n" +
                "\t</properties>\n" +
                "\n" +
                "\t<dependencies>\n" +
                "\t\t<dependency>\n" +
                "\t\t\t<groupId>$groupId</groupId>\n" +
                "\t\t\t<artifactId>$artifactId</artifactId>\n" +
                "\t\t\t<version>$version</version>\n" +
                "\t\t</dependency>\n" +
                "\t</dependencies>\n" +
                "\n" +
                "\t<build>\n" +
                "\t\t<plugins>\n" +
                "\t\t\t<plugin>\n" +
                "\t\t\t\t<groupId>org.apache.maven.plugins</groupId>\n" +
                "\t\t\t\t<artifactId>maven-dependency-plugin</artifactId>\n" +
                "\t\t\t\t<executions>\n" +
                "\t\t\t\t\t<execution>\n" +
                "\t\t\t\t\t\t<id>copy-dependencies</id>\n" +
                "\t\t\t\t\t\t<phase>package</phase>\n" +
                "\t\t\t\t\t\t<goals>\n" +
                "\t\t\t\t\t\t\t<goal>copy-dependencies</goal>\n" +
                "\t\t\t\t\t\t</goals>\n" +
                "\t\t\t\t\t\t<configuration>\n" +
                "\t\t\t\t\t\t</configuration>\n" +
                "\t\t\t\t\t</execution>\n" +
                "\t\t\t\t</executions>\n" +
                "\t\t\t</plugin>\n" +
                "\n" +
                "\t\t</plugins>\n" +
                "\t</build>\n" +
                "\n" +
                "\n" +
                "</project>"

        var rootDir = File("down")

        if (rootDir.exists()){
            FileUtils.deleteDirectory(rootDir)
        }

        rootDir.mkdirs()

        FileUtils.writeStringToFile(File(rootDir, "pom.xml"), pomTmp)

    }
    
}