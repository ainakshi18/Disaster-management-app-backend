package com.groupchat.Groupchat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.groupchat.Groupchat.models.UpdateRequest;


import com.groupchat.Groupchat.models.TimelineUpdate;


import java.io.IOException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "http://localhost:5174")
@RestController
@RequestMapping("/api/process")
public class ProcessController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
    private static final Logger log = LoggerFactory.getLogger(ProcessController.class);


    // Endpoint for Donors to update
    @PostMapping("/donor")
    public void updateByDonor(@RequestBody UpdateRequest request) {
        broadcastUpdate("Donor Update", request.getDescription(), request.getDate());
    }

    // Endpoint for Organization to update
    @PostMapping("/organization")
    public void updateByOrganization(@RequestBody UpdateRequest request) {
        broadcastUpdate("Organization Update", request.getDescription(), request.getDate());
    }

    // Endpoint for Volunteers to update
    @PostMapping("/volunteer")
    public void updateByVolunteer(@RequestBody UpdateRequest request) {
        broadcastUpdate("Volunteer Update", request.getDescription(), request.getDate());
    }

    // Endpoint for Needy to update
    @PostMapping("/needy")
    public void updateByNeedy(@RequestBody UpdateRequest request) {
        broadcastUpdate("Needy Update", request.getDescription(), request.getDate());
    }

    // Endpoint for Needy to send feedback with optional photo or message
    @PostMapping("/needy/feedback")
    public void submitFeedback(
            @RequestParam("description") String description,
            @RequestParam("date") String date,
            @RequestParam("imageUrl") String imageUrl) {

        // Create feedback message with image URL
        TimelineUpdate feedback = new TimelineUpdate("Needy Feedback", description, date, true);
        feedback.setImage(imageUrl);  // Set the image URL directly

        messagingTemplate.convertAndSend("/topic/timeline-updates", feedback);
    }


    // Method to broadcast updates
    private void broadcastUpdate(String title, String description, String date) {
        TimelineUpdate update = new TimelineUpdate(title, description, date, true);
        log.debug("Broadcasting update to /topic/timeline-updates: {}", update);
        messagingTemplate.convertAndSend("/topic/timeline-updates", update);
    }
}
