package software.we.create.hadouken

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HadoukenApplication

fun main(args: Array<String>) {
    runApplication<HadoukenApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}