package org.cloudbox.console.dao;

import org.apache.ibatis.annotations.*;
import org.cloudbox.console.api.VboxHost;

import java.util.List;

/**
 * Created by kevin on 8/11/16.
 */
public interface VboxHostDAO {
    @Select("SELECT * FROM vbox_host")
    List<VboxHost> getVboxHosts();

    @Select("SELECT * FROM vbox_host WHERE id = #{id}")
    VboxHost getVboxHostById(int id);

    @Insert("INSERT INTO vbox_host (ip, port, user, passwd, tls, name, desc) " +
            "VALUES (#{ip}, #{port}, #{user}, #{passwd}, #{tls}, #{name}, #{desc})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int createVboxHost(VboxHost host);

    @Update("UPDATE vbox_host SET " +
            "ip = #{ip}, " +
            "port = #{port}, " +
            "user = #{user}, " +
            "passwd = #{passwd}, " +
            "tls = #{tls}, " +
            "name = #{name}, " +
            "desc = #{desc} " +
            "WHERE id = #{id}")
    int modifyVboxHost(VboxHost host);

    @Delete("DELETE FROM vbox_host WHERE id=#{id}")
    int deleteVboxHost(int id);
}
