import java.util.HashSet;
import java.util.Map;
import java.util.Collections;
import java.util.Set;

public class ProblemWaterJugs extends Problem {

    static final int twelveG = 0;
    static final int eightG = 1;
    static final int threeG = 2;

    boolean goal_test(Object state) {
        StateWaterJugs can_state = (StateWaterJugs) state;

        if (can_state.jugArray[twelveG] == 1 || can_state.jugArray[eightG] == 1 || can_state.jugArray[threeG] == 1)
            return true;
        else return false;
    }

    Set<Object> getSuccessors(Object state) {
        Set<Object> set = new HashSet<Object>();
        StateWaterJugs can_state = (StateWaterJugs) state;
        
        //Let's create without any constraint, then remove the illegal ones
        StateWaterJugs successor_state;
        
        //12->8
        successor_state = new StateWaterJugs(can_state);
        successor_state.jugArray[twelveG] -= 8;
        successor_state.jugArray[eightG] += 8;
        if (isValid(successor_state)) set.add(successor_state);

        //8->12
        successor_state = new StateWaterJugs(can_state);
        successor_state.jugArray[twelveG] += 8;
        successor_state.jugArray[eightG] -= 8;
        if (isValid(successor_state)) set.add(successor_state);

        //12->3
        successor_state = new StateWaterJugs(can_state);
        successor_state.jugArray[twelveG] -= 3;
        successor_state.jugArray[threeG] += 3;
        if (isValid(successor_state)) set.add(successor_state);

        //3->12
        successor_state = new StateWaterJugs(can_state);
        successor_state.jugArray[twelveG] += 3;
        successor_state.jugArray[threeG] -= 3;
        if (isValid(successor_state)) set.add(successor_state);

        //8->3
        successor_state = new StateWaterJugs(can_state);
        successor_state.jugArray[eightG] -= 3;
        successor_state.jugArray[threeG] += 3;
        if (isValid(successor_state)) set.add(successor_state);

        //3->8
        successor_state = new StateWaterJugs(can_state);
        successor_state.jugArray[eightG] += 3;
        successor_state.jugArray[threeG] -= 3;
        if (isValid(successor_state)) set.add(successor_state);

        //12->0
        successor_state = new StateWaterJugs(can_state);
        successor_state.jugArray[twelveG] -= 12;
        if (isValid(successor_state)) set.add(successor_state);

        //0->12
        successor_state = new StateWaterJugs(can_state);
        successor_state.jugArray[twelveG] += 12;
        if (isValid(successor_state)) set.add(successor_state);

        //8->0
        successor_state = new StateWaterJugs(can_state);
        successor_state.jugArray[eightG] -= 8;
        if (isValid(successor_state)) set.add(successor_state);

        //0->8
        successor_state = new StateWaterJugs(can_state);
        successor_state.jugArray[eightG] += 8;
        if (isValid(successor_state)) set.add(successor_state);

        //3->0
        successor_state = new StateWaterJugs(can_state);
        successor_state.jugArray[threeG] -= 3;
        if (isValid(successor_state)) set.add(successor_state);

        //0->3
        successor_state = new StateWaterJugs(can_state);
        successor_state.jugArray[threeG] += 3;
        if (isValid(successor_state)) set.add(successor_state);

        return set;
    }

    private boolean isValid(StateWaterJugs state)
    {   
        //Checking to see if any element of the array is negative 
        for (int i=0; i<3; i++)
            if (state.jugArray[i] < 0) return false;
        
        //Checking to see if the numbers of containers 
        //are more then 12,8,3 respectively
        if ((state.jugArray[twelveG] > 12)
        ||(state.jugArray[eightG] > 8)
        ||(state.jugArray[threeG] > 3)) return false;

        return true;
    }

    double step_cost(Object fromState, Object toState) {
        StateWaterJugs from = (StateWaterJugs) fromState;
		StateWaterJugs to = (StateWaterJugs) toState;
		int cost = 0;
		for (int i = 0; i<3; i++){
			cost = Math.max(cost, Math.abs(to.jugArray[i] - from.jugArray[i]));
		}
		return cost;
    }

    public double h(Object state) {
		return 0;
    }

    public static void main(String[] args) throws Exception {
        ProblemWaterJugs problem = new ProblemWaterJugs();
        int[] jugArray = { 0,0,0 };
        problem.initialState = new StateWaterJugs(jugArray);

        Search search = new Search(problem);

        int limit = 6;


        //Tree Search
        System.out.println("TreeSearch----------------------------------");
        System.out.println("BreadthFirstTreeSearch:\t\t\t" + search.BreadthFirstTreeSearch());
        System.out.println("UniformCostTreeSearch:\t\t\t" + search.UniformCostTreeSearch());
        System.out.println("DepthFirstTreeSearch:\t\t\t" + search.DepthFirstTreeSearch());
        System.out.println("GreedyBestFirstTreeSearch:\t\t" + search.GreedyBestFirstTreeSearch());
        System.out.println("DepthLimitedTreeSearch:\t\t\t" + search.DepthLimitedGraphSearch(limit));
        System.out.println("IterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch());
        System.out.println("AStarTreeSearch:\t\t\t\t" + search.AstarTreeSearch() + "\n");

        //Graph Search
        System.out.println("GraphSearch----------------------------------");
        System.out.println("BreadthFirstGraphSearch:\t\t\t\t" + search.BreadthFirstGraphSearch());
        System.out.println("UniformCostGraphSearch:\t\t\t" + search.UniformCostGraphSearch());
        System.out.println("DepthFirstGraphSearch:\t\t\t" + search.DepthFirstGraphSearch());
        System.out.println("GreedyBestFirstGraphSearch:\t\t" + search.GreedyBestFirstGraphSearch());
        System.out.println("DepthLimitedGraphSearch:\t\t" + search.DepthLimitedGraphSearch(limit));
        System.out.println("IterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());
        System.out.println("AStarGraphSearch:\t\t\t\t" + search.AstarGraphSearch());
		}
}