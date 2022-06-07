package com.upclicks.ffc.data.event

class EventsModel {
     class ExternalLoginEvent(val isLoginFailed: Boolean)
     class UnSeenNotificationsCountEvent(val count: String)
}