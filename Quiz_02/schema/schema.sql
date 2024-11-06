-- User Table
CREATE TABLE Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Message Table
CREATE TABLE Messages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sender_username VARCHAR(255) NOT NULL,
    recipient_username VARCHAR(255) NOT NULL,
    message_content TEXT NOT NULL,
    sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_username) REFERENCES Users(username) ON DELETE CASCADE,
    FOREIGN KEY (recipient_username) REFERENCES Users(username) ON DELETE CASCADE
);
