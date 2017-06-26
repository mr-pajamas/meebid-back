package com.company.auction.core.dao;

import java.util.List;

import com.company.auction.core.Entity.Message;
import org.springframework.stereotype.Repository;

/**
 * Created by sukey on 2017/3/27.
 */
@Repository
public class MessageDao extends BaseDao {

    public void updateMessageState(Message message) {
        StringBuilder sql = new StringBuilder();
        sql.append("update Message m set m.state=:state ");
        sql.append(" where m.recipients=:recipients and m.state =0 ");
        updateObject(sql.toString(), message);

    }

    public List<Message> findMessages(Message message) {
        StringBuilder sql = new StringBuilder();
        sql.append(" From Message m where 1=1  ");
        if (message.getRecipients() != null) {
            sql.append(" and m.recipients=:recipients  ");
        }
        sql.append(" order by m.createTime desc ");

        return findObjects(sql.toString(), message);
    }

    public List<Message> findMessages(Message message, int page, int pageSize) {
        StringBuilder sql = new StringBuilder();
        sql.append(" From Message m where 1=1  ");
        if (message.getRecipients() != null) {
            sql.append(" and m.recipients=:recipients  ");
        }
        sql.append(" order by m.createTime desc ");

        return findObjects(sql.toString(), page, pageSize, message);
    }

    public Integer countMessages(Message message) {
           StringBuilder sql = new StringBuilder();
           sql.append(" select count(*) From Message m where 1=1  ");
           if (message.getRecipients() != null) {
               sql.append(" and m.recipients=:recipients  ");
           }


           return countResult(sql.toString(),message);
       }

}
