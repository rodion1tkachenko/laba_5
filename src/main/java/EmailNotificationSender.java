

public class EmailNotificationSender implements NotificationSender {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending email notification: " + message);
    }
}