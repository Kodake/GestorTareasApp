package com.gestortareasapp.presentacion;

import com.gestortareasapp.GestorTareasApp;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class GestorTareasFx extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        this.context = new SpringApplicationBuilder(GestorTareasApp.class).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(GestorTareasApp.class.getResource("/templates/index.fxml"));
        loader.setControllerFactory(context::getBean);
        Scene escena = new Scene(loader.load());
        stage.setScene(escena);
        stage.show();
    }

    @Override
    public void stop() {
        context.close();
        Platform.exit();
    }
}
