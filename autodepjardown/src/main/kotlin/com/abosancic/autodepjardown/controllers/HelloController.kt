package com.abosancic.autodepjardown.controllers

import com.abosancic.autodepjardown.servcies.DownloadService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping

@RestController
class HelloController {

    @Value("\${person.name}")
    private val name: String? = null

    @Autowired
    private lateinit var downloadService: DownloadService

    @GetMapping
    fun hello(): String {
        return "Hello, $name!"
    }

    @GetMapping("/test")
    fun run(): String {
        downloadService.download()
        return "Hello, $name!"
    }

    @GetMapping(value = "/pom", produces = arrayOf("text/plain"))
    fun pom(): String = downloadService.testPom()
}
