package com.miaoshasha.common.code;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
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
//        ResultSet tableRet = databaseMetaData.getTables(null, "%", tableName, new String[]{"TABLE"});
//        /*其中"%"就是表示*的意思，也就是任意所有的意思。其中m_TableName就是要获取的数据表的名字，如果想获取所有的表的名字，就可以使用"%"来作为参数了。*/
//
//        //3. 提取表的名字。
//        while (tableRet.next()) {
//            System.out.println(tableRet.getString("TABLE_NAME"));
//        }

        /*通过getString("TABLE_NAME")，就可以获取表的名字了。
        从这里可以看出，前面通过getTables的接口的返回，JDBC是将其所有的结果，保存在一个类似table的内存结构中，而其中TABLE_NAME这个名字的字段就是每个表的名字。*/

        //4. 提取表内的字段的名字和类型

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
            List<TableColumn> list = dbMetaData.getTableColumn("order_info");
            for (TableColumn tableColumn : list) {
                System.out.println("------------" + tableColumn.getColumnName() + "-------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
