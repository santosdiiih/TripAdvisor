package com.example.tripadvisorboechat.dataSource

class DatabaseDefinitions {
    object Travel{
        const val TABLE_NAME = "tblTravel"
        object Columns {
            const val ID_TRAVEL = "idTravel"
            const val ORIGEM = "origin"
            const val DESTINO = "destiny"
            const val CLASSIFICACAO = "classification"
            const val DATA_IDA = "dateGo"
            const val DATA_VOLTA = "dateBack"
            const val QTD_PASSAGENS = "amounttickets"
            const val TIPO_VIAGEM = "typeTrip"
        }
    }

    object User{
        const val TABLE_NAME = "tblUser"
        object Columns {
            const val ID_USER = "idUser"
            const val NOME = "nome"
            const val EMAIL = "email"
            const val CPF = "cpf"
            const val PASSWORD = "password"
        }
    }
}