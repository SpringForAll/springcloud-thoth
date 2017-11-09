//package com.prometheus.thoth.kafka.service;
//
//import com.alibaba.fastjson.JSON;
//import com.prometheus.thoth.common.service.LoggerService;
//import com.prometheus.thoth.common.util.Constants;
//import com.prometheus.thoth.data.kafka.producer.KafkaProducer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * Created by liangliang on 2017/05/06.
// *
// * @author liangliang
// * @since 2017/05/06
// */
//@Service
//public class KafkaService extends LoggerService {
//
//    @Autowired
//    private KafkaProducer kafkaProducer;
//
//    public boolean sendSession(Session session) {
//        String sessionJson = JSON.toJSONString(session);
//        logger.debug("sendSession sessionJson:{}", sessionJson);
//        kafkaProducer.send(Constants.KAFKA_TOPIC_SESSION, session);
//        return true;
//    }
//
//    public boolean sendImMsg(ChatRecord imMsg) {
//        String imMsgJson = JSON.toJSONString(imMsg);
//        logger.debug("sendImMsg imMsg:{}", imMsg);
//        kafkaProducer.send(Constants.KAFKA_TOPIC_IM_MSG, imMsg);
//        return true;
//    }
//}
