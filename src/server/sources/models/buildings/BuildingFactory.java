package server.sources.models.buildings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import server.sources.models.goods.*;
import server.sources.models.perks.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by robin on 24-5-2018.
 */
public class BuildingFactory {

    private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private DocumentBuilder builder = factory.newDocumentBuilder();

    private Document document;

    /**
     * Creates a buildingFactory
     * @throws ParserConfigurationException
     * @author Robin Silverio
     */
    public BuildingFactory() throws ParserConfigurationException {

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("server/resources/data/buildings.xml");
            this.document = builder.parse(is);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Read Houses from building XML.
     * @return MarketHouses Arraylist
     * @author Robin Silverio
     */
    public ArrayList<MarketHouse> loadHousesFromXML(){
        ArrayList<MarketHouse> housesArrayList = new ArrayList<MarketHouse>();

        System.out.println("document = " + document.getXmlEncoding());
        NodeList house = document.getElementsByTagName("house");

        for (int i = 0; i < house.getLength(); i++){
            Node buildingNode = house.item(i);
            Element buildingElement = (Element) buildingNode;

            int price = Integer.parseInt(buildingElement.getElementsByTagName("price").item(0).getTextContent());
            String image = buildingElement.getElementsByTagName("img").item(0).getTextContent();

            try {
                housesArrayList.add(new MarketHouse(price, this.fetchPerk(buildingNode), image));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        return housesArrayList;
    }

    /**
     * Read Star Houses from building XML
     * @return MarketStarHouses ArrayList
     * @author Robin Silverio
     */
    public ArrayList<MarketStarHouse> loadStarHousesFromXML(){
        ArrayList<MarketStarHouse> starHousesArrayList = new ArrayList<MarketStarHouse>();

        NodeList house = document.getElementsByTagName("starhouse");

        for (int i = 0; i < house.getLength(); ++i) {
            Node buildingNode = house.item(i);
            Element buildingElement = (Element) buildingNode;

            int price = Integer.parseInt(buildingElement.getElementsByTagName("price").item(0).getTextContent());

            String image = buildingElement.getElementsByTagName("img").item(0).getTextContent();

            try {
                starHousesArrayList.add(new MarketStarHouse(price, this.fetchPerk(buildingNode), image));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return starHousesArrayList;
    }

    /**
     * Read Key Houses from building XML
     * @return MarketKeyHouses ArrayList
     * @author Robin Silverio
     */
    public ArrayList<MarketKeyHouse> loadKeyHousesFromXML(){
        ArrayList<MarketKeyHouse> keyHousesArrayList = new ArrayList<MarketKeyHouse>();

        NodeList house = document.getElementsByTagName("keyhouse");

        for (int i = 0; i < house.getLength(); ++i) {
            Node buildingNode = house.item(i);
            Element buildingElement = (Element) buildingNode;

            int price = Integer.parseInt(buildingElement.getElementsByTagName("price").item(0).getTextContent());
            String image = buildingElement.getElementsByTagName("img").item(0).getTextContent();

            try {
                keyHousesArrayList.add(new MarketKeyHouse(price, this.fetchPerk(buildingNode), image));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        return keyHousesArrayList;
    }

    /**
     * Read outposts from building XML
     * @return MarketOutpost ArrayList
     * @author Robin Silverio
     */
    public ArrayList<MarketOutpost> loadOutpostsFromXML(){
        ArrayList<MarketOutpost> outpostsArrayList = new ArrayList<MarketOutpost>();

        NodeList house = document.getElementsByTagName("outpost");

        for (int i = 0; i < house.getLength(); ++i) {
            Node buildingNode = house.item(i);
            Element buildingElement = (Element) buildingNode;

            int price = Integer.parseInt(buildingElement.getElementsByTagName("price").item(0).getTextContent());

            String image = buildingElement.getElementsByTagName("img").item(0).getTextContent();

            try {
                outpostsArrayList.add(new MarketOutpost(price, this.fetchPerk(buildingNode), image));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        return outpostsArrayList;
    }

    private ArrayList<Perk> fetchPerk(Node buildingNode) {
        ArrayList<Perk> perks = new ArrayList<Perk>();

        Element buildingElement = (Element) buildingNode;

        NodeList perkNodes = buildingElement.getElementsByTagName("perk");

        for (int i = 0; i < perkNodes.getLength(); i++) {
            Node perkNode = perkNodes.item(i);
            Element perkElement = (Element) perkNode;

            Good good = null;

            switch (perkElement.getTextContent()){
                case "REPLENISHABLE_PERK":
                    good = this.getGoodFromString(perkElement.getAttribute("good"));
                    perks.add(new ReplenishableGoodPerk(good));
                    break;

                case "HARVESTABLE_PERK":
                    good = this.getGoodFromString(perkElement.getAttribute("good"));
                    perks.add(new HarvestableGoodPerk(good));
                    break;

                case "CIDER":
                    perks.add(new CiderPerk(Integer.parseInt(perkElement.getAttribute("value"))));
                    break;

                case "POTION":
                    perks.add(new PotionPerk());
                    break;

                case "REROLL":
                    perks.add(new RerollPerk(perkElement.getTextContent()));
                    break;

                case "INCOME":
                    perks.add(new IncomePerk(Integer.parseInt(perkElement.getAttribute("value"))));
                    break;

                case "COINS":
                    perks.add(new CoinPerk(Integer.parseInt(perkElement.getAttribute("value"))));
                    break;

                case "TRAIN_TO_READY":
                    perks.add(new TrainToReadyPerk());
                    break;

                case "INCOME_FOR_GOODS":
                    perks.add(
                        new IncomeForGoodsPerk(
                            Integer.parseInt(perkElement.getAttribute("value")),
                            perkElement.getAttribute("good")
                        )
                    );
                    break;

                case "BEDS":
                    perks.add(new BedPerk());
                    break;

                case "VILLAGE_POINTS":
                    perks.add(new VillagePointsPerk(Integer.parseInt(perkElement.getAttribute("value"))));
                    break;

                case "VILLAGE_POINTS_FOR_THINGS":

                    good = this.getGoodFromString(perkElement.getAttribute("good"));

                    perks.add(
                        new VillagePointsForGoodsPerk(
                            Integer.parseInt(perkElement.getAttribute("value")),
                            good
                        )
                    );
                    break;

                case "VILLAGE_POINTS_FOR_EMPTY_CAVE":
                    perks.add(new VillagePointsForEmptyCavePerk(Integer.parseInt(perkElement.getAttribute("value"))));
                    break;

                case "VILLAGE_POINTS_FOR_VILLAGERS":
                    perks.add(new VillagePointsForVillagersPerk(Integer.parseInt(perkElement.getAttribute("value"))));
                    break;

                case "GAIN_REPUTATION":
                    perks.add(new ReputationPerk());
                    break;

                default:
                    break;
            }
        }
        return perks;
    }
    
    private Good getGoodFromString(String good) {

        switch (good){
            case "AMETHYST":
                return new AmethystGood();

            case "PAPER":
                return new PaperGood();

            case "FISH":
                return new FishGood();

            case "FRUIT":
                return new FruitGood();

            case "MUSHROOM":
                return new MushroomGood();

            case "ORE":
                return new OreGood();

            case "POT":
                return new PotGood();

            case "ROPE":
                return new RopeGood();

            default:
                return null;
        }
        
    }
}
