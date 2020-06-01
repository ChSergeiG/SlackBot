package ru.chsergeig.bot.slack.bot

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import me.ramswaroop.jbot.core.slack.models.Attachment
import me.ramswaroop.jbot.core.slack.models.RichMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import ru.chsergeig.bot.slack.Utils.Companion.safeGetValueFromEvOrCli
import javax.annotation.PostConstruct


@Component
open class WebHookBot {

    companion object {
        @JvmStatic
        private val LOG: Logger = LoggerFactory.getLogger(WebHookBot::class.java)
    }

    //    @Value("\${slackIncomingWebhookUrl}")
    private val token: String = safeGetValueFromEvOrCli(arrayOf(), "", "Slack webhook token", "WEBHOOK_TOKEN")

    @PostConstruct
    open fun invokeSlackWebHook() {
        val restTemplate = RestTemplate()
        val richMessage = RichMessage("Just to test Slack's incoming webhooks.")

        val attachments: Array<Attachment?> = arrayOfNulls<Attachment>(1)
        attachments[0] = Attachment()
        attachments[0]?.text = "Some data relevant to your users."
        richMessage.attachments = attachments

        try {
            LOG.info("Reply (RichMessage): {}", ObjectMapper().writeValueAsString(richMessage))
        } catch (e: JsonProcessingException) {
            LOG.error("Error parsing RichMessage: ", e)
        }

        try {
            restTemplate.postForEntity(token, richMessage.encodedMessage(), String::class.java)
        } catch (e: RestClientException) {
            LOG.error("Error posting to Slack Incoming Webhook: ", e)
        }
    }

}