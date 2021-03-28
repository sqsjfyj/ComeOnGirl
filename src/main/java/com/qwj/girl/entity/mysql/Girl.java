package com.qwj.girl.entity.mysql;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;

@Entity
@Table(name = "actresses")
public class Girl {

    @Id
    @GeneratedValue(generator = "myid")
    @GenericGenerator(name = "myid", strategy = "uuid")
    private String id;
    //唯一标识
    @Column(name = "code", nullable = false)
    private String code;
    //艺名
    @Column(name = "name", nullable = false)
    private String name;
    //生日
    @Column(name = "birthday", nullable = true)
    private String birthday;
    //年龄
    @Column(name = "age", nullable = true)
    private Integer age;
    //身高
    @Column(name = "height", nullable = true)
    private String height;
    //罩杯
    @Column(name = "cup", nullable = true)
    private String cup;
    //胸围
    @Column(name = "bust", nullable = true)
    private String bust;
    //腰围
    @Column(name = "waistline", nullable = true)
    private String waistline;
    //臀围
    @Column(name = "hips", nullable = true)
    private String hips;
    //爱好
    @Column(name = "hobby", nullable = true)
    private String hobby;
    //入口
    @Column(name = "detailLink", nullable = false)
    private String detailLink;

    public String getDetailLink() {
        return detailLink;
    }

    public void setDetailLink(String detailLink) {
        this.detailLink = detailLink;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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
}
