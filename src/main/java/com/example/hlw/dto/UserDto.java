package com.example.hlw.dto;

import com.example.hlw.annotation.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by hlw on 2019/8/1.
 */
@Data
public class UserDto {
    @ApiModelProperty(name = "姓名")
    private String name;

    @ApiModelProperty(name = "姓名2")
    private String name2;
    @ApiModelProperty(name = "姓名id")
    private Integer id;

    @ApiModelProperty(name = "小数")
    private BigDecimal price;
    @ApiModelProperty(name ="日期")
    private Date birthDate;

    @ApiModelProperty(name ="是否入学")
    private Boolean isJoinSchool;

    @ApiModelProperty(name ="状态")
    private Byte status;

    @ApiModelProperty(name ="基本的数据类型")
    private int grade;

    @ApiModelProperty(name = "姓名List")
    private List<UserDemoDto>  listUserName;
    @ApiModelProperty(name = "用户")
    private UserADto user;
}
