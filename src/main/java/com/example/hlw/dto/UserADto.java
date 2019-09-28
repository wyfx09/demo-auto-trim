package com.example.hlw.dto;

import com.example.hlw.annotation.ApiModelProperty;
import lombok.Data;

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
    @ApiModelProperty(name = "姓名2")
    private Integer id;
    @ApiModelProperty(name = "列表")
    private List<UserDto> userList;
    @ApiModelProperty(name = "用户")
    private UserDto user;
}
