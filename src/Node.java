import org.dreambot.api.methods.MethodContext;

/**
 * Created by ncorsopassaro on 8/9/16.
 */

public abstract class Node {
    private MethodContext context;

    public Node(MethodContext context){
        this.context = context;
    }
    public int priority () {
        return 0;
    }
    public abstract boolean accept();

    //This can return whatever you like.
    public abstract int execute();

    //We can now use this inside of any class which extends node.
    public MethodContext getContext(){
        return context;
    }
}