package cn.xyzs.api.mapper;

import cn.xyzs.api.pojo.XyExamManager;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface XyExamManagerMapper extends Mapper<XyExamManager> {
    @Select("<script>" +
            "SELECT p.*  FROM XY_EXAM_MANAGER m,XY_EXAM_PAPER p WHERE p.PAPERNO=m.PAPERNO AND m.EXAMCODE=#{examCode,jdbcType=VARCHAR}" +
            "</script>")
    public List<Map<String,Object>> getExamPaper(String examCode) throws SQLException;

    /**
     *
     * @Description: 抽取主观题,type=2
     * @author: GeWeiliang
     * @date: 2018\10\27 0027 14:50
     * @param: [questionNo]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT * FROM XY_QUESTION_MIND WHERE QUESTIONNO = #{questionNo}" +
            "</script>")
    public Map<String,Object> getMindQuestion(String questionNo) throws SQLException;

    /**
     *
     * @Description: 抽取填空题,type=
     * @author: GeWeiliang
     * @date: 2018\10\27 0027 14:52
     * @param: [questionNo]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT * FROM XY_QUESTION_FILL WHERE QUESTIONNO = #{questionNo}" +
            "</script>")
    public Map<String,Object> getFillQuestion(String questionNo) throws SQLException;

    /**
     *
     * @Description: 抽取选择题,type=1
     * @author: GeWeiliang
     * @date: 2018\10\27 0027 14:55
     * @param: [questionNo]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT * FROM XY_QUESTION_Multi WHERE QUESTIONNO = #{questionNo}" +
            "</script>")
    public Map<String,Object> getMultiQuestion(String questionNo) throws SQLException;

    /**
     *
     * @Description: 抽取判断题,type=0
     * @author: GeWeiliang
     * @date: 2018\10\27 0027 14:58
     * @param: [questionNo]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    @Select("<script>" +
            "SELECT * FROM XY_QUESTION_YN WHERE QUESTIONNO = #{questionNo}" +
            "</script>")
    public Map<String,Object> getYnQuestion(String questionNo) throws SQLException;

    @Insert("<script>" +
            "INSERT INTO XY_EXAM_ANSWER " +
            "VALUES(#{examCode,jdbcType=VARCHAR},#{empNo,jdbcType=VARCHAR},#{questionNo,jdbcType=VARCHAR}," +
            "#{answer,jdbcType=VARCHAR},#{spare,jdbcType=VARCHAR})" +
            "</script>")
    public void addAnswer(@Param("examCode") String examCode,@Param("empNo") String empNo,
                          @Param("questionNo") String questionNo, @Param("answer") String answer,
                          @Param("spare") String spare) throws SQLException;

    /**
     *
     * @Description: 查询答案是否已经添加过
     * @author: GeWeiliang
     * @date: 2018\10\28 0028 15:53
     * @param: [examCode, empNo, questionNo]
     * @return: int
     */
    @Select("<script>" +
            "SELECT COUNT(1) FROM XY_EXAM_ANSWER " +
            "WHERE EXAMCODE=#{examCode,jdbcType=VARCHAR} AND EMPNO=#{empNo,jdbcType=VARCHAR} AND QUESTIONNO=#{questionNo,jdbcType=VARCHAR} " +
            "</script>")
    public int isExist(@Param("examCode") String examCode,@Param("empNo") String empNo,@Param("questionNo") String questionNo) throws SQLException;

    /**
     *
     * @Description: 修改答案
     * @author: GeWeiliang
     * @date: 2018\10\28 0028 15:58
     * @param: [examCode, empNo, questionNo, answer, spare]
     * @return: void
     */
    @Update("<script>" +
            "UPDATE XY_EXAM_ANSWER SET ANSWER=#{answer,jdbcType=VARCHAR}" +
            "WHERE EXAMCODE=#{examCode,jdbcType=VARCHAR} AND EMPNO=#{empNo,jdbcType=VARCHAR} AND QUESTIONNO=#{questionNo,jdbcType=VARCHAR}" +
            "</script>")
    public void updateAnswer(@Param("examCode") String examCode,@Param("empNo") String empNo,
                             @Param("questionNo") String questionNo, @Param("answer") String answer) throws SQLException;
}
