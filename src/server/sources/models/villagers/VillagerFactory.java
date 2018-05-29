package server.sources.models.villagers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class VillagerFactory {

    private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private DocumentBuilder builder = factory.newDocumentBuilder();

    private ArrayList<Villager> list;

    public VillagerFactory()throws ParserConfigurationException {

    }

    //TODO: afmaken methods
    public Villager createVillager(ArrayList<Lanterns> lanterns){
        Villager villager = new Villager(lanterns, false, false );
        return villager;
    }

    public BuilderVillager createBuilder(ArrayList<Lanterns> lanterns){
        BuilderVillager villager = new BuilderVillager(lanterns, false, false);
        return villager;
    }

    public TrainerVillager createTrainer(ArrayList<Lanterns> lanterns){
        TrainerVillager villager = new TrainerVillager(lanterns, false, false);
        return villager;
    }

    public AllroundVillager createAllround(ArrayList<Lanterns> lanterns){
        AllroundVillager villager = new AllroundVillager(lanterns, false, false);
        return villager;
    }

    public ArrayList<Villager> createFromXml(){
        list = new ArrayList<>();

        try {
            Document document = builder.parse(new InputSource(new FileReader(new File("src/server/resources/data/villagers.xml"))));

            NodeList types = document.getElementsByTagName("type");

            for (int i = 0; i < types.getLength(); i++){
                Node typesNode = types.item(i);
                switch (fetchType(typesNode).toLowerCase()) {
                    case "builder" :
                        break;
                    case "trainer" :
                        break;
                    case "allround" :
                        break;
                    case "villager" :
                        break;
                }
            }

        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }

        return list;

    }

    private String fetchType(Node typesNode){

        Element typesElement = (Element) typesNode;

        String type = typesElement.getElementsByTagName("text").item(0).getTextContent();

        return type;
    }

}
