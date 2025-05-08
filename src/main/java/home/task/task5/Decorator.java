package home.task.task5;

interface Notification {
    void send(String message);
}

class Message implements Notification {
    private String msg;

    @Override
    public void send(String message) {
        this.msg = message;
        System.out.println("Отправить сообщение: " + msg);
    }
}

public class Decorator implements Notification {
    private Message message;

    public Decorator(Message message) {
        this.message = message;
    }

    @Override
    public void send(String msg) {
        String newMessage = msg + addSmile();
        message.send(newMessage);
    }

    private String addSmile() {
        return " =)";
    }
}

class TestDecorator {
    public static void main(String[] args) {
        Notification message = new Message();
        message.send("Грустно");
        Notification notification = new Decorator(new Message());
        notification.send("Классно");
    }
}