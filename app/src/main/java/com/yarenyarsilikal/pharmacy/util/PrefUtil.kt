package com.yarenyarsilikal.pharmacy.util

import com.orhanobut.hawk.Hawk
import com.yarenyarsilikal.pharmacy.network.model.User

class PrefUtil {
    companion object {

        private const val SAVED_FLAG_LIST = "SAVED_CITY_LIST"
        private const val SAVED_USER = "SAVED_USER"
        private const val SAVED_FIRST = "SAVED_FIRST"



        fun setCityList(value: HashMap<String, List<String>>) {
            Hawk.put(SAVED_FLAG_LIST, value)
        }

        fun getCityList(): HashMap<String, List<String>> {
            return Hawk.get(SAVED_FLAG_LIST, HashMap())
        }

        fun setUser(value: User) {
            Hawk.put(SAVED_USER, value)
        }

        fun getUser(): User {
            return Hawk.get(SAVED_USER, User())
        }

        fun setFirstTime(value: Boolean) {
            Hawk.put(SAVED_FIRST, value)
        }

        fun isFirstTime(): Boolean {
            return Hawk.get(SAVED_FIRST, true)
        }
    }
}
