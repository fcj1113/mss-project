package com.miaoshasha.common.code;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取表结构信息
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-09-20
 * Time：18:46
 * -----------------------------
 */
public class DBMetaData {
    private Connection connection;
    private DatabaseMetaData databaseMetaData;

    public DBMetaData() {
        connection = DBConnection.getConnection();
        try {
            databaseMetaData = connection.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DatabaseMetaData getMetaData() {
        return this.databaseMetaData;
    }

    /**
     * 获取表结构信息
     * @param tableName
     * @return
     * @throws SQLException
     */
    public List<TableColumn> getTableColumn(String tableName) throws SQLException {
        List<TableColumn> list = new ArrayList<>();

        ResultSet resultSet = databaseMetaData.getColumns(null, "%", tableName, "%");
        while (resultSet.next()) {
            TableColumn tableColumn = new TableColumn();
            String columnName = resultSet.getString("COLUMN_NAME");
            String columnType = resultSet.getString("TYPE_NAME");
            int columnSize = resultSet.getInt("COLUMN_SIZE");
            int digits = resultSet.getInt("DECIMAL_DIGITS");
            int nullable = resultSet.getInt("NULLABLE");
            String remarks = resultSet.getString("REMARKS");
            int dataType = resultSet.getInt("DATA_TYPE");

            tableColumn.setColumnName(columnName);
            tableColumn.setTypeName(columnType);
            tableColumn.setDataType(dataType);
            tableColumn.setRemarks(remarks);
            tableColumn.setNullable(nullable);
            tableColumn.setDecimalDigits(digits);
            tableColumn.setColumnSize(columnSize);
            list.add(tableColumn);
        }

        DBConnection.close(resultSet,connection);
        return list;
    }


    public static void main(String[] args) {
        DBMetaData dbMetaData = new DBMetaData();
        try {
            List<TableColumn> list = dbMetaData.getTableColumn("product_detail");
            for (TableColumn tableColumn : list) {
                System.out.println("------------" + tableColumn.getColumnName() + "-------------"+tableColumn.getTypeName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
