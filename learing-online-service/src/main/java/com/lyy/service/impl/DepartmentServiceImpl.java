package com.lyy.service.impl;

import com.lyy.dao.DepartmentDao;
import com.lyy.pojo.dto.DepartmentDTO;
import com.lyy.pojo.entity.Department;
import com.lyy.service.DepartmentService;
import com.lyy.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LGX_TvT
 * @date 2020-03-24 0:54
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private ConverterUtil converterUtil;

    /**
     * 保存类别信息
     * @param departmentDTO
     * @return
     * @throws Exception
     */
    @Override
    public boolean save(DepartmentDTO departmentDTO) throws Exception{
        departmentDao.save(new Department(departmentDTO.getId(), departmentDTO.getName(), departmentDTO.getState()));
        return true;
    }

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    @Override
    public List<DepartmentDTO> queryAll() throws Exception {
        List<Department> departmentList = departmentDao.queryAll();
        List<DepartmentDTO> DepartmentDTOList = converterUtil.convertList(departmentList, DepartmentDTO.class);
        return DepartmentDTOList;
    }

    /**
     * 按照id查找
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public DepartmentDTO queryById(String id) throws Exception {
        Department department = departmentDao.queryById(id);
        DepartmentDTO DepartmentDTO = converterUtil.copyComplicatedObjectAndReturnNewOne(department, DepartmentDTO.class);
        return DepartmentDTO;
    }

    /**
     * 更新
     * @param departmentDTO
     * @return
     * @throws Exception
     */
    @Override
    public boolean update(DepartmentDTO departmentDTO) throws Exception {
        departmentDao.update(converterUtil.copyComplicatedObjectAndReturnNewOne(departmentDTO, Department.class));
        return true;
    }

    /**
     * 删除
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public boolean remove(String id) throws Exception {
        departmentDao.remove(id);
        return true;
    }


}
