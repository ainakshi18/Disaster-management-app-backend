package com.groupchat.Groupchat.models;
public class TimelineUpdate {
    private String title;
    private String description;
    private String date;
    private boolean completed;
    private String image;  // Store image URL here

    // Constructor and getters/setters

    public TimelineUpdate(String title, String description, String date, boolean completed) {
        this.setTitle(title);
        this.setDescription(description);
        this.setDate(date);
        this.setCompleted(completed);
    }

    // Getter and setter for image URL
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
