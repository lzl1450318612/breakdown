package com.design.background.model;

import java.util.List;

/**
 * @Author： 李紫林
 * @Date： 2019/3/8
 * @Description：
 */
public class StatisticModel {
    private List<Integer> count;
    private List<Integer> status;


    public StatisticModel() {
    }

    public StatisticModel(List<Integer> count, List<Integer> status) {
        this.count = count;
        this.status = status;
    }

    public List<Integer> getCount() {
        return count;
    }

    public void setCount(List<Integer> count) {
        this.count = count;
    }

    public List<Integer> getStatus() {
        return status;
    }

    public void setStatus(List<Integer> status) {
        this.status = status;
    }
}
