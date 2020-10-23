package view.trator;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;



import entity.Trator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import DAO.TratorDAO;

public class ControllerTrator extends Application implements Initializable {


    @FXML
    private TextField textFieldMarca;

    @FXML
    private TextField textFieldModelo;

    @FXML
    private TextField textFieldano;

    @FXML
    private TextField textFieldValor;

    @FXML
    private TextArea textAreaTrator;

    @FXML
    private TextField textField_ID;
    @FXML
    private Label labelTextId;

    @FXML
    private Label labelTextIdInserted;
    @FXML
    private Label labelQtd;




    @FXML
    void ExcluirTrator(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Este trator foi vendido???");
        alert.setHeaderText("Realmente deseja excluir do estoque?");
        alert.setContentText("OK???");
        Optional<ButtonType> resultado = alert.showAndWait();
        if (resultado.get() == ButtonType.OK){
            Trator trator = pegaTrator() ;
            int qnt = new TratorDAO().ExcluirTrator(trator.getId());
            limpaCampos();
            listarTrator();
        }

    }
    @FXML
    void PesquisarTratorID(ActionEvent event) {
        String idString = textField_ID.getText();
        Trator trator = null;
        if (!idString.equals("")) {
            try {
                int id = Integer.valueOf(idString);
                trator = new TratorDAO().findByID(id);
            } catch (Exception e) {

            }

            if (trator != null) {
                labelTextId.setVisible(true);
                labelTextIdInserted.setVisible(true);
                labelTextId.setText(trator.getId() + "");
                textFieldMarca.setText(trator.getMarca());
                textFieldModelo.setText(trator.getModelo());
                textFieldano.setText("" + trator.getAno());
                textFieldValor.setText(trator.getValor() + "");
            }

        }
        textField_ID.clear();

    }

    @FXML
    void AlterarTrator(ActionEvent event) {
        Trator trator = pegaDadosID();
        limpaCampos();
        int qtde = new TratorDAO().alterar(trator);
        listarTrator();
    }

    private void limpaCampos() {
        textFieldModelo.clear();
        textFieldMarca.clear();
        textFieldano.clear();
        textFieldValor.clear();
        textFieldMarca.requestFocus();
        labelTextId.setVisible(false);
        labelTextIdInserted.setVisible(false);
        labelTextIdInserted.setText("");

    }

    private Trator pegaDadosID() {
        return new Trator(Integer.valueOf(labelTextId.getText()), textFieldMarca.getText(), textFieldModelo.getText(),
                Integer.valueOf(textFieldano.getText()), Float.valueOf(textFieldValor.getText()));
    }

    private Trator pegaTrator() {
        return new Trator(textFieldMarca.getText(), textFieldModelo.getText(), Integer.valueOf(textFieldano.getText()),
                Float.valueOf(textFieldValor.getText()));
    }

    public void inserirTrator() {
        launch();
    }

    @FXML
    void CriarTrator(ActionEvent event) {
        Trator trator = pegaTrator();
        limpaCampos();
        int qtde = new TratorDAO().inserirTrator(trator);
        listarTrator();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        listarTrator();
    }

    private void listarTrator() {

        textAreaTrator.clear();
        List<Trator> listaTrator = new TratorDAO().listAll();
        listaTrator.forEach(trator -> {
            textAreaTrator.appendText(trator.toString() + "\n");
        });
        labelQtd.setText(listaTrator.size()+"");

    }

    @Override
    public void start(Stage stage) {
        try {
            AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Trator.fxml"));
            Scene sc = new Scene(pane);
            stage.setScene(sc);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void execute() {
        launch();
    }

    @FXML
    protected void SAIR(ActionEvent e){
        System.exit(0);
    }
}



