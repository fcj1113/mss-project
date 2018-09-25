package com.miaoshasha.common.code;

import com.miaoshasha.common.base.BaseEnum;

import java.sql.Types;

/**
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-09-21
 * Time：18:20
 * -----------------------------
 */
public enum  SqlTypeEnum implements BaseEnum<String> {
    CHAR("CHAR","String"),
    VARCHAR("VARCHAR","String"),
    BLOB("BLOB","byte[]"),
    CLOB("CLOB","byte[]"),
    TEXT("TEXT","String"),
    INT("INT","Integer"),
    INTEGER("INTEGER","Integer"),
    BIGINT("BIGINT","Long"),
    TINYINT("TINYINT","Boolean"),
    SMALLINT("SMALLINT","Integer"),
    MEDIUMINT("MEDIUMINT","Integer"),
    BIT("BIT","Boolean"),
    FLOAT("FLOAT","Float"),
    DOUBLE("DOUBLE","Double"),
    DECIMAL("DECIMAL","BigDecimal"),
    DATE("DATE","Date"),
    TIME("TIME","Time"),
    TIMESTAMP("TIMESTAMP","Timestamp"),
    DATETIME("BINARY","Timestamp"),
    YEAR("YEAR","Date")
    ;

    private String dataType;
    private String javaType;

    SqlTypeEnum(String dataType, String javaType) {
        this.dataType = dataType;
        this.javaType = javaType;
    }

    @Override
    public String getValue() {
        return dataType;
    }

    @Override
    public String getLabel() {
        return javaType;
    }
}
