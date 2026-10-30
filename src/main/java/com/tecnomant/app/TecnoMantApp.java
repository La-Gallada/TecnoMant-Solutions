package com.tecnomant.app;

import com.tecnomant.view.VentanaPrincipal;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Punto de entrada de la aplicación JavaFX. */
public final class TecnoMantApp extends Application {
    @Override public void start(Stage stage) {
        Scene scene=new Scene(new VentanaPrincipal().crear(),1280,760);
        scene.getStylesheets().add(getClass().getResource("/styles/tecnomant.css").toExternalForm());
        stage.setTitle("TecnoMant Solutions · Gestión de órdenes de trabajo");
        stage.setMinWidth(1180);stage.setMinHeight(700);stage.setScene(scene);stage.show();
    }
    public static void main(String[] args){launch(args);}
}
