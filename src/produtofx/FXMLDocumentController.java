package produtofx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import view.InterfaceView;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    public Button buttonInclude;
    public Button buttonList;
    public Button buttonDelete;
    public Button buttonUpdate;
    
    public Button buttonListClient;
    public Button buttonFinish;
    
    public TextArea textAreaProducts;
    public TextArea textAreaProductsClient;
    
    public TextField textId;
    public TextField textName;
    public TextField textCostValue;
    public TextField textSaleValue;
    public TextField textAmount;
    
    public TextField textAmountClient;
    public TextField textIdClient;
    
    public TextField textTotal;
    
    InterfaceView interfaceView = new InterfaceView();
    
    @FXML
    private void handleButtonInclude(ActionEvent event) {
        interfaceView.include(textName.getText(), Double.parseDouble(textCostValue.getText()), Double.parseDouble(textSaleValue.getText()), Integer.parseInt(textAmount.getText()));
    }
    
    @FXML
    private void handleButtonList(ActionEvent event) {    
        textAreaProducts.setText(interfaceView.getList());
    }
    
    @FXML
    private void handleButtonDelete(ActionEvent event) {
        interfaceView.delete(Long.parseLong(textId.getText()));
        clearAdministratorTextField();
    }
    
    @FXML
    private void handleButtonUpdate(ActionEvent event) {
        interfaceView.update(Long.parseLong(textId.getText()), textName.getText(), Double.parseDouble(textCostValue.getText()), Double.parseDouble(textSaleValue.getText()), Integer.parseInt(textAmount.getText()));
        clearAdministratorTextField();
    }
    
    @FXML
    private void handleButtonListClient(ActionEvent event) {
        textAreaProductsClient.setText(interfaceView.getListClient());
    }
    
    @FXML
    private void handleButtonFinish(ActionEvent event) {
        textTotal.setText(interfaceView.getFinish(Integer.parseInt(textAmountClient.getText()), Long.parseLong(textIdClient.getText())));  
    }
    
    @FXML
    private void handleButtonClear(ActionEvent event) {
        clearClientTextField();
    }
    
    private void clearAdministratorTextField(){
        textId.setText("");
        textName.setText("");
        textCostValue.setText("");
        textSaleValue.setText("");
        textAmount.setText("");
    }
    
    private void clearClientTextField(){
        textIdClient.setText("");
        textAmountClient.setText("");
        textTotal.setText("");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
