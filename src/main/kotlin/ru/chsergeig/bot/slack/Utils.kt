package ru.chsergeig.bot.slack

class Utils {

    companion object {

        @JvmStatic   val JOB_KEY: String = "JOB_KEY"

        @JvmStatic
        fun safeGetValueFromEvOrCli(args: Array<String>, prefix: String, valueDef: String, envVar: String?): String {
            val valueFromEnv: String? = System.getenv()[envVar]
            if (!valueFromEnv.isNullOrEmpty()) {
                return valueFromEnv
            }
            val value: String = args.first { arg: String -> arg.startsWith(prefix) }
            if (value.isNotEmpty() && value.contains("=")) {
                return value.split("=")[1]
            } else {
                throw RuntimeException("$valueDef is empty or not valid")
            }
        }

        @JvmStatic
        fun getJenkinsArguments(text: String?): Map<String, String> {
            val result :MutableMap<String, String> = HashMap()
            result[JOB_KEY] = ""
            return result
        }

    }

}
