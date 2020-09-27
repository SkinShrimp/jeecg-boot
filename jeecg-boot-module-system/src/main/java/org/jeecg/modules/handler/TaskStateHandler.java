package org.jeecg.modules.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.jeecg.modules.hospital.utils.TaskState;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 类型转换器  getTaskStateById
 */
public class TaskStateHandler implements TypeHandler<TaskState> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, TaskState taskState, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i,taskState.getValue());

    }

    @Override
    public TaskState getResult(ResultSet resultSet, String s) throws SQLException {
        return TaskState.getTaskStateById (resultSet.getString(s));

    }

    @Override
    public TaskState getResult(ResultSet resultSet, int i) throws SQLException {
        return TaskState.getTaskStateById  (resultSet.getString(i));
    }

    @Override
    public TaskState getResult(CallableStatement callableStatement, int i) throws SQLException {
        return TaskState.getTaskStateById  (callableStatement.getString(i));
    }
}
