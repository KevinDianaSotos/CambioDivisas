package dad.cambiodivisa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Cambio extends Application {
	private TextField cantidadOrigen;
	private TextField cantidadDestino;
	private ComboBox<Divisa> comboOrigen;
	private ComboBox<Divisa> comboDestino;
	private Button botonCambiar;
	
	private Divisa euro = new Divisa("Euro", 1.0);//DECLARACION DE CLASE DIVISA CON NOMBRE Y LA TASA
	private Divisa libra = new Divisa("Libra", 0.8873);//DECLARACION DE CLASE DIVISA CON NOMBRE Y LA TASA
	private Divisa dolar = new Divisa("Dolar", 1.2007);//DECLARACION DE CLASE DIVISA CON NOMBRE Y LA TASA
	private Divisa yen = new Divisa("Yen", 133.59);//DECLARACION DE CLASE DIVISA CON NOMBRE Y LA TASA
	
	private Divisa [] divisas = {euro, libra, dolar, yen};//CREACION DE ARRAY CON DIVISAS
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		cantidadOrigen = new TextField("0");//CREAMOS EL TEXTFIELD CON EL 0 POR DEFECTO
		cantidadOrigen.setPrefColumnCount(4);
		cantidadDestino = new TextField("0");//CREAMOS EL TEXTFIELD CON EL 0 POR DEFECTO
		cantidadDestino.setPrefColumnCount(4);
		cantidadDestino.setEditable(false);//NO EDITABLE PARA EL USUARIO
		
		comboOrigen = new ComboBox<>();//CREAMOS EL COMBOBOX DE ORIGEN 
		comboOrigen.getItems().addAll(divisas);//LE AÑADIMOS EL ARRAY DE DIVISAS AL COMBOBOX
		comboOrigen.getSelectionModel().selectFirst();//SELECCIONAMOS EL PRIMERO POR DEFECTO
		comboDestino = new ComboBox<>();//CREAMOS EL COMBOBOX DE ORIGEN 
		comboDestino.getItems().addAll(divisas);//LE AÑADIMOS EL ARRAY DE DIVISAS AL COMBOBOX
		comboDestino.getSelectionModel().select(libra);//SELECCIONAMOS LIBRA POR DEFECTO
		
		botonCambiar = new Button("Cambiar");//CREAMOS EL BOTON CAMBIAR
		botonCambiar.setOnAction(e -> onCambiarButton(e));//CON LA ACCION ONCAMBIARBUTTON CREADO MAS ABAJO
		
		HBox origenBox = new HBox();//CREAMOS EL HBOX PARA ORIGEN
		origenBox.setAlignment(Pos.BASELINE_CENTER);//ALINEAMOS AL CENTRO
		origenBox.setSpacing(5);//LE DAMOS UN MARGIN
		origenBox.getChildren().addAll(cantidadOrigen, comboOrigen);//AÑADIMOS LA CANTIDAD Y EL COMBO DE DIVISAS
		
		HBox destinoBox = new HBox ();//**REALIZAMOS LO MISMO CON EL HBOX DE DESTINO
		destinoBox.setSpacing(5);
		destinoBox.getChildren().addAll(cantidadDestino, comboDestino);
		destinoBox.setAlignment(Pos.BASELINE_CENTER);
		
		VBox root = new VBox ();//CREAMOS EL VBOX CON TODO LOS DOS HBOX Y EL BOTON
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(origenBox, destinoBox, botonCambiar);
		
		Scene scene = new Scene(root, 320, 200);//CREAMOS LA ESCENA
		
		primaryStage.setTitle("Cambio de Divisas");
		primaryStage.setScene(scene);
		primaryStage.show();//MOSTRAMOS

	}
	
	private void onCambiarButton(ActionEvent e) {
		
		try {//SI FUNCIONA
			
			Double cantidadOriginal = Double.parseDouble(cantidadOrigen.getText());//COGEMOS LA CANTIDAD DE ORIGEN Y LO PARSEAMOS
			Divisa divisaOrigen = comboOrigen.getSelectionModel().getSelectedItem();//Y RECOGEMOS AMBOS COMBOS SELECCIONADOS
			Divisa divisaDestino = comboDestino.getSelectionModel().getSelectedItem();
			
			Double enEuros = divisaOrigen.toEuro(cantidadOriginal);//CON EL TOEURO PASAMOS A EUROS EL PRIMER VALOR COGIDO
			Double mostrarDestino = divisaDestino.fromEuro(enEuros);//PASAMOS A LA MONEDA SELECCIONADA DESDE EUROS 
			cantidadDestino.setText("" + mostrarDestino);//MOSTRAMOS EN CANTIDAD DESTINO
			
		}
		
		catch (NumberFormatException nfe) {//SI NO FUNCIONA MOSTRAMOS EL ALERT DICIENDO QUE INTRODUZCA UN NUMERO
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Cambio de Divisas");
			error.setHeaderText("Error");
			error.setContentText("Por favor, introduzca un número.");
			error.showAndWait();
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
