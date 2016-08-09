import org.dreambot.api.methods.MethodContext;

/**
 * Created by ncorsopassaro on 8/9/16.
 */
public class LootBonesNode extends Node {

    //Since we define Node with a constructor accepting a MethodContext,
    // we must do the same from each class which extends from it.
    public LootBoneNode(MethodContext context) {
        super(context);  //Pass the arguments to Node class. We need this.
    }

    @Override
    public int priority() {
        return 4;   //We set the level of priority for this node. Higher == More Priority
    }

    //We must extend this method since we declared it as abstract.
    @Override
    public boolean accept() {
        if (!getInventory.isFull()){
            GameObject bones = new GameObjects.closest("Bones");
            if (bones != null){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //We must extend this method since we declared it as abstract.
    @Override
    public int execute() {
        GameObject bones = new GameObjects.closest("Bones");
        if (bones != null){
            bones.interact("Take");
        }
        return (int)Calculations.nextGaussianRandom(400, 200); //How long do I want to wait after executing this node
    }
}
