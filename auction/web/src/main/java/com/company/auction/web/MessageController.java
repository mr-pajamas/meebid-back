package com.company.auction.web;

import java.util.Date;

import com.company.auction.core.Entity.*;
import com.company.auction.core.Exception.*;
import com.company.auction.core.service.MessageService;
import com.company.auction.core.service.UserService;
import com.company.auction.web.model.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sukey on 2017/3/27.
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "create", notes = "创建站内信")
    @ResponseStatus(HttpStatus.OK)
    public String create(@ApiParam(value = "主题", required = true) @RequestParam(required = true) String subject,
                         @ApiParam(value = "发送内容", required = true) @RequestParam(required = true) String content,
                         @ApiParam(value = "收件人ID", required = true) @RequestParam(required = true) String recipients,
                         @ApiParam(value = "发件人ID", required = true) @RequestParam(required = true) String correspondent,
                         @ApiParam(value = " 附件地址", required = false) @RequestParam(required = false) String attachment,
                         @ApiParam(value = "消息来源", required = false) @RequestParam(required = false) String source
    ) {
        User correspondentUser = userService.findUser(correspondent);
        User recipientsUser = userService.findUser(recipients);
        if (correspondentUser == null) {
            throw new SenderNotFindException();
        }
        if (recipientsUser == null) {
            throw new RecipientsNotFindException();
        }
        Message message = new Message();
        message.setState(0);
        message.setRecipients(recipients);
        message.setCorrespondent(correspondent);
        message.setCorrespondentName(correspondentUser.getName());
        message.setSubject(subject);
        message.setContent(content);
        message.setAttachment(attachment);
        message.setSource(source);
        message.setUpdateTime(new Date());
        message.setCreateTime(new Date());
        messageService.saveMessage(message);
        return message.getId();
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "获取列表", notes = "获取所有站内信")
    @ResponseStatus(HttpStatus.OK)
    public TableListModel list(
        @ApiParam(value = "用户ID", required = true) @RequestParam(required = true) String uid,
        @ApiParam(value = "页码", required = true) @RequestParam int page,
        @ApiParam(value = "页数", required = true) @RequestParam int pageSize
    ) {
        User recipientsUser = userService.findUser(uid);
        if (recipientsUser == null) {
            throw new RecipientsNotFindException();
        }
        TableListModel messageList = messageService.findMessages(uid, page, pageSize);

        return messageList;
    }

    @RequestMapping(value = "/updateAllMessageToRead", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "updateAllMessageToRead", notes = "一键更新所有未读为已读，将用户所有消息更新为已读")
    @ResponseStatus(HttpStatus.OK)
    public String updateAllMessageToRead(
        @ApiParam(value = "用户ID", required = true) @RequestParam(required = true) String uid
    ) {

        messageService.updateAllMessageToRead(uid);
        return "success";
    }

    @RequestMapping(value = "/updateMessageState", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "updateMessageState", notes = "更新消息状态")
    @ResponseStatus(HttpStatus.OK)
    public String updateMessageToRead(@ApiParam(value = "站内信ID", required = true) @RequestParam(required = true) String messageId,
                                      @ApiParam(value = "消息状态，1为已读，0为未读", required = true) @RequestParam(required = true) int state) {
        Message message = messageService.findMessageById(messageId);
        message.setState(state);
        messageService.saveMessage(message);
        return "success";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorModel handleRecipientsNotFindException(RecipientsNotFindException e) {
        ErrorModel model = new ErrorModelBuilder("000404").build();
        return model;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorModel handleUserNotFindException(SenderNotFindException e) {
        ErrorModel model = new ErrorModelBuilder("000405").build();
        return model;
    }


}
