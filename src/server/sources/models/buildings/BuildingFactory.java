package server.sources.models.buildings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import server.sources.interfaces.Perk;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by robin on 24-5-2018.
 */
public class BuildingFactory {

    private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private DocumentBuilder builder = factory.newDocumentBuilder();

    private static ArrayList<Building> buildings;

    public BuildingFactory() throws ParserConfigurationException {
    }

    /**
     * This is for generating random houses when game is started.
     * @return
     */
    public Building randomBuildings() {
        if (this.buildings.size() == 0) this.loadBuildingsFromXML();

        int key = (int) (Math.random() * this.buildings.size());

        return this.buildings.get(key);
    }

    public void loadBuildingsFromXML() {

        ArrayList<Building> buildingArrayList = new ArrayList<Building>();

        try {
            Document document = builder.parse(new InputSource(new FileReader(new File("src/server/resources/data/buildings.xml"))));

            NodeList house = document.getElementsByTagName("house");
            NodeList starhouse = document.getElementsByTagName("starhouse");
            NodeList keyhouse = document.getElementsByTagName("keyhouse");

            for (int i = 0; i < house.getLength(); ++i) {
                Node buildingNode = house.item(i);
                Element buildingElement = (Element) buildingNode;

                int price = Integer.parseInt(buildingElement.getElementsByTagName("price").item(0).getTextContent());

                buildingArrayList.add(new Building(price, this.fetchPerk(buildingNode)));
            }

            for (int i = 0; i < starhouse.getLength(); ++i){
                Node buildingNode = starhouse.item(i);
                Element buildingElement = (Element) buildingNode;

                int price = Integer.parseInt(buildingElement.getElementsByTagName("price").item(0).getTextContent());

                buildingArrayList.add(new Building(price, this.fetchPerk(buildingNode)));
            }

            for (int i = 0; i < keyhouse.getLength(); ++i){
                Node buildingNode = keyhouse.item(i);
                Element buildingElement = (Element) buildingNode;

                int price = Integer.parseInt(buildingElement.getElementsByTagName("price").item(0).getTextContent());

                buildingArrayList.add(new Building(price, this.fetchPerk(buildingNode)));
            }

        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }

        // Set all stories in the static object
        this.buildings = buildingArrayList;
    }

    private ArrayList<Perk> fetchPerk(Node buildingNode) {
        ArrayList<Perk> perks = new ArrayList<Perk>();

        Element storyElement = (Element) buildingNode;

        NodeList perkNodes = storyElement.getElementsByTagName("perk");

        for (int i = 0; i < perkNodes.getLength(); i++) {
            Node perkNode = perkNodes.item(i);
            Element perkElement = (Element) perkNode;
            String description = perkElement.getAttribute("description");

//            perks.add(new Perk(description, this.fetchOptions(perkNode)));
        }

        return perks;
    }
}
