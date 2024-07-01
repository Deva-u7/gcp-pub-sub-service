package com.gcp.resource

import com.gcp.entity.Message
import com.gcp.service.MessageService
import jakarta.inject.Inject
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType


@Path("/api/v1/gcp")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class MessageResource {
    @Inject
    lateinit var messageService: MessageService

    @GET
    @Path("/msg")
     fun getAllMsg(): List<Message> {
        return messageService.getAllMsg()
    }

}