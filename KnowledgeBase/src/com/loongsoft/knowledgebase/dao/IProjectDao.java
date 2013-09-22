package com.loongsoft.knowledgebase.dao;

import java.util.List;

import com.loongsoft.knowledgebase.bean.Project;

/**
 * @desc modle层 项目类别管理
 * 
 * @author suoyanming 2013-8-15
 *
 */
public interface IProjectDao {
     /**
      * 获取所有父级项目
      * @return List<Project>
      */
    List<Project> getAllParentProject();
    
    /**
     * 根据父级项目Id获取所有所属子项目
     * @param parentProId
     * @return List<Project>
     * 
     */
    List<Project> getProByParentProId(int parentProId);
}
