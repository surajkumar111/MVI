package com.example.mvi.designPattern

//provide a simple interface of a complex functionality

class ComplexSystemStore(private val filePath: String) {
    private val cache: HashMap<String, String> = HashMap()

    init {
        ///do some work
    }

    fun store(key: String, value: String) {
        cache[key] = value
    }

    fun read(key: String): String? {
        return cache[key]
    }
}

data class User(val login: String)

class UserRepoFacade {
    private val systemPref = ComplexSystemStore("filePath")
    fun save(user: User) {
        systemPref.store("USER_KEY", "USER_LOGIN")
    }

}