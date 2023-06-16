import model.MessageTask;

import java.time.LocalDateTime;

public class Hii {


    public static MessageTask[] createMessageTaskArray() {
        MessageTask t1 = new MessageTask("1", "MTask1", "mesaj", "da", "Raul", LocalDateTime.now());
        MessageTask t2 = new MessageTask("2", "MTask2", "mesaj2", "BB", "da", LocalDateTime.now());
        MessageTask t3 = new MessageTask("3", "MTask3", "mesaj3", "OO", "Raul", LocalDateTime.now());
        return new MessageTask[]{t1, t2, t3};
    }
    public static void main(String[] args) {
        MessageTask[] messageTasks = createMessageTaskArray();
        for (MessageTask t : messageTasks) {
            t.execute();
        }
    }
}