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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StoryFactory {

    private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private DocumentBuilder builder = factory.newDocumentBuilder();

    private static ArrayList<Story> stories;

    public StoryFactory() throws ParserConfigurationException {
    }

    public Story randomStory() {
        if (StoryFactory.stories.size() == 0) this.loadStoriesFromXML();

        int key = (int) (Math.random() * StoryFactory.stories.size());

        return StoryFactory.stories.get(key);
    }

    public void loadStoriesFromXML() {

        ArrayList<Story> storyArrayList = new ArrayList<Story>();

        try {
            Document document = builder.parse(new InputSource(new FileReader(new File("src/server/resources/data/stories.xml"))));

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

        // Set all stories in the static object
        StoryFactory.stories = storyArrayList;
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

            rewards.add(new Reward(rewardElement.getTextContent()));

        }

        return rewards;
    }
}
