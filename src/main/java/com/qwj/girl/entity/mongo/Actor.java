package com.qwj.girl.entity.mongo;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;


@Document(value = "actresses")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Actor implements Serializable {

    @Id
    private String id;
    //唯一标识
    @Field(value = "code")
    private String code;
    //艺名
    @Field(value = "name")
    private String name;
    //生日
    @Field(value = "birthday")
    private String birthday;
    //年龄
    @Field(value = "age")
    private Integer age;
    //身高
    @Field(value = "height")
    private String height;
    //罩杯
    @Field(value = "cup")
    private String cup;
    //胸围
    @Field(value = "bust")
    private String bust;
    //腰围
    @Field(value = "waistline")
    private String waistline;
    //臀围
    @Field(value = "hips")
    private String hips;
    //爱好
    @Field(value = "hobby")
    private String hobby;
    //入口
    @Field(value = "detailLink")
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
