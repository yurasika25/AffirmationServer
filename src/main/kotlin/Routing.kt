package com.app

import com.app.data.AffirmationData
import com.app.data.NotificationData
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Application.configureRouting() {
    routing {

        get("/") {
            call.respondText("Server is running!")
        }

        get("/images/{name}") {
            val name = call.parameters["name"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            val file = File("resources/images/$name")
            if (!file.exists()) {
                call.respond(HttpStatusCode.NotFound)
            } else {
                call.respondFile(file)
            }
        }

        get("user/profile") {
            val data = UserProfile(
                "Yurii",
                "Sika",
                28,
                "Male",
                "+380967927303",
                "yrasika80@gmail.com")

            call.respond(data)
        }

        get("affirmation/details") {
            call.respondText("Every day is a new opportunity to grow.")
        }

        get("user/status") {
            call.respondText("Enabled")
        }

        get("affirmations") {
            try {
                val affirmationData = listOf(
                    AffirmationData(1, "You are capable of amazing things."),
                    AffirmationData(2, "Believe in yourself and all that you are."),
                    AffirmationData(3, "Every day is a new opportunity to grow."),
                    AffirmationData(4, "You have the power to create change."),
                    AffirmationData(5, "Mistakes are proof that you are trying."),
                    AffirmationData(6, "Your potential is limitless."),
                    AffirmationData(7, "Stay positive, work hard, and make it happen."),
                    AffirmationData(8, "You are stronger than your fears."),
                    AffirmationData(9, "Progress, not perfection, is the goal."),
                    AffirmationData(10, "Embrace the journey and trust the process.")
                )

                call.respond(affirmationData)
            } catch (e: Exception) {
                e.printStackTrace()
                call.respondText("Internal error: ${e.message}")
            }
        }
        get("notifications") {
            try {
                val sampleTitles = listOf(
                    "Welcome!", "Daily Affirmation", "Update Available", "Reminder", "Alert",
                    "Motivational Quote", "Tip of the Day", "News Flash", "Challenge Unlocked", "Upcoming Event",
                    "Weekly Summary", "New Feature", "System Notice", "Friend Request", "Message Received",
                    "Achievement Unlocked", "Survey Invitation", "App Maintenance", "Special Offer", "Subscription Reminder"
                )

                val sampleMessages = listOf(
                    "Thanks for joining our app. Let's start your journey!",
                    "Remember: You are capable of achieving great things today!",
                    "New affirmations are available. Check them out now!",
                    "Don't forget your daily meditation.",
                    "You have a new challenge waiting for you!",
                    "Stay positive and keep pushing forward.",
                    "Here is a tip to improve your focus.",
                    "An event is coming soon, mark your calendar!",
                    "Your progress looks great, keep it up!",
                    "Time to take a short break and breathe."
                )

                fun randomDate(offset: Int): String {
                    val date = java.time.LocalDate.now().minusDays(offset.toLong())
                    val formatter = java.time.format.DateTimeFormatter.ofPattern("MMMM d, yyyy")
                    return date.format(formatter)
                }

                val notifications = (1..100).map { id ->
                    NotificationData(
                        id = id,
                        title = sampleTitles.random(),
                        message = sampleMessages.random(),
                        dateText = randomDate(id)
                    )
                }

                call.respond(notifications)
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
