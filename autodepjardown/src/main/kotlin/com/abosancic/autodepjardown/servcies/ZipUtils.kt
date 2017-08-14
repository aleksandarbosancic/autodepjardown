package com.abosancic.autodepjardown.servcies

import org.springframework.stereotype.Component
import java.io.File
import java.io.IOException
import java.io.FileInputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import java.io.FileOutputStream
import java.util.ArrayList

@Component
class ZipUtils {

    lateinit var fileList: ArrayList<String>

    private lateinit var SOURCE_FOLDER: String

    /**
     * Zip it
     * @param source output ZIP file location
     */
    fun zipIt(source: String, zip: String) {

        SOURCE_FOLDER = File(source).absolutePath
        fileList = arrayListOf()

        generateFileList(File(File("."), source))

        val buffer = ByteArray(1024)

        try {

            val fos = FileOutputStream(zip)
            val zos = ZipOutputStream(fos)

            println("Output to Zip : " + zip)

            for (file in this.fileList) {

                println("File Added : " + file)
                val ze = ZipEntry(file)
                zos.putNextEntry(ze)

                val inp = FileInputStream(source + File.separator + file)

                var len: Int
                while (true) {
                    len = inp.read(buffer)
                    if (len < 0) {
                        break
                    }
                    zos.write(buffer, 0, len)
                }

                inp.close()
            }

            zos.closeEntry()
            //remember close it
            zos.close()

            println("Done")
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

    }

    /**
     * Traverse a directory and get all files,
     * and add the file into fileList
     * @param node file or directory
     */
    fun generateFileList(node: File) {

        //add file only
        if (node.isFile()) {
            fileList.add(generateZipEntry(node.getAbsoluteFile().toString()))
        }

        if (node.isDirectory()) {
            val subNote = node.list()
            for (filename in subNote) {
                generateFileList(File(node, filename))
            }
        }

    }

    /**
     * Format the file path for zip
     * @param file file path
     * *
     * @return Formatted file path
     */
    private fun generateZipEntry(file: String): String {
        return file.substring(SOURCE_FOLDER.length + 3, file.length)
    }

}