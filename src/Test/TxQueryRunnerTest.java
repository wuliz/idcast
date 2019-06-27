package Test;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.JdbcUtils;
import cn.itcast.jdbc.TxQueryRunner;
import entity.Address;
import entity.Admin;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TxQueryRunnerTest {
    /*
    测试update方法
     */
    @Test
    public void testUpdate() throws  Exception{
        String sql="insert into t_admin(adminId,adminname,adminpwd) values(?,?,?)";
        Object[] params={"2","wulz","123456"};
        QueryRunner qr = new TxQueryRunner();
        qr.update(sql,params);//不需要提供连接，内部的jdbcUtils会获取连接
    }
    /*
    测试带事务的update方法
     */
    @Test
    public void testUpdateTransaction(){
        try{
            JdbcUtils.beginTransaction();
            String sql="insert into t_admin(adminId,adminname,adminpwd) values(?,?,?)";
            QueryRunner qr = new TxQueryRunner();
            Object[] params={"4","wul11z","123"};
            qr.update(sql,params);
            /*if(true){
                throw new Exception();
            }*/
            Object[] params2={"3","wu23slz1","123"};
            qr.update(sql,params2);
           JdbcUtils.commitTransaction();
        }catch (Exception e){
            try{
                JdbcUtils.rollbackTransaction();
            }catch (Exception e1){

            }

        }
    }
    /*
    测试查询方法
    jdbc查询得到的是ResultSet对象
    QueryRunner查询的结果是通过ResultSet映射后的数据
    javabean:将结果封装称JavaBean中
    map:将结果封装称map中
    object：将结果封装成object中（如果结果集是单行单列）
     */
    @Test
    public void testQuery() throws SQLException{
        String  sql="select *from t_admin where adminId=?";
        QueryRunner qr = new TxQueryRunner();
        /*
        第二个参数类型为ResultSetHander,它表示一个接口，表示结果集的对象
         */
        Admin admin = qr.query(sql, new BeanHandler<Admin>(Admin.class), "1");
        System.out.println(admin);

    }

    /*
    封装成List<Bean>
     */
    @Test
    public void testQuery1() throws Exception{
        QueryRunner qr = new TxQueryRunner();
        String sql="select *from t_admin";
        List<Admin> list = qr.query(sql, new BeanListHandler<Admin>(Admin.class));
        System.out.println(list);

    }

    /*
    封装成map
     */
    @Test
    public void testQueryMap() throws  Exception{
        String sql="select *from t_admin where adminId=?";
        TxQueryRunner qr = new TxQueryRunner();
        Map<String, Object> m = qr.query(sql, new MapHandler(), "1");
        System.out.println(m);
    }

    /*
    封装成List<Map>
     */

    @Test
    public void testQueryListMap() throws  Exception{
        String sql="select *from t_admin";
        TxQueryRunner qr = new TxQueryRunner();
        List<Map<String, Object>> list = qr.query(sql, new MapListHandler());
        System.out.println(list);
    }

    /*
    封装到Object中
    单行单列的封装
     */
    @Test
    public void testQueryObject() throws Exception{
        String sql="select count(*) t_admin ";
        TxQueryRunner qr = new TxQueryRunner();
        Object o = qr.query(sql, new ScalarHandler());
        System.out.println(o);
    }

    /*
    查询的结果涉及多表
    先将结果集放在map中
    如果一个表中存在外键，需要修改JavaBean
    具体操作如下
     */
    @Test
    public void testQueryBetweenTables() throws  Exception{
        String sql="select * from t_admin t,t_address a where t.adminId=a.adminId and t.adminId=?";
        TxQueryRunner qr = new TxQueryRunner();
        //得到单行map对象
        Map<String, Object> map = qr.query(sql, new MapHandler(),"a1");
        //将map封装到Admin对象中
        Admin admin = CommonUtils.toBean(map, Admin.class);
        //将map封装到Address中
        Address address = CommonUtils.toBean(map, Address.class);
        //建立两者的联系
        address.setAdmin(admin);
        System.out.println(address);
    }
}
