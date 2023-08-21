import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculadora de IMC");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        Label labelKg = new Label("Quantos kg você tem?");
        GridPane.setConstraints(labelKg, 0, 0);

        TextField kgInput = new TextField();
        kgInput.setPromptText("Informe seu peso em kg");
        GridPane.setConstraints(kgInput, 1, 0);

        Label labelAltura = new Label("Qual sua altura?");
        GridPane.setConstraints(labelAltura, 0, 1);

        TextField alturaInput = new TextField();
        alturaInput.setPromptText("Informe sua altura em metros");
        GridPane.setConstraints(alturaInput, 1, 1);

        Button calcularButton = new Button("Calcular IMC");
        GridPane.setConstraints(calcularButton, 0, 2);

        Label resultadoLabel = new Label();
        GridPane.setConstraints(resultadoLabel, 0, 3, 2, 1);

        calcularButton.setOnAction(e -> {
            try {
                double kg = Double.parseDouble(kgInput.getText());
                double altura = Double.parseDouble(alturaInput.getText());
                if (kg <= 0 || altura <= 0) {
                    resultadoLabel.setText("Erro: por favor, insira valores numéricos positivos válidos.");
                    return;
                }

                double imc = kg / (altura * altura);

                String mensagem;
                if (imc < 18.5) {
                    mensagem = "Seu IMC está abaixo de 18.5, abaixo do peso saudável. Seu IMC é " + String.format("%.1f", imc);
                } else if (imc <= 24.9) {
                    mensagem = "Seu IMC está entre 18.5 e 24.9, peso saudável. Seu IMC é " + String.format("%.1f", imc);
                } else if (imc <= 29.9) {
                    mensagem = "Seu IMC está entre 25 e 29.9, sobrepeso. Seu IMC é " + String.format("%.1f", imc);
                } else if (imc <= 34.9) {
                    mensagem = "Seu IMC está entre 30 e 34.9, obesidade grau 1. Seu IMC é " + String.format("%.1f", imc);
                } else if (imc <= 39.9) {
                    mensagem = "Seu IMC está entre 35 e 39.9, obesidade grau 2. Seu IMC é " + String.format("%.1f", imc);
                } else {
                    mensagem = "Seu IMC está acima de 40, obesidade grau 3 (obesidade mórbida). Seu IMC é  " + String.format("%.1f", imc);
                }

                resultadoLabel.setText(mensagem);
            } catch (NumberFormatException ex) {
                resultadoLabel.setText("Erro: por favor, insira valores numéricos válidos.");
            }
        });

        grid.getChildren().addAll(labelKg, kgInput, labelAltura, alturaInput, calcularButton, resultadoLabel);

        VBox vbox = new VBox(grid);
        Scene scene = new Scene(vbox, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
