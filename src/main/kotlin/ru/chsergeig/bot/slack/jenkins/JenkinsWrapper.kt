package ru.chsergeig.bot.slack.jenkins

import com.offbytwo.jenkins.JenkinsServer
import ru.chsergeig.bot.slack.Utils.Companion.safeGetValueFromEvOrCli
import java.net.URI

class JenkinsWrapper {

    companion object {

        @JvmStatic
        private val ENDPOINT: String =
            safeGetValueFromEvOrCli(arrayOf(), "", "Jenkins endpoint", "JENKINS_REST_ENDPOINT")

        @JvmStatic
        private val JENKINS_USER: String =
            safeGetValueFromEvOrCli(arrayOf(), "", "Jenkins credentials", "JENKINS_USER")

        @JvmStatic
        private val JENKINS_PASSWORD: String =
            safeGetValueFromEvOrCli(arrayOf(), "", "Jenkins credentials", "JENKINS_PASSWORD")

        @JvmStatic
        fun execute(command: JenkinsCommand, arguments: Map<String, String>) {
            when (command) {
                JenkinsCommand.INTERRUPT -> {
                    interrupt(arguments)
                }
                JenkinsCommand.START -> {
                    start(arguments)
                }
                JenkinsCommand.JOBSTATUS -> {
                    jobStatus(arguments)
                }
                else -> {
                    // do nothing
                }
            }

        }

        private fun jobStatus(arguments: Map<String, String>) {


            val js: JenkinsServer = JenkinsServer(URI(ENDPOINT), JENKINS_USER, JENKINS_PASSWORD)

            js.getJob("Slack/Test_pipe")

//            val client: JenkinsClient = JenkinsClient.builder().credentials(CREDENTIALS).endPoint(ENDPOINT).build()
//            client.api().jobsApi();
            TODO("Not yet implemented")
        }

        private fun start(arguments: Map<String, String>) {
            TODO("Not yet implemented")
        }

        private fun interrupt(arguments: Map<String, String>) {
            TODO("Not yet implemented")
        }


    }

}