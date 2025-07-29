package com.app

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {

        get("/") {
            call.respondText("Server is running")
        }

        get("/affirmations") {
            try {
                val affirmations = listOf(
                    Affirmation(1, "You are capable of amazing things."),
                    Affirmation(2, "Believe in yourself and all that you are."),
                    Affirmation(3, "Every day is a new opportunity to grow.")
                )
                call.respond(affirmations)
            } catch (e: Exception) {
                e.printStackTrace()
                call.respondText("Internal error: ${e.message}")
            }
        }

        get("/health") {
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
