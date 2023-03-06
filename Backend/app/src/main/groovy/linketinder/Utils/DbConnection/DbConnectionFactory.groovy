package linketinder.Utils.DbConnection

class DbConnectionFactory {

    static IDbConnectionFactory createConnectionFactory(String type){
        switch (type){
            case "Postgres":
                return PostgresConnectionFactory.getInstance()
            default:
                throw new IllegalAccessException("Configurar outro banco de dados")
        }
    }

}
