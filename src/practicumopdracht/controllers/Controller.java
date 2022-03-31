package practicumopdracht.controllers;

import practicumopdracht.data.DummySchrijverDAO;
import practicumopdracht.models.Schrijver;
import practicumopdracht.views.View;

import java.time.LocalDate;

public abstract  class Controller {


   public abstract View getView();

}
