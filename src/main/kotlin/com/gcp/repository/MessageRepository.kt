package com.gcp.repository

import com.gcp.entity.Message
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class  MessageRepository : PanacheRepository<Message>