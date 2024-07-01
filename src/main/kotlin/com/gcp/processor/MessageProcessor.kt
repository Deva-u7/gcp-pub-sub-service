package com.gcp.processor

import com.gcp.entity.Message
import com.gcp.service.MessageService
import com.google.cloud.pubsub.v1.AckReplyConsumer
import com.google.cloud.pubsub.v1.MessageReceiver
import com.google.pubsub.v1.PubsubMessage
import io.quarkus.logging.Log
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
class MessageProcessor : MessageReceiver{

    @Inject
    lateinit var messageService: MessageService

    override fun receiveMessage(message: PubsubMessage, consumer: AckReplyConsumer) {
        try {
            Log.info("Message received : $message")
            val content = message.data.toStringUtf8()
            val attributes = message.attributesMap
            val messageEntity =
                Message(
                    content = content,
                    messageId = message.messageId,
                    attributes = attributes.toString()
                )
            messageService.saveMessage(messageEntity)
            consumer.ack()
        } catch (e: Exception) {
            Log.error("Error processing message:$e")
            consumer.nack()
        }
    }
}
