package com.cgn.bbs.services.inte;

import java.util.List;
import java.util.Map;

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

public interface IAllService {

	Account login(Account account, String type);

	void registry(Account account, List<Integer> list) throws NumberFormatException, Exception ;

	int registryVerification(Account account);

	Account changeAccountInfo(Account account);

	boolean verificationInfoForResetPass(Account account);

	void resetPass(String password, String accId);

	List<Model> getAllModel();

	Integer addModel(Model model, String role);

	Model getModelById(Model model);

	Integer updateModel(Model model, Map<String, Object> map);

	void deleteModel(Model model);

	List<BbsPost> getPostInfo(int modelId);

	void addAccessRecord(AccessRecord accessRecord);

	void createBbsPost(BbsPost bbsPost);

	BbsPost getBbsPostInfoById(int postId);

	void updateBbsPost(BbsPost bbsPost);

	Map<String, Object> getPostDetail(int postId, int pageNum, Account account);

	void deletePost(int postId);

	List<Comment> getCommentForPost(int postId);

	void createComment(Comment comment);

	boolean deleteComment(Comment comment, Account account);

	List<Report> getReportInfo();

	void createReport(Report report);

	List<Idea> getIdeaInfo();

	void createIdea(Idea idea);

	List<Examine> getExamineModelInfo();

	void accessExamModel(Examine examine);

	void upPost(Dict dict);

	boolean changeAccState(Account account);

	String savefile(String filePath);

	List<BbsPost> getMyPost(Integer accId);

	Integer getCommentByPid(Integer pid);

	List<Examine> getMyExamines(Integer accId);

	List<BbsPost> getAllPost();

	List<Dict> checkDict(Dict dict);

	List<Account> getAllUser();

	List<Examine> getAllExamines();

	void updateExamine(Examine examine);

	List<BadRecord> getAllBadRecord();

	List<Map<String, String>> getAllLog();

	Files getFileById(int fileId);

	boolean addExamPost(Examine examine);

	List<Model> getAllModelList();

	List<BbsPost> getNewBbsPostsByAcc(Integer accId);

	List<BbsPost> getJhBbsPostsByAcc(Integer accId);

	List<BbsPost> getNewBbsPosts();

	List<BbsPost> getJhBbsPosts();

	List<Model> getJxModelList();

	List<BbsPost> getAllHotBbsPosts();

	List<BbsPost> getAllNewBbsPosts();

	List<BbsPost> getAllJhBbsPosts();

	List<BbsPost> getPostByModel(int modelId);

	List<BbsPost> search(String string);


}
