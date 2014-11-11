package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

import Cabin.Cabin;
import Cabin.Reservation;
import application.DateUtil;

public abstract class AbstractEditor {
	

    protected Stage dialogStage;
    protected boolean okClicked = false;

    	@FXML
	    protected abstract void initialize();

	    public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }

	
	   //public abstract void setChanges();


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
