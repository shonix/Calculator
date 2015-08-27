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
}
