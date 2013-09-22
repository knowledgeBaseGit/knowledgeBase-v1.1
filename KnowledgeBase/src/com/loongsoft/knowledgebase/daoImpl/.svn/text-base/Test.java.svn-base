package com.loongsoft.knowledgebase.daoImpl;

import java.util.List;

import com.loongsoft.knowledgebase.basis.Applicationhandler;
import com.loongsoft.knowledgebase.basis.SpringContextHolder;
import com.loongsoft.knowledgebase.basis.TestBase;
import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.Project;
import com.loongsoft.knowledgebase.dao.IFilesDao;
import com.loongsoft.knowledgebase.dao.IKeyWordDao;
import com.loongsoft.knowledgebase.dao.IProjectDao;
import com.loongsoft.knowledgebase.dao.IUserDao;
import com.loongsoft.knowledgebase.service.ProjectKeyTypeService;
import com.loongsoft.knowledgebase.util.Util;

public class Test extends TestBase {

	public void testUserDao() {
		/*
		 * IUserDao userDao = Applicationhandler.getBean("userDaoImpl");
		 * System.out
		 * .println(userDao.getRoleByUserId("03039").get(0).getRoleName());
		 * System.out.println(userDao.isStaff("03039"));
		 * System.out.println(userDao.findUserById("03039").getLoginName() );
		 * System.out.println(userDao.getAllUsers().get(0).getLoginName());
		 */

		// 关键词模块测试
		IKeyWordDao keywordDao = Applicationhandler.getBean("keywordDaoImpl");

		/*
		 * System.out.println(keywordDao.getKeywordById(2).getKeywordName());
		 * System
		 * .out.println(keywordDao.getAllKeyOfProChecked(1).get(0).getKeywordName
		 * ()); System.out.println(keywordDao.getAllKeyOfProUncheck(1).get(0).
		 * getKeywordName());
		 */

		// 项目类别管理模块测试
		/*
		 * IProjectDao projectDao = Applicationhandler.getBean("projectDao");
		 * List<Project> list = projectDao.getAllParentProject(); for(int
		 * i=0;i<list.size();i++){
		 * System.out.println(list.get(i).getProjectName()); List<Project> list1
		 * = projectDao.getProByParentProId(list.get(i).getProjectId()); for(int
		 * j=0;j<list1.size();j++){
		 * System.out.println("*****"+list1.get(j).getProjectName()); } }
		 */

		/*
		 * List<Project> list1 = projectDao.getProByParentProId(2); for(int
		 * i=0;i<list1.size();i++){
		 * System.out.println(list1.get(i).getProjectName());
		 * 
		 * }
		 */

		/*ProjectKeyTypeService proKeyTypeService = Applicationhandler
				.getBean("projectKeyTypeService");
		StringBuffer result = new StringBuffer();

		List<Keyword> list = proKeyTypeService.getAllKeywordOfPro(1);
		System.out.println(list.size());
		*/
		IFilesDao fileDao = Applicationhandler.getBean("fileDao");
		fileDao.deleteFile(11);
	}

	public static void main(String[] args) {
		String[] strs = Util.splitStr("11,8,5",",");
		System.out.println(strs.length);
		for (int i = 0; i < strs.length; i++) {
			System.out.println(strs[i]);
		}
	}
}
