package com.abosancic.autodepjardown.servcies

import org.springframework.stereotype.Component
import jdk.nashorn.internal.runtime.ScriptingFunctions.readLine
import org.apache.commons.io.FileUtils
import org.springframework.beans.factory.annotation.Autowired
import java.io.InputStreamReader
import java.io.BufferedReader
import java.io.File

@Component
class DownloadService{

    @Autowired
    lateinit var pomService: PomService

    @Autowired
    lateinit var zip: ZipUtils

    fun download(): Unit {
        //Runtime.getRuntime().exec("mvn clean", System.out);
        try {
            var root = File(".")

            var line: String
            val p = Runtime
                    .getRuntime()
//                    .exec("apache-maven-3.5.0" + File.separator + "bin" + File.separator + "mvn.cmd -Dmaven.repo.local=./my clean", null, root)
                    .exec("apache-maven-3.5.0" + File.separator + "bin" + File.separator + "mvn.cmd -Dmaven.repo.local=./my --version", null, root)

            val inp = BufferedReader(
                    InputStreamReader(p.inputStream))
            val results = StringBuilder()
            while (true) {
                val line = inp.readLine()
                if (line == null) break
                results.append(line).append(System.lineSeparator())
            }
            inp.close()
            val inputAsString = results.toString()

            println(inputAsString)

            var dep = Runtime
                    .getRuntime()
                    .exec("apache-maven-3.5.0" + File.separator + "bin" + File.separator + "mvn.cmd -Dmaven.repo.local=./my dependency:dependencies")

        } catch (e: Throwable) {
            e.printStackTrace()
        }

    }

    fun testPom(): String {
        pomService.createPomFile("org.apache.httpcomponents", "httpclient", "4.5.3")
        try {
            var dep = Runtime
                    .getRuntime()
                    .exec("apache-maven-3.5.0" + File.separator + "bin" + File.separator + "mvn.cmd -f down" + File.separator + "pom.xml dependency:copy-dependencies")
            val inp = BufferedReader(
                    InputStreamReader(dep.inputStream))
            val results = StringBuilder()
            while (true) {
                val line = inp.readLine()
                if (line == null) break
                results.append(line).append(System.lineSeparator())
            }
            inp.close()
            val inputAsString = results.toString()

            println(inputAsString)

            FileUtils.writeStringToFile(File("down" + File.separator + "target" + File.separator + "dependency" + File.separator + "log", "log.log"), "test")

            zip.zipIt("down" + File.separator + "target" + File.separator + "dependency", "my.zip")

            return inputAsString
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return "Error"
    }

}