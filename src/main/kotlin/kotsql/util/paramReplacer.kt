package kotsql.util

const val PARAM_MATCHER_REGEX = ":[a-zA-Z]+"


fun paramReplacer(query: String, params: Map<String, Any>): String {
    return query.replace(Regex(PARAM_MATCHER_REGEX), fun(match): String {
        val matchedSubString = match.value
        val paramValue: Any? = params[matchedSubString.drop(1)]
        if (paramValue is String)
            return "'$paramValue'"
        return "$paramValue"
    })

}

fun doesQueryContainArgs(query: String): Boolean {
    return Regex(PARAM_MATCHER_REGEX).containsMatchIn(query)
}


