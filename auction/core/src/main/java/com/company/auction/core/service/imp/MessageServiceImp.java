package com.company.auction.core.service.imp;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.company.auction.core.Entity.Message;
import com.company.auction.core.Entity.TableListModel;
import com.company.auction.core.dao.MessageDao;
import com.company.auction.core.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sukey on 2017/3/27.
 */
@Service
public class MessageServiceImp implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public void saveMessage(Message message) {
        messageDao.save(message);
    }

    @Override
    public void updateAllMessageToRead(String recipients) {
        Message message = new Message();
        message.setRecipients(recipients);
        message.setState(1);
        messageDao.updateMessageState(message);

    }

    @Override
    public TableListModel findMessages(String uid, int page, int pageSize) {
        Message message = new Message();
        message.setRecipients(uid);
        TableListModel model = new TableListModel();
        model.setDateList(messageDao.findMessages(message,page,pageSize));
        model.setTotalCount(messageDao.countMessages(message));
        return model;
    }

    @Override
    public Message findMessageById(String messageId) {

        Message message = (Message) messageDao.getObjectById(messageId,new Message());
        return message;
    }
}
