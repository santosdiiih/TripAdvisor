package com.example.tripadvisorboechat.repository

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.tripadvisorboechat.dataBase.DataBaseHelper
import com.example.tripadvisorboechat.dataSource.DatabaseDefinitions
import com.example.tripadvisorboechat.model.Travel

class TravelRepository(context: Context) {

    private val dbHelper = DataBaseHelper(context);

    fun saveTravel(travel: Travel) : Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues()

        values.put(DatabaseDefinitions.Travel.Columns.DATA_IDA, travel.dateGo)
        values.put(DatabaseDefinitions.Travel.Columns.DATA_VOLTA, travel.dateBack)
        values.put(DatabaseDefinitions.Travel.Columns.DESTINO, travel.destiny)
        values.put(DatabaseDefinitions.Travel.Columns.CLASSIFICACAO, travel.classification)
        values.put(DatabaseDefinitions.Travel.Columns.QTD_PASSAGENS, travel.amounttickets)
        values.put(DatabaseDefinitions.Travel.Columns.ORIGEM, travel.origin)
        values.put(DatabaseDefinitions.Travel.Columns.TIPO_VIAGEM, travel.typeTrip)

        val id = db.insert(DatabaseDefinitions.Travel.TABLE_NAME, null,values)
        return id.toInt()
    }

    @SuppressLint("Range")
    fun getTravels():ArrayList<Travel>{
        val db = dbHelper.readableDatabase
        val projection = arrayOf(DatabaseDefinitions.Travel.Columns.ID_TRAVEL,
        DatabaseDefinitions.Travel.Columns.ORIGEM,
        DatabaseDefinitions.Travel.Columns.DESTINO,
        DatabaseDefinitions.Travel.Columns.DATA_IDA,
        DatabaseDefinitions.Travel.Columns.DATA_VOLTA,
        DatabaseDefinitions.Travel.Columns.TIPO_VIAGEM,
        DatabaseDefinitions.Travel.Columns.CLASSIFICACAO,
        DatabaseDefinitions.Travel.Columns.QTD_PASSAGENS)

        val cursor = db.query(
            DatabaseDefinitions.Travel.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        var travels = ArrayList<Travel>()

        if(cursor != null){
            while (cursor.moveToNext()){
                var travel = Travel(
                    idTravel = cursor.getInt(cursor.getColumnIndex(DatabaseDefinitions.Travel.Columns.ID_TRAVEL)),
                    origin = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Travel.Columns.ORIGEM)),
                    destiny = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Travel.Columns.DESTINO)),
                    classification = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Travel.Columns.CLASSIFICACAO)),
                    dateGo = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Travel.Columns.DATA_IDA)),
                    dateBack = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Travel.Columns.DATA_VOLTA)),
                    amounttickets = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Travel.Columns.QTD_PASSAGENS)),
                    typeTrip = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Travel.Columns.TIPO_VIAGEM))
                )
                travels.add(travel)
            }
        }
        return travels
    }

    @SuppressLint("Range")
    fun getTravel(id: Int): Travel{
        val db = dbHelper.readableDatabase
        val projection = arrayOf(DatabaseDefinitions.Travel.Columns.ID_TRAVEL,
            DatabaseDefinitions.Travel.Columns.ORIGEM,
            DatabaseDefinitions.Travel.Columns.DESTINO,
            DatabaseDefinitions.Travel.Columns.DATA_IDA,
            DatabaseDefinitions.Travel.Columns.DATA_VOLTA,
            DatabaseDefinitions.Travel.Columns.TIPO_VIAGEM,
            DatabaseDefinitions.Travel.Columns.CLASSIFICACAO,
            DatabaseDefinitions.Travel.Columns.QTD_PASSAGENS)

        val selection = "${DatabaseDefinitions.Travel.Columns.ID_TRAVEL} = ?"
        val selectionArgs = arrayOf(id.toString())

        val cursor = db.query(
            DatabaseDefinitions.Travel.TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        var travel = Travel ()
        
        if(cursor != null){
            cursor.moveToNext()
            travel.idTravel = cursor.getInt(cursor.getColumnIndex(DatabaseDefinitions.Travel.Columns.ID_TRAVEL))
            travel.origin = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Travel.Columns.ORIGEM))
            travel.destiny = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Travel.Columns.DESTINO))
            travel.classification = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Travel.Columns.CLASSIFICACAO))
            travel.dateGo = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Travel.Columns.DATA_IDA))
            travel.dateBack = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Travel.Columns.DATA_VOLTA))
            travel.amounttickets = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Travel.Columns.QTD_PASSAGENS))
            travel.typeTrip = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Travel.Columns.TIPO_VIAGEM))
        }
        else{
            //Toast.makeText(, "", Toast.LENGTH_SHORT).show()
            Log.d("chamado", "chamado não encontrado")
        }
        return travel
    }
    fun updateTravel(travel : Travel) {

    }
}