package linketinder.Utils.DbConnection

import groovy.sql.Sql

interface IDbConnectionFactory {

    Sql start()

}