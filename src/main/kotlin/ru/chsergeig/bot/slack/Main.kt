package ru.chsergeig.bot.slack

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate


@SpringBootApplication(scanBasePackages = ["me.ramswaroop.jbot", "ru.chsergeig.bot.slack"])
open class Main {

    @Bean
    open fun restTemplate(): RestTemplate? {
        return RestTemplate()
    }

}

fun main() {
    SpringApplication.run(Main::class.java)
}
