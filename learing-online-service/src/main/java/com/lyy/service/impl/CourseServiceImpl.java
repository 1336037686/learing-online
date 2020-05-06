package com.lyy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyy.dao.*;
import com.lyy.exception.ErrorCode;
import com.lyy.exception.base.BussinessException;
import com.lyy.pojo.dto.CourseDTO;
import com.lyy.pojo.dto.VideoDTO;
import com.lyy.pojo.entity.*;
import com.lyy.pojo.entity.extend.CourseExtend;
import com.lyy.service.CourseService;
import com.lyy.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LGX_TvT
 * @date 2020-03-31 22:04
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private SectionDao sectionDao;

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private StudentCourseDao studentCourseDao;

    @Autowired
    private ConverterUtil converterUtil;


    /**
     * 分页查询
     * @param dto
     * @return
     */
    @Override
    public CourseDTO queryAllAndNotCheck(CourseDTO dto) {
        try {
            PageHelper.startPage(dto.getCurrentPage(), dto.getSize());
            List<Course> courseList = courseDao.queryAllAndNotCheck();
            PageInfo<Course> pageInfo = new PageInfo<Course>(courseList);
            dto.setPageInfo(pageInfo);
            return dto;
        } catch (Exception e) {
            throw new BussinessException(ErrorCode.SERVICE_COURSE_QUERY_FAIL_ERROR, "课程信息查找失败");
        }
    }

    /**
     * 更新
     * @param courseDTO
     * @return
     */
    @Override
    public boolean update(CourseDTO courseDTO) {
        try {
            courseDao.update(converterUtil.copyComplicatedObjectAndReturnNewOne(courseDTO, Course.class));
        } catch (Exception e) {
            throw new BussinessException(ErrorCode.SERVICE_COURSE_UPDATE_FAIL_ERROR, "课程信息更新失败");
        }
        return true;
    }

    /**
     * 课程名称模糊查找
     * @param dto
     * @return
     */
    @Override
    public CourseDTO queryByName(CourseDTO dto) throws BussinessException{
        PageHelper.startPage(dto.getCurrentPage(), dto.getSize());
        try {
            List<CourseExtend> courseList = courseDao.queryByName(dto.getName());
            PageInfo<CourseExtend> pageInfo = new PageInfo<>(courseList);
            dto.setPageInfo(pageInfo);
            return dto;
        } catch (Exception e) {
            throw new BussinessException(ErrorCode.SERVICE_COURSE_QUERY_FAIL_ERROR, "课程信息查找失败");
        }
    }

    /**
     * 保存
     * @param courseDTO
     * @return
     * @throws BussinessException
     */
    @Override
    public boolean save(CourseDTO courseDTO) throws BussinessException {
        try {
            Course course = converterUtil.copyPropertiesAndReturnNewOne(courseDTO, Course.class);
            int result = courseDao.save(course);
        } catch (Exception e) {
            throw new BussinessException(ErrorCode.SERVICE_COURSE_SAVE_FAIL_ERROR, "课程信息保存失败");
        }
        return true;
    }

    /**
     * 删除
     * @param courseDTO
     * @return
     * @throws BussinessException
     */
    @Override
    public boolean remove(CourseDTO courseDTO) throws BussinessException {
        // 判断是否有章节

        // 删除章节
        try {
            int result = courseDao.remove(courseDTO.getId());
        } catch (Exception e) {
            throw new BussinessException(ErrorCode.SERVICE_COURSE_UPDATE_FAIL_ERROR, "课程信息更新失败");
        }
        return true;
    }

    /**
     * 根据教师信息查找课程信息
     * @param courseDTO
     * @return
     * @throws BussinessException
     */
    @Override
    public CourseDTO queryByTeacher(CourseDTO courseDTO) throws BussinessException {
        PageHelper.startPage(courseDTO.getCurrentPage(), courseDTO.getSize());
        try {
            List<CourseExtend> course = courseDao.queryByTeacher(courseDTO.getTeacher());
            PageInfo<CourseExtend> pageInfo = new PageInfo<>(course);
            courseDTO.setPageInfo(pageInfo);
            return courseDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_COURSE_QUERY_FAIL_ERROR, "查找课程信息失败");
        }
    }

    /**
     * 按照教师查找所有已经审核通过的课程信息
     * @param courseDTO
     * @return
     * @throws BussinessException
     */
    @Override
    public List<CourseExtend> queryAllPassByTeacher(CourseDTO courseDTO) throws BussinessException {
        return courseDao.queryAllPassByTeacher(courseDTO.getTeacher());
    }

    /**
     * 按照课程ID查找课程信息
     * @param id
     * @return
     * @throws BussinessException
     */
    @Override
    public CourseDTO queryById(String id) throws BussinessException {
        try {
            Course course = courseDao.queryById(id);
            CourseDTO courseDTO = converterUtil.copyPropertiesAndReturnNewOne(course, CourseDTO.class);
            return courseDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_COURSE_QUERY_FAIL_ERROR, "查找课程信息失败");
        }
    }

    /**
     * 查找首页推荐课程
     * @return
     */
    @Override
    public Map<String, Object> queryCategoryAndCourse(Integer num) {
        try {
            Map<String,Object> map = new HashMap<>();
            List<Category> categories = categoryDao.queryAll();
            for (Category category : categories) {
                List<Course> courses = courseDao.queryByTypeAndNum(category.getId(), num);
                if (courses.size() > 0) {
                    Map<String, Object> params = new HashMap<>();
                    params.put("id", category.getId());
                    params.put("list", courses);
                    map.put(category.getCategoryName(), params);
                }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_COURSE_QUERY_FAIL_ERROR, "查找首页推荐课程信息失败");
        }
    }

    /**
     * 根据课程ID查找目录信息
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> queryCatalogById(String id) {
        try {
            List<Section> sections = sectionDao.queryAllByCourse(id);
            Map<String,Object> map = new HashMap<>();
            for (Section section : sections) {
                VideoDTO videoDTO = new VideoDTO();
                videoDTO.setCourse(id);
                videoDTO.setSection(section.getId());
                List<Video> videos = videoDao.queryAllByCourseAndSection(videoDTO);
                if (videos.size() > 0) {
                    List<String> videoList = new ArrayList<>();
                    for (Video video : videos) {
                        videoList.add(video.getName());
                    }
                    Map<String, Object> params = new HashMap<>();
                    params.put("id", section.getId());
                    params.put("list", videoList);
                    map.put(section.getName(), params);
                } else {
                    Map<String, Object> params = new HashMap<>();
                    params.put("id", section.getId());
                    params.put("list", new ArrayList<>());
                    map.put(section.getName(), params);
                }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_COURSE_QUERY_FAIL_ERROR, "课程目录信息失败");
        }
    }

    /**
     * 查找人气课程
     * @param num
     * @return
     */
    @Override
    public List<CourseExtend> queryMoodsCourse(Integer num) {
        try {
            return courseDao.queryMoodsCourse(num);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_COURSE_QUERY_FAIL_ERROR, "查找人气课程信息失败");
        }
    }

    /**
     * 根据类型查找课程
     * @param type
     * @return
     */
    @Override
    public List<CourseExtend> queryAllByType(String type) {
        try {
            if("all".equals(type)) {
                return courseDao.queryAll();
            } else {
                List<CourseExtend> list = converterUtil.convertList(courseDao.queryByType(type), CourseExtend.class);
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_COURSE_QUERY_FAIL_ERROR, "查找课程信息失败");
        }
    }

    /**
     * 根据学生ID查找选课信息
     * @param id
     * @return
     * @throws BussinessException
     */
    @Override
    public Map<String, Object> queryCourseByStudentId(String id) throws BussinessException {
        try {
            Map<String, Object> map = new HashMap<>();
            List<CourseExtend> list = studentCourseDao.queryByStudent(id);
            List<CourseExtend> pass = new ArrayList<>();
            List<CourseExtend> check = new ArrayList<>();
            List<CourseExtend> fail = new ArrayList<>();
            for (CourseExtend courseExtend : list) {
                if ("0".equals(courseExtend.getCheckState())) {
                    check.add(courseExtend);
                }
                if ("1".equals(courseExtend.getCheckState())) {
                    pass.add(courseExtend);
                }
                if ("2".equals(courseExtend.getCheckState())) {
                    fail.add(courseExtend);
                }
            }
            map.put("pass", pass);
            map.put("check", check);
            map.put("fail", fail);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_COURSE_QUERY_FAIL_ERROR, "查找课程信息失败");
        }
    }

    @Override
    public Map<String, Object> queryCatalogAllById(String id) {
        try {
            List<Section> sections = sectionDao.queryAllByCourse(id);
            Map<String,Object> map = new HashMap<>();
            for (Section section : sections) {
                VideoDTO videoDTO = new VideoDTO();
                videoDTO.setCourse(id);
                videoDTO.setSection(section.getId());
                List<Video> videos = videoDao.queryAllByCourseAndSection(videoDTO);
                if (videos.size() > 0) {
                    Map<String, Object> params = new HashMap<>();
                    params.put("id", section.getId());
                    params.put("list", videos);
                    map.put(section.getName(), params);
                } else {
                    Map<String, Object> params = new HashMap<>();
                    params.put("id", section.getId());
                    params.put("list", new ArrayList<>());
                    map.put(section.getName(), params);
                }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(ErrorCode.SERVICE_COURSE_QUERY_FAIL_ERROR, "课程目录信息失败");
        }
    }
}
