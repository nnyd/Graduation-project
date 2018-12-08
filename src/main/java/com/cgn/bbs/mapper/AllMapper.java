package com.cgn.bbs.mapper;

import java.util.List;
import java.util.Map;

import net.sf.jsqlparser.statement.select.Join;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

public interface AllMapper {
	@Select(" select * from account where accId!=-1 ")
	List<Account> getAllAccount();
	@Select(" select a.*,f.filePath headPath from account a left join files f on a.headImg=f.fileId where accName=#{accName} and accPassword=#{accPassword} ")
	Account login(Account account);
	@Insert(" insert into account "
			+ "	(accName,accPassword,accRealName,accIDNum,registerName, "
			+ "	registerIDNum,accSex,accAge,hobby,nativePlace,address, "
			+ "	role,realPassword,passKey,schoolPro,schoolCity,schoolName,email) "
			+ "	values "
			+ "	(#{accName},#{accPassword},#{accRealName},#{accIDNum},#{registerName}, "
			+ "	#{registerIDNum},#{accSex},#{accAge},#{hobby},#{nativePlace},#{address}, "
			+ "	#{role},#{realPassword},#{passKey},#{schoolPro},#{schoolCity},#{schoolName},#{email}) ")
	@Options(useGeneratedKeys = true, keyProperty = "accId", keyColumn = "accId")
	void registry(Account account);
	@Select("select a.*,f.filePath headPath from account a left join files f on a.headImg=f.fileId where accId=#{accId} ")
	Account queryAccountById(@Param("accId") int id);
	@Select(" select address from utils.cardnumadd where lsixid=#{lsixid} ")
	Map<String, Object> queryNativePlace(@Param("lsixid") String nativePlaceNum);
	
	@Select(" <script> "
			+ "	select * from account where 1=1 "
			+ "	<if test='accName!=null'> and accName=#{accName} </if> "
			+ "	<if test='registerIDNum!=null'> and registerIDNum=#{registerIDNum} </if> "
			+ "	<if test='accIDNum!=null'> and accIDNum=#{accIDNum} </if> "
			+ "	<if test='email!=null'> and email=#{email} </if> "
			+ "	<if test='accId!=null'> and accId=#{accId} </if> "
			+ " </script>")
	List<Account> registryVerification(@Param("accId") Integer accId,@Param("accName") String accName,@Param("registerIDNum") String registerIDNum,
			@Param("accIDNum") String accIDNum,@Param("email") String email);
	@Insert(" insert into intention "
			+ "	(accId,modelId) "
			+ "	values "
			+ "	(#{accId},#{modelId}) ")
	void registryIntention(@Param("accId") Integer accId,@Param("modelId") Integer modelId);
	@Update(" <script> UPDATE account "
			+ " set accName=#{accName}, "
			+ "	<if test='realPassword!=null'> accPassword=#{accPassword},realPassword=#{realPassword}, </if> "
			+ "	hobby=#{hobby},address=#{address},email=#{email},headImg=#{headImg} "
			+ "	where accId=#{accId} </script> ")
	void changeAccountInfo(Account account);
	
	@Select(" select * from account where "
			+ "	accName=#{accName} and registerName=#{registerName} and registerIDNum=#{registerIDNum} "
			+ "	and schoolPro=#{schoolPro} and schoolCity=#{schoolCity} and schoolName=#{schoolName} "
			+ "	and email=#{email} ")
	Account verificationInfoForResetPass(Account account);
	@Update(" update account "
			+ "	accPassword=#{accPassword}, "
			+ "	realPassword=#{realPassword} "
			+ "	where accId=#{accId} ")
	void resetPass(@Param("accPassword") String password,@Param("realPassword") String realPassword,@Param("accId") String accId);
	@Select(" select m.*,a.accName from model m left join account a on m.createAccountId=a.accId where pid=#{pid} ")
	@Results({@Result(property = "modelId",column = "modelId"),
			@Result(property = "modelName",column = "modelName"),
			@Result(property = "pid",column = "pid"),
			@Result(property = "createAccountId",column = "createAccountId"),
			@Result(property = "createTime",column = "createTime"),
			@Result(property = "visitNum",column = "visitNum"),
			@Result(property = "accName",column = "accName"),
			@Result(property = "childList",javaType = List.class,column ="modelId",
                    many = @Many(select = "com.cgn.bbs.mapper.AllMapper.getAllModel"))})
	List<Model> getAllModel(@Param("pid") int pid);
	@Insert(" insert into model "
			+ "	(modelName,pid,createAccountId) "
			+ "	values "
			+ "	(#{modelName},#{pid},#{createAccountId}) ")
	@Options(useGeneratedKeys = true, keyProperty = "modelId", keyColumn = "modelId")
	void addModel(Model model);
	@Insert(" insert into examine "
			+ "	(examineType,pid,name,accId) "
			+ "	values "
			+ "	(#{examineType},#{pid},#{name},#{accId}) ")
	void addExamForModel(Examine examine);
	@Select(" select modelId from model where modelName=#{modelName} and pid=#{pid} ")
	List<Model> checkModelRepeat(Model model);
	@Select(" select examineId from examine where examineType=#{examineType} and pid=#{pid} and name=#{name} ")
	List<Model> checkExamForModelRepeat(Examine examine);
	@Select(" select * from model where modelId=#{modelId} ")
	Model getModelById(Model model);
	@Update(" update model "
			+ "	set "
			+ "		modelName=#{modelName}, "
			+ "		pid=#{pid} "
			+ "	where modelId=#{modelId} ")
	void updateModel(Model model);
	@Delete(" delete from model where modelId=#{modelId} ")
	void deleteModel(Model model);
	@Select(" select postId,postTitle,postDetil,bs.accId,bs.createTime,bs.modelId, "
			+ "	bs.visitNum,accName,m1.modelName modelName1,m2.modelName modelName2 "
			+ "	from bbspost bs left join model m1 on bs.modelId=m1.modelId "
			+ "	left join model m2 on m1.pid=m2.modelId "
			+ "	left join account a on bs.accId=a.accId ")
	List<BbsPost> getPostInfo(int modelId);
	@Insert(" insert into accessrecord "
			+ "	(accId,modelId) values (#{accId},#{modelId}) ")
	void addAccessRecord(AccessRecord accessRecord);
	@Insert(" insert into bbspost "
			+ "	(postTitle,postDetil,accId,modelId,fileId) "
			+ "	values "
			+ "	(#{postTitle},#{postDetil},#{accId},#{modelId},#{fileId}) ")
	@Options(useGeneratedKeys = true, keyProperty = "postId", keyColumn = "postId")
	void createBbsPost(BbsPost bbsPost);
	@Select(" select * from bbspost where postId=#{postId} ")
	BbsPost getBbsPostById(BbsPost bbsPost);
	@Update(" update bbspost "
			+ "	set "
			+ "	postTitle=#{postTitle}, "
			+ "	postDetil=#{postDetil}, "
			+ "	fileId=#{fileId} "
			+ "	where postId=#{postId} ")
	void updateBbsPost(BbsPost bbsPost);
	@Select(" select p.*,a.accName,a.accSex,a.role,m.modelName modelName1,f.filePath headPath from bbspost p "
			+ "	left join account a on p.accId=a.accId "
			+ "	left join files f on a.headImg=f.fileId "
			+ "	left join model m on p.modelId=m.modelId "
			+ "	where postId=#{postId} ")
	BbsPost getPostDetail(@Param("postId") int postId);
	@Update(" update bbspost "
			+ "	set "
			+ "	visitNum=(1+(select a.visitNum from (select visitNum from bbspost where postId=#{postId}) a)) "
			+ "	where postId=#{postId} ")
	void addPostVisitNum(@Param("postId") int postId);
	@Select(" select a1.commentId,a1.postId,a1.accId,a1.commentContent,a1.commentRealContent,a1.pid,a1.createTime, "
			+ "	a1.lou,a1.accName,a1.deleted,a1.headPath,a1.accSex,a1.role,a2.accName accNamePare,a2.commentContent commentContentPare,a2.lou louPare  from "
			+ "	(select c.*,a.accName,f.filePath headPath,a.accSex,a.role from `comment` c left join account a on c.accId=a.accId "
			+ "	left join files f on a.headImg=f.fileId where postId=#{postId}) a1 left join "
			+ "	(select c.*,a.accName from `comment` c left join account a on c.accId=a.accId where postId=#{postId}) a2 on a1.pid=a2.commentId "
			+ "	order by a1.lou ")
	List<Comment> getComment(@Param("postId") int postId);
	@Delete(" delete from bbspost where postId=#{postId} ")
	void deletePost(@Param("postId") int postId);
	@Insert(" insert into `comment` "
			+ "	(postId,accId,commentContent,commentRealContent,pid,lou) "
			+ "	values "
			+ "	(#{postId},#{accId},#{commentContent},#{commentRealContent},#{pid},#{lou}) ")
	void createComment(Comment comment);
	@Insert(" insert into badrecord "
			+ "	(accId,postId,recordDetail) "
			+ "	values "
			+ "	(#{accId},#{postId},#{recordDetail}) ")
	void createBadRecord(BadRecord badRecord);
	@Select(" select IFNULL(max(lou),0) lou from `comment` where postId=#{postId} ")
	Integer getMostLou(@Param("postId") Integer postId);
	@Select(" select * from `comment` where commentId=#{commentId} ")
	Comment getCommentInfo(Comment comment);
	@Update(" update `comment` "
			+ "	set deleted=true "
			+ "	where commentId=#{commentId} ")
	void deleteComment(Comment comment);
	@Select(" select r.*,a.accName,b.accName reportaccName from report r "
			+ "	left join account a on r.accId=a.accId "
			+ "	left join account b on r.reportAccId=b.accId "
			+ "	order by reportTime desc ")
	List<Report> getReportInfo();
	@Insert(" insert into report "
			+ "	(accId,reportAccId,reportType,reportDetail) "
			+ "	values "
			+ "	(#{accId},#{reportAccId},#{reportType},#{reportDetail}) ")
	@Options(useGeneratedKeys = true, keyProperty = "reportId", keyColumn = "reportId")
	void createReport(Report report);
	@Select(" select i.*,a.accName from idea i "
			+ "	left join account a on i.accId=a.accId "
			+ " order by createTime desc ")
	List<Idea> getIdeaInfo();
	@Insert(" insert into idea "
			+ "	(ideaContent,accId) "
			+ "	values "
			+ "	(#{ideaContent},#{accId}) ")
	@Options(useGeneratedKeys = true, keyProperty = "ideaId", keyColumn = "ideaId")
	void createIdea(Idea idea);
	@Select(" <script> select e.*,a.accName,m.modelName from examine e "
			+ "	left join account a on e.accId=a.accId "
			+ "	left join model m on e.pid=m.modelId "
			+ "	where examineType='model' <if test='examineId!=null'> and examineId=#{examineId} </if> </script> ")
	List<Examine> getExamineModelInfo(@Param("examineId") Integer examineId);
	@Update(" update examine "
			+ "	set status=#{status} "
			+ "	where examineId=#{examineId} ")
	void accessExamModel(Examine examine);
	@Insert(" insert into dict "
			+ "	(dictType,tid,beizhu) "
			+ "	values "
			+ "	(#{dictType},#{tid},#{beizhu}) ")
	void upPost(Dict dict);
	@Select(" select * from dict where tId=#{tid} and dictType=#{dictType} ")
	List<Dict> checkDict(Dict dict);
	@Update(" update account "
			+ "	set isInUse=#{isInUse} "
			+ "	where accId=#{accId} ")
	void changeAccState(Account account);
	@Insert(" insert into files "
			+ "	(filePath) "
			+ "	values "
			+ "	(#{filePath}) ")
	@Options(useGeneratedKeys = true, keyProperty = "fileId", keyColumn = "fileId")
	void savefile(Files files);
	@Select(" select p.*,a.accName,m1.modelName modelName1,m2.modelName modelName2 from bbspost p "
			+ "	left join account a on p.accId=a.accId "
			+ "	left join model m1 on p.modelId=m1.modelId "
			+ "	left join model m2 on m1.pid=m2.modelId "
			+ "	where p.accId=#{accId} "
			+ "	order by createTime desc ")
	List<BbsPost> getMyPost(@Param("accId") Integer accId);
	@Select(" select count(*) comNum from `comment` where postId=#{postId} ")
	String getComNumByPostId(@Param("postId") int postId);
	@Select(" select postId from comment where commentId=#{commentId} ")
	Integer getCommentByPid(@Param("commentId") Integer pid);
	@Select(" select examineId,examineType,e.pid,e.name,`status`,e.accId,accName,m1.modelName,postTitle,m2.modelName postModel,e.createTime from examine e "
			+ "	left join account a on e.accId=a.accId "
			+ "	left join bbspost b on e.pid=b.postId "
			+ "	left join model m1 on e.pid=m1.modelId "
			+ "	left join model m2 on b.modelId=m2.modelId where e.accId=#{accId} "
			+ "	order by e.createTime desc ")
	List<Examine> getMyExamines(@Param("accId") Integer accId);
	@Select(" select p.*,a.accName,m1.modelName modelName1,m2.modelName modelName2 from bbspost p "
			+ "	left join account a on p.accId=a.accId "
			+ "	left join model m1 on p.modelId=m1.modelId "
			+ "	left join model m2 on m1.pid=m2.modelId "
			+ "	order by createTime desc ")
	List<BbsPost> getAllPost();
	@Select(" select * from account where accId!=-1 ")
	List<Account> getAllUser();
	@Select(" select examineId,examineType,e.pid,e.name,`status`,e.accId,accName,role,m1.modelName,postTitle,m2.modelName postModel,e.createTime from examine e "
			+ "	left join account a on e.accId=a.accId "
			+ "	left join bbspost b on e.pid=b.postId "
			+ "	left join model m1 on e.pid=m1.modelId "
			+ "	left join model m2 on b.modelId=m2.modelId "
			+ "	order by e.createTime desc")
	List<Examine> getAllExamines();
	@Update(" UPDATE examine set `status`='WTG' where examineId=#{examineId} ")
	void updateExamineWtg(Examine examine);
	@Update(" UPDATE examine set `status`='TG' where examineId=#{examineId} ")
	void updateExamineTg(Examine examine);
	@Insert(" insert into model "
			+ "	(modelName,pid,createAccountId) "
			+ "	(select `name`,pid,accId from examine where examineId=#{examineId}) ")
	void updateExamineTgModel(Examine examine);
	@Insert(" insert into dict "
			+ "	(dictType,tid) "
			+ "	(select examineType,pid from examine where examineId=#{examineId}) ")
	void updateExamineTgPost(Examine examine);
	@Select(" select b.*,a.accName,bp.postTitle from badrecord b "
			+ "	left join account a on b.accId=a.accId "
			+ "	left join bbspost bp on b.postId=bp.postId ")
	List<BadRecord> getAllBadRecord();
	@Select(" select logInfo,createTime from log where logType='INFO'"
			+ "	order by createTime desc ")
	List<Map<String, String>> getAllLog();
	@Select(" select fileId,filePath from files where fileId=#{fileId} ")
	Files getFileById(@Param("fileId") Integer fileId);
	@Select(" select * from model where pid=#{modelId} ")
	List<Model> getChildModel(Model model);
	@Delete(" delete from bbspost where modelId=#{modelId} ")
	void deletePostByModel(@Param("modelId") Integer modelId);
	@Delete(" delete from model where pid=#{modelId} ")
	void deleteModelByModel(@Param("modelId") Integer modelId);
	@Insert(" insert into examine "
			+ "	(examineType,pid,accId) "
			+ "	values "
			+ "	(#{examineType},#{pid},#{accId})")
	void addExamPost(Examine examine);
	@Select(" select * from examine where examineType=#{examineType} and pid=#{pid} ")
	List<Examine> checkExamPostRepeat(Examine examine);
	@Select(" select m1.modelId,m1.modelName,m2.modelName pareName from model m1 "
			+ "	left join model m2 on m1.pid=m2.modelId "
			+ "	where m1.pid!=-1 "
			+ "	order by m1.pid ")
	List<Model> getAllModelList();
	@Select(" select a.postId,a.modelName modelName1,a.postTitle,a.visitNum,ifnull(b.num,0) comNum,a.createTime from ( "
			+ "	(select b.postId,m.modelName,b.postTitle,b.visitNum,b.createTime from ( "
			+ "	select @rownum:=@rownum+1 rownum,modelId,count(*) num from accessrecord,(select @rownum:=0) r "
			+ "	where accId=#{accId} "
			+ "	GROUP BY modelId "
			+ "	order by num desc) a left join bbspost b on a.modelId=b.modelId "
			+ "	left join model m on b.modelId=m.modelId "
			+ "	left join account ac on b.accId=ac.accId "
			+ "	order by a.rownum asc,b.createTime desc)union all "
			+ "	(select b.postId,m.modelName,b.postTitle,b.visitNum,b.createTime from bbspost b "
			+ "	left join model m on b.modelId=m.modelId "
			+ "	left join account ac on b.accId=ac.accId "
			+ "	where b.modelId not in(select distinct modelId from accessrecord where accId=#{accId}) "
			+ "	order by b.createTime desc)) a "
			+ "	left join (select postId,count(*) num from `comment` group by postId) b "
			+ "	on a.postId=b.postId "
			+ "	LIMIT 0,5 ")
	List<BbsPost> getNewBbsPostsByAcc(@Param("accId") Integer accId);
	@Select(" select a.postId,a.modelName modelName1,a.postTitle,a.visitNum,ifnull(b.num,0) comNum,a.createTime from ( "
			+ "	(select b.postId,m.modelName,b.postTitle,b.visitNum,b.createTime from ( "
			+ "	select @rownum:=@rownum+1 rownum,modelId,count(*) num from accessrecord,(select @rownum:=0) r "
			+ "	where accId=#{accId} "
			+ "	GROUP BY modelId "
			+ "	order by num desc) a left join bbspost b on a.modelId=b.modelId "
			+ "	left join model m on b.modelId=m.modelId "
			+ "	left join account ac on b.accId=ac.accId "
			+ "	where b.postId in (select tid from dict where dictType='JH') "
			+ "	order by a.rownum asc,b.createTime desc)union all "
			+ "	(select b.postId,m.modelName,b.postTitle,b.visitNum,b.createTime from bbspost b "
			+ "	left join model m on b.modelId=m.modelId "
			+ "	left join account ac on b.accId=ac.accId "
			+ "	where b.postId not in(select b.postId from ( "
			+ "	select @rownum:=@rownum+1 rownum,modelId,count(*) num from accessrecord,(select @rownum:=0) r "
			+ "	where accId=#{accId} "
			+ "	GROUP BY modelId "
			+ "	order by num desc) a left join bbspost b on a.modelId=b.modelId "
			+ "	left join model m on b.modelId=m.modelId "
			+ "	left join account ac on b.accId=ac.accId "
			+ "	where b.postId in (select tid from dict where dictType='JH')) and b.postId in (select tid from dict where dictType='JH') "
			+ "	order by b.createTime desc)) a "
			+ "	left join (select postId,count(*) num from `comment` group by postId) b "
			+ "	on a.postId=b.postId "
			+ "	LIMIT 0,5 ")
	List<BbsPost> getJhBbsPostsByAcc(@Param("accId") Integer accId);
	@Select(" <script> select b.postId,m.modelName modelName1,b.postTitle,b.visitNum,ifnull(n.num,0) comNum,b.createTime from bbspost b "
			+ "	left join model m on b.modelId=m.modelId "
			+ "	left join account ac on b.accId=ac.accId "
			+ "	left join (select postId,count(*) num from `comment` group by postId) n on b.postId=n.postId "
			+ "	order by b.createTime desc "
			+ "	<if test='postId==null'>LIMIT 0,5 </if></script> ")
	List<BbsPost> getNewBbsPosts(BbsPost bbsPost);
	@Select(" <script> select b.postId,m.modelName modelName1,b.postTitle,b.visitNum,ifnull(n.num,0) comNum,b.createTime from bbspost b "
			+ "	left join model m on b.modelId=m.modelId "
			+ "	left join account ac on b.accId=ac.accId "
			+ "	left join (select postId,count(*) num from `comment` group by postId) n on b.postId=n.postId "
			+ "	where b.postId in(select tid from dict where dictType='JH') "
			+ "	order by b.createTime desc "
			+ "	<if test='postId==null'>LIMIT 0,5 </if></script>")
	List<BbsPost> getJhBbsPosts(BbsPost bbsPost);//<if test='examineId!=null'> and examineId=#{examineId} </if> </script>
	@Update(" update model "
			+ "	set visitNum=(select a.num+1 from (select visitNum num from model where modelId=#{modelId}) a) "
			+ "	where modelId=#{modelId} ")
	void addModelVisitNum(@Param("modelId") Integer modelId);
	@Select(" select modelId,modelName,visitNum from model "
			+ "	where pid!=-1 order by visitNum desc "
			+ "	limit 0,3 ")
	List<Model> getJxModelList();
	@Select(" select b.postId,m.modelName modelName1,b.postTitle,b.visitNum,ifnull(n.num,0) comNum,b.createTime from bbspost b "
			+ "	left join model m on b.modelId=m.modelId "
			+ "	left join account ac on b.accId=ac.accId "
			+ "	left join (select postId,count(*) num from `comment` group by postId) n on b.postId=n.postId "
			+ "	where b.visitNum>50 "
			+ "	order by b.createTime desc ")
	List<BbsPost> getAllHotBbsPosts();
	@Select(" select b.postId,m.modelName modelName1,b.postTitle,b.visitNum,ifnull(n.num,0) comNum,b.createTime from bbspost b "
			+ "	left join model m on b.modelId=m.modelId "
			+ "	left join account ac on b.accId=ac.accId "
			+ "	left join (select postId,count(*) num from `comment` group by postId) n on b.postId=n.postId "
			+ "	where b.modelId=#{modelId} "
			+ "	order by b.createTime desc ")
	List<BbsPost> getPostByModel(@Param("modelId") int modelId);
	@Select(" select b.postId,m.modelName modelName1,b.postTitle,b.visitNum,ifnull(n.num,0) comNum,b.createTime from bbspost b "
			+ "	left join model m on b.modelId=m.modelId "
			+ "	left join account ac on b.accId=ac.accId "
			+ "	left join (select postId,count(*) num from `comment` group by postId) n on b.postId=n.postId "
			+ "	where b.postTitle like CONCAT('%',#{modelId},'%') "
			+ "	order by b.createTime desc ")
	List<BbsPost> search(String word);

}
