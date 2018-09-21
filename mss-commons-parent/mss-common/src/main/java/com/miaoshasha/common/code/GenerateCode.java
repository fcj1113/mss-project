package com.miaoshasha.common.code;

import cn.hutool.core.util.StrUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 代码生成器
 * 1.生成mapper.xml
 * 2.生成mapper接口
 * 3.生成service接口
 * 4.生成service接口实现类
 * 5.生成基本的controller
 * 6.生成entity类
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-09-20
 * Time：21:57
 * -----------------------------
 */
public class GenerateCode {


    /**
     * 基础路径
     */
    private final static String basePath = "";

    private DBMetaData dbMetaData ;
    public GenerateCode(){
        dbMetaData = new DBMetaData();
    }

    /**
     * 把内容写到文件中
     * @param file
     * @param content
     * @throws IOException
     */
    public void writeFile(File file, String content) throws IOException {
        if(!file.exists()){
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(content);
        bufferedWriter.flush();
        bufferedWriter.close();

    }


    /**
     * 生成实体类
     * @param packageName
     * @param tableName
     */
    public void createEntity(String packageName,String tableName){

        try {
            List<TableColumn> list = dbMetaData.getTableColumn(tableName);
            String className = StrUtil.upperFirst(StrUtil.toCamelCase(tableName));

            StringBuffer sb = new StringBuffer();
            sb.append("package "+packageName+";\n");
            sb.append("\n");
            sb.append("import com.miaoshasha.common.base.AbstractBaseEntity;\n");
            sb.append("import lombok.Data;\n");
            sb.append("\n");
            sb.append("@Data\n");
            sb.append("public class "+className+" extends AbstractBaseEntity {\n");
            sb.append("\n");
            for (TableColumn tableColumn: list){
                String columnName = tableColumn.getColumnName();
                String typeName = tableColumn.getTypeName();
                String remarks = tableColumn.getRemarks();
                SqlTypeEnum sqlTypeEnum = SqlTypeEnum.valueOf(typeName);
                if(!StrUtil.isEmpty(remarks)){
                    sb.append("\t\t/**"+remarks+"*/ \n");
                }
                sb.append("\t\tprivate "+sqlTypeEnum.getLabel()+" "+StrUtil.toCamelCase(columnName)+" ;\n");
            }
            sb.append("}\n");

            System.out.println(sb.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        GenerateCode generateCode = new GenerateCode();
        generateCode.createEntity("com.miaoshasha.common.entity","product_detail");

    }

}
