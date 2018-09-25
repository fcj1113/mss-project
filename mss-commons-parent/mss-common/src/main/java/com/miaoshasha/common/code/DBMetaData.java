package com.miaoshasha.common.code;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static final String COLUMNLIST = "columnList";
    public static final String PKLIST = "pkList";
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

    public void close(ResultSet resultSet){
        DBConnection.close(resultSet,connection);
    }
    /**
     * 获取表字段结构信息
     * @param tableName
     * @return
     * @throws SQLException
     */
    public Map<String ,List<TableColumn>> getTableColumn(String tableName) throws SQLException {
        Map<String ,List<TableColumn>> map = new HashMap<>();

        ResultSet resultSet = databaseMetaData.getColumns(null, "%", tableName, "%");
        List<TableColumn> list = tableColumn(resultSet);

        map.put(COLUMNLIST,list);
        List<TableColumn> pkList = getTablePk(tableName);
        map.put(PKLIST,pkList);

        close(resultSet);
        return map;
    }

    /**
     * 获取主键信息
     * @param tableName
     * @return
     * @throws SQLException
     */
    private List<TableColumn> getTablePk(String tableName) throws SQLException{

        ResultSet resultSet = databaseMetaData.getPrimaryKeys(null, null, tableName);
        List<TableColumn> list = new ArrayList<>();
        while (resultSet.next()) {
            String columnName = resultSet.getString("COLUMN_NAME");//列名
            short keySeq = resultSet.getShort("KEY_SEQ");//序列号(主键内值1表示第一列的主键，值2代表主键内的第二列)
            String pkName = resultSet.getString("PK_NAME"); //主键名称
//            System.out.println("------------pkName="+pkName+"-------------columnName="+columnName);
            TableColumn tableColumn = new TableColumn();
            tableColumn.setColumnName(columnName);
            list.add(tableColumn);
        }
        return list;
    }


    private List<TableColumn> tableColumn( ResultSet resultSet) throws SQLException{
        List<TableColumn> list = new ArrayList<>();
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
        return list;
    }
}
