package es.ieslavereda.proyectoServidor2025_2026.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;

public class MyDataSource {

    private static final String DB_URL =
            "jdbc:mysql://localhost:3307/db_recetarium?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "1234";

    // Método público para obtener la instancia de DataSource
    @Getter
    private static HikariDataSource dataSource;

    static {
        dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(DB_URL);
        dataSource.setUsername(DB_USER);
        dataSource.setPassword(DB_PASS);
        dataSource.setMaximumPoolSize(10);
    }

}

