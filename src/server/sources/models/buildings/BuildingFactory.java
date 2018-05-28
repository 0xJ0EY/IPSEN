package server.sources.models.buildings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import server.sources.models.perks.Perk;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
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
        loadBuildingsFromXML();
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

    /**
     * This is for loading objects from XML file buildings.xml.
     */
    public void loadBuildingsFromXML() {

        ArrayList<Building> buildingArrayList = new ArrayList<Building>();

        try {
            Document document = builder.parse(new InputSource(new FileReader(new File("src/server/resources/data/buildings.xml"))));

            NodeList house = document.getElementsByTagName("house");
            NodeList starhouse = document.getElementsByTagName("starhouse");
            NodeList keyhouse = document.getElementsByTagName("keyhouse");

            Node buildingNode;
            Element buildingElement;

            for (int i = 0; i < house.getLength(); ++i) {
                buildingNode = house.item(i);
                buildingElement = (Element) buildingNode;

                int price = Integer.parseInt(buildingElement.getElementsByTagName("price").item(0).getTextContent());

                buildingArrayList.add(new House(price, this.fetchPerk(buildingNode)));
            }

            for (int i = 0; i < starhouse.getLength(); ++i){
                buildingNode = starhouse.item(i);
                buildingElement = (Element) buildingNode;

                int price = Integer.parseInt(buildingElement.getElementsByTagName("price").item(0).getTextContent());

                buildingArrayList.add(new StarHouse(price, this.fetchPerk(buildingNode)));
            }

            for (int i = 0; i < keyhouse.getLength(); ++i){
                buildingNode = keyhouse.item(i);
                buildingElement = (Element) buildingNode;

                int price = Integer.parseInt(buildingElement.getElementsByTagName("price").item(0).getTextContent());

                buildingArrayList.add(new KeyHouse(price, this.fetchPerk(buildingNode)));
            }

        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }

        // Set all stories in the static object
        this.buildings = buildingArrayList;
    }

    /**
     * This is for fetching perks per building object
     * @param buildingNode
     * @return perks
     */
    private ArrayList<Perk> fetchPerk(Node buildingNode) {
        ArrayList<Perk> perks = new ArrayList<Perk>();

        Element buildingElement = (Element) buildingNode;

        NodeList perkNodes = buildingElement.getElementsByTagName("perk");

        for (int i = 0; i < perkNodes.getLength(); i++) {
            Node perkNode = perkNodes.item(i);
            Element perkElement = (Element) perkNode;

//            System.out.println(
//                    "Building: " + buildingElement.getTagName() + "{\n" +
//                    "\tperkElement = " + perkElement.getTextContent() + "\n" +
//                    "\tvalue:" + perkElement.getAttribute("value") + "\n" +
//                    "\tgood: " + perkElement.getAttribute("good") + "\n}"
//            );
//            perks.add(new Perk(description, this.fetchOptions(perkNode)));
        }

        return perks;
    }

    public static void main(String[] args) throws ParserConfigurationException {

        new BuildingFactory();

    }
}
