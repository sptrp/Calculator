import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import java.util.Stack;

public class CalcController {

    private String txtDisplay="";
    private Character operator;
    private Double res;
    private Stack<Character> stack = new Stack<>();
    private Character save;

    @FXML
    private TextField display;


    // Method will be called, if quit menuitem was clicked and quits the program
    @FXML
    public void onQuitClicked(ActionEvent event) {

        Platform.exit();
    }

    // Method will be called, if undo menuitem was clicked and undo the last input
    @FXML
    public void onUndoClicked(ActionEvent event) {

        save = stack.pop();
        txtDisplay = txtDisplay.substring(0, txtDisplay.length()-1);
        display.setText(txtDisplay);
    }

    // Method will be called, if redo menuitem was clicked and returns the last input
    @FXML
    public void onRedoClicked(ActionEvent event) {

        txtDisplay = txtDisplay+save;
        display.setText(txtDisplay);
    }

    // Method will be called, if about menuitem was clicked and shows the author
    @FXML
    public void onAboutClicked(ActionEvent event) {

        display.setText("Made by ivpo3178");
    }

    // Method will be called, if any number was clicked and pushes number to a stack
    @FXML
    public void onNumberClicked(ActionEvent event) {

        // Number of characters is limited due to a screen size
        if (txtDisplay.length()<=16) {



                Character num = getBtnIntValue(event.toString());
                txtDisplay = txtDisplay + num;
                display.setText(txtDisplay);
                stack.push(num);


        } else if (txtDisplay.length()>16) {

            display.setText(txtDisplay);
        }
    }

    // Method will be called, if "C" was clicked and cleans the display
    @FXML
    public void onDelClicked(ActionEvent event) {

        txtDisplay = "";
        display.setText(txtDisplay);
    }

    // Method will be called, if "+" was clicked and pushes "+" to a stack
    @FXML
    public void onPlusClicked(ActionEvent event) {

        operator = '+';
        txtDisplay = txtDisplay+operator;
        display.setText(txtDisplay);
        stack.push(operator);
    }

    // Method will be called, if "-" was clicked and pushes "-" to a stack
    @FXML
    public void onMinClicked(ActionEvent event) {

        operator='-';
        txtDisplay=txtDisplay+operator;
        display.setText(txtDisplay);
        stack.push(operator);
    }

    // Method will be called, if "*" was clicked and pushes "*" to a stack
    @FXML
    public void onMltClicked(ActionEvent event) {

        operator='*';
        txtDisplay=txtDisplay+operator;
        display.setText(txtDisplay);
        stack.push(operator);
    }

    // Method will be called, if "/" was clicked and pushes "/" to a stack
    @FXML
    public void onDivClicked(ActionEvent event) {

        operator='/';
        txtDisplay=txtDisplay+operator;
        display.setText(txtDisplay);
        stack.push(operator);
    }

    // Method will be called, if "=" was clicked.
    // Counts result and shows it on the display
    @FXML
    public void onResClicked(ActionEvent event) {

        System.out.println(stack);

        String toCount = CountController.makePostfix(txtDisplay);
        res = CountController.evaluatePostfix(toCount);
        txtDisplay = res.toString();
        display.setText(txtDisplay);
    }

    @FXML
    public void onDotClicked(ActionEvent event) {

        operator='.';
        txtDisplay=txtDisplay+operator;
        display.setText(txtDisplay);
        stack.push(operator);
    }

    // Additional method to extract number from event info
    public Character getBtnIntValue (String input) {

        Character num = input.charAt(42);
        return num;
    }
}
