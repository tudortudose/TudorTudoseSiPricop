package command;

import catalog.*;
import exception.EmptyCatalogException;
import exception.NullCatalogException;
import freemarker.core.ParseException;
import freemarker.template.*;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Report Command implementation
 *
 * @author Tudose Tudor-Cristian 2A2
 */
public class ReportCommand implements Command {

    /**
     * It creates and opens an html report of the catalog using freemarker
     *
     * @param catalog the catalog to execute the command on
     * @throws NullCatalogException if the catalog is null
     * @throws EmptyCatalogException if the catalog has no items
     */
    @Override
    public void execute(Catalog catalog) throws EmptyCatalogException, NullCatalogException {
        if (catalog == null) throw new NullCatalogException("Null catalog argument given in ReportCmd!");
        if (catalog.getItems().size() == 0) throw new EmptyCatalogException("Cant create report of an empty catalog!");

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);
        configuration.setClassForTemplateLoading(ReportCommand.class, "/template");

        try {
            Template template = configuration.getTemplate("catalog.ftl");
            File myFile = new File("./src/main/resources/report/report.html");
            Writer writer = new FileWriter(myFile);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("items", catalog.getItems());
            template.process(map, writer);

            Desktop desktop = Desktop.getDesktop();
            desktop.open(myFile);

        } catch (TemplateException e) {
            System.out.println("Template expr: " + e.getMessage());
        } catch (TemplateNotFoundException e) {
            System.out.println("Template not found expr: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Parse expr: " + e.getMessage());
        } catch (MalformedTemplateNameException e) {
            System.out.println("Template name expr: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO expr: " + e.getMessage());
        }
    }
}
