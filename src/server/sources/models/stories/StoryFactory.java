package server.sources.models.stories;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import server.sources.models.goods.*;
import server.sources.models.stories.rewards.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;

/**
 * This classe builds an arrayList with stories, which are used during the explore action.
 *
 * @author Richard Kerkvliet
 */
public class StoryFactory {

    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;

    /**
     * creates a storyfactory.
     * @author Richard Kerkvliet
     */
    public StoryFactory() {

        try {
            this.factory = DocumentBuilderFactory.newInstance();
            this.builder = factory.newDocumentBuilder();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

    /**
     * Reads the stories form the stories.xml file and places them in the arraylist.
     * @return Arraylist stories
     * @author Richard Kerkvliet
     */
    public ArrayList<Story> loadStoriesFromXML() {

        ArrayList<Story> storyArrayList = new ArrayList<Story>();

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("server/resources/data/stories.xml");
            Document document = builder.parse(is);

            NodeList stories = document.getElementsByTagName("story");

            for (int i = 0; i < stories.getLength(); ++i) {
                Node storyNode = stories.item(i);
                Element storyElement = (Element) storyNode;

                String text = storyElement.getElementsByTagName("text").item(0).getTextContent();

                storyArrayList.add(new Story(text, this.fetchChoice(storyNode)));
            }

        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }

        // Return all the stories
        return storyArrayList;
    }

    private ArrayList<Choice> fetchChoice(Node storyNode) {
        ArrayList<Choice> choices = new ArrayList<Choice>();

        Element storyElement = (Element) storyNode;

        NodeList choiceNodes = storyElement.getElementsByTagName("choice");

        for (int i = 0; i < choiceNodes.getLength(); i++) {
            Node choiceNode = choiceNodes.item(i);
            Element choiceElement = (Element) choiceNode;
            String description = choiceElement.getAttribute("description");

            choices.add(new Choice(description, this.fetchOptions(choiceNode)));
        }

        return choices;
    }

    private ArrayList<Option> fetchOptions(Node choiceNode) {
        ArrayList<Option> options = new ArrayList<Option>();

        Element choiceElement = (Element) choiceNode;

        NodeList optionNodes = choiceElement.getElementsByTagName("option");

        for (int i = 0; i < optionNodes.getLength(); i++) {
            Node optionNode = optionNodes.item(i);
            Element optionElement = (Element) optionNode;

            int cost = Integer.parseInt(optionElement.getAttribute("cost"));

            options.add(new Option(cost, this.fetchRewards(optionNode)));

        }

        return options;
    }

    private ArrayList<Reward> fetchRewards(Node optionNode) {
        ArrayList<Reward> rewards = new ArrayList<Reward>();

        Element optionElement = (Element) optionNode;

        NodeList rewardNodes = optionElement.getElementsByTagName("reward");

        for (int i = 0; i < rewardNodes.getLength(); i++) {
            Node rewardNode = rewardNodes.item(i);
            Element rewardElement = (Element) rewardNode;
            String type = rewardElement.getAttribute("type");
            int value;

            switch (rewardElement.getTextContent()){
                case "COIN":
                    value = Integer.parseInt(rewardElement.getAttribute("value"));
                    rewards.add(new CoinReward(value));
                    break;
                case "VILLAGER":
                    rewards.add(new VillagerReward(type));
                    break;
                case "GOOD":
                    value = Integer.parseInt(rewardElement.getAttribute("value"));

                    Good good = this.getGoodsFromString(type);

                    rewards.add(new GoodReward(good, value));
                    break;
                case "REPUTATION":
                    value = Integer.parseInt(rewardElement.getAttribute("value"));
                    rewards.add(new ReputationReward(value));
                    break;
                case "POTION":
                    rewards.add(new PotionReward());
                    break;
                case "CIDER":
                    rewards.add(new CiderReward());
                    break;
                default:
                    System.out.println("not a reward");
                    break;
            }

        }

        return rewards;
    }

    private Good getGoodsFromString(String text) {

        switch (text){
            case "MUSHROOM":
                return new MushroomGood();

            case "FISH":
                return new FishGood();

            case "FRUIT":
                return new FruitGood();

            case "AMETHYST":
                return new AmethystGood();

            case "PAPER":
                return new PaperGood();

            case "POT":
                return new PotGood();

            case "ROPE":
                return new RopeGood();

            case "ORE":
                return new OreGood();

            default:
                return null;
        }
    }
}
