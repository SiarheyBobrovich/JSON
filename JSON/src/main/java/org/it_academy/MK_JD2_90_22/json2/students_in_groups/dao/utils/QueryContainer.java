package org.it_academy.MK_JD2_90_22.json2.students_in_groups.dao.utils;

public class QueryContainer {

    private static final String DB_SCHEMA_TABLE_NAME = "courses.students_in_groups";

    public static final String INSERT_QUERY =
            "INSERT INTO + " + DB_SCHEMA_TABLE_NAME + " + (group_id, student_id) VALUES (?, ?);";

    public static final String SELECT_ALL_QUERY =
            "SELECT " +
                    "g.id, g.name, s.id, s.name, s.age, s.score, s.olympic_gamer " +
                    "FROM " + DB_SCHEMA_TABLE_NAME + " as sg " +
                    "JOIN courses.groups as g ON sg.group_id = g.id " +
                    "JOIN courses.students as s ON sg.student_id = s.id;";

    public static final String SELECT_BY_GROUP_ID_QUERY =
            "SELECT " +
                    "s.id, s.name, s.age, s.score, s.olympic_gamer " +
                    "FROM  " + DB_SCHEMA_TABLE_NAME + " as sg " +
                    "JOIN courses.students as s ON sg.student_id = s.id " +
                    "WHERE sg.group_id = ?";

    public static final String SELECT_BY_STUDENT_ID_QUERY =
            "SELECT " +
                    "g.id, g.name " +
                    "FROM  " + DB_SCHEMA_TABLE_NAME + " as sg " +
                    "JOIN courses.groups as g ON sg.group_id = g.id " +
                    "WHERE sg.student_id = ?";

    public static final String DELETE_BY_STUDENT_ID_QUERY =
            "DELETE FROM " +  DB_SCHEMA_TABLE_NAME + "  WHERE student_id = ?";

    public static final String DELETE_BY_GROUP_ID_QUERY =
            "DELETE FROM " +  DB_SCHEMA_TABLE_NAME + "  WHERE group_id = ?";


}
