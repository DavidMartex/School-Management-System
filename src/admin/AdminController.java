package admin;

import com.intellij.javaee.model.xml.Icon;
import com.intellij.javaee.model.xml.Listener;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import dbUtil.dbconnect;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableListValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EventListener;
import java.util.ResourceBundle;

/**
 * Created by Martex on 6/10/2017.
 */
public class AdminController implements Initializable {

    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger ham;
    @FXML
    private JFXTreeTableView<Student> tree;

    private String sql = "SELECT * FROM student";


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        JFXTreeTableColumn<Student, String> idnumber = new JFXTreeTableColumn<>("IDN");
        idnumber.setPrefWidth(150);
        idnumber.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().id;
            }
        });


        JFXTreeTableColumn<Student, String> fname = new JFXTreeTableColumn<>("First Name");
        fname.setPrefWidth(150);
        fname.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().fname;
            }
        });

        JFXTreeTableColumn<Student, String> lname = new JFXTreeTableColumn<>("Last Name");
        lname.setPrefWidth(150);
        lname.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().lname;
            }
        });

        JFXTreeTableColumn<Student, String> sex = new JFXTreeTableColumn<>("Sex");
        sex.setPrefWidth(150);
        sex.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().sex;
            }
        });

        JFXTreeTableColumn<Student, String> dob = new JFXTreeTableColumn<>("Date Of Birth");
        dob.setPrefWidth(150);
        dob.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().id;
            }
        });

        JFXTreeTableColumn<Student, String> level = new JFXTreeTableColumn<>("Class");
        level.setPrefWidth(150);
        level.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().level;
            }
        });

        JFXTreeTableColumn<Student, String> email = new JFXTreeTableColumn<>("Email");
        email.setPrefWidth(150);
        email.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().email;
            }
        });

        JFXTreeTableColumn<Student, String> address = new JFXTreeTableColumn<>("Address");
        address.setPrefWidth(150);
        address.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().address;
            }
        });

        ObservableList<Student> students = FXCollections.observableArrayList();

        tree.getStylesheets().add("/admin/JFXTreeTable-CSS.css");


        try {

            Connection conn = dbconnect.getConnection();

            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                students.add(new AdminController.Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
                final TreeItem<Student> root = new RecursiveTreeItem<AdminController.Student>(students, RecursiveTreeObject::getChildren);
                tree.getColumns().setAll(idnumber, fname, lname, sex, dob, level, email, address);
                tree.setRoot(root);
                tree.setShowRoot(false);
                tree.setEditable(true);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        tree.setRowFactory(new Callback<TreeTableView<Student>, TreeTableRow<Student>>() {
            @Override
            public TreeTableRow<Student> call(TreeTableView<Student> param) {
                final TableRow<Student>row = new TableRow<>();
                final ContextMenu rowmenu = new ContextMenu();

                MenuItem m1 = new MenuItem("One");
                MenuItem m2 = new MenuItem("Two");
                MenuItem m3 = new MenuItem("Three");

                rowmenu.getItems().addAll(m1,new SeparatorMenuItem(),m2,new SeparatorMenuItem(),m3);
                row.contextMenuProperty()
                        .bind(Bindings
                                .when(Bindings
                                        .isNotNull(row.itemProperty()))
                                .then(rowmenu)
                                .otherwise((ContextMenu)null));

                return null;
            }
        });*/

    }

    static class Student extends RecursiveTreeObject<Student> {
        StringProperty id;
        StringProperty fname;
        StringProperty lname;
        StringProperty sex;
        StringProperty dob;
        StringProperty level;
        StringProperty email;
        StringProperty address;

        public Student(String id, String fname, String lname, String sex, String dob, String level, String email, String address) {
            this.id = new SimpleStringProperty(id);
            this.fname = new SimpleStringProperty(fname);
            this.lname = new SimpleStringProperty(lname);
            this.sex = new SimpleStringProperty(sex);
            this.dob = new SimpleStringProperty(dob);
            this.level = new SimpleStringProperty(level);
            this.email = new SimpleStringProperty(email);
            this.address = new SimpleStringProperty(address);
        }
    }
}
