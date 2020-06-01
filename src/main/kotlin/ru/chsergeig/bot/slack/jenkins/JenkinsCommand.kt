package ru.chsergeig.bot.slack.jenkins

enum class JenkinsCommand {

    START,
    INTERRUPT,
    JOBSTATUS,
    ;

    companion object {
        fun of(command: String, text: String?): JenkinsCommand {
            return valueOf(command.removePrefix("/").toUpperCase())
        }
    }

}