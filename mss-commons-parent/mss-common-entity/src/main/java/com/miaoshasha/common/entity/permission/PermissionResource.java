package com.miaoshasha.common.entity.permission;

import com.miaoshasha.common.base.AbstractBaseEntity;
import lombok.Data;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table base_permission_resource
 *
 * @mbg.generated do_not_delete_during_merge
 */
@Data
public class PermissionResource extends AbstractBaseEntity {

    private Long resId;

    /** 编码*/
    private String resCode;

    /** */
    private String resName;

    /** 权限资源类型，1-菜单，2-页面URL，3-按钮，4-逻辑权限，5-app*/
    private Integer resType;

    /** 父节点*/
    private Long parentId;

    /** 权限图标*/
    private String resIcon;

    /** 入口路径*/
    private String entryPath;

    /** 排序的顺序号*/
    private Integer seqNo;

    /** 状态，0-未启用，1-已启用，2-已停用*/
    private Integer state;

    /** 是否页面展示，针对菜单等需要展示使用*/
    private Byte isShow;

    /** 节点类型，1-目录节点，2-叶子节点*/
    private Integer nodeType;

    /** 备注*/
    private String notes;

    @Override
    public Long getId() {
        return this.resId;
    }
}