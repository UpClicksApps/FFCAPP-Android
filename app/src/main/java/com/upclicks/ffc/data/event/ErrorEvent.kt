package com.upclicks.ffc.data.event

open class ErrorEvent {
    var code: Int? = null
    var message: String? = null
    var details: String? = null

    constructor(code: Int?, message: String?, details: String?) {
        this.code = code
        this.message = message
        this.details = details
    }
}