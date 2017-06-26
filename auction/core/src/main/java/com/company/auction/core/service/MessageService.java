package com.company.auction.core.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.company.auction.core.Entity.Message;
import com.company.auction.core.Entity.TableListModel;

/**
 * Created by sukey on 2017/3/27.
 */
public interface MessageService {

    void saveMessage(Message message);

    void updateAllMessageToRead(String recipients);

    TableListModel findMessages(String uid, int page, int pageSize);



    Message findMessageById(String messageId);


}
