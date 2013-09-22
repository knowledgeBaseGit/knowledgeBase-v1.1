package com.loongsoft.knowledgebase.util;

import java.util.ArrayList;
import java.util.List;

import com.loongsoft.knowledgebase.bean.Annex;
import com.loongsoft.knowledgebase.bean.Files;
import com.loongsoft.knowledgebase.bean.Fileversion;
import com.loongsoft.knowledgebase.bean.FunRole;
import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.Project;
import com.loongsoft.knowledgebase.bean.QuesSolu;
import com.loongsoft.knowledgebase.bean.Questions;
import com.loongsoft.knowledgebase.bean.Role;
import com.loongsoft.knowledgebase.bean.Solution;
import com.loongsoft.knowledgebase.bean.Staff;
import com.loongsoft.knowledgebase.bean.Users;
import com.loongsoft.knowledgebase.util.utilBean.UtilAnnexBean;
import com.loongsoft.knowledgebase.util.utilBean.UtilFilesBean;
import com.loongsoft.knowledgebase.util.utilBean.UtilFileversionBean;
import com.loongsoft.knowledgebase.util.utilBean.UtilKeywordBean;
import com.loongsoft.knowledgebase.util.utilBean.UtilProjectBean;
import com.loongsoft.knowledgebase.util.utilBean.UtilQuesBean;
import com.loongsoft.knowledgebase.util.utilBean.UtilRoleBean;
import com.loongsoft.knowledgebase.util.utilBean.UtilSolutionBean;
import com.loongsoft.knowledgebase.util.utilBean.UtilStaffBean;
import com.loongsoft.knowledgebase.util.utilBean.UtilUsersBean;

/**
 * bean转换类：将自动映射的bean文件转换为辅助bean
 * 
 * @author suoyanming 2013-8-19
 * 
 */
public class TransformBean {
	/**
	 * 问题 bean转换
	 * 
	 * @param ulQues
	 * @param que
	 * @throws Exception
	 */
	public static void quesTrans(UtilQuesBean ulQues, Questions que)
			throws Exception {

		ulQues.setQuestionId(que.getQuestionId());
		ulQues.setBrowseTimes(que.getBrowseTimes());
		ulQues.setCheckDate(que.getCheckDate());
		if (que.getCheckStatus() != null) {
			if (que.getCheckStatus() == 1) {
				ulQues.setCheckStatus("已审核");
			} else {
				ulQues.setCheckStatus("未审核");
			}
		} else {
			ulQues.setCheckStatus("未审核");
		}
		/*
		 * if (que.getIsSolve() == 1) { ulQues.setIsSolve("已解决"); } else {
		 * ulQues.setIsSolve("未解决"); }
		 */
		if (que.getProject() != null) {
			ulQues.setProject(que.getProject().getProjectName());
		}
		ulQues.setQuestionDesc(que.getQuestionDesc());
		ulQues.setQuestionTitle(que.getQuestionTitle());
		if (que.getUsersByCheckUser() != null) {
			ulQues.setUsersByCheckUser(que.getUsersByCheckUser().getLoginName());
		}
		if (que.getUsersByWriteUser() != null)
			ulQues.setUsersByWriteUser(que.getUsersByWriteUser().getLoginName());
		ulQues.setWriteDate(que.getWriteDate());

		List<UtilSolutionBean> list = new ArrayList<UtilSolutionBean>();
		if (que.getQuesSolus().size() > 0) {
			for (Object que_so : que.getQuesSolus()) {
				UtilSolutionBean usol = new UtilSolutionBean();
				TransformBean.solutionTrans(usol,
						((QuesSolu) que_so).getSolution());
				list.add(usol);
			}
			ulQues.setSolutions(list);
			ulQues.setIsSolve("已解决");
		} else {
			ulQues.setIsSolve("未解决");
		}

	}

	/**
	 * 解决方案转换表
	 * 
	 * @param usol
	 * @param solu
	 * @throws Exception
	 */
	public static void solutionTrans(UtilSolutionBean usol, Solution solu)
			throws Exception {
		usol.setSolutionId(solu.getSolutionId());
		usol.setSolutionName(solu.getSolutionName());
		if (solu.getUsersByWriteUser() != null) {
			usol.setUsersByWriteUser(solu.getUsersByWriteUser().getLoginName());
		}

		if (solu.getAnnex() != null) {
			UtilAnnexBean ua = new UtilAnnexBean();
			TransformBean.annexTrans(ua, solu.getAnnex());
			usol.setAnnex(ua);
			usol.setAnnexId(solu.getAnnex().getAnnexId());
			usol.setAnnexName(solu.getAnnex().getAnnexName());
			if (solu.getAnnex().getIsAppear() != null
					&& solu.getAnnex().getIsAppear() == 1) {
				usol.setIsAppear("可下载");
			} else {

				usol.setIsAppear("不可下载");
			}
		} else {
			usol.setAnnexName("无");
			usol.setIsAppear("");
		}
		if (solu.getUsersByCheckUser() != null) {
			usol.setUsersByCheckUser(solu.getUsersByCheckUser().getLoginName());
		}
		usol.setSolutionContent(solu.getSolutionContent());
		usol.setCheckDate(solu.getCheckDate());
		if (solu.getCheckStatus() != null) {
			if (solu.getCheckStatus() == 1) {
				usol.setCheckStatus("已审核");
			} else {
				usol.setCheckStatus("未审核");
			}
		} else {
			usol.setCheckStatus("未审核");
		}
		usol.setWriteDate(solu.getWriteDate());
	}

	/**
	 * 附件转换
	 */
	public static void annexTrans(UtilAnnexBean ua, Annex annex) {
		ua.setAnnexId(annex.getAnnexId());
		ua.setAnnexName(annex.getAnnexName());
		ua.setAnnexUrl(annex.getAnnexUrl());
		ua.setBrowseTimes(annex.getBrowseTimes());
		ua.setLoadTimes(annex.getLoadTimes());
		ua.setIsAppear(annex.getIsAppear());
	}

	/**
	 * 文件表转换
	 */
	public static void filesTrans(UtilFilesBean ufile, Files file) {
		ufile.setBrowseTimes(file.getBrowseTimes());
		ufile.setCheckDate(file.getCheckDate());
		if (file.getCheckStatus() != null) {
			if (file.getCheckStatus() == 1) {
				ufile.setCheckStatus("已审核");
			} else {
				ufile.setCheckStatus("未审核");
			}
		} else {
			ufile.setCheckStatus("未审核");
		}
		ufile.setCreateDate(file.getCreateDate());
		ufile.setFileDesc(file.getFileDesc());
		ufile.setFileId(file.getFileId());
		ufile.setFileName(file.getFileName());
		if (file.getIsAppear() != null) {
			if (file.getIsAppear() == 1) {
				ufile.setIsAppear("可下载！");
			} else {
				ufile.setIsAppear("没有权限下载");
			}
		} else {
			ufile.setIsAppear("没有权限下载");
		}
		ufile.setLoadTimes(file.getLoadTimes());
		if (file.getProject() != null) {
			UtilProjectBean up = new UtilProjectBean();
			TransformBean.projectTrans(up, file.getProject());
			ufile.setProject(up);
		}

		ufile.setFileType(file.getFileType());
		
		if(FileUtil.getBrowsFileType().contains(file.getFileType())){
			   ufile.setIsBrowse("可浏览");
		}else{
			ufile.setIsBrowse("不可浏览");
		}
		
		if (file.getUsersByCheckUser() != null) {
			UtilUsersBean uu = new UtilUsersBean();
			TransformBean.userTrans(uu, file.getUsersByCheckUser());
			ufile.setUsersByCheckUser(uu);
		}
		if (file.getUsersByCreateUser() != null) {
			UtilUsersBean uu = new UtilUsersBean();
			TransformBean.userTrans(uu, file.getUsersByCreateUser());
			ufile.setUsersByCreateUser(uu);
			ufile.setCreateUser(uu.getLoginName());
			ufile.setCreatUserId(uu.getUserId());
		}
		List<UtilFileversionBean> list = new ArrayList<UtilFileversionBean>();
		if (file.getFileversions().size() > 0) {
			for (Object fileversion : file.getFileversions()) {
				UtilFileversionBean uf = new UtilFileversionBean();
				TransformBean.fileversionTrans(uf, (Fileversion) fileversion);
				list.add(uf);
			}
		}
		if (list.size() > 0) {
			ufile.setFileversions(list);
		}

	}

	// user 转换
	public static void userTrans(UtilUsersBean uu, Users user) {
		uu.setCreateDate(user.getCreateDate());
		uu.setLoginName(user.getLoginName());
		uu.setLoginPassword(user.getLoginPassword());
		if (user.getRole() != null) {
			UtilRoleBean ur = new UtilRoleBean();
			TransformBean.roleTrans(ur, user.getRole());
			uu.setRole(ur);
			uu.setRoleName(ur.getRoleName());
		}
		if (user.getStaff() != null) {
			UtilStaffBean us = new UtilStaffBean();
			TransformBean.staffTrans(us, user.getStaff());
			uu.setStaff(us);
			uu.setTel(us.getStaffTel());
			uu.setDepartment(us.getDepartment());
			uu.setEntryDate(us.getEntryDate());
			uu.setStaffName(us.getStaffName());
		}

		uu.setUserId(user.getUserId());
	}

	// 角色转换
	public static void roleTrans(UtilRoleBean ur, Role role) {
		ur.setRoleId(role.getRoleId());
		ur.setRoleName(role.getRoleName());
		if (role.getFunRoles() != null) {
			StringBuffer resource = new StringBuffer();
			for (Object obj : role.getFunRoles()) {
				FunRole fr = (FunRole) obj;
				resource.append(fr.getFunctions().getFunctionName());
			}
			ur.setResource(resource.toString());
		}
		ur.setRemark(role.getRemark());
	}

	// 员工转换
	public static void staffTrans(UtilStaffBean us, Staff staff) {
		us.setDepartment(staff.getDepartment());
		us.setEntryDate(staff.getEntryDate());
		us.setStaffId(staff.getStaffId());
		us.setStaffName(staff.getStaffName());
		us.setStaffTel(staff.getStaffTel());
	}

	// 文件版本转换
	public static void fileversionTrans(UtilFileversionBean uf,
			Fileversion fileversion) {

		if (fileversion.getFiles() != null) {
			uf.setFileId(fileversion.getFiles().getFileId());
		}
		uf.setFileUrl(fileversion.getFileUrl());
		uf.setId(fileversion.getId());
		uf.setUpAuthor(fileversion.getUpAuthor());
		uf.setUploadDate(fileversion.getUploadDate());
		uf.setVersionId(fileversion.getVersionId());
	}

	// 项目类别转换
	public static void projectTrans(UtilProjectBean up, Project project) {

		up.setCreater(project.getCreater());
		up.setCreateTime(project.getCreateTime());
		if (project.getProject() != null) {
			up.setParentProId(project.getProject().getProjectId());
			up.setParentProName(project.getProject().getProjectName());
		}
		up.setProjectDesc(project.getProjectDesc());
		up.setProjectId(project.getProjectId());
		up.setProjectName(project.getProjectName());
	}

	// 关键词实体转换
	public static void keywordTrans(UtilKeywordBean uk, Keyword key) {
		uk.setCheckDate(key.getCheckDate());
		if (key.getCheckStatus() == 1) {
			uk.setCheckStatus("已审核");
		} else {
			uk.setCheckStatus("未审核");
		}
		uk.setDestroyDate(key.getDestroyDate());
		if (key.getIsDstroy() != null && key.getIsDstroy() == 1) {
			uk.setIsDstroy("已销毁");
		} else {
			uk.setIsDstroy("未销毁");
		}
		uk.setCreateUser(key.getCreateUser());
		uk.setKeywordId(key.getKeywordId());
		uk.setKeywordName(key.getKeywordName());
		uk.setSearchTimes(key.getSearchTimes());
		if (key.getUsers() != null) {
			UtilUsersBean uu = new UtilUsersBean();
			TransformBean.userTrans(uu, key.getUsers());
			uk.setUsers(uu);
		}
		uk.setKeywordDesc(key.getKeywordDesc());
		uk.setCreateDate(key.getCreateDate());

	}
}
