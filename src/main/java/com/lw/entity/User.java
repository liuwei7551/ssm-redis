package com.lw.entity;

import java.util.Date;

public class User {
  private Integer id;

  private String nickName;

  private Byte gender;

  private String avatar;

  private String note;

  private Byte completeness;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName == null ? null : nickName.trim();
  }

  public Byte getGender() {
    return gender;
  }

  public void setGender(Byte gender) {
    this.gender = gender;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar == null ? null : avatar.trim();
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note == null ? null : note.trim();
  }

  public Byte getCompleteness() {
    return completeness;
  }

  public void setCompleteness(Byte completeness) {
    this.completeness = completeness;
  }

}