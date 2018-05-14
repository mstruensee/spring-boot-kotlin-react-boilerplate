package software.we.create.hadouken.api.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import software.we.create.hadouken.api.service.HelloService

@RestController
class HelloController(
        val helloService: HelloService
) {
    @GetMapping("api/v1/hello")
    fun hello(): String {
        return helloService.hello()
    }
}