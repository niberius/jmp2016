package org.zoltor.db.h2;

import javax.annotation.PostConstruct;

/**
 * Created by Pavel Ordenko on 13/11/2016, 11:55.
 */
public class DataGenerator {

    private DbServer db = new DbServer();

    @PostConstruct
    public void createDables() {
        db.update("CREATE TABLE IF NOT EXISTS personal_info (" +
                "id IDENTITY PRIMARY KEY," +
                "first_name VARCHAR(100) NOT NULL," +
                "last_name VARCHAR(100) NOT NULL," +
                "birth_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                "gender TINYINT NOT NULL," +
                "additional_info VARCHAR(10000)" +
                ");");
        db.update("CREATE TABLE IF NOT EXISTS project (" +
                "id IDENTITY PRIMARY KEY," +
                "name VARCHAR(100) NOT NULL," +
                "CONSTRAINT project_name_u UNIQUE(name)" +
                ");");
        db.update("CREATE TABLE IF NOT EXISTS unit (" +
                "id IDENTITY PRIMARY KEY," +
                "name VARCHAR(100) NOT NULL," +
                "CONSTRAINT unit_name_u UNIQUE(name)" +
                ");");
        db.update("CREATE TABLE IF NOT EXISTS employee (" +
                "id IDENTITY PRIMARY KEY," +
                "login VARCHAR(100) NOT NULL," +
                "password VARCHAR(100) NOT NULL," +
                "email VARCHAR(100) NOT NULL," +
                "country VARCHAR(100) NOT NULL," +
                "city VARCHAR(100) NOT NULL," +
                "zip_code VARCHAR(100) NOT NULL," +
                "address VARCHAR(100) NOT NULL," +
                "employee_status TINYINT NOT NULL," +
                "unit_id BIGINT NULL," +
                "personal_info_id BIGINT NOT NULL," +
                "FOREIGN KEY (unit_id) REFERENCES unit (id) ON DELETE CASCADE," +
                "FOREIGN KEY (personal_info_id) REFERENCES personal_info (id) ON DELETE CASCADE," +
                "CONSTRAINT employee_login_u UNIQUE(login)," +
                "CONSTRAINT employee_email_u UNIQUE(email)" +
                ");");
        db.update("CREATE TABLE IF NOT EXISTS rel_employee_project (" +
                "employee_id BIGINT NOT NULL," +
                "project_id BIGINT NOT NULL," +
                "FOREIGN KEY (employee_id) REFERENCES employee (id) ON DELETE CASCADE," +
                "FOREIGN KEY (project_id) REFERENCES project (id) ON DELETE CASCADE," +
                ");");
    }

}
