package com.upclicks.ffc.data.remote

import android.util.Log
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Manager
import com.github.nkzawa.socketio.client.Socket
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.socket.api.SocketController
import com.upclicks.ffc.ui.chat.data.model.MediaFile
import com.upclicks.ffc.ui.chat.data.model.Message
import com.upclicks.ffc.ui.chat.data.model.SocketMessageRemovedResponse
import com.upclicks.ffc.ui.chat.data.model.SocketMessageResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Type

class Result<T> {
    val result: T? = null
}

