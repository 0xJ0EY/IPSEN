package server.sources.models.buildings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import server.sources.models.perks.*;

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

    public BuildingFactory() throws ParserConfigurationException {
    }

    //create Document for reading building XML
    private Document createDocument(){
        try{
            Document document = builder.parse(new InputSource(new FileReader(new File("src/server/resources/data/buildings.xml"))));
            return document;
        } catch (IOException | SAXException e){
            e.printStackTrace();
            return null;
        }
    }

    //Read Houses from building XML
    public ArrayList<House> loadHousesFromXML(){
        ArrayList<House> housesArrayList = new ArrayList<House>();

        Document document = createDocument();
        NodeList house = document.getElementsByTagName("house");

        for (int i = 0; i < house.getLength(); i++){
            Node buildingNode = house.item(i);
            Element buildingElement = (Element) buildingNode;

            int price = Integer.parseInt(buildingElement.getElementsByTagName("price").item(0).getTextContent());

            housesArrayList.add(new House(price, this.fetchPerk(buildingNode)));
        }

        return housesArrayList;
    }

    //Read Star Houses from building XML
    public ArrayList<StarHouse> loadStarHousesFromXML(){
        ArrayList<StarHouse> starHousesArrayList = new ArrayList<StarHouse>();

        Document document = createDocument();

        NodeList house = document.getElementsByTagName("starhouse");

        for (int i = 0; i < house.getLength(); ++i) {
            Node buildingNode = house.item(i);
            Element buildingElement = (Element) buildingNode;

            int price = Integer.parseInt(buildingElement.getElementsByTagName("price").item(0).getTextContent());

            starHousesArrayList.add(new StarHouse(price, this.fetchPerk(buildingNode)));
        }
        return starHousesArrayList;
    }

    //Read Key Houses from building XML
    public ArrayList<KeyHouse> loadKeyHousesFromXML(){
        ArrayList<KeyHouse> keyHousesArrayList = new ArrayList<KeyHouse>();

        Document document = createDocument();

        NodeList house = document.getElementsByTagName("keyhouse");

        for (int i = 0; i < house.getLength(); ++i) {
            Node buildingNode = house.item(i);
            Element buildingElement = (Element) buildingNode;

            int price = Integer.parseInt(buildingElement.getElementsByTagName("price").item(0).getTextContent());

            keyHousesArrayList.add(new KeyHouse(price, this.fetchPerk(buildingNode)));
        }

        return keyHousesArrayList;
    }

    //Read Outposts from building XML
    public ArrayList<Outpost> loadOutpostsFromXML(){
        ArrayList<Outpost> outpostsArrayList = new ArrayList<Outpost>();

        Document document = createDocument();

        NodeList house = document.getElementsByTagName("outpost");

        for (int i = 0; i < house.getLength(); ++i) {
            Node buildingNode = house.item(i);
            Element buildingElement = (Element) buildingNode;

            int price = Integer.parseInt(buildingElement.getElementsByTagName("price").item(0).getTextContent());

            outpostsArrayList.add(new Outpost(price, this.fetchPerk(buildingNode)));
        }

        return outpostsArrayList;
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
            switch (perkElement.getTextContent()){
                case "CIDER":
                    perks.add(
                        new CiderPerk(Integer.parseInt(perkElement.getAttribute("value")))
                    );
//                    System.out.println("Perk implemented");
                    break;
                case "POTION":
                    perks.add(
                        new PotionPerk(Integer.parseInt(perkElement.getAttribute("value")))
                    );
//                    System.out.println("Perk implemented");
                    break;
                case "REROLL":
                    perks.add(
                        new RerollPerk(perkElement.getTextContent())
                    );
//                    System.out.println("Perk implemented");
                    break;
                case "INCOME":
                    perks.add(new IncomePerk(Integer.parseInt(perkElement.getAttribute("value"))));
//                    System.out.println("Perk implemented");
                    break;
                case "COINS":
                    perks.add(
                        new CoinPerk(Integer.parseInt(perkElement.getAttribute("value")))
                    );
//                    System.out.println("Perk implemented");
                    break;
                case "AMETHYST":
                    perks.add(
                        new AmethystPerk(
                            Integer.parseInt(perkElement.getAttribute("value"))
                        )
                    );
//                    System.out.println("Perk implemented");
                    break;
                case "ORE":
                    perks.add(
                        new OrePerk(Integer.parseInt(perkElement.getAttribute("value")))
                    );
//                    System.out.println("Perk implemented");
                    break;
                case "MUSHROOM":
                    perks.add(
                        new MushroomPerk(Integer.parseInt(perkElement.getAttribute("value")))
                    );
//                    System.out.println("Perk implemented");
                    break;
                case "TRAIN_TO_READY":
                    perks.add(new Train_to_ready_perk());
//                    System.out.println("Perk implemented");
                    break;
                case "INCOME_FOR_GOODS":
                    perks.add(
                        new Income_for_goods_perk(
                            Integer.parseInt(perkElement.getAttribute("value")),
                            perkElement.getAttribute("good")
                        )
                    );
//                    System.out.println("Perk implemented");
                    break;
                case "BEDS":
                    perks.add(new BedPerk(Integer.parseInt(perkElement.getAttribute("value"))));
//                    System.out.println("Perk implemented");
                    break;
                case "VILLAGE_POINTS":
                    perks.add(new Village_points_perk(Integer.parseInt(perkElement.getAttribute("value"))));
//                    System.out.println("Perk implemented");
                    break;
                case "VILLAGE_POINTS_FOR_THINGS":
                    perks.add(
                        new Village_points_for_things_perk(
                            Integer.parseInt(perkElement.getAttribute("value")),
                            perkElement.getAttribute("goods")
                        )
                    );
//                    System.out.println("Perk implemented");
                    break;
                case "VILLAGE_POINTS_FOR_EMPTY_CAVE":
                    perks.add(
                        new Village_points_for_empty_cave_perk(Integer.parseInt(perkElement.getAttribute("value")))
                    );
//                    System.out.println("Perk implemented");
                    break;
                case "VILLAGE_POINTS_FOR_VILLAGERS":
                    perks.add(
                        new Village_points_for_villagers_perk(Integer.parseInt(perkElement.getAttribute("value")))
                    );
//                    System.out.println("Perk implemented");
                    break;
                case "ROPE":
                    perks.add(
                        new RopePerk(Integer.parseInt(perkElement.getAttribute("value")))
                    );
//                    System.out.println("Perk implemented");
                    break;
                case "GAIN_REPUTATION":
                    perks.add(
                        new ReputationPerk()
                    );
//                    System.out.println("Perk implemented");
                    break;
                default:
//                    System.out.println("Perk is unknown");
                    break;
            }
        }
        return perks;
    }
}
