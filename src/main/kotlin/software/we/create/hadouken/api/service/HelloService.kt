package software.we.create.hadouken.api.service

import org.springframework.stereotype.Service

@Service
class HelloService {
    fun hello(): String {
        return "Hello from Kotlin!"
    }
}