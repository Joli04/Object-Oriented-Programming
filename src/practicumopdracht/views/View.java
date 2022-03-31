package practicumopdracht.views;

import javafx.scene.Parent;
import javafx.scene.control.Button;

public abstract class View {
    private Parent root;
    protected Button nieuw;
    protected Button verwijderen;
    protected Button schakelen;
    protected Button opslaan;

    public View(){
        this.root = initializeView();
    }

    protected abstract Parent initializeView();


    public Parent getRoot(){
        return root;
    }

}

