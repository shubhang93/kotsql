package kotsql
fun main(args: Array<String>) {
    val sqlService: SqlService = SqlService("/home/shubhang/IdeaProjects/kotsql/sql")
    val res = sqlService.runQuery(queryName = "product", argsMap = mapOf(Pair("code", "p123")))
    println(res)

}