package com.miaoshasha.common.code;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.dvcs.DVCSCertInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 * 1.生成mapper.xml
 * 2.生成mapper接口
 * 3.生成service接口
 * 4.生成service接口实现类
 * 5.生成基本的controller
 * 6.生成entity类
 *
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-09-20
 * Time：21:57
 * -----------------------------
 */
@Slf4j
public class GenerateCode {


    /**
     * 项目第一级路径
     */
    private String projectPath;
    public static final String JAVA_EXT = ".java";
    public static final String COMMON_BASE = "com.miaoshasha.common.base";

    private String entityPackage;
    private String mapperPackage;
    private String mapperXmlPath;
    private String servicePackage;

    /**
     * @param projectPath    项目第一级路径
     * @param entityPackage  实体类包名
     * @param mapperPackage  mapper接口类包名
     * @param mapperXmlPath  mapperXML路径
     * @param servicePackage service接口类包名
     */
    public GenerateCode(String projectPath,String mapperXmlPath, String entityPackage, String mapperPackage,  String servicePackage) {

        this.servicePackage = servicePackage;
        this.projectPath = projectPath;
        this.entityPackage = entityPackage;
        this.mapperPackage = mapperPackage;
        this.mapperXmlPath = mapperXmlPath;

    }


    /**
     * 把内容写到文件中
     *
     * @param file
     * @param content
     * @throws IOException
     */
    public void writeFile(File file, String content) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(content);
        bufferedWriter.flush();
        bufferedWriter.close();
        fileWriter.close();
        log.info(file.getName()+" create success");
    }

    /**
     * 创建java类
     *
     * @param javaSourcePath
     * @param packageName
     * @param fileName
     * @param content
     * @throws IOException
     */
    public void createJavaFile(String javaSourcePath, String packageName, String fileName, String content) throws IOException {
        String filePath = projectPath + javaSourcePath + StrUtil.replace(packageName, ".", "\\") + "\\";
        File file = new File(filePath + fileName + JAVA_EXT);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        writeFile(file, content);
    }

    /**
     * @param tableName
     * @return
     */
    public String className(String tableName) {
        return StrUtil.upperFirst(StrUtil.toCamelCase(tableName));
    }

    public void classComment(StringBuffer sb) {
        String dateStr = DateUtil.formatDate(new Date());
        String timeStr = DateUtil.formatTime(new Date());
        sb.append("\n/**                            ");
        sb.append("\n *                             ");
        sb.append("\n * @author fengchaojun <br/>   ");
        sb.append("\n * ----------------------------");
        sb.append("\n * Created with IDEA.          ");
        sb.append("\n * Date：" + dateStr + "            ");
        sb.append("\n * Time：" + timeStr + "                 ");
        sb.append("\n * ----------------------------");
        sb.append("\n */                            \n");
    }

    /**
     * 生成实体类
     *
     * @param javaSourcePath
     * @param tableName
     */
    public void createEntity(String javaSourcePath, String tableName) {

        DBMetaData dbMetaData = new DBMetaData();
        try {
            Map<String, List<TableColumn>> map = dbMetaData.getTableColumn(tableName);
            //表字段信息
            List<TableColumn> list = map.get(DBMetaData.COLUMNLIST);
            String className = className(tableName);

            StringBuffer sb = new StringBuffer();
            sb.append("package " + entityPackage + ";\n");
            sb.append("\n");
            sb.append("import " + COMMON_BASE + ".AbstractBaseEntity;\n");
            sb.append("import lombok.Data;\n");
            sb.append("\n");
            classComment(sb);
            sb.append("@Data\n");
            sb.append("public class " + className + " extends AbstractBaseEntity {\n");
            sb.append("\n");
            for (TableColumn tableColumn : list) {
                String columnName = tableColumn.getColumnName();
                String typeName = tableColumn.getTypeName();
                String remarks = tableColumn.getRemarks();
//                System.out.println("------------typeName=" + typeName + "-------------");
                SqlTypeEnum sqlTypeEnum = SqlTypeEnum.valueOf(typeName);
                if (!StrUtil.isEmpty(remarks)) {
                    sb.append("\t\t/**" + remarks + "*/ \n");
                }
                sb.append("\t\tprivate " + sqlTypeEnum.getLabel() + " " + StrUtil.toCamelCase(columnName) + " ;\n\n");
            }


            //主键信息
            List<TableColumn> pkList = map.get(DBMetaData.PKLIST);

            if (pkList != null && pkList.size() > 0) {
                TableColumn tableColumn = pkList.get(0);
                String columnName = tableColumn.getColumnName();
                sb.append("\t\t@Override\n" +
                        "\t\tpublic Long getId() {\n" +
                        " \t\t\treturn this." + StrUtil.toCamelCase(columnName) + ";\n" +
                        "\t\t}\n\n");
            }

            sb.append("}\n");
            try {
                createJavaFile(javaSourcePath, entityPackage, className, sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 创建mapper接口
     */
    public void createMapper(String javaSourcePath, String tableName) {
        String className = className(tableName);

        StringBuffer sb = new StringBuffer();
        sb.append("package " + mapperPackage + "\n");

        sb.append("import " + COMMON_BASE + ".BaseMapper;\n");
        sb.append("import " + entityPackage + "." + className + ";\n");
        classComment(sb);
        sb.append("public interface " + className + "Mapper extends BaseMapper<" + className + "> {\n");

        sb.append("}\n");
        try {
            createJavaFile(javaSourcePath, mapperPackage, className, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建XML
     */
    public void createMapperXml(String tableName) {
        try {
            DBMetaData dbMetaData = new DBMetaData();

            Map<String, List<TableColumn>> map = dbMetaData.getTableColumn(tableName);
            //表字段信息
            List<TableColumn> columnList = map.get(DBMetaData.COLUMNLIST);
            //主键信息
            List<TableColumn> pkList = map.get(DBMetaData.PKLIST);

            String columnStr = "";
            String fieldStr = "";
            String columnUpdateSql = "";
            String columnUpdateSql2 = "";
            String columnInsertSql = "";
            String columnInsertValuesSql = "";

            for (TableColumn tableColumn : columnList) {
                String columnName = tableColumn.getColumnName();
                String fieldName = StrUtil.toCamelCase(columnName);
                columnStr += columnName + ",";
                fieldStr += "#{" + fieldName + "},";

                columnUpdateSql += "\t\t<if test=\"" + fieldName + " != null\"> " + columnName + "=#{" + fieldName + "}, </if> \n";
                columnUpdateSql2 += columnName + "=#{" + fieldName + "},";
                columnInsertSql += "\t\t\t<if test=\"" + fieldName + " != null\"> " + columnName + ", </if> \n";
                columnInsertValuesSql += "\t\t\t<if test=\"" + fieldName + " != null\"> #{" + fieldName + "}, </if> \n";
            }
            columnStr = columnStr.substring(0, columnStr.length() - 1);
            fieldStr = fieldStr.substring(0, fieldStr.length() - 1);
            columnUpdateSql2 = columnUpdateSql2.substring(0, columnUpdateSql2.length() - 1);
            //主键信息
            String pkColumnName = "";
            String pkFieldName = "";
            if (pkList != null && pkList.size() > 0) {
                TableColumn tableColumn = pkList.get(0);
                pkColumnName = tableColumn.getColumnName();
                pkFieldName = StrUtil.toCamelCase(pkColumnName);
            }

            String wherePKSql = " where " + pkColumnName + " = #{" + pkFieldName + "}\n";
            String className = className(tableName);
            String absoluteEntityClass = entityPackage + "." + className;

            StringBuffer sb = new StringBuffer();

            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n");
            sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\"> \n");
            sb.append("<mapper namespace=\"" + mapperPackage + "." + className + "Mapper\"> \n");

            sb.append("\t<sql id=\"Base_Column_List\"> \n \t\t\t" + columnStr + "\n\t</sql> \n\n");

            //根据主键查询
            sb.append("\t<select id=\"selectByPrimaryKey\" parameterType=\"java.lang.Integer\" resultType=\"" + absoluteEntityClass + "\">\n");
            sb.append("\t\tselect \n");
            sb.append("\t\t<include refid=\"Base_Column_List\" />\n");
            sb.append("\t\tfrom " + tableName + " " + wherePKSql + "\n");
            sb.append("\t</select>\n\n");

            //delete
            sb.append("\t<delete id=\"deleteByPrimaryKey\" parameterType=\"java.lang.Integer\"> \n");
            sb.append("\t\tdelete from " + tableName + wherePKSql + "\n");
            sb.append("\t</delete> \n\n");

            //INSERT
            sb.append("\t<insert id=\"insert\" parameterType=\"" + absoluteEntityClass + "\" keyProperty=\"" + pkFieldName + "\" useGeneratedKeys=\"true\">\n");
            sb.append("\t\t  insert into " + tableName + " ( " + columnStr + ")values(" + fieldStr + ")\n ");
            sb.append("\t</insert>\n\n");

            //insertSelective
            sb.append("\t<insert id=\"insertSelective\" parameterType=\"" + absoluteEntityClass + "\"> \n ");
            sb.append("\t\tinsert into " + tableName + " \n");
            sb.append("\t\t<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\"> \n");
            sb.append(columnInsertSql);
            sb.append("\t\t</trim>\n");
            sb.append("\t\t<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\"> \n");
            sb.append(columnInsertValuesSql);
            sb.append("\n\t\t</trim>\n");
            sb.append("\t</insert> \n\n");

            //updateByPrimaryKeySelective
            sb.append("\t<update id=\"updateByPrimaryKeySelective\" parameterType=\"" + absoluteEntityClass + "\">  ");
            sb.append("\t\tupdate " + tableName + "\n");
            sb.append("\t\t<set>\n");
            sb.append("\t\t" + columnUpdateSql);
            sb.append("\t\t</set> \n");
            sb.append("\t\t" + wherePKSql);
            sb.append("\t</update>\n\n");

            //updateByPrimaryKey
            sb.append("\t<update id=\"updateByPrimaryKey\" parameterType=\"" + absoluteEntityClass + "\">\n");
            sb.append("\t\tupdate " + tableName + "\n");
            sb.append("\t\tset\n");
            sb.append("\t\t" + columnUpdateSql2 + "\n");
            sb.append("\t\t" + wherePKSql);
            sb.append("\t</update>\n");
            sb.append("</mapper>\n");

            //System.out.println(sb.toString());

            String filePath = projectPath + mapperXmlPath + "\\";
            File file = new File(filePath + className + ".xml");
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            try {
                writeFile(file, sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建service接口
     */
    public void createService(String javaSourcePath, String tableName) {
        String className = className(tableName);

        StringBuffer sb = new StringBuffer();
        sb.append("package " + servicePackage + "\n");

        sb.append("import " + COMMON_BASE + ".BaseService;\n");
        sb.append("import " + entityPackage + "." + className + ";\n");
        classComment(sb);
        sb.append("public interface " + className + "Service extends BaseService<" + className + "> {\n");

        sb.append("}\n");
        try {
            createJavaFile(javaSourcePath, servicePackage, className+"Service", sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        createServiceImpl(javaSourcePath, tableName);
    }

    /**
     * 创建service实现类
     *
     * @param javaSourcePath
     * @param tableName
     */
    public void createServiceImpl(String javaSourcePath, String tableName) {
        String className = className(tableName);

        StringBuffer sb = new StringBuffer();
        sb.append("package " + servicePackage + ".impl\n");

        sb.append("import " + COMMON_BASE + ".AbstractBaseService;\n");
        sb.append("import " + servicePackage + "." + className + "Service;\n");
        sb.append("import " + entityPackage + "." + className + ";\n");
        classComment(sb);
        sb.append("\n@Slf4j");
        sb.append("@Service");
        sb.append("public class " + className + "ServiceImpl extends AbstractBaseService<" + className + "Mapper," + className + "> implements " + className + "Service {\n");
        sb.append("}\n");
        try {
            createJavaFile(javaSourcePath, servicePackage + ".impl", className+"ServiceImpl", sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createController() {

    }

    public static void main(String[] args) {
        GenerateCode generateCode = new GenerateCode("D:\\temp\\mss-project\\",
                "mss-services-parent\\mss-base-service\\src\\main\\resources\\mapper\\",
                "com.miaoshasha.common.entity.member",
                "com.miaoshasha.base.mapper.member",
                "com.miaoshasha.base.service");
        generateCode.createEntity("mss-commons-parent\\mss-common-entity\\src\\main\\java\\", "base_member");

        generateCode.createMapperXml("base_member");

        generateCode.createMapper("mss-services-parent\\mss-base-service\\src\\main\\java\\", "base_member");
        generateCode.createService("mss-services-parent\\mss-base-service\\src\\main\\java\\", "base_member");

    }

}
