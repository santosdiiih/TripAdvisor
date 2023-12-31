package com.example.tripadvisorboechat.dataBase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tripadvisorboechat.dataSource.DatabaseDefinitions

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_TRAVEL)
        db.execSQL(CREATE_TABLE_USER)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    companion object{
        private const val DATABASE_NAME = "travel"
        private const val DATABASE_VERSION = 4

        private const val CREATE_TABLE_TRAVEL = "CREATE TABLE ${DatabaseDefinitions.Travel.TABLE_NAME}(" +
                "${DatabaseDefinitions.Travel.Columns.ID_TRAVEL} INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "${DatabaseDefinitions.Travel.Columns.DATA_IDA} VARCHAR(20)," +
                "${DatabaseDefinitions.Travel.Columns.DATA_VOLTA} VARCHAR(20)," +
                "${DatabaseDefinitions.Travel.Columns.ORIGEM} VARCHAR(50)," +
                "${DatabaseDefinitions.Travel.Columns.DESTINO} VARCHAR(50)," +
                "${DatabaseDefinitions.Travel.Columns.CLASSIFICACAO} VARCHAR(20)," +
                "${DatabaseDefinitions.Travel.Columns.QTD_PASSAGENS} VARCHAR(10)," +
                "${DatabaseDefinitions.Travel.Columns.TIPO_VIAGEM} VARCHAR(50));"

        private const val CREATE_TABLE_USER = "CREATE TABLE ${DatabaseDefinitions.User.TABLE_NAME}(" +
                "${DatabaseDefinitions.User.Columns.ID_USER} INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "${DatabaseDefinitions.User.Columns.CPF} VARCHAR(20)," +
                "${DatabaseDefinitions.User.Columns.EMAIL} VARCHAR(20)," +
                "${DatabaseDefinitions.User.Columns.NOME} VARCHAR(20)," +
                "${DatabaseDefinitions.User.Columns.PASSWORD} VARCHAR(20));"

    }
}