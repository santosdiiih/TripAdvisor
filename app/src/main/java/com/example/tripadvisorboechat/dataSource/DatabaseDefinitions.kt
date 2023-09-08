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
}