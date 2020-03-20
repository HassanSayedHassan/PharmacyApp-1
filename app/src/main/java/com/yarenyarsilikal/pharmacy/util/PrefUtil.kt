package com.yarenyarsilikal.pharmacy.util

import com.orhanobut.hawk.Hawk

class PrefUtil {
    companion object {

        private const val SAVED_FLAG_LIST = "SAVED_CITY_LIST"

        fun setCityList(value: HashMap<String, List<String>>) {
            Hawk.put(SAVED_FLAG_LIST, value)
        }

        fun getCityList(): HashMap<String, List<String>> {
            return Hawk.get(SAVED_FLAG_LIST, HashMap())
        }


    }
}
