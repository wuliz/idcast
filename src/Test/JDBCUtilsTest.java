package Test;

import cn.itcast.jdbc.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtilsTest {
    /*
    jdbcUtils测试类
    底层使用了c3p0连接池
    还需要mysql驱动
     */

    /*
    底层使用了c3p0连接池，需要修改c3p0的配置文件
     */
    @Test
    public void testGetConnection(){

        try{
            Connection conn = JdbcUtils.getConnection();
            System.out.println(conn);
            JdbcUtils.releaseConnection(conn);
            System.out.println(conn.isClosed());
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }
    /*
    jdbc提供一下与事物相关的功能
     */
    @Test
    public void testTransaction(){

        try{
            //开启事务
            JdbcUtils.beginTransaction();
            //数据库操作

            //提交事务
            JdbcUtils.commitTransaction();
        }catch (Exception e){
            //回滚
            try{
                JdbcUtils.rollbackTransaction();
            }catch (SQLException e1){

            }

        }


    }
}
