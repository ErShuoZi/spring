package com.java.spring.test;

import com.java.spring.bean.Monster;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.JdbcAccessor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author liushuo
 * @version 1.0
 */
public class JdbcTemplateTest {
    @Test
    public void TestDataSourceByJdbcTemplate() throws SQLException {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        DataSource dataSource = ioc.getBean(DataSource.class);
        //获取到connection
        Connection connection = dataSource.getConnection();
        System.out.println(connection);


        System.out.println("ok");
    }

    //通过JdbcTemplate完成数据添加
    @Test
    public void addDataByJdbcTemplate() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);
        System.out.println(jdbcTemplate);

//        String sql =
//                "INSERT INTO monster VALUES(400,'红孩儿','红缨枪')";
//        jdbcTemplate.execute(sql);

        String sql =
                "INSERT INTO monster VALUES(?,?,?)";
        int affected = jdbcTemplate.update(sql, 500, "张三", "睡觉");
        System.out.println("添加成功");
    }

    @Test
    public void addDataByJdbcTemplateRemove() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);
        String sql = "UPDATE monster SET skill=? where id = ?";
        int affected = jdbcTemplate.update(sql, "吃饭", 500);
        System.out.println("ok~ affected:" + affected);
    }

    @Test
    public void addDataByJdbcTemplateBath() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);
        String sql =
                "INSERT INTO monster VALUES(?,?,?)";
        List<Object[]> batchArgs = new ArrayList<>();
        batchArgs.add(new Object[]{600,"老鼠精","偷吃"});
        batchArgs.add(new Object[]{700,"猫精","抓老鼠"});
        int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);

        for (int anInt : ints) {
            System.out.println("anInt=" + anInt);
        }
        System.out.println("批量添加成功~");

    }



//查询后封装成对象
    @Test
    public void SelectDataByJdbcTemplate() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);

        String sql = "SELECT id AS monsterId,name,skill FROM monster WHERE id = 100";

        RowMapper<Monster> monsterBeanPropertyRowMapper = new BeanPropertyRowMapper<>(Monster.class);
        Monster monster = jdbcTemplate.queryForObject(sql, monsterBeanPropertyRowMapper);
        System.out.println(monster);
    }


    @Test
    public void SelectDataByJdbcTemplateList() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);

        String sql = "SELECT id AS monsterId,name,skill FROM monster WHERE id >= ?";

        RowMapper<Monster> monsterBeanPropertyRowMapper = new BeanPropertyRowMapper<>(Monster.class);
        List<Monster> monsterList = jdbcTemplate.query(sql, monsterBeanPropertyRowMapper, 100);
        for (Monster monster : monsterList) {
            System.out.println(monster);
        }


    }


    @Test
    public void SelectDataByJdbcTemplateColumn() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);

        String sql = "SELECT name FROM monster WHERE id =100";


        String name = jdbcTemplate.queryForObject(sql, String.class);
        System.out.println(name);
    }



    @Test
    public void SelectDataByJdbcTemplateHasName() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = ioc.getBean(NamedParameterJdbcTemplate.class);
        String sql =
                "INSERT INTO monster VALUES(:my_id,:name,:skill)";
        HashMap<String , Object> paramMap = new HashMap<>();
        paramMap.put("my_id",800);
        paramMap.put("name","李四");
        paramMap.put("skill","打篮球");
        namedParameterJdbcTemplate.update(sql,paramMap);
        System.out.println("添加成功了~");

    }



    //SQLParameterSource 封装具名参数
    @Test
    public void SelectDataByJdbcTemplateSQLParameterSource() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = ioc.getBean(NamedParameterJdbcTemplate.class);
        String sql =
                "INSERT INTO monster VALUES(:monsterId ,:name,:skill)";
        //   public BeanPropertySqlParameterSource(Object object)
        Monster monster = new Monster(900  , "大象", "哈哈哈");
        SqlParameterSource beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(monster);

        //public int update(String sql, SqlParameterSource paramSource)
        int update = namedParameterJdbcTemplate.update(sql, beanPropertySqlParameterSource);
        System.out.println("添加成功了~");

    }
}
