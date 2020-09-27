package org.jeecg.modules.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.jeecg.modules.hospital.utils.TaskType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 类型转换器
 */
public class TaskTypeHandler implements TypeHandler<TaskType> {
    /**
     * 像JDBC一样进行占位并且 当传入TaskType实例时 使用setString方法进行类型转换，实现当传入数据库时写的是taskType但存的是一个整数
     */
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, TaskType taskType, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i,taskType.getValue());
    }
    /**
     * 查取的时候如果是列名就用getInt(String column)
     */
    @Override
    public TaskType getResult(ResultSet resultSet, String s) throws SQLException {
        return TaskType.getTaskTypeById (resultSet.getString(s));
    }
    /**
     * 查取的时候如果是列数就用getInt(int column)
     */
    @Override
    public TaskType getResult(ResultSet resultSet, int i) throws SQLException {
        return TaskType.getTaskTypeById (resultSet.getString(i));
    }
    /**
     * 查询过程同理
     */
    @Override
    public TaskType getResult(CallableStatement callableStatement, int i) throws SQLException {
        return TaskType.getTaskTypeById (callableStatement.getString(i));
    }
}
