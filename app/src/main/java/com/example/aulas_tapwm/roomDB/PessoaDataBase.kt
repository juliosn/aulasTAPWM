package com.example.aulas_tapwm.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Pessoa::class],
    version = 1
)

abstract class PessoaDataBase: RoomDatabase() {
    abstract fun pessoaDao(): PessoaDao
}