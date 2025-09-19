import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import routes.*

fun Application.configureRouting() {
    routing {
        userRoutes()
        affirmationRoutes()
        notificationRoutes()
        healthRoutes()
        platformRoutes()

        get("/") {
            call.respondText("Server is running!")
        }
    }
}
