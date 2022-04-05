package com.upclicks.ffc.ui.general.events

class EventsModel {
    data class MessageEvent(val message: String)
    data class UnAuthorizedEvent(val message: String)
    data class UnSeenNotificationsCountEvent(val count: String)
}