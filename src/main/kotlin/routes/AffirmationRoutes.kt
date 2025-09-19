package routes

import data.AffirmationModel
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.affirmationRoutes() {

    get("/affirmation/details") {
        call.respondText("Every day is a new opportunity to grow.")
    }

    get("affirmations") {
        try {
            val affirmationData = listOf(
                AffirmationModel(1, "You are capable of amazing things."),
                AffirmationModel(2, "Believe in yourself and all that you are."),
                AffirmationModel(3, "Every day is a new opportunity to grow."),
                AffirmationModel(4, "You have the power to create change."),
                AffirmationModel(5, "Mistakes are proof that you are trying."),
                AffirmationModel(6, "Your potential is limitless."),
                AffirmationModel(7, "Stay positive, work hard, and make it happen."),
                AffirmationModel(8, "You are stronger than your fears."),
                AffirmationModel(9, "Progress, not perfection, is the goal."),
                AffirmationModel(10, "Embrace the journey and trust the process.")
            )

            call.respond(affirmationData)
        } catch (e: Exception) {
            e.printStackTrace()
            call.respondText("Internal error: ${e.message}")
        }
    }
}
