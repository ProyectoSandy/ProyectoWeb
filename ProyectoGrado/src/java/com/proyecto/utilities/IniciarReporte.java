
package com.proyecto.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperCompileManager;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class IniciarReporte{
    
    public static Connection conexion = null; 

    public IniciarReporte() {
        
        String baseDatos = "jdbc:postgresql://localhost:5432/bd_formato";
        String usuario = "user_java";
        String clave = "123456";

        try{
            Class.forName("org.postgresql.Driver").newInstance();
            conexion = DriverManager.getConnection(baseDatos,usuario,clave);
        }//Fin del try
        catch (ClassNotFoundException cnxex){
            System.err.println("Fallo al cargar JDBC");
            cnxex.printStackTrace();
            System.exit(1);
        }//Fin del catch1
        catch (SQLException sqlex){
            System.err.println("No se pudo conectar a BD");
            sqlex.printStackTrace();
        }//Fin del catch2
        catch (java.lang.InstantiationException sqlex){
            System.err.println("Imposible Conectar");
            sqlex.printStackTrace();
        }//Fin del catch3
        catch (Exception sqlex){
            System.err.println("Imposible Conectar");
            sqlex.printStackTrace();
        }//Fin del catch4

    }
    
    public void runReport() throws JRException 
    {
        InputStream inputStream = null;
        try
        {
            inputStream = new FileInputStream("src/java/com/proyecto/utilities/IniciarReporte.jrxml");

        }catch(Exception e)
        {
            System.out.println(e.toString()+" Error");
        }
        Map parameters = new HashMap();
        JasperDesign jsDesing = JRXmlLoader.load(inputStream);
        JasperReport jsReport = JasperCompileManager.compileReport(jsDesing);
        JasperPrint jsPrint = JasperFillManager.fillReport(jsReport, parameters);
        JasperExportManager.exportReportToPdfFile(jsPrint, "src/java/com/proyecto/utilities/IniciarReporte.pdf");
    }


}