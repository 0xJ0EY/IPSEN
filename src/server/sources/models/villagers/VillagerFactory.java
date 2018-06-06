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
import java.util.ArrayList;

public class VillagerFactory {

    private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private DocumentBuilder builder = factory.newDocumentBuilder();

    private ArrayList<Villager> list;

    public VillagerFactory()throws ParserConfigurationException {

    }

    //TODO: afmaken methods
    public Villager createVillager(ArrayList<Lantern> lanterns){
        Villager villager = new Villager(lanterns, false, false );
        return villager;
    }

    public BuilderVillager createBuilder(ArrayList<Lantern> lanterns){
        BuilderVillager villager = new BuilderVillager(lanterns, false, false);
        return villager;
    }

    public TrainerVillager createTrainer(ArrayList<Lantern> lanterns){
        TrainerVillager villager = new TrainerVillager(lanterns, false, false);
        return villager;
    }

    public AllroundVillager createAllround(ArrayList<Lantern> lanterns){
        AllroundVillager villager = new AllroundVillager(lanterns, false, false);
        return villager;
    }

    public ArrayList<Villager> createFromXml() {
        list = new ArrayList<>();

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("server/resources/data/villagers.xml");
            Document document = builder.parse(is);
            
            NodeList villagers = document.getElementsByTagName("villager");


            for (int i = 0; i < villagers.getLength(); i++) {
                Node villagerNode = villagers.item(i);
                Element villagerElement = (Element) villagerNode; 
                
                // Fetch villager type
                switch (this.fetchType(villagerElement).toUpperCase()){
                    case "BUILDER" : list.add(new BuilderVillager(fetchLanterns(villagerElement), false, false));
                        break;
                    case "TRAINER" : list.add(new TrainerVillager(fetchLanterns(villagerElement), false, false));
                        break;
                    case "ALLROUND" : list.add(new AllroundVillager(fetchLanterns(villagerElement), false, false));
                        break;
                    case "VILLAGER" : list.add(new Villager(fetchLanterns(villagerElement), false, false));
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

        // Fetch eyes
        int eyes = Integer.parseInt(lanternElement.getElementsByTagName("eyes").item(0).getTextContent());

        return new Lantern(cost, eyes);
    }

    public TinVillager createTinVillager() {
        ArrayList<Lantern> lanterns = new ArrayList<Lantern>();
        lanterns.add(new Lantern(1, 2));
        lanterns.add(new Lantern(4, 3));
        return new TinVillager(lanterns, false, false);
    }

    public FishVillager createFishVillager() {
        ArrayList<Lantern> lanterns = new ArrayList<Lantern>();
        lanterns.add(new Lantern(2, 1));
        lanterns.add(new Lantern(4, 3));
        return new FishVillager(lanterns, false, false);
    }

    public CatVillager createCatVillager() {
        ArrayList<Lantern> lanterns = new ArrayList<Lantern>();
        lanterns.add(new Lantern(1, 1));
        lanterns.add(new Lantern(4, 3));
        return new CatVillager(lanterns, false, false);
    }

    public OilGirlVillager createOilGirlVillager() {
        ArrayList<Lantern> lanterns = new ArrayList<Lantern>();
        lanterns.add(new Lantern(2, 3));
        return new OilGirlVillager(lanterns, false, false);
    }
}
