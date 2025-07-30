package com.app

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Application.configureRouting() {
    routing {

        get("/images/{name}") {
            val name = call.parameters["name"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            val file = File("resources/images/$name")
            if (!file.exists()) {
                call.respond(HttpStatusCode.NotFound)
            } else {
                call.respondFile(file)
            }
        }

        get("/") {
            call.respondText("Server is running")
        }

        get("affirmation/details") {
            call.respondText("Every day is a new opportunity to grow.")
        }

        get("user/profile") {
            call.respondText("User user 001")
        }

        get("affirmations") {
            try {
                val affirmations = listOf(
                    Affirmation(1, "You are capable of amazing things."),
                    Affirmation(2, "Believe in yourself and all that you are."),
                    Affirmation(3, "Every day is a new opportunity to grow."),
                    Affirmation(4, "You have the power to create change."),
                    Affirmation(5, "Mistakes are proof that you are trying."),
                    Affirmation(6, "Your potential is limitless."),
                    Affirmation(7, "Stay positive, work hard, and make it happen."),
                    Affirmation(8, "You are stronger than your fears."),
                    Affirmation(9, "Progress, not perfection, is the goal."),
                    Affirmation(10, "Embrace the journey and trust the process.")
                )

                call.respond(affirmations)
            } catch (e: Exception) {
                e.printStackTrace()
                call.respondText("Internal error: ${e.message}")
            }
        }

        get("health") {
            call.respond(
                mapOf(
                    "status" to "OK",
                    "uptime" to System.currentTimeMillis(),
                    "timestamp" to java.time.Instant.now().toString()
                )
            )
        }
    }
}
