package com.qwj.girl.entity.redis;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;


@RedisHash(value = "actresses")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Teacher implements Serializable {

    @Id
    private String id;
    //唯一标识
    @Indexed
    private String code;
    //艺名
    @Indexed
    private String name;
    //生日
    @Indexed
    private String birthday;
    //年龄
    @Indexed
    private Integer age;
    //身高
    @Indexed
    private String height;
    //罩杯
    @Indexed
    private String cup;
    //胸围
    @Indexed
    private String bust;
    //腰围
    @Indexed
    private String waistline;
    //臀围
    @Indexed
    private String hips;
    //爱好
    @Indexed
    private String hobby;
    //入口
    @Indexed
    private String detailLink;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getCup() {
        return cup;
    }

    public void setCup(String cup) {
        this.cup = cup;
    }

    public String getBust() {
        return bust;
    }

    public void setBust(String bust) {
        this.bust = bust;
    }

    public String getWaistline() {
        return waistline;
    }

    public void setWaistline(String waistline) {
        this.waistline = waistline;
    }

    public String getHips() {
        return hips;
    }

    public void setHips(String hips) {
        this.hips = hips;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getDetailLink() {
        return detailLink;
    }

    public void setDetailLink(String detailLink) {
        this.detailLink = detailLink;
    }
}
