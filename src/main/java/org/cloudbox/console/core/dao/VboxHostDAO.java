package org.cloudbox.console.core.dao;

import org.apache.ibatis.annotations.*;
import org.cloudbox.console.core.pojo.VBoxHost;

import java.util.List;

/**
 * Created by kevin on 8/11/16.
 */
public interface VboxHostDAO {
    @Select("SELECT * FROM vbox_host")
    List<VBoxHost> getVboxHosts();

    @Select("SELECT * FROM vbox_host WHERE id = #{id}")
    VBoxHost getVboxHostById(int id);

    @Insert("INSERT INTO vbox_host (ip, port, user, passwd, tls, name, desc) " +
            "VALUES (#{ip}, #{port}, #{user}, #{passwd}, #{tls}, #{name}, #{desc})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int createVboxHost(VBoxHost host);

    @Update("UPDATE vbox_host SET " +
            "ip = #{ip}, " +
            "port = #{port}, " +
            "user = #{user}, " +
            "passwd = #{passwd}, " +
            "tls = #{tls}, " +
            "name = #{name}, " +
            "desc = #{desc} " +
            "WHERE id = #{id}")
    int modifyVboxHost(VBoxHost host);

    @Delete("DELETE FROM vbox_host WHERE id=#{id}")
    int deleteVboxHost(int id);
}
