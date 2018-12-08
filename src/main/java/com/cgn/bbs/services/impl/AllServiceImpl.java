package com.cgn.bbs.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgn.bbs.beans.AccessRecord;
import com.cgn.bbs.beans.Account;
import com.cgn.bbs.beans.BadRecord;
import com.cgn.bbs.beans.BbsPost;
import com.cgn.bbs.beans.Comment;
import com.cgn.bbs.beans.Dict;
import com.cgn.bbs.beans.Examine;
import com.cgn.bbs.beans.Files;
import com.cgn.bbs.beans.Idea;
import com.cgn.bbs.beans.Model;
import com.cgn.bbs.beans.Report;
import com.cgn.bbs.mapper.AllMapper;
import com.cgn.bbs.services.inte.IAllService;
import com.cgn.bbs.util.IdCardInfo;
import com.cgn.bbs.util.Utils;
import com.cgn.bbs.util.encryption.AesUtil;
import com.cgn.bbs.util.encryption.EncrypNotRollback;
import com.cgn.bbs.util.sensitivewdfilter.WordFilter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("allService")
@Transactional
public class AllServiceImpl implements IAllService {
	
	@Autowired
	private AllMapper allMapper;
	//================================================其他用户相关
	@Override
	public List<BbsPost> getMyPost(Integer accId) {
		return allMapper.getMyPost(accId);
	}
	@Override
	public List<Examine> getMyExamines(Integer accId) {
		return allMapper.getMyExamines(accId);
	}
	@Override
	public boolean addExamPost(Examine examine) {
		List<Examine> list=allMapper.checkExamPostRepeat(examine);
		if(list!=null&&list.size()>0)
			return false;
		allMapper.addExamPost(examine);
		return true;
	}
	//================================================其他管理员相关
	@Override
	public List<Examine> getExamineModelInfo() {
		return allMapper.getExamineModelInfo(null);
	}
	@Override
	public void updateExamine(Examine examine) {
		if(examine.getStatus().equals("WTG")){
			allMapper.updateExamineWtg(examine);
		}else {
			allMapper.updateExamineTg(examine);
			if(examine.getExamineType().equals("model")){
				Model model = new Model();model.setModelName(examine.getName());model.setPid(examine.getPid());
				List<Model> checkModelRepeat = allMapper.checkModelRepeat(model);
				if(checkModelRepeat==null||checkModelRepeat.size()==0)
					allMapper.updateExamineTgModel(examine);
			}else {
				Dict dict = new Dict();dict.setDictType(examine.getExamineType());dict.setTid(examine.getPid());
				List<Dict> checkDict = allMapper.checkDict(dict);
				if(checkDict==null||checkDict.size()==0)
					allMapper.updateExamineTgPost(examine);
			}
		}
	}
	@Override
	public List<Examine> getAllExamines() {
		return allMapper.getAllExamines();
	}
	@Override
	public List<Account> getAllUser() {
		return allMapper.getAllUser();
	}
	@Override
	public List<BbsPost> getAllPost() {
		return allMapper.getAllPost();
	}
	@Override
	public void accessExamModel(Examine examine) {
		allMapper.accessExamModel(examine);
		if("TG".equals(examine.getStatus())){
			examine = allMapper.getExamineModelInfo(examine.getExamineId()).get(0);
			Model model = new Model();
			model.setModelName(examine.getName());
			model.setPid(examine.getPid());
			model.setCreateAccountId(examine.getAccId());
			allMapper.addModel(model);
		}
	}
	@Override
	public void upPost(Dict dict) {
		allMapper.upPost(dict);
	}
	@Override
	public boolean changeAccState(Account account) {
		List<Account> list=allMapper.registryVerification(account.getAccId(),null,null,null,null);
		if(null!=list&&list.size()==1){
			account=list.get(0);
			account.setIsInUse(account.getIsInUse()?false:true);
			allMapper.changeAccState(account);
			return true;
		}else {
			return false;
		}
	}
	@Override
	public List<Dict> checkDict(Dict dict) {
		return allMapper.checkDict(dict);
	}
	//================================================意见反馈相关
	
	@Override
	public List<Report> getReportInfo() {
		return allMapper.getReportInfo();
	}
	@Override
	public List<BadRecord> getAllBadRecord() {
		return allMapper.getAllBadRecord();
	}
	@Override
	public List<Map<String, String>> getAllLog() {
		return allMapper.getAllLog();
	}
	@Override
	public List<Idea> getIdeaInfo() {
		return allMapper.getIdeaInfo();
	}
	@Override
	public void createReport(Report report) {
		allMapper.createReport(report);
	}
	@Override
	public void createIdea(Idea idea) {
		allMapper.createIdea(idea);
	}
	//================================================评论相关
	@Override
	public List<Comment> getCommentForPost(int postId) {
		return allMapper.getComment(postId);
	}
	@Override
	public void createComment(Comment comment) {
		if(WordFilter.isContains(comment.getCommentRealContent())){
			comment.setCommentContent(WordFilter.doFilter(comment.getCommentRealContent()));
			BadRecord badRecord=new BadRecord();
			badRecord.setAccId(comment.getAccId());badRecord.setPostId(comment.getPostId());
			badRecord.setRecordDetail("一条含有敏感字的评论，评论内容:"+comment.getCommentRealContent());
			allMapper.createBadRecord(badRecord);
		}else {
			comment.setCommentContent(comment.getCommentRealContent());
		}
		comment.setLou(1+allMapper.getMostLou(comment.getPostId()));
		allMapper.createComment(comment);
	}
	@Override
	public Integer getCommentByPid(Integer pid) {
		return allMapper.getCommentByPid(pid);
	}

	@Override
	public boolean deleteComment(Comment comment, Account account) {
		comment=allMapper.getCommentInfo(comment);
		if(account.getAccId()!=comment.getAccId())
			return false;
		allMapper.deleteComment(comment);
		return true;
	}
	//================================================帖子相关
	@Override
	public List<BbsPost> getPostInfo(int modelId) {
		return allMapper.getPostInfo(modelId);
	}
	@Override
	public void addAccessRecord(AccessRecord accessRecord) {
		allMapper.addAccessRecord(accessRecord);
	}
	@Override
	public void createBbsPost(BbsPost bbsPost) {
		bbsPost.setFileId(bbsPost.getFileId()==-1?null:bbsPost.getFileId());
		allMapper.createBbsPost(bbsPost);
//		bbsPost=getBbsPostInfoById(bbsPost.getPostId());
	}
	@Override
	public BbsPost getBbsPostInfoById(int postId) {
		BbsPost bbsPost=new BbsPost();
		bbsPost.setPostId(postId);
		return allMapper.getBbsPostById(bbsPost);
	}
	@Override
	public void updateBbsPost(BbsPost bbsPost) {
		allMapper.updateBbsPost(bbsPost);
	}
	@Override
	public Map<String, Object> getPostDetail(int postId,int pageNum, Account account3) {
		try {
			//获取帖子
			BbsPost bbsPost=allMapper.getPostDetail(postId);
			bbsPost.setComNum(allMapper.getComNumByPostId(postId));
			//访问加一
			if(null!=bbsPost&&null!=bbsPost.getAccId()&&account3!=null&&account3.getAccId()!=null&&bbsPost.getAccId()==account3.getAccId()){}else {
				allMapper.addPostVisitNum(postId);
			}
			allMapper.addModelVisitNum(bbsPost.getModelId());
			if(account3!=null&&account3.getAccId()!=null){
				AccessRecord accessRecord = new AccessRecord();accessRecord.setAccId(account3.getAccId());accessRecord.setModelId(bbsPost.getModelId());
				allMapper.addAccessRecord(accessRecord);
			}
			//获取评论
			PageHelper.startPage(pageNum,10);
			List<Comment> list=allMapper.getComment(postId);
			PageInfo<Comment> pageInfo=new PageInfo<Comment>(list);
			//获取文件
			Files files=null;
			if(!Utils.isNullOrEmpty(bbsPost.getFileId())){
				files=allMapper.getFileById(bbsPost.getFileId());
				if(null!=files&&!Utils.isNullOrEmpty(files.getFilePath())){
					files.setFilePath(files.getFilePath().substring(files.getFilePath().lastIndexOf("/")+1));
				}
			}
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("post", bbsPost);
			data.put("files", files);
			data.put("comment", pageInfo);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Files getFileById(int fileId) {
		return allMapper.getFileById(fileId);
	}
	@Override
	public void deletePost(int postId) {
		allMapper.deletePost(postId);
	}
	//================================================板块相关
	@Override
	public List<Model> getAllModel() {
		return allMapper.getAllModel(-1);
	}
	@Override
	public Integer addModel(Model model, String role) {
		if(role.equals("student")){
			return 3;
		}else {
			List<Model> list1=allMapper.checkModelRepeat(model);
			if(null!=list1&&list1.size()>0)
				return 4;
			Examine examine = new Examine();
			examine.setExamineType("model");
			examine.setName(model.getModelName());
			examine.setPid(model.getPid());
			List<Model> list2=allMapper.checkExamForModelRepeat(examine);
			if(null!=list2&&list2.size()>0)
				return 5;
			if(role.equals("admin")){
				allMapper.addModel(model);
				return 1;
			}else {
				examine.setAccId(model.getCreateAccountId());
				allMapper.addExamForModel(examine);
				return 2;
			}
		}
	}
	@Override
	public List<Model> getJxModelList() {
		return allMapper.getJxModelList();
	}
	@Override
	public Model getModelById(Model model) {
		model=allMapper.getModelById(model);
		return model;
	}
	@Override
	public Integer updateModel(Model model, Map<String, Object> map) {
		List<Model> list1=allMapper.checkModelRepeat(model);
		if(null!=list1&&list1.size()>0&&!(list1.get(0).getModelId().toString().equals(map.get("modelId"))))
			return 4;
		Examine examine = new Examine();
		examine.setExamineType("model");
		examine.setName(model.getModelName());
		examine.setPid(model.getPid());
		List<Model> list2=allMapper.checkExamForModelRepeat(examine);
		if(null!=list2&&list2.size()>0)
			return 5;
		model.setModelId(Integer.parseInt(map.get("modelId").toString()));
		allMapper.updateModel(model);
		return 1;
	}
	@Override
	public void deleteModel(Model model) {
		Model modelById = allMapper.getModelById(model);
		if(modelById.getPid()==-1){
			List<Model> list=allMapper.getChildModel(model);
			if(null!=list&&list.size()!=0){
				for (Model model2 : list) {
					allMapper.deletePostByModel(model2.getModelId());//删除帖子
				}
			}
			allMapper.deleteModelByModel(modelById.getModelId());
		}else {
			allMapper.deletePostByModel(modelById.getModelId());
		}
		allMapper.deleteModel(model);
	}
	//================================================登录相关
	@Override
	public Account login(Account account, String type) {
		try {
			if("1".equals(type)){
				String beizhu=account.getBeizhu();
				String[] userInfo = beizhu.split("||");
				account.setAccName(userInfo[0]);
				account.setAccPassword(userInfo[1]);
				return allMapper.login(account);
			}else {
				account.setAccPassword(EncrypNotRollback.md5Password(EncrypNotRollback.MD5(account.getAccPassword())));
				return allMapper.login(account);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Account changeAccountInfo(Account account) {
		if(!Utils.isNullOrEmpty(account.getRealPassword())){
			account.setAccPassword(EncrypNotRollback.md5Password(EncrypNotRollback.MD5(account.getRealPassword())));
		}else {
			account.setRealPassword(null);
		}
		allMapper.changeAccountInfo(account);
		return allMapper.queryAccountById(account.getAccId());
	}

	@Override
	public boolean verificationInfoForResetPass(Account account) {
		List<Account> list = allMapper.registryVerification(null,account.getAccName(), null, null, null);
		if(null==list||list.size()<1){
			return false;
		}else {
			account.setRegisterIDNum(AesUtil.parseByte2HexStr(AesUtil.encrypt(account.getRegisterIDNum(),list.get(0).getPassKey())));
		}
		account=allMapper.verificationInfoForResetPass(account);
		return !(null==account||null==account.getAccId());
	}

	@Override
	public void resetPass(String realPassword, String accId) {
		String password=EncrypNotRollback.md5Password(EncrypNotRollback.MD5(realPassword));
		allMapper.resetPass(password,realPassword,accId);
	}

	@Override
	public int registryVerification(Account account) {
		//1:accName 2:registerIDNum 3:accIDNum 4:email 5:通过 6:报错或未知
		try {
			String accName = account.getAccName();
			String registerIDNum = account.getRegisterIDNum();
			String accIDNum = account.getAccIDNum();
			String email = account.getEmail();
			if(registerIDNum!=null||accIDNum!=null){
				List<Account> allAccount = allMapper.getAllAccount();
				for (Account account2 : allAccount) {
					if(new String(AesUtil.decrypt(AesUtil.parseHexStr2Byte(account2.getRegisterIDNum()), account2.getPassKey()), "utf-8").equals(registerIDNum))
						return -2;
					if(new String(AesUtil.decrypt(AesUtil.parseHexStr2Byte(account2.getAccIDNum()), account2.getPassKey()), "utf-8").equals(accIDNum))
						return -3;
				}
			}
			List<Account> list=allMapper.registryVerification(null,accName,registerIDNum,accIDNum,email);
			if(list==null||(list!=null&&list.size()==0)){
				return -5;
			}else {
				if(accName!=null)
					return -1;
				else if(email!=null)
					return list.get(0).getAccId();
				else
					return -6;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 6;
		}
	}
	@Override
	public void registry(Account account, List<Integer> models) throws NumberFormatException, Exception {
		String passKey=AesUtil.getKeyRandom();
		account.setPassKey(passKey);
		account.setNativePlace(queryNativePlace(account.getRegisterIDNum().substring(0, 6)));
		account.setAccPassword(EncrypNotRollback.md5Password(EncrypNotRollback.MD5(account.getRealPassword())));
		account.setAccAge(Integer.parseInt(IdCardInfo.getIdCardInfo(account.getRegisterIDNum()).get("age").toString()));
		account.setAccIDNum(AesUtil.parseByte2HexStr(AesUtil.encrypt(account.getAccIDNum(),passKey)));
		account.setRegisterIDNum(AesUtil.parseByte2HexStr(AesUtil.encrypt(account.getRegisterIDNum(),passKey)));
		allMapper.registry(account);
		account=allMapper.queryAccountById(account.getAccId());
		if(models!=null&&models.size()>0){
			for (Integer modelId : models) {
				allMapper.registryIntention(account.getAccId(),modelId);
			}
		}
	}
	@Override
	public String savefile(String filePath) {
		Files files = new Files();
		files.setFilePath(filePath);
		allMapper.savefile(files);
		return files.getFileId().toString();
	}
	@Override
	public List<Model> getAllModelList() {
		return allMapper.getAllModelList();
	}
	
	@Override
	public List<BbsPost> getNewBbsPostsByAcc(Integer accId) {
		return allMapper.getNewBbsPostsByAcc(accId);
	}
	@Override
	public List<BbsPost> getJhBbsPostsByAcc(Integer accId) {
		return allMapper.getJhBbsPostsByAcc(accId);
	}
	@Override
	public List<BbsPost> getNewBbsPosts() {
		return allMapper.getNewBbsPosts(new BbsPost());
	}
	@Override
	public List<BbsPost> getJhBbsPosts() {
		return allMapper.getJhBbsPosts(new BbsPost());
	}
	@Override
	public List<BbsPost> getAllHotBbsPosts() {
		return allMapper.getAllHotBbsPosts();
	}
	@Override
	public List<BbsPost> getAllNewBbsPosts() {
		BbsPost bbsPost = new BbsPost();bbsPost.setPostId(-1);
		return allMapper.getNewBbsPosts(bbsPost);
	}
	@Override
	public List<BbsPost> getAllJhBbsPosts() {
		BbsPost bbsPost = new BbsPost();bbsPost.setPostId(-1);
		return allMapper.getJhBbsPosts(bbsPost);
	}
	@Override
	public List<BbsPost> getPostByModel(int modelId) {
		return allMapper.getPostByModel(modelId);
	}
	@Override
	public List<BbsPost> search(String word) {
		return allMapper.search(word);
	}
	//========================================================
	private String queryNativePlace(String nativePlaceNum) {
		return allMapper.queryNativePlace(nativePlaceNum).get("address").toString();
	}
}
