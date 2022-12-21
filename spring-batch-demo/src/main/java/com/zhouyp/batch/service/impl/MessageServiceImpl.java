package com.zhouyp.batch.service.impl;

import com.zhouyp.batch.entity.Message;
import com.zhouyp.batch.mapper.MessageMapper;
import com.zhouyp.batch.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 周毅鹏
 * @since 2022-06-30
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

}
