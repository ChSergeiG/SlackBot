package ru.chsergeig.bot.slack.bot

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import me.ramswaroop.jbot.core.slack.models.Attachment
import me.ramswaroop.jbot.core.slack.models.RichMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.chsergeig.bot.slack.Utils

@RestController
open class SlashCommandBot {

    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(SlashCommandBot::class.java)
    }

    //    @Value("${slashCommandToken}")
    private val token: String = Utils.safeGetValueFromEvOrCli(arrayOf(), "", "Slack slash command token", "SLASH_TOKEN")

    @RequestMapping(
        value = ["/slash-command"],
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE]
    )
    open fun onReceiveSlashCommand(
        @RequestParam("token") token: String,
        @RequestParam("team_id") teamId: String?,
        @RequestParam("team_domain") teamDomain: String?,
        @RequestParam("channel_id") channelId: String?,
        @RequestParam("channel_name") channelName: String?,
        @RequestParam("user_id") userId: String?,
        @RequestParam("user_name") userName: String?,
        @RequestParam("command") command: String?,
        @RequestParam("text") text: String?,
        @RequestParam("response_url") responseUrl: String?
    ): RichMessage? {
        // validate token
        LOG.info("ENV token: '{}'", this.token)
        LOG.info("REQ token: '{}'", token)


        if (token != this.token) {
            return RichMessage("Sorry! You're not lucky enough to use our slack command.")
        }

        val richMessage = RichMessage("The is Slash Commander!")
        richMessage.responseType = "in_channel"
        val attachments: Array<Attachment?> = arrayOfNulls(1)
        attachments[0] = Attachment()
        attachments[0]?.text = "I will perform all tasks for you."
        richMessage.attachments = attachments
        try {
            LOG.info("Reply (RichMessage): {}", ObjectMapper().writeValueAsString(richMessage))
        } catch (e: JsonProcessingException) {
            LOG.error("Error parsing RichMessage: ", e)
        }
        return richMessage.encodedMessage()
    }


}