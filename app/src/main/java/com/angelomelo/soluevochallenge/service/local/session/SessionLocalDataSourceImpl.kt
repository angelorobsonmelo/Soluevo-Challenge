package com.angelomelo.soluevochallenge.service.local.session

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.angelomelo.soluevochallenge.domain.AuthResponse
import com.angelomelo.soluevochallenge.domain.User
import com.google.gson.Gson


class SessionLocalDataSourceImpl(private val mContext: Context): SessionLocalDataSource {

    private val preferenceShareNameIdentifier = "authResponse"
    private val userIdentifier                = "user"

    override fun saveAuthSession(authResponse: AuthResponse) {
        val sharedPreferences = this.mContext.getSharedPreferences(preferenceShareNameIdentifier, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val gson = Gson()
        val authResponseToJson = gson.toJson(authResponse)

        editor.putString(preferenceShareNameIdentifier, authResponseToJson)
        editor.apply()
    }

    override fun saveUserInSession(user: User) {
        val sharedPreferences = this.mContext.getSharedPreferences(userIdentifier, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val gson = Gson()
        val userToJson = gson.toJson(user)

        editor.putString(userIdentifier, userToJson)
        editor.apply()
    }

    override fun getAuthSession(): AuthResponse {
        val gson = Gson()
        val sharedPreferences = this.mContext.getSharedPreferences(preferenceShareNameIdentifier, MODE_PRIVATE)

        val authResponseToJson = sharedPreferences.getString(preferenceShareNameIdentifier, "")
        return gson.fromJson<AuthResponse>(authResponseToJson, AuthResponse::class.java)
    }

    override fun getUserInSession(): User {
        val gson = Gson()
        val sharedPreferences = this.mContext.getSharedPreferences(userIdentifier, MODE_PRIVATE)

        val userFromJson = sharedPreferences.getString(userIdentifier, "")
        return gson.fromJson<User>(userFromJson, User::class.java)
    }

    override fun destroySession(): Boolean {
        val sharedPreferences = this.mContext.getSharedPreferences(preferenceShareNameIdentifier, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.clear()
        editor.apply()

       val hasValue = sharedPreferences.getString(preferenceShareNameIdentifier, "")
       return hasValue.isEmpty()
    }

    override fun isLogged(): Boolean {
        val sharedPreferences = this.mContext.getSharedPreferences(preferenceShareNameIdentifier, MODE_PRIVATE)

        val hasValue = sharedPreferences.getString(preferenceShareNameIdentifier, "")
        return hasValue.isNotEmpty()
    }

}