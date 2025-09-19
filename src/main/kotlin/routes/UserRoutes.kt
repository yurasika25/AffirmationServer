package routes

import data.PostData
import data.UserProfileModel
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.put
import java.io.File

fun Route.userRoutes() {
    get("user/details") {
        val file = File("data.txt")
        if (!file.exists()) {
            call.respond(HttpStatusCode.NotFound, "No data found")
            return@get
        }

        val lines = file.readLines()

        val posts = lines.mapNotNull { line ->
            val regex = """Name: (.*), Message: (.*)""".toRegex()
            val matchResult = regex.matchEntire(line)
            matchResult?.let {
                val (name, message) = it.destructured
                PostData(name, message)
            }
        }

        call.respond(posts)
    }

    put("user/profile/update") {
        val postData = call.receive<UserProfileModel>()

        val file = File("data.txt")
        file.appendText(
            "FirstName: ${postData.firstName}, " +
                    "LastName: ${postData.lastName}, " +
                    "Age: ${postData.age}, " +
                    "Gender: ${postData.gender}, " +
                    "Phone: ${postData.phoneNumber}, " +
                    "Email: ${postData.email}\n"
        )
        call.respond(HttpStatusCode.Created, "Data received and saved")
    }

    get("user/profile") {
        val data = UserProfileModel(
            "Yurii",
            "Sika",
            "18",
            "Male",
            "+380967927303",
            "yrasika80@gmail.com"
        )

        call.respond(data)
    }
}