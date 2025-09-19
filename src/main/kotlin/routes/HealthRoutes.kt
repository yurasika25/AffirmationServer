package routes

import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.time.Instant

fun Route.healthRoutes() {
    get("/health") {
        call.respond(
            mapOf(
                "status" to "OK",
                "uptime" to System.currentTimeMillis(),
                "timestamp" to Instant.now().toString()
            )
        )
    }
}
