package cn.jdx.mapper;

import cn.jdx.entity.StuCenter;
import cn.jdx.entity.StuCenterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StuCenterMapper {
    long countByExample(StuCenterExample example);

    int deleteByExample(StuCenterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StuCenter record);

    int insertSelective(StuCenter record);

    List<StuCenter> selectByExample(StuCenterExample example);

    StuCenter selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StuCenter record, @Param("example") StuCenterExample example);

    int updateByExample(@Param("record") StuCenter record, @Param("example") StuCenterExample example);

    int updateByPrimaryKeySelective(StuCenter record);

    int updateByPrimaryKey(StuCenter record);
}