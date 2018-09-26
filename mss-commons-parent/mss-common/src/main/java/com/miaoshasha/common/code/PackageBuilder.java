package com.miaoshasha.common.code;

import lombok.Data;

/**
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-09-26
 * Time：14:33
 * -----------------------------
 */
public class PackageBuilder {

    /**
     * 项目第一级路径
     */
    private String projectPath;

    private String entityPackage;
    private String mapperPackage;
    private String mapperXmlPath;
    private String servicePackage;
    private String controllerPackage;

    public String getProjectPath() {
        return projectPath;
    }

    public PackageBuilder setProjectPath(String projectPath) {
        this.projectPath = projectPath;
        return this;
    }

    public String getEntityPackage() {
        return entityPackage;
    }

    public PackageBuilder setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
        return this;
    }

    public String getMapperPackage() {
        return mapperPackage;
    }

    public PackageBuilder setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
        return this;
    }

    public String getMapperXmlPath() {
        return mapperXmlPath;
    }

    public PackageBuilder setMapperXmlPath(String mapperXmlPath) {
        this.mapperXmlPath = mapperXmlPath;
        return this;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public PackageBuilder setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
        return this;
    }

    public String getControllerPackage() {
        return controllerPackage;
    }

    public PackageBuilder setControllerPackage(String controllerPackage) {
        this.controllerPackage = controllerPackage;
        return this;
    }
}
