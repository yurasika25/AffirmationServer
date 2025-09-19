package routes

import data.NotificationModel
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import java.time.LocalDate
import java.time.format.DateTimeFormatter


fun Route.notificationRoutes() {

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
                val date = LocalDate.now().minusDays(offset.toLong())
                val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy")
                return date.format(formatter)
            }

            val notifications = (1..100).map { id ->
                NotificationModel(
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

}