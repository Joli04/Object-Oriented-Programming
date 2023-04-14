package practicumopdracht.controllers;
import javafx.scene.control.Alert;
import practicumopdracht.views.View;

public abstract  class Controller {

   public abstract View getView();
   protected Alert alert;

}
