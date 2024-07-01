package com.gcp.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.time.LocalDateTime

@Entity
class  Message(

     @Column(name = "content")
     var content: String? = null,

     @Column(name = "message_id")
     var messageId: String? = null,

     @Column(name = "attributes ")
     var attributes : String? = null,

     @Column(name = "created_date")
     var createdDate: LocalDateTime = LocalDateTime.now()

): PanacheEntity()
