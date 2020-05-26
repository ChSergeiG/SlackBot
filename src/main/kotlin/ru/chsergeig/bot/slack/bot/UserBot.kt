package ru.chsergeig.bot.slack.bot

import me.ramswaroop.jbot.core.slack.Bot
import me.ramswaroop.jbot.core.slack.Controller
import me.ramswaroop.jbot.core.slack.EventType
import me.ramswaroop.jbot.core.slack.models.Event
import me.ramswaroop.jbot.core.slack.models.Message
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.socket.WebSocketSession
import ru.chsergeig.bot.slack.Utils.Companion.safeGetValueFromEvOrCli

@Component
open class UserBot : Bot() {

    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(UserBot::class.java)
    }

    //    @Value("${slackBotToken}")
    private var token: String = safeGetValueFromEvOrCli(arrayOf(), "", "Slack bot token", "TOKEN")

    override fun getSlackBot(): Bot {
        return this
    }

    override fun getSlackToken(): String {
        return token
    }

    @Controller(
        events = [EventType.DIRECT_MENTION, EventType.DIRECT_MESSAGE]
    )
    open fun onReceiveMentionOrMessage(socketSession: WebSocketSession, event: Event) {
        LOG.info("Got event in onReceiveMentionOrMessage channel with type '{}'", event.type.toString())
        reply(socketSession, event, Message("Got it"))
    }


}
