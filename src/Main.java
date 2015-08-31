/**
 * Created by Shonix on 26-08-2015.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Main extends Application {

    //Action upon click on button (yeah i know, what is this even)
    EventHandler<ActionEvent> numAction = (event)-> {
        int textChar = ((int)((Button)event.getSource()).getText().charAt(0))-48;
        numInput = numInput*10+ textChar;
        updateOnView();
    };

    //Fields
    static float numInput = 0;
    static float tmp = 0;
    static EventHandler<ActionEvent> actionOperation = null;
    static char operation;
    Button btnClose = new Button();
    TextField txtInput = new TextField();

    //Buttons - do we even know?
    Operation[] operators = new Operation[]{
            new Operation('+',(event)->{PerformAction(numInput);numInput = 0;actionOperation = (event1) ->{tmp = numInput + tmp;}; updateOnView();}),
            new Operation('-',(event)->{PerformAction(numInput);numInput = 0;actionOperation = (event1) ->{tmp = tmp - numInput;}; updateOnView();}),
            new Operation('*',(event)->{PerformAction(numInput);numInput = 0;actionOperation = (event1) ->{tmp = numInput * tmp;}; updateOnView();}),
            new Operation('/',(event)->{PerformAction(numInput);numInput = 0;actionOperation = (event1) ->{tmp = tmp / numInput;}; updateOnView();}),
            new Operation('=',(event)->{PerformAction(numInput); if (actionOperation==null){tmp =numInput; }else{}numInput = 0; actionOperation = null; updateOnView(); }),
            new Operation('C',(event)->{tmp =0; numInput = 0; actionOperation = null; updateOnView();})};
    Button[] operatorBtns = new Button[operators.length];
    Button[] numBtns = new Button[10];
    String[] layout = new String[]{"789/","456*","123-","0C+="};

    //Layouts
    static VBox main = new VBox();
    GridPane gridBot = new GridPane();
    GridPane grid = new GridPane();
    Scene sceneMain = new Scene(main,275,270);

    public static void main(String[]args){
        launch();
    }

    //do we even know what we are doing anymore? jonas?
    private void updateOnView(){
        setTxtInput(Float.toString((numInput==0? tmp :numInput)));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        main.getStylesheets().add("Styled.css");
        grid.setHgap(5); //horizontal gap in pixels => that's what you are asking for
        grid.setVgap(5); //vertical gap in pixels
        grid.setPadding(new Insets(10, 10, 10, 10));
        gridBot.setPadding(new Insets(0,0,0,10));

        //Create operator buttons
        for(int i = 0; i < operators.length; i++){
            operatorBtns[i] = new Button (Character.toString(operators[i].value));
            operatorBtns[i].getStyleClass().add("operatorBtn");
            operatorBtns[i].setOnAction(operators[i].action);

        }
        //Create Number buttons
        for(int i = 0; i < numBtns.length; i++){
            numBtns[i] = new Button(Integer.toString(i));
            numBtns[i].getStyleClass().add("numBtn");
            numBtns[i].setOnAction(numAction);
        }
        //Make an editable layout (see String array in fields to edit)
        for(int i = 0; i < layout.length; i++){
            for(int j = 0; j < layout[i].length(); j++){
                int q = (int)layout[i].charAt(j);
                if (q < 58 && q> 47) {
                    grid.add(numBtns[q - 48], j, i);
                }
                else{
                    int k = 0;
                    for(; k < operators.length; k++){
                        if(operators[k].value==layout[i].charAt(j)){
                            break;
                        }
                    }
                    try {
                        grid.add(operatorBtns[k], j, i);
                    }catch (Exception e){
                       Stage dialogStage = new Stage();
                        dialogStage.initModality(Modality.WINDOW_MODAL);
                        Button okbtn;
                        dialogStage.setScene(new Scene(VBoxBuilder.create().children(new Text("dun goofed"), okbtn = new Button("Ok.")).alignment(Pos.CENTER).padding(new Insets(5)).build()));
                        okbtn.setOnAction((event)->dialogStage.close());
                        dialogStage.show();
                    }
                }
            }
        }
        //close button
        btnClose.getStyleClass().add("btnClose");
        btnClose.setText("Close");
        btnClose.setOnAction((event)->primaryStage.close());
        gridBot.add(btnClose,2,1);

        //Properties for textfield
        txtInput.setEditable(false);
        txtInput.setId("txtInput");

        //Add everything to main layout
        main.getChildren().addAll(txtInput,grid,gridBot);

        //Select start scene, and shows the stage
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(sceneMain);
        primaryStage.show();
    }

    static void PerformAction(float cykablyatidinahuit){
        if(actionOperation!=null){
            actionOperation.handle(null);
        }
        else{
            tmp = cykablyatidinahuit;
        }
    }

    private void setTxtInput(String s){
        main.getChildren().filtered((Node) -> {return Node.getId() == "txtInput"; }).forEach(Node -> {((TextField) Node).setText(s);});
    }
}
