package com.gcp.service

import com.gcp.entity.Message
import com.gcp.repository.MessageRepository
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import io.quarkus.logging.Log


@ApplicationScoped
class MessageService {
    @Inject
    lateinit var messageRepository: MessageRepository

    @Transactional
    fun saveMessage(msg: Message) {
        messageRepository.persist(msg)
        Log.info("Message processed and saved")
    }

    fun getAllMsg(): List<Message> {
        return messageRepository.findAll().list()
    }


}