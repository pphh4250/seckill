package org.seckill.entity;

import java.util.Date;

import java.util.Date;

/**
 * Created by Admin on 2016/5/16.
 */
public class SecKill {

    private long seckillId;
    private  String name;

    private int number;
    private Date startTime;
    private Date endTime;
    private Date createTime;

    public long getSeckillId() {
        return seckillId;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SecKill{" +
                "seckillId=" + seckillId +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                '}';
    }

    public Date getCreateTime() {
        return createTime;
    }
}
