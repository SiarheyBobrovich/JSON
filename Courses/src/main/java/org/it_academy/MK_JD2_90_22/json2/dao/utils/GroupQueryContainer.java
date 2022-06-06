package org.it_academy.MK_JD2_90_22.json2.dao.utils;

public class GroupQueryContainer {

    private static final String DB_NAME = "courses.groups";
    private static final String CROSS_DB_NAME = "courses.students_in_groups";

    public static final String INSERT_QUERY =
            "INSERT INTO " +
                DB_NAME + "\n" +
                    "\t(name)\n" +
            "\tVALUES " +
                "(?);\n";

    public static final String SELECT_QUERY =
            " SELECT " +
                "id, name " +
            "FROM " +
                DB_NAME + " " +
            "ORDER BY " +
                "id;\n";

    public static final String SELECT_WHERE_QUERY =
            " SELECT " +
                "id, name " +
            "FROM " +
                DB_NAME + " " +
            "WHERE " +
                "id = ?;\n";

    public static final String UPDATE_QUERY =
            " UPDATE " +
                    DB_NAME + " " +
                    "SET " +
                    "name = ? " +
                    "WHERE " +
                    "id = ?;\n";

    public static final String deleteQuery =
            " DELETE FROM " +
                    DB_NAME + " " +
                    "WHERE " +
                    "id = ?;\n";

    public static final String deleteCrossQuery =
            " DELETE FROM " +
                    CROSS_DB_NAME + " " +
                    "WHERE " +
                    "group_id = ?;\n";
}
