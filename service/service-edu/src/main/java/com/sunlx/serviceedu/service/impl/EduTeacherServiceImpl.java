package com.sunlx.serviceedu.service.impl;

   import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
   import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunlx.serviceedu.entity.EduTeacher;
import com.sunlx.serviceedu.mapper.EduTeacherMapper;
 import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunlx.serviceedu.entity.vo.query.TeacherQuery;
import com.sunlx.serviceedu.service.EduteacherService;
 import org.springframework.stereotype.Service;
 import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author sunlx
 * @since 2022-07-27
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduteacherService {


   @Override
   public void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery) {
        QueryWrapper<EduTeacher> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");

        if(teacherQuery == null ){
          baseMapper.selectPage(pageParam,queryWrapper);
          return;
        }

        String name=teacherQuery.getName();
        Integer level=teacherQuery.getLevel();
        String begin=teacherQuery.getBegin();
        String end=teacherQuery.getEnd();

        if(!StringUtils.isEmpty(name)){
           queryWrapper.like("name",name);
        }

        if(!StringUtils.isEmpty(level)){
            queryWrapper.eq("level", level);
        }

        if(!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }
       if (!StringUtils.isEmpty(end)) {
           queryWrapper.le("gmt_create", end);
       }


       baseMapper.selectPage(pageParam,queryWrapper);

   }
}
