package com.example.tripadvisorboechat.repository

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteException
import android.util.Log
import com.example.tripadvisorboechat.dataBase.DataBaseHelper
import com.example.tripadvisorboechat.dataSource.DatabaseDefinitions
import com.example.tripadvisorboechat.model.User


class UserRepository(context: Context) {
    private val dbHelper = DataBaseHelper(context)

    fun saveUser(user: User) : Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues()

        values.put(DatabaseDefinitions.User.Columns.CPF, user.cpf )
        values.put(DatabaseDefinitions.User.Columns.EMAIL, user.email)
        values.put(DatabaseDefinitions.User.Columns.NOME, user.name)
        values.put(DatabaseDefinitions.User.Columns.PASSWORD, user.password)

        val id = db.insert(DatabaseDefinitions.User.TABLE_NAME, null,values)
        return id.toInt()
    }

    @SuppressLint("Range")
    fun login(email: String, senha: String): User? {
        if (email.isBlank() || senha.isBlank()) {
            Log.d("Cadastro", "Email e senha inválidos")
            return null
        }

        try {
            val db = dbHelper.readableDatabase
            val selection = "${DatabaseDefinitions.User.Columns.EMAIL} = ? and ${DatabaseDefinitions.User.Columns.PASSWORD} = ?"
            val selectionArgs = arrayOf(email, senha)
            val cursor = db.query(DatabaseDefinitions.User.TABLE_NAME, null, selection, selectionArgs, null, null, null)

            if (cursor != null && cursor.moveToNext()) {
                val user = User()
                user.idUser = cursor.getInt(cursor.getColumnIndex(DatabaseDefinitions.User.Columns.ID_USER))
                user.cpf = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.User.Columns.CPF))
                user.name = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.User.Columns.NOME))
                user.email = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.User.Columns.EMAIL))
                user.password = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.User.Columns.PASSWORD))
                return user
            } else {
                Log.d("Cadastro", "Usuário Não Encontrado")
            }
        } catch (e: SQLiteException) {
            // Lide com exceções do SQLite aqui, se necessário
            Log.e("Cadastro", "Erro ao executar consulta no banco de dados", e)
        }

        return null
    }


}