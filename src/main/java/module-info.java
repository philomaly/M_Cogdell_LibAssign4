module com.main.m_cogdell_libraryassignment4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.main.m_cogdell_libraryassignment4 to javafx.fxml;
    exports com.main.m_cogdell_libraryassignment4;
}