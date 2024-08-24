import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * @author markymouseee
 **/

public class FXMLController implements Initializable{

    private String operatorHandler;
    private String display = "0";
    private double totalHandler = 0;


    @Override
    public void initialize(URL url, ResourceBundle resource){
        
    }
    
    @FXML
    private TextField displayHandler;

    @FXML
    private Text calculateHandler;

    @FXML
    private void btnNumber(ActionEvent event){
        String buttonNumber = ((Button)event.getSource()).getText();

        if(buttonNumber.equals(".")){
            if(display.contains(".")){
                displayHandler.setText("Invalid operation");
                return;
            }
        }

        if(display.equals("0") && !buttonNumber.equals(".")){
            display = buttonNumber;
        }else{
            display += buttonNumber;
        }
        displayHandler.setText(display);
        calcHandler();
    }

    @FXML
    private void btnOperator(ActionEvent event){
        String buttonOperator = ((Button)event.getSource()).getText();

        switch(buttonOperator){
            case "C":
                display = "0";
                calculateHandler.setText("0");
                break;
            case "←":
                if(display.length() == 1 || display.equals("Invalid operation") || display.equals("Infinity")){
                    display = "0";
                    calculateHandler.setText(display);
                }else{
                    display = display.substring(0, display.length() - 1);
                    calculateHandler.setText(calculateHandler.getText().substring(0, calculateHandler.getText().length() - 1));
                }
                break;
            case "=":
                clickEqual(Double.parseDouble(display));
                if(totalHandler == (int) totalHandler){
                    display = String.valueOf((int) totalHandler);
                }else{
                    display = String.valueOf(totalHandler);
                    
                }
                calculateHandler.setText(display);
                operatorHandler = "";
                totalHandler = 0;
                break;
            case "÷":
            case "×":
            case "–":
            case "+":
                handlerOperator(buttonOperator);
                display = "0";
                break;
            }
        displayHandler.setText(display);
    }

    private void handlerOperator(String buttonOperator){
        if(display.equals("0")){
           clickEqual(totalHandler);
        }

        double result = Double.parseDouble(display);
        if(totalHandler == 0){
            totalHandler = result;
        }else{
            clickEqual(result);
        }
        operatorHandler = buttonOperator;
    }

    private void calcHandler(){
        if(totalHandler == (int) totalHandler){
            calculateHandler.setText(String.valueOf((int) totalHandler));
        }else{
            calculateHandler.setText(String.valueOf(totalHandler));
        }
    }

    private void clickEqual(double result){
        switch(operatorHandler){
            case "÷":
                totalHandler /= result;
                break;
            case "×":
                totalHandler *=result;
                break;
            case "–":
                totalHandler -= result;
                break;
            case "+":
                totalHandler += result;
                break;
        }
    }

}
