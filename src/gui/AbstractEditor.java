package gui;

import javafx.fxml.FXML;
import javafx.stage.Stage;


/**
 * Abstract editor that has all the methods that are the same for both editors.
 */
public abstract class AbstractEditor {
	

    protected Stage dialogStage;
    protected boolean okClicked = false;


    @FXML
    protected abstract void initialize();

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }



    public boolean isOkClicked() {
        return okClicked;
    }



    protected void closeEditor(){
         okClicked = true;
         dialogStage.close();
    }
    @FXML
    protected abstract void handleOk();


    @FXML
    protected void handleCancel() {
        dialogStage.close();
    }


    protected abstract boolean isInputValid();
	

}
