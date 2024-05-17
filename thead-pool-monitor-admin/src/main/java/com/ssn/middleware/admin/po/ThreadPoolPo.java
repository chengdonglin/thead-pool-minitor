package com.ssn.middleware.admin.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author linchengdong
 * @Date 2024-05-17 18:10
 * @PackageName:com.ssn.middleware.admin.po
 * @ClassName: ThreadPoolPo
 * @Description: TODO
 * @Version 1.0
 */
@TableName("t_thread_pool")
@Data
public class ThreadPoolPo implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String applicationId;

    private String applicationName;

    private String threadPoolName;

    private Integer corePoolSize;

    private Integer maximumPoolSize;

    private Integer activeCount;

    private Integer poolSize;

    private String queueType;

    private Integer queueSize;

    private Integer remainingCapacity;

    private Date createTime;

    private Date activeTime;

    private Integer down;
}
