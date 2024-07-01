package com.gcp.config

import com.gcp.processor.MessageProcessor
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.pubsub.v1.Subscriber
import com.google.pubsub.v1.ProjectSubscriptionName
import io.quarkus.logging.Log
import io.quarkus.runtime.ShutdownEvent
import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import org.eclipse.microprofile.config.inject.ConfigProperty

@ApplicationScoped
class GcpPubSubConfig(
    @ConfigProperty(name = "google.cloud.project-id")
    private var projectId: String,

    @ConfigProperty(name = "google.cloud.pubsub.topic.subscription")
    private var topicSubscription: String,

    @ConfigProperty(name = "google.cloud.service-account.path")
    private var serviceAccountPath: String,

    private var messageProcessor: MessageProcessor
) {
    private lateinit var subscriber: Subscriber

    fun onStart(@Observes ev: StartupEvent) {
        configureGcpAndTopic()
    }

    private fun configureGcpAndTopic() {
            val credentials = GoogleCredentials.fromStream(GcpPubSubConfig::class.java.getResourceAsStream(serviceAccountPath))
            val subscriptionName = ProjectSubscriptionName.of(projectId, topicSubscription)
            subscriber = Subscriber.newBuilder(subscriptionName, messageProcessor).setCredentialsProvider { credentials }.build()
            subscriber.startAsync().awaitRunning()
            Log.info("Subscriber configured and started.")

    }

    fun shutdown(@Observes ev: ShutdownEvent) {
        subscriber.stopAsync().awaitTerminated()
        Log.info("Subscriber stopped.")
    }
}