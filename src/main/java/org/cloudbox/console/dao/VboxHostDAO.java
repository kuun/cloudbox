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

    @Insert("INSERT INTO vbox_host (id, ip, port, name, desc) " +
            "VALUES (#{id}, #{ip}, #{port}, #{name}, #{desc})")
    @Options(useGeneratedKeys = true)
    int createVboxHost(VboxHost host);

    @Update("UPDATE vbox_host SET " +
            "ip = #{ip}, " +
            "port = #{port}, " +
            "name = #{name}, " +
            "desc = #{desc} " +
            "WHERE id = #{id}")
    int modifyVboxHost(VboxHost host);

    @Delete("DELETE FROM vbox_host WHERE id=#{id}")
    int deleteVboxHost(int id);
}
