package com.sunlx.serviceedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunlx.serviceedu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sunlx.serviceedu.entity.vo.query.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author sunlx
 * @since 2022-07-27
 */
public interface EduteacherService extends IService<EduTeacher> {
    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);

}
