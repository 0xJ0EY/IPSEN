package server.sources.models.stories;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;

/**
 * Deze klasse bouwt een arraylist met verhalen, welke gebruikt worden tijdens een explore actie.
 *
 * @author richard
 */
public class StoryFactory {

    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;

    /**
     * maakt een storyfactory aan.
     * @author richard
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
     * laad de stories uit de xml file en plaatste ze in een arraylist van Story
     * @return Arraylist
     * @author richard
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

            rewards.add(new Reward(rewardElement));

        }

        return rewards;
    }
}
