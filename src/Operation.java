import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

/**
 * Created by Shonix on 26-08-2015.
 */
public class Operation {
    public char value;
    public EventHandler<ActionEvent> action;

    public Operation(char c, EventHandler<ActionEvent> event){
        this.value = c;
        this.action = event;
    }

    public static void clear(){
        Main.value = 0;
        Main.main.getChildren().filtered((Node) ->{return Node.getId()=="txtInput";}).forEach(Node -> {
            ((TextField) Node).setText(Float.toString(0));
        });

    }
    public static int add(int a){
        int b = a;
        int result = a + b;
        return result;
    }
}
