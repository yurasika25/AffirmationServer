package routes

import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.platformRoutes() {
    get("/check-platform") {
        val userAgent = call.request.headers["User-Agent"] ?: ""
        val platform = when {
            userAgent.contains("Android", ignoreCase = true) -> "Android"
            userAgent.contains("iPhone", ignoreCase = true) ||
                    userAgent.contains("iPad", ignoreCase = true) ||
                    userAgent.contains("iOS", ignoreCase = true) -> "iOS"
            else -> "Web"
        }
        call.respond(mapOf("platform" to platform))
    }
}
