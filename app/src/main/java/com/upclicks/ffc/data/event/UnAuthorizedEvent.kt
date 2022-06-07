package com.upclicks.ffc.data.event

class UnAuthorizedEvent(code:Int,message:String,details:String) : ErrorEvent(code, message, details) {
}