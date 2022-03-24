package study.datajpa.entity

import java.util.regex.Pattern

@JvmInline
value class EmailAddress(private val value: String) {
    init {
        require(isValid()) {
            "올바르지 않은 이메일 규칙입니다."
        }
    }
    private fun isValid(): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@" +
                "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" +
                "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\." +
                "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" +
                "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|" +
                "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(this.value).matches()
    }

    fun getDomain() = value.substring(getIndex() + 1)

    fun getId() = value.substring(0, getIndex())

    private fun getIndex() = this.value.indexOf('@')
}
