package com.tecnomant.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.io.InputStream;

final class Componentes {
    private Componentes(){}
    static Button boton(String texto,String estilo){Button b=new Button(texto);b.getStyleClass().add(estilo);return b;}
    static Label etiqueta(String texto,String estilo){Label l=new Label(texto);l.getStyleClass().add(estilo);return l;}
    static VBox tarjeta(){VBox box=new VBox(10);box.getStyleClass().add("card");box.setPadding(new Insets(18));return box;}
    static ImageView icono(String nombre,double tamano){InputStream s=Componentes.class.getResourceAsStream("/com/tecnomant/assets/PNG_256/"+nombre+"_blue.png");if(s==null)return null;ImageView v=new ImageView(new Image(s));v.setFitWidth(tamano);v.setFitHeight(tamano);v.setPreserveRatio(true);return v;}
    static void mensaje(Alert.AlertType tipo,String titulo,String texto){Alert a=new Alert(tipo,texto,ButtonType.OK);a.setTitle(titulo);a.setHeaderText(null);a.showAndWait();}
    static boolean confirmar(String texto){Alert a=new Alert(Alert.AlertType.CONFIRMATION,texto,ButtonType.YES,ButtonType.CANCEL);a.setTitle("Confirmar operación");a.setHeaderText("¿Desea continuar?");return a.showAndWait().orElse(ButtonType.CANCEL)==ButtonType.YES;}
}
