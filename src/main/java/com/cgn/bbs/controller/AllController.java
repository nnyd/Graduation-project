package com.cgn.bbs.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cgn.bbs.beans.AccessRecord;
import com.cgn.bbs.beans.Account;
import com.cgn.bbs.beans.BadRecord;
import com.cgn.bbs.beans.BbsPost;
import com.cgn.bbs.beans.Comment;
import com.cgn.bbs.beans.Dict;
import com.cgn.bbs.beans.Examine;
import com.cgn.bbs.beans.Idea;
import com.cgn.bbs.beans.Model;
import com.cgn.bbs.beans.Report;
import com.cgn.bbs.beans.ReturnMap;
import com.cgn.bbs.common.Log;
import com.cgn.bbs.common.LoginAuth;
import com.cgn.bbs.config.LoginInterceptor;
import com.cgn.bbs.services.inte.IAllService;
import com.cgn.bbs.util.Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class AllController {
	
	@Autowired
	private IAllService allService;
	@Autowired
	private Log log;
	private Map<String, Object> map=new HashMap<String, Object>();
	
	//================================================其他用户相关
	//获取我的信息
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/getMyPost")
	public ReturnMap getMyPost(int pageNum,HttpSession session){
		try {
			Integer accId = ((Account) session.getAttribute("USER_LOGIN_KEY")).getAccId();
			PageHelper.startPage(pageNum,10);
			List<BbsPost> list=allService.getMyPost(accId);
			PageInfo<BbsPost> data= new PageInfo<BbsPost>(list);
			return ReturnMap.success().put("data", data);
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中getMyPost异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/addExamPost")
	public ReturnMap addExamPost(Examine examine,HttpSession session){
		try {
			examine.setAccId(((Account) session.getAttribute("USER_LOGIN_KEY")).getAccId());
			if(allService.addExamPost(examine))
				return ReturnMap.success();
			else
				return ReturnMap.fail();
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中addExamPost异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	//获取我的申请
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/getMyExamines")
	public ReturnMap getMyExamines(int pageNum,HttpSession session){
		try {
			Integer accId = ((Account) session.getAttribute("USER_LOGIN_KEY")).getAccId();
			PageHelper.startPage(pageNum,10);
			List<Examine> list=allService.getMyExamines(accId);
			PageInfo<Examine> data= new PageInfo<Examine>(list);
			return ReturnMap.success().put("data", data);
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中getMyExamines异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/getAllExamines")
	public ReturnMap getAllExamines(int pageNum,HttpSession session){
		try {
			PageHelper.startPage(pageNum,10);
			List<Examine> list=allService.getAllExamines();
			PageInfo<Examine> data= new PageInfo<Examine>(list);
			return ReturnMap.success().put("data", data);
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中getAllExamines异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	
	//================================================其他管理员相关
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/getAllUser")
	public ReturnMap getAllUser(int pageNum,HttpSession session){
		try {
			PageHelper.startPage(pageNum,10);
			List<Account> list=allService.getAllUser();
			PageInfo<Account> data= new PageInfo<Account>(list);
			return ReturnMap.success().put("data", data);
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中getAllUser异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/updateExamine")
	public ReturnMap updateExamine(Examine examine,HttpSession session){
		try {
			allService.updateExamine(examine);
			return ReturnMap.success();
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中updateExamine异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/getAllBadRecord")
	public ReturnMap getAllBadRecord(int pageNum,HttpSession session){
		try {
			PageHelper.startPage(pageNum,10);
			List<BadRecord> list=allService.getAllBadRecord();
			PageInfo<BadRecord> data= new PageInfo<BadRecord>(list);
			return ReturnMap.success().put("data", data);
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中getAllBadRecord异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/getAllLog")
	public ReturnMap getAllLog(int pageNum,HttpSession session){
		try {
			PageHelper.startPage(pageNum,10);
			List<Map<String, String>> list=allService.getAllLog();
			PageInfo<Map<String, String>> data= new PageInfo<Map<String, String>>(list);
			return ReturnMap.success().put("data", data);
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中getAllLog异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/getAllPost")
	public ReturnMap getAllPost(int pageNum,HttpSession session){
		try {
			PageHelper.startPage(pageNum,10);
			List<BbsPost> list=allService.getAllPost();
			PageInfo<BbsPost> data= new PageInfo<BbsPost>(list);
			return ReturnMap.success().put("data", data);
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中getAllPost异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/getExamineModelInfo")
	public ReturnMap getExamineModelInfo(int pageNum,HttpSession session){
		try {
			if(!((Account) session.getAttribute("USER_LOGIN_KEY")).getRole().equals("admin")){
				return ReturnMap.noAuthorized();
			}
			PageHelper.startPage(pageNum,10);
			List<Examine> list=allService.getExamineModelInfo();
			PageInfo<Examine> data= new PageInfo<Examine>(list);
			return ReturnMap.success().put("data", data);
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中getExamineModelInfo异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/accessExamModel")
	public ReturnMap accessExamModel(Examine examine,HttpSession session){
		try {
			if(!((Account) session.getAttribute("USER_LOGIN_KEY")).getRole().equals("admin")){
				return ReturnMap.noAuthorized();
			}
			allService.accessExamModel(examine);
			log.Info(Log.INFO, "管理员："+((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName()+"更改了审批："+examine.getExamineId()+" 的状态为"+examine.getStatus());
			return ReturnMap.success();
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中accessExamModel异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/upPost")
	public ReturnMap upPost(Dict dict,HttpSession session){
		try {
			if(!((Account) session.getAttribute("USER_LOGIN_KEY")).getRole().equals("admin")){
				return ReturnMap.noAuthorized();
			}
			List<Dict> list=allService.checkDict(dict);
			if(Utils.isNullOrEmpty(list)||list.size()==0){
				allService.upPost(dict);
				log.Info(Log.INFO, "管理员："+((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName()+"更改了帖子："+allService.getBbsPostInfoById(dict.getTid()).getPostTitle()+" 的状态为"+dict.getDictType());
			}
			return ReturnMap.success();
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中upPost异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/changeAccState")
	public ReturnMap changeAccState(Account account,HttpSession session){
		try {
			if(!((Account) session.getAttribute("USER_LOGIN_KEY")).getRole().equals("admin")){
				return ReturnMap.noAuthorized();
			}
			if(allService.changeAccState(account)){
				log.Info(Log.INFO, "管理员："+((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName()+"更改了用户："+account.getAccName()+" 的状态为"+account.getIsInUse());
				return ReturnMap.success();
			}else {
				return ReturnMap.fail();
			}
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中changeAccState异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	//================================================意见反馈相关
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/getReportInfo")
	public ReturnMap getReportInfo(int pageNum,HttpSession session){
		try {
			if(!((Account) session.getAttribute("USER_LOGIN_KEY")).getRole().equals("admin")){
				return ReturnMap.noAuthorized();
			}
			PageHelper.startPage(pageNum,10);
			List<Report> list=allService.getReportInfo();
			if(list!=null&&list.size()>0){
				for (Report report : list) {
					report.setReportType(Report.map.get(report.getReportType()));
				}
			}
			PageInfo<Report> data= new PageInfo<Report>(list);
			return ReturnMap.success().put("data", data);
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中getReportInfo异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/getIdeaInfo")
	public ReturnMap getIdeaInfo(int pageNum,HttpSession session){
		try {
			if(!((Account) session.getAttribute("USER_LOGIN_KEY")).getRole().equals("admin")){
				return ReturnMap.noAuthorized();
			}
			PageHelper.startPage(pageNum,10);
			List<Idea> list=allService.getIdeaInfo();
			PageInfo<Idea> data= new PageInfo<Idea>(list);
			return ReturnMap.success().put("data", data);
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中getIdeaInfo异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/createReport")
	public ReturnMap createReport(Report report,HttpSession session){
		try {
			if(((Account) session.getAttribute("USER_LOGIN_KEY")).getRole().equals("admin")){
				return ReturnMap.noAuthorized();
			}report.setReportAccId(((Account) session.getAttribute("USER_LOGIN_KEY")).getAccId());
			allService.createReport(report);
			log.Info(Log.INFO, "用户："+((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName()+"进行了举报："+report.getReportId());
			return ReturnMap.success();
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中createReport异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/createIdea")
	public ReturnMap createIdea(Idea idea,HttpSession session){
		try {
			if(((Account) session.getAttribute("USER_LOGIN_KEY")).getRole().equals("admin")){
				return ReturnMap.noAuthorized();
			}idea.setAccId(((Account) session.getAttribute("USER_LOGIN_KEY")).getAccId());
			allService.createIdea(idea);
			log.Info(Log.INFO, "用户："+((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName()+"提出意见："+idea.getIdeaId());
			return ReturnMap.success();
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中createIdea异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	//================================================评论相关
	@ResponseBody
	@RequestMapping("/bbs/getCommentForPost")
	public ReturnMap getCommentForPost(int postId,int pageNum){
		try {
			PageHelper.startPage(pageNum, 10);
			List<Comment> list=allService.getCommentForPost(postId);
			PageInfo<Comment> pageInfo=new PageInfo<Comment>(list);
			return ReturnMap.success().put("data", pageInfo);
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中getCommentForPost异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/createComment")
	public ReturnMap createComment(Comment comment,HttpSession session){
		try {
			comment.setAccId(((Account) session.getAttribute("USER_LOGIN_KEY")).getAccId());
			allService.createComment(comment);
			log.Info(Log.INFO, "用户："+((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName()+"评论了帖子"+allService.getBbsPostInfoById(comment.getPostId()).getPostTitle());
			return ReturnMap.success();
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中createComment异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@LoginAuth
	@RequestMapping("/bbs/redirectAfterCreateComment")
	public ModelAndView redirectAfterCreateComment(int postId){//int postId,int pageNum
		return new ModelAndView("redirect:/bbs/getPostDetail?postId="+postId+"&pageNum=99999");
	}
	@LoginAuth
	@RequestMapping("/bbs/tohuifu")
	public ModelAndView tohuifu(Comment comment){//int postId,int pageNum
		comment.setPostId(allService.getCommentByPid(comment.getPid()));
		ModelAndView mav = new ModelAndView("post/huifu");
		mav.addObject("comment", comment);
		return mav;
	}
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/deleteComment")
	public ReturnMap deleteComment(Comment comment,HttpSession session){
		try {
			comment.setAccId(((Account) session.getAttribute("USER_LOGIN_KEY")).getAccId());
			boolean isDelete=allService.deleteComment(comment,((Account) session.getAttribute("USER_LOGIN_KEY")));
			if(isDelete)
				log.Info(Log.INFO, "用户："+((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName()+"删除了：‘"+allService.getBbsPostInfoById(comment.getPostId()).getPostTitle()+"’中的评论"+comment.getCommentContent());
			return isDelete?ReturnMap.success():ReturnMap.fail();
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中deleteComment异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	//================================================帖子相关
	@RequestMapping("/bbs/getPostInfo")
	public ModelAndView getPostInfo(int modelId,int pageNum,HttpSession session){
		try {
			PageHelper.startPage(pageNum, 10);
			List<BbsPost> list=allService.getPostInfo(modelId);
			PageInfo<BbsPost> pageInfo=new PageInfo<BbsPost>(list);//查找文件
			ModelAndView mav = new ModelAndView("post/list");
			mav.addObject("data", pageInfo);
			if(!Utils.isNullOrEmpty(((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName())){
				AccessRecord accessRecord = new AccessRecord();
				accessRecord.setAccId(((Account) session.getAttribute("USER_LOGIN_KEY")).getAccId());accessRecord.setModelId(modelId);
				allService.addAccessRecord(accessRecord);
				Model model = new Model();
				model.setModelId(modelId);
				log.Info(Log.INFO, "用户："+((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName()+"查看了"+allService.getModelById(model).getModelName()+"下的帖子");
			}
			return mav;
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中getPostInfo异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return new ModelAndView("error");
		}
	}
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/createBbsPost")
	public ReturnMap createBbsPost(BbsPost bbsPost,HttpSession session){
		try {
			bbsPost.setAccId(((Account) session.getAttribute("USER_LOGIN_KEY")).getAccId());
			allService.createBbsPost(bbsPost);
			log.Info(Log.INFO, "用户："+((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName()+"创建了帖子"+bbsPost.getPostTitle());
			return ReturnMap.success().put("data", bbsPost);
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中createBbsPost异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/getBbsPostInfoById")
	public ReturnMap getBbsPostInfoById(int postId,HttpSession session){
		try {
			BbsPost bbsPost=allService.getBbsPostInfoById(postId);//查文件
			if(null!=bbsPost&&null!=bbsPost.getAccId()&&bbsPost.getAccId()==((Account) session.getAttribute("USER_LOGIN_KEY")).getAccId()){
				map.put("postId", postId);
				log.Info(Log.INFO, "用户："+((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName()+"获取帖子信息准备修改："+bbsPost.getPostTitle());
				return ReturnMap.success().put("data", bbsPost);
			}else {
				if(((Account) session.getAttribute("USER_LOGIN_KEY")).getAccId()!=null)
					log.Info(Log.INFO, "用户："+((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName()+"获取帖子信息失败："+bbsPost.getPostId());
				return ReturnMap.fail();
			}
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中getBbsPostInfoById异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/updateBbsPost")
	public ReturnMap updateBbsPost(BbsPost bbsPost,HttpSession session){
		try {
			if(null==map.get("postId"))
				return ReturnMap.unknown();
			BbsPost bbsPost2=allService.getBbsPostInfoById(Integer.parseInt(map.get("postId").toString()));
			if(null!=bbsPost2&&null!=bbsPost2.getAccId()&&bbsPost2.getAccId()==((Account) session.getAttribute("USER_LOGIN_KEY")).getAccId()){
				allService.updateBbsPost(bbsPost);
				log.Info(Log.INFO, "用户："+((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName()+"更新帖子信息："+bbsPost.getPostTitle());
				return ReturnMap.success();
			}
			return ReturnMap.fail();
		} catch (NumberFormatException e) {
			log.Warning(Log.WARNING, "AllController中updateBbsPost异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@RequestMapping("/bbs/getPostDetail")
	public ModelAndView getPostDetail(int postId,int pageNum,HttpSession session){
		try {
			Map<String, Object> data=allService.getPostDetail(postId,pageNum,((Account) session.getAttribute("USER_LOGIN_KEY")));
			if(null!=data){
				ModelAndView mav = new ModelAndView("post/detail");
				mav.addObject("data",data);
				if(session.getAttribute("USER_LOGIN_KEY")==null||Utils.isNullOrEmpty(((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName())){
					log.Info(Log.INFO, "游客访问了帖子："+((BbsPost)(data.get("post"))).getPostTitle());
				}else {
					log.Info(Log.INFO, "用户："+((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName()+"访问了帖子："+((BbsPost)(data.get("post"))).getPostTitle());
				}
				return mav;
			}else {
				return new ModelAndView("error"); 
			}
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中getPostDetail异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return new ModelAndView("error"); 
		}
	}
	@LoginAuth
	@RequestMapping("/bbs/toCreatePost")
	public ModelAndView toCreatePost(){
		try {
			ModelAndView mav = new ModelAndView("post/createPost");
			return mav;
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中toCreatePost异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return new ModelAndView("error"); 
		}
	}
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/deletePost")
	public ReturnMap deletePost(int postId,HttpSession session){
		try {
			BbsPost bbsPost=allService.getBbsPostInfoById(postId);
			if(null!=bbsPost&&null!=bbsPost.getAccId()){
				allService.deletePost(postId);
				log.Info(Log.INFO, "用户："+((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName()+"删除了帖子："+bbsPost.getPostTitle());
				return ReturnMap.success();
			}else {
				return ReturnMap.fail();
			}
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中deletePost异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	//================================================模块相关
	
	@ResponseBody
	@RequestMapping("/bbs/getAllModel")
	public ReturnMap getAllModel(){
		try {
//			if(((Account) session.getAttribute("USER_LOGIN_KEY")).getRole().equals("admin")){
				List<Model> list=allService.getAllModel();
//				log.Info(Log.INFO, "用户："+((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName()+"查看了所有版块");
				return ReturnMap.success().put("data", list);
//			}else {
//				log.Info(Log.INFO, "用户："+((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName()+"试图查看了所有版块被拦截");
//				return ReturnMap.noAuthorized();
//			}
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中getAllModel异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/addModel")
	public ReturnMap addModel(Model model,HttpSession session){
		Account account=((Account) session.getAttribute("USER_LOGIN_KEY"));
		try {
			String role=(null!=account&&Utils.isNullOrEmpty(account.getRole()))?account.getRole():((Account) session.getAttribute("USER_LOGIN_KEY")).getRole();
			if (role==null) {
				return ReturnMap.noLogin();
			}
			model.setCreateAccountId(account.getAccId());
			Integer state=allService.addModel(model,role);
			switch (state) {
			case 1:
				log.Info(Log.INFO, "管理员："+((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName()+"添加了板块"+model.getModelName());
				return ReturnMap.success().put("data", allService.getModelById(model));
			case 2:
				log.Info(Log.INFO, "公司人员："+((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName()+"添加了板块信息进入审批"+model.getModelName());
				return ReturnMap.success().put("tishi", "数据已经上报管理员，请耐心等待审批");
			case 3:
				log.Info(Log.INFO, "用户："+((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName()+"尝试添加板块"+model.getModelName()+"被拦截");
				return ReturnMap.noAuthorized();
			case 4:
				log.Info(Log.INFO, "用户："+((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName()+"尝试添加板块"+model.getModelName()+"重复");
				return ReturnMap.fail().put("tishi", "该板块已存在");
			case 5:
				log.Info(Log.INFO, "用户："+((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName()+"尝试添加板块"+model.getModelName()+"重复");
				return ReturnMap.fail().put("tishi", "该板块正在审批中，请耐心等待");
			default:
				log.Record(Log.RECORD, "AllController中addModel返回预想以外的值");
				return ReturnMap.unknown();
			}
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中addModel异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/getModelById")
	public ReturnMap getModelById(Model model){
		try {
			allService.getModelById(model);
			map.put("modelId", model.getModelId());
			map.put("modelName", model.getModelName());
			return ReturnMap.success().put("data", model);
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中getModelById异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/updateModel")
	public ReturnMap updateModel(Model model,HttpSession session){
		Account account=((Account) session.getAttribute("USER_LOGIN_KEY"));
		try {
			String role=null!=account.getRole()?account.getRole():((Account) session.getAttribute("USER_LOGIN_KEY")).getRole();
			if(!role.equals("admin")){
				return ReturnMap.noAuthorized();
			}
			map.put("modelId", model.getModelId());
			map.put("modelName", model.getModelName());
			if(null==map.get("modelId"))
				return ReturnMap.fail().put("tishi", "您为正确执行修改顺序，无法执行修改操作");
			Integer state=allService.updateModel(model,map);
			switch (state) {
			case 1:
				log.Info(Log.INFO, "管理员："+((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName()+"修改了板块"+map.get("modelName")+"--->"+model.getModelName());
				map.remove("modelId");
				map.remove("modelName");
				return ReturnMap.success();
			case 4:
				log.Info(Log.INFO, "用户："+((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName()+"尝试修改板块"+model.getModelName()+"重复");
				return ReturnMap.fail().put("tishi", "该板块已存在");
			case 5:
				log.Info(Log.INFO, "用户："+((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName()+"尝试修改板块"+model.getModelName()+"重复");
				return ReturnMap.fail().put("tishi", "该板块正在审批中，请耐心等待");
			default:
				log.Record(Log.RECORD, "AllController中addModel返回预想以外的值");
				return ReturnMap.unknown();
			}
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中updateModel异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/deleteModel")
	public ReturnMap deleteModel(Model model,HttpSession session){
		Account account=((Account) session.getAttribute("USER_LOGIN_KEY"));
		try {
			String role=null!=account.getRole()?account.getRole():((Account) session.getAttribute("USER_LOGIN_KEY")).getRole();
			if(!role.equals("admin")){
				return ReturnMap.noAuthorized();
			}
			model=allService.getModelById(model);
			allService.deleteModel(model);
			log.Info(Log.INFO, "管理员："+((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName()+"删除了板块"+model.getModelName());
			return ReturnMap.success();
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中deleteModel异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	//================================================登录相关
	@ResponseBody
	@RequestMapping("/login")
	public ReturnMap login(Account account,String type,HttpSession session,HttpServletRequest request){
		try {
			if(session.getAttribute("USER_LOGIN_KEY")!=null&&!Utils.isNullOrEmpty(((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName())){return ReturnMap.success();}
			Account account2 = allService.login(account,type);
			if(null!=account2){
				if(account2.getIsInUse()){
					session.setMaxInactiveInterval(-1);
					session.setAttribute("USER_LOGIN_KEY", account2);
					log.Info(Log.INFO, "用户："+account2.getAccName()+"访问"+request.getHeader("Referer")+"时登录");
					System.out.println(account2);
					return ReturnMap.success().put("role", account2.getRole()).put("url", map.get("beforeLoginUrl")==null?"/index":map.get("beforeLoginUrl"));
				}else {
					session.setMaxInactiveInterval(-1);
					session.setAttribute("USER_LOGIN_KEY",new Account());
					return ReturnMap.fail().put("info", "该账户已冻结");
				}
			}else {
				session.setMaxInactiveInterval(-1);
				session.setAttribute("USER_LOGIN_KEY",new Account());
				return ReturnMap.fail().put("info", "用户名密码错误");
			}
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中login异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@RequestMapping("/toLogin")
	public ModelAndView toLogin(HttpServletRequest request){
		map.put("beforeLoginUrl", request.getHeader("Referer"));
		System.out.println(request.getHeader("Referer"));
		return new ModelAndView("account/login");
	}
	@LoginAuth
	@ResponseBody
	@RequestMapping("/bbs/changeAccountInfo")
	public ReturnMap changeAccountInfo(Account account,HttpSession session){
		try {
			account.setAccId(((Account) session.getAttribute("USER_LOGIN_KEY")).getAccId());
			account=allService.changeAccountInfo(account);
			log.Info(Log.INFO, "用户："+((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName()+"更新了用户信息");
			session.setAttribute("USER_LOGIN_KEY", account);
			System.out.println(account);
			return ReturnMap.success();
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中changeAccountInfo异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@ResponseBody
	@RequestMapping("/verificationInfoForResetPass")
	public ReturnMap verificationInfoForResetPass(Account account){
		try {
			boolean isOk=allService.verificationInfoForResetPass(account);
			if(isOk){
				map.put("accId", account.getAccId());
				log.Info(Log.INFO, "用户："+account.getAccName()+"要修改密码验证成功");
			}
			return isOk?ReturnMap.success():ReturnMap.fail();
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中verificationInfoForResetPass异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@ResponseBody
	@RequestMapping("/resetPass")
	public ReturnMap resetPass(String password){
		try {
			if(null==map.get("accId"))return ReturnMap.unknown().put("errorInfo", "未验证的用户");
			allService.resetPass(password,map.get("accId").toString());
			log.Info(Log.INFO, "用户："+map.get("accId").toString()+"已重置密码为"+password);
			map.remove("accId");
			return ReturnMap.success();
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中resetPass异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@ResponseBody
	@RequestMapping("/logout")
	public ReturnMap logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		try {
			String accName=((Account) session.getAttribute("USER_LOGIN_KEY")).getAccName();
//			LoginInterceptor.IsLogin=false;
			map=new HashMap<String, Object>();
			session.setMaxInactiveInterval(-1);
			session.setAttribute("USER_LOGIN_KEY", new Account());
			log.Info(Log.INFO, "用户："+accName+"在"+request.getHeader("Referer")+"时注销登录");
			return ReturnMap.success();
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中logout异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@ResponseBody
	@RequestMapping("/registryVerification")
	public ReturnMap registryVerification(Account account,HttpSession session){
		try {
			int status=allService.registryVerification(account);
			if(status>0){
				if(((Account) session.getAttribute("USER_LOGIN_KEY")).getAccId()!=null&&((Account) session.getAttribute("USER_LOGIN_KEY")).getAccId()==status){
					return ReturnMap.success();
				}else {
					return ReturnMap.fail().put("error", "该邮箱已注册").put("local", "error5");
				}
			}
			switch (status) {
			case -1:
				return ReturnMap.fail().put("error", "该用户名已存在").put("local", "error1");
			case -2:
				return ReturnMap.fail().put("error", "该身份证号已经注册").put("local", "error4");
			case -3:
				return ReturnMap.fail().put("error", "该证件号码已经注册").put("local", "error11");
//			case -4:
//				return ReturnMap.fail().put("error", "该邮箱已注册").put("local", "error5");
			case -5:
				return ReturnMap.success();
			default:
				return ReturnMap.unknown();
			}
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中registryVerification异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	@ResponseBody
	@RequestMapping("/registry")
	public ReturnMap registry(Account account,HttpSession session){
		try {
//			Map<String,Object> map=new HashMap<String, Object>();
//			if(result.hasErrors())
//			{
//				List<FieldError> list=result.getFieldErrors();
//				for(FieldError error:list)
//				{
//					map.put(error.getField(),error.getDefaultMessage());
//				}
//				return ReturnMap.fail().put("errorInfo", map);
//			}else {
				List<Integer> mids=new ArrayList<Integer>();
				if(account.getModels()!=null){
					String[] models=account.getModels().split(",");
					for (String string : models) {
						if(!Utils.isNullOrEmpty(string)){
							mids.add(Integer.parseInt(string));
						}
					}
				}
				allService.registry(account,mids);
				session.setMaxInactiveInterval(-1);
				session.setAttribute("USER_LOGIN_KEY", account);
				log.Info(Log.INFO, "用户："+account.getAccName()+"注册成功");
				return ReturnMap.success();
//			}
		} catch (Exception e) {
			log.Warning(Log.WARNING, "AllController中registry异常："+Utils.getStackTrace(e), e);
			e.printStackTrace();
			return ReturnMap.unknown();
		}
	}
	//=====================================================首页
	@ResponseBody
	@RequestMapping("/getJxModelList")
	public ReturnMap getJxModelList(){
		//精选板块
		List<Model> jxModels = allService.getJxModelList();
		return ReturnMap.success().put("jxModels", jxModels);
	}
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		//获取首页需要信息
		boolean isLogin=!(((Account) session.getAttribute("USER_LOGIN_KEY"))==null||((Account) session.getAttribute("USER_LOGIN_KEY")).getAccId()==null);
		//新帖推荐
		List<BbsPost> newBbsPosts=null;
		//精华帖
		List<BbsPost> jhBbsPosts=null;
		//所有板块
		List<Model> allModel = allService.getAllModelList();
		if(isLogin){//已经登录
			Account account=((Account) session.getAttribute("USER_LOGIN_KEY"));
			newBbsPosts=allService.getNewBbsPostsByAcc(account.getAccId());
			jhBbsPosts=allService.getJhBbsPostsByAcc(account.getAccId());
		}else {
			newBbsPosts=allService.getNewBbsPosts();
			jhBbsPosts=allService.getJhBbsPosts();
		}
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("models", allModel);
		mav.addObject("newBbsPosts", newBbsPosts);
		mav.addObject("jhBbsPosts", jhBbsPosts);
		return mav;
	}
//	 <li><a href="/getAllHotBbsPosts">论坛热帖</a></li>
//	  <li><a href="/getAllNewBbsPosts">论坛新帖</a></li>
//	  <li><a href="/getAllJhBbsPosts">精华帖</a></li>
	@RequestMapping("/getAllHotBbsPosts")
	public ModelAndView getAllHotBbsPosts(int pageNum){
		ModelAndView mav = new ModelAndView("post/list");
		PageHelper.startPage(pageNum,10);
		List<BbsPost> list=allService.getAllHotBbsPosts();
		PageInfo<BbsPost> data= new PageInfo<BbsPost>(list);
		mav.addObject("data", data);
		return mav;
	}
	@RequestMapping("/getAllNewBbsPosts")
	public ModelAndView getAllNewBbsPosts(int pageNum){
		ModelAndView mav = new ModelAndView("post/list");
		PageHelper.startPage(pageNum,10);
		List<BbsPost> list=allService.getAllNewBbsPosts();
		PageInfo<BbsPost> data= new PageInfo<BbsPost>(list);
		mav.addObject("data", data);
		return mav;
	}
	@RequestMapping("/getAllJhBbsPosts")
	public ModelAndView getAllJhBbsPosts(int pageNum){
		ModelAndView mav = new ModelAndView("post/list");
		PageHelper.startPage(pageNum,10);
		List<BbsPost> list=allService.getAllJhBbsPosts();
		PageInfo<BbsPost> data= new PageInfo<BbsPost>(list);
		mav.addObject("data", data);
		return mav;
	}
	@RequestMapping("/getPostByModel")///getPostByModel?modelId=${model.modelId}&pageNum=1
	public ModelAndView getPostByModel(int modelId,int pageNum){
		ModelAndView mav = new ModelAndView("post/list");
		PageHelper.startPage(pageNum,10);
		List<BbsPost> list=allService.getPostByModel(modelId);
		PageInfo<BbsPost> data= new PageInfo<BbsPost>(list);
		mav.addObject("data", data);
		return mav;
	}
	@RequestMapping("/search")///search?word="+$('#search').val()+"&pageNum=1
	public ModelAndView search(String word,int pageNum,HttpServletRequest request) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView("post/list");
		PageHelper.startPage(pageNum,10);
		List<BbsPost> list=allService.search(word);
		PageInfo<BbsPost> data= new PageInfo<BbsPost>(list);
		mav.addObject("data", data);
		return mav;
	}
}
