package com.example.labequpment.data

object MockDb {
    val list = listOf(
        Equipment(
            id = 1,
            name = "ДАГ-500",
            factoryNumber = "2B110A24",
            dateOfLastVerification = 1719881150119L,
            verificationPeriodInMonth = 12
        ),
        Equipment(
            id = 2,
            name = "Спектрофотометр",
            factoryNumber = "2B110A24",
            dateOfLastVerification = 1697739150119L,
            verificationPeriodInMonth = 12
        ),
        Equipment(
            id = 3,
            name = "Метеоскоп",
            factoryNumber = "2B110A24",
            dateOfLastVerification = 1709881150119L,
            verificationPeriodInMonth = 12
        ),
        Equipment(
            id = 4,
            name = "ДАГ-500",
            factoryNumber = "2B110A24",
            dateOfLastVerification = 1719881150119L,
            verificationPeriodInMonth = 12
        ),
        Equipment(
            id = 5,
            name = "Спектрофотометр",
            factoryNumber = "2B110A24",
            dateOfLastVerification = 1697739150119L,
            verificationPeriodInMonth = 12
        ),
        Equipment(
            id = 6,
            name = "Метеоскоп",
            factoryNumber = "2B110A24",
            dateOfLastVerification = 1709881150119L,
            verificationPeriodInMonth = 12
        ),
        Equipment(
            id = 7,
            name = "ДАГ-500",
            factoryNumber = "2B110A24",
            dateOfLastVerification = 1719881150119L,
            verificationPeriodInMonth = 12
        ),
        Equipment(
            id = 8,
            name = "Спектрофотометр",
            factoryNumber = "2B110A24",
            dateOfLastVerification = 1697739150119L,
            verificationPeriodInMonth = 12
        ),
        Equipment(
            id = 9,
            name = "Метеоскоп",
            factoryNumber = "2B110A24",
            dateOfLastVerification = 1709881150119L,
            verificationPeriodInMonth = 12
        )
    )
}

object MutableDB {
    val db = mutableListOf<Equipment>()
}