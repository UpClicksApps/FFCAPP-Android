package com.upclicks.ffc.data.event

class MessageEvent(code:Int,message:String,details:String) : ErrorEvent(code, message, details) {
}