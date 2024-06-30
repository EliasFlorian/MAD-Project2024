package com.example.mad_project2024.data

data class subcategoryData(val title: String, val description: String)

val categories = mapOf(
    "General" to listOf(
        subcategoryData("Public Holidays", "Information about public holidays."),
        subcategoryData("Healthcare-system", "Details about the healthcare system."),
        subcategoryData("Restrictions", "Current restrictions in place."),
        subcategoryData("Fun facts", "Some fun facts."),
        subcategoryData("Dos and Don’ts", "Important dos and don’ts.")
    ),
    "Communication" to listOf(
        subcategoryData("Local greetings", "Common greetings used locally."),
        subcategoryData("Etiquette", "Proper etiquette to follow."),
        subcategoryData("Expectations", "What to expect when communicating."),
        subcategoryData("Popular phrases", "Popular phrases and their meanings."),
        subcategoryData("Gestures and facial expressions", "Common gestures and facial expressions.")
    ),
    "Travel Guide" to listOf(
        subcategoryData("Public Transport", "Information about public transport."),
        subcategoryData("Payment norms", "Payment norms and methods."),
        subcategoryData("Traveling by car", "Guide to traveling by car."),
        subcategoryData("Traveling on foot", "Guide to traveling on foot."),
        subcategoryData("Risks and dangers", "Potential risks and dangers."),
        subcategoryData("Shopping", "Shopping tips and locations."),
        subcategoryData("Gastronomy", "Local food and gastronomy.")
    )
)