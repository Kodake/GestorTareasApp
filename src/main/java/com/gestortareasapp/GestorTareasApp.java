package com.gestortareasapp;

import com.gestortareasapp.presentacion.GestorTareasFx;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestorTareasApp {

	public static void main(String[] args) {
		Application.launch(GestorTareasFx.class, args);
	}

}
