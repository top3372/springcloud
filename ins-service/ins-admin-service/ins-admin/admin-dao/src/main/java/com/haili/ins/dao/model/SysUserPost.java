package com.haili.ins.dao.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

@Table(name = "`sys_user_post`")
@Data
public class SysUserPost implements Serializable {
    /**
     * 用户ID
     * <p>
     * Table:     sys_user_post
     * Column:    user_id
     * Nullable:  false
     */
    @Id
    @Column(name = "`user_id`")
    private Long userId;

    /**
     * 岗位ID
     * <p>
     * Table:     sys_user_post
     * Column:    post_id
     * Nullable:  false
     */
    @Id
    @Column(name = "`post_id`")
    private Long postId;

    private static final long serialVersionUID = 1L;
}