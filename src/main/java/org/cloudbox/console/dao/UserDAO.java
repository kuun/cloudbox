package org.cloudbox.console.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.cloudbox.console.api.User;

import java.util.List;

/**
 * Created by kevin on 7/27/16.
 */
public interface UserDAO {

    @Update("INSERT INTO usr (name, passwd) VALUES (#{name}, #{passwd})")
    void insert(User user);

    @Select("SELECT * FROM usr")
    List<User> getAllUsers();

    @Select("SELECT * FROM usr WHERE name = #{name}")
    User getUserByName(String name);

    @Select("SELECT * FROM usr WHERE id = #{id}")
    User getUserById(int id);

    @Select("UPDATE usr SET passwd = #{passwd} WHERE name = #{name}")
    void modifyPasswd(@Param("name") String name, @Param("passwd") String passwd);

    @Select("DELETE FROM usr WHERE id = #{id}")
    void deleteUser(int id);
}
