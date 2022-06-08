package com.upclicks.ffc.socket.api

import android.content.Context
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Ack
import com.github.nkzawa.socketio.client.Socket
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.upclicks.ffc.socket.models.BaseSocketResponseModel
import org.json.JSONObject

class SocketController constructor(context: Context) {
    companion object {
        fun <T : Any> request(
            socket: Socket,
            event: String,
            jsonOb: JSONObject,
            onResult: (T) -> Unit
        ) {
            var model: BaseSocketResponseModel<T> = BaseSocketResponseModel<T>()
            socket.emit(event, jsonOb, Ack { response ->
                val collectionType = object : TypeToken<BaseSocketResponseModel<T>?>() {}.type
                val gson = Gson()
                model = gson.fromJson(
                    (response[0] as JSONObject).toString(), collectionType
                )
                onResult(model.result)
            })
        }

        fun <T : Any> receive(
            socket: Socket,
            event: String,
            onResult: (T) -> Unit
        ) {
            var model: BaseSocketResponseModel<T> = BaseSocketResponseModel<T>()
            socket.on(event, Emitter.Listener { response ->
                val collectionType = object : TypeToken<BaseSocketResponseModel<T>?>() {}.type
                val gson = Gson()
                model = gson.fromJson(
                    (response[0] as JSONObject).toString(), collectionType
                )
                onResult(model.result)
            })
        }
    }
}