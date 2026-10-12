package ca.gbc.comp3074.kenny_john.lab03

import android.content.Context

data class Message(
    val user: String,
    val content: String
)

fun loadMessages(context: Context): List<Message> {

    val users = context.resources.getStringArray(R.array.users)
    val messages = context.resources.getStringArray(R.array.messages)

    // this is the function that is loading the data
    return users.zip(messages){
        user, message -> Message(user, message)
    }


}
