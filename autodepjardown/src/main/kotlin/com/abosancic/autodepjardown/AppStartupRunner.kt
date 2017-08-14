package com.abosancic.autodepjardown

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationRunner
import java.awt.SystemColor.info
import org.springframework.boot.ApplicationArguments
import org.springframework.stereotype.Component
import java.awt.SystemColor.info
import java.util.*

/**
java -jar command-line.jar \
        this-is-a-non-option-arg \
        --server.port=9090 \
        --person.name=Memorynotfound.com
 */

@Component
class AppStartupRunner : ApplicationRunner {

    private var logger:Logger = LoggerFactory.getLogger(AppStartupRunner::class.java);

    @Throws(Exception::class)
    override fun run(args: ApplicationArguments) {
        //logger.info("Your application started with option names : {}", args.optionNames)
        //logger.info("Your application started with option names : {}", args.sourceArgs)
        logger.info("Application started with command-line arguments: {}", Arrays.toString(args.sourceArgs))
        logger.info("NonOptionArgs: {}", args.nonOptionArgs)
        logger.info("OptionNames: {}", args.optionNames)

        for (name in args.optionNames) {
            logger.info("arg-" + name + "=" + args.getOptionValues(name))
        }

        val containsOption = args.containsOption("person.name")
        logger.info("Contains person.name: " + containsOption)
    }


}