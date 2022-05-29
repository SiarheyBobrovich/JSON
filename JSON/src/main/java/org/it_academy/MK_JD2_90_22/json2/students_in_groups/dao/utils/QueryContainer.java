package org.it_academy.MK_JD2_90_22.json2.students_in_groups.dao.utils;

public class QueryContainer {

    private static final String DB_SCHEMA_TABLE_NAME = "courses.students_in_groups";

    public static final String INSERT_QUERY =
            "INSERT INTO " + DB_SCHEMA_TABLE_NAME + " (group_id, student_id) VALUES (?, ?);";

    public static final String SELECT_ALL_QUERY =
            "SELECT \n" +
                "\tg.id as group_id,\n" +
                "\tg.name as group_name, \n" +
                "\ts.id as student_id, \n" +
                "\ts.name as student_name, \n" +
                "\ts.age as student_age, \n" +
                "\ts.score as student_score, \n" +
                "\ts.olympic_gamer as student_olympic_gamer\n" +
            "FROM \n" +
              "\tcourses.students_in_groups as sg \n" +
            "JOIN \n" +
                "\tcourses.groups as g \n" +
            "ON \n" +
                "\tsg.group_id = g.id \n" +
            "JOIN \n" +
                "\tcourses.students as s \n" +
            "ON \n" +
                "\tsg.student_id = s.id\n" +
            "ORDER BY \n" +
                "\tg.id, \n" +
                "\ts.id;";

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
