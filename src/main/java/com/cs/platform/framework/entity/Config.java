package com.cs.platform.framework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

/**
 * 配置项管理实体
 */
@Entity
@Table(name = "pub_config")
public class Config extends BaseEntity {

  /**
   * 主键id
   */
  @Id
  @GeneratedValue(generator = "UIDGenerator")
  @GenericGenerator(name = "UIDGenerator", strategy = "com.cs.platform.framework.persistence.AssignedUIDGenerator")
  @Column(length = 40)
  protected String id;

  protected String ckey;
  /**
   * 业务类型
   */
  @Column(length = 1, columnDefinition = "varchar(1) default 1", nullable = false)
  protected String type = "1";

  /**
   * 中文名称
   */
  @Transient
  private String cnName;

  /**
   * 备注信息
   */
  @Column(length = 255)
  @Size(min = 0, max = 255, message = "备注长度必须在255个字符以内")
  private String remarks;

  /**
   * value值
   */
  @Column(length = 400)
  private String cval;

  /**
   * 展示类型:radio,checkbox,textarea,select,text,password,number
   */
  @Transient
  private String showType;

  /**
   * 可用于存储select的option
   */
  @Transient
  private List<Config> childs;

  /**
   * 可用于扩展input和textarea标签，设置其相应属性和值：如class，maxlength等样式
   */
  @Transient
  private Map<String, String> attributes;

  /**
   * 排序
   */
  @Transient
  @JsonIgnore
  private int sort;

  public String getCkey() {
    return ckey;
  }

  public void setCkey(String ckey) {
    this.ckey = ckey;
  }

  /**
   * 分组
   */
  @Transient
  @JsonIgnore
  private String group;

  /**
   * 构造函数
   *
   * @param id      id
   * @param remarks remarks
   * @param type    type
   * @param cval    cval
   */
  public Config(String id, String cval, String remarks) {
    this.id = id;
    this.remarks = remarks;
    this.cval = cval;
  }

  /**
   * @return group
   */
  public String getGroup() {
    return group;
  }

  /**
   * @param group 要设置的 group
   */
  public void setGroup(String group) {
    this.group = group;
  }

  /**
   * @return cnName
   */
  public String getCnName() {
    return cnName;
  }

  /**
   * @param cnName 要设置的 cnName
   */
  public void setCnName(String cnName) {
    this.cnName = cnName;
  }

  /**
   * @return showType
   */
  public String getShowType() {
    return showType;
  }

  /**
   * @param showType 要设置的 showType
   */
  public void setShowType(String showType) {
    this.showType = showType;
  }

  /**
   * 构造函数
   */
  public Config() {
  }

  /**
   * 构造函数
   *
   * @param id id
   */
  public Config(String id) {
    this.id = id;
  }

  /**
   * @return id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id 要设置的 id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return remarks
   */
  public String getRemarks() {
    return remarks;
  }

  /**
   * @param remarks 要设置的 remarks
   */
  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  /**
   * @return cval
   */
  public String getCval() {
    return cval;
  }

  /**
   * @param cval 要设置的 cval
   */
  public void setCval(String cval) {
    this.cval = cval;
  }

  /**
   * @return sort
   */
  public int getSort() {
    return sort;
  }

  /**
   * @param sort 要设置的 sort
   */
  public void setSort(int sort) {
    this.sort = sort;
  }

  /**
   * @return the childs
   */
  public List<Config> getChilds() {
    return childs;
  }

  /**
   * @param childs the childs to set
   */
  public void setChilds(List<Config> childs) {
    this.childs = childs;
  }

  /**
   * @return the attributes
   */
  public Map<String, String> getAttributes() {
    return attributes;
  }

  /**
   * @param attributes the attributes to set
   */
  public void setAttributes(Map<String, String> attributes) {
    this.attributes = attributes;
  }

  /**
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * @param type the type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Config{" +
            "id='" + id + '\'' +
            ", ckey='" + ckey + '\'' +
            ", type='" + type + '\'' +
            ", cnName='" + cnName + '\'' +
            ", remarks='" + remarks + '\'' +
            ", cval='" + cval + '\'' +
            ", showType='" + showType + '\'' +
            ", childs=" + childs +
            ", attributes=" + attributes +
            ", sort=" + sort +
            ", group='" + group + '\'' +
            '}';
  }
}
