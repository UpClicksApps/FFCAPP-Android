package com.upclicks.ffc.events

class EventsModel {
    data class MessageEvent(val message: String)
    data class UnAuthorizedEvent(val message: String)
    data class UnSeenNotificationsCountEvent(val count: String)
}