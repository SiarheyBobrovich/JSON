package org.it_academy.MK_JD2_90_22.json2.student.dao.util;

public class StudentsQueryContainer {

    private static final String DB_NAME = "courses.students";

    private static final String CROSS_DB_NAME = "courses.students_in_groups";

    public static final String INSERT_QUERY =
            "INSERT INTO " +
                    DB_NAME + " (" +
                    "name, " +
                    "age, " +
                    "score, " +
                    "olympic_gamer" +
                    ") " +
                    "VALUES " +
                    "(?, ?, ?, ?);"
            ;

    public static final String SELECT_QUERY =
            "SELECT " +
                    "id, " +
                    "name, " +
                    "age, " +
                    "score, " +
                    "olympic_gamer " +
                    "FROM " +
                    DB_NAME + ";"
            ;

    //Сформировать запрос
    public static final String SELECT_WHERE_QUERY =
            "SELECT " +
                    "id, " +
                    "name, " +
                    "age, " +
                    "score, " +
                    "olympic_gamer " +
                    "FROM " +
                    DB_NAME + " " +
                    "WHERE " +
                    "id = ?;"
            ;


    //Сформировать запрос
    public static final String UPDATE_QUERY =
            "UPDATE " +
                    DB_NAME + " " +
                    "SET " +
                    "name = ?, " +
                    "age = ?, " +
                    "score = ?, " +
                    "olympic_gamer = ? " +
                    "WHERE " +
                    "id = ?;"
            ;

    public static final String DELETE_QUERY =
            "DELETE FROM " +
                    DB_NAME + " " +
                    "WHERE " +
                    "id = ?;";

    public static final String DELETE_FROM_CROSS_TABLE =
            "DELETE FROM " + CROSS_DB_NAME + " WHERE student_id = ?;";
}
