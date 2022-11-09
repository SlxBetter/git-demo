package com.sunlx.serviceedu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunlx.commonutils.R;
import com.sunlx.servicebase.exception.SunlxException;
import com.sunlx.serviceedu.entity.EduTeacher;
import com.sunlx.serviceedu.entity.vo.query.TeacherQuery;
import com.sunlx.serviceedu.service.EduteacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author sunlx
 * @since 2022-07-27
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/admin/edu/teacher")
//跨域
@CrossOrigin
public class EduTeacherController {
    @Autowired
    private EduteacherService eduteacherService;

    @ApiOperation("所有讲师列表")
    @GetMapping
    public  R list(){
        List<EduTeacher> list = eduteacherService.list(null);

        return R.ok().data("items",list);
    }

    @ApiOperation("分页讲师列表")
    @GetMapping("/{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page",value = "当前页码" ,required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit",value = "每页记录数" ,required = true)
            @PathVariable Long limit,

            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
             TeacherQuery teacherQuery

            ){
        Page<EduTeacher> pageParam=new Page<>(page,limit);

        eduteacherService.pageQuery(pageParam , teacherQuery );

        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return R.ok().data("total",total).data("rows",records);
    }
    @ApiOperation("分页讲师列表")
    @PostMapping("/{page}/{limit}")
    public R postPageList(
            @ApiParam(name = "page",value = "当前页码" ,required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit",value = "每页记录数" ,required = true)
            @PathVariable Long limit,

            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
             @RequestBody       TeacherQuery teacherQuery

    ){
        Page<EduTeacher> pageParam=new Page<>(page,limit);

        eduteacherService.pageQuery(pageParam , teacherQuery );

        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return R.ok().data("total",total).data("rows",records);
    }


    /**
     * localhost:8001/serviceedu/edu-teacher/1
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public R removeById(
                @ApiParam(name = "id",value = "讲师id",required = false)
                @PathVariable
                String id
    ){
        eduteacherService.removeById(id);
        return  R.ok();
    }

    @ApiOperation("新增讲师")
    @PostMapping
    public R save(
            @ApiParam( name="teacher" ,value ="讲师对象" ,required = true)
            @RequestBody EduTeacher eduTeacher){

        eduteacherService.save(eduTeacher);
        return R.ok();
    }
    @ApiOperation("根据讲师id查询讲师")
    @GetMapping("{id}")
    public R getById(
        @ApiParam(name = "id", value = "讲师" ,required = true)
        @PathVariable String id
    ){
        EduTeacher teacher = eduteacherService.getById(id);
        return R.ok().data("item",teacher);
    }
    @ApiOperation("根据讲师的id修改讲师")
    @PutMapping("{id}")
    public R updateById(
        @ApiParam(name = "id",value = "讲师id" , required = true)
        @PathVariable String id,

        @ApiParam(name= "teacher",value = "讲师信息",required = true)
        @RequestBody EduTeacher eduTeacher
    ){
        eduTeacher.setId(id);
        eduteacherService.updateById(eduTeacher);
        return R.ok();
    }


    @ApiOperation("所有讲师列表")
    @GetMapping("/testEx")
    public  R testException(){
        try {
            int a= 23/0;

        }catch (Exception e){
            throw  new SunlxException(404,"孙鲁鑫异常");
        }
        return  R.ok();
     }

}

