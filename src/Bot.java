import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.utilities.Timer;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by ncorsopassaro on 8/9/16.
 */


@ScriptManifest(name = "BoneBot", author = "Nick", description = "Bury ALL the bones!", version = 1, category = Category.PRAYER)
public class Bot extends AbstractScript {

    //This holds the open possible nodes to use.
    private final List<Node> open = new ArrayList<Node>();
    //This holds the nodes you have created to iterate through.
    private final List<Node> cache = new ArrayList<Node>();

    private Timer t = new Timer();

    @Override
    public void onStart() {
        //This is where we add the nodes to itterate, since we made our nodes take a MethodContext, we can pass it
        // by using the keyword this from our Script class.
        cache.add(new CutTreeNode(this));
    }

    @Override
    public int onLoop() {
        int delay = 420; //Default return value
        if (!cache.isEmpty()) { //Check if empty just to be safe.
            open.clear();
            //If the node accept method is true, add node to open list. Java8 ftw :D
            open.addAll(cache.stream().filter(Node::accept).collect(Collectors.toList()));
            if (!open.isEmpty()) {
                //Find the best node in the open list
                delay = getSuitableOpenNode().execute(); //This will preform the action, and since I return int, I can use it to delay.
            }
        }


        return Calculations.random(300, 600);
    }

    public Node getSuitableOpenNode() {
        Node node = null;
        if (!open.isEmpty()) {
            node = open.get(0); //Set to the first item
            if (open.size() > 1) { //If more than one item, iterate through the list
                for (Node possible : open) {
                    if (node.priority() < possible.priority()) { //Find the highest priority node
                        node = possible;
                    }
                }
            }
        }
        return node;
    }

    //This is the list of all your nodes you have added
    public List<Node> getNodeCache() {
        return cache;
    }

    @Override
    public void onExit() {

    }

    public void onPaint(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", 1, 11));
        g.drawString("Time Running: " + t.formatTime(), 25, 50);
    }
}
