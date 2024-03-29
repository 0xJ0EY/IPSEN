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
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class VillagerFactory {

    private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private DocumentBuilder builder = factory.newDocumentBuilder();

    public VillagerFactory() throws ParserConfigurationException {

    }

    public ArrayList<Villager> createFromXml() {
        ArrayList<Villager> list = new ArrayList<>();

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("server/resources/data/villagers.xml");
            Document document = builder.parse(is);
            
            NodeList villagers = document.getElementsByTagName("villager");


            for (int i = 0; i < villagers.getLength(); i++) {
                Node villagerNode = villagers.item(i);
                Element villagerElement = (Element) villagerNode; 

                ArrayList<Lantern> lanterns = this.fetchLanterns(villagerElement);
                String background = this.fetchImage(villagerElement);

                // Fetch villager villagerType
                switch (this.fetchType(villagerElement).toUpperCase()){
                    case "BUILDER" :
                        list.add(
                            new BuilderVillager(
                                fetchLanterns(villagerElement),
                                Villager.VillagerState.USABLE,
                                background
                            )
                        );
                        break;

                    case "TRAINER" :
                        list.add(
                            new TrainerVillager(
                                fetchLanterns(villagerElement),
                                Villager.VillagerState.USABLE,
                                background
                            )
                        );
                        break;

                    case "ALLROUND" :
                        list.add(
                            new AllroundVillager(
                                fetchLanterns(villagerElement),
                                Villager.VillagerState.USABLE,
                                background
                            )
                        );
                        break;

                    case "VILLAGER" :
                        list.add(
                            new Villager(
                                fetchLanterns(villagerElement),
                                Villager.VillagerState.USABLE,
                                background
                            )
                        );
                        break;

                }

            }

        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }

        return list;
    }

    private String fetchType(Element villager){
        return villager.getElementsByTagName("type").item(0).getTextContent();
    }

    private String fetchImage(Element villager) {
        return villager.getElementsByTagName("image").item(0).getTextContent();
    }

    private ArrayList<Lantern> fetchLanterns(Element villager) {
        ArrayList<Lantern> lanterns = new ArrayList<Lantern>();

        NodeList lanternsNode = villager.getElementsByTagName("lantern");

        for (int i = 0; i < lanternsNode.getLength(); i++) {
            Node lanternNode = lanternsNode.item(i);

            lanterns.add(this.fetchLantern((Element) lanternNode));
        }
        
        return lanterns;
    }


    private Lantern fetchLantern(Element lanternElement) {
        // Fetch cost
        int cost = Integer.parseInt(lanternElement.getElementsByTagName("cost").item(0).getTextContent());

        // Fetch amount
        int amount = Integer.parseInt(lanternElement.getElementsByTagName("amount").item(0).getTextContent());

        return new Lantern(cost, amount);
    }

    public TinVillager createTinVillager() {
        ArrayList<Lantern> lanterns = new ArrayList<Lantern>();
        lanterns.add(new Lantern(1, 2));
        lanterns.add(new Lantern(4, 3));

        try {
            return new TinVillager(
                lanterns,
                Villager.VillagerState.USABLE,
                "special_villagers/tin_villager_background.png"
            );

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return null;
    }

    public FishVillager createFishVillager() {
        ArrayList<Lantern> lanterns = new ArrayList<Lantern>();
        lanterns.add(new Lantern(2, 1));
        lanterns.add(new Lantern(4, 3));

        try {
            return new FishVillager(
                lanterns,
                Villager.VillagerState.USABLE,
                "special_villagers/fish_villager_background.png"
            );

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return null;
    }

    public CatVillager createCatVillager() {
        ArrayList<Lantern> lanterns = new ArrayList<Lantern>();
        lanterns.add(new Lantern(1, 1));
        lanterns.add(new Lantern(4, 3));

        try {
            return new CatVillager(
                lanterns,
                Villager.VillagerState.USABLE,
                "special_villagers/cat_villager_background.png"
            );

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return null;
    }

    public OilGirlVillager createOilGirlVillager() {
        ArrayList<Lantern> lanterns = new ArrayList<Lantern>();
        lanterns.add(new Lantern(2, 3));

        try {
            return new OilGirlVillager(
                lanterns,
                Villager.VillagerState.USABLE,
                "special_villagers/oil_villager_background.png"
            );

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return null;
    }
}
