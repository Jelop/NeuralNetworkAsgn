//Author: Joshua La Pine - April 2015
import java.util.*;

/**
 * Building block of my Neural Network implementation.
 * Stores all of the weights of its forwards connections.
 * And the previous weight changes to facilitate use of momentum
 */
public class Neurode{

    private double bias = 0;
    private double[] weights;
    private double[] changes;
    private double[] prevChanges;
    
    private double activation;
    private double error;

    /**
     * Takes the size of the next layer as an int and uses it to initialise
     * the weights array. 2 boolean flags determine which layer the neurode
     * is in and initialises things accordingly.
     */
    public Neurode(int nextLayer, boolean inputNeurode, boolean outputNeurode){

        Random random = new Random();
        if(!inputNeurode)
            bias = random.nextDouble() * 0.6 - 0.3;
        if(!outputNeurode){
            weights = new double[nextLayer];
            changes = new double[nextLayer];
            prevChanges = new double[nextLayer];
            for(int i = 0; i < nextLayer; i++){
                weights[i] = random.nextDouble() * 0.6 - 0.3;
            }
        }
    }

    public double getWeight(int index){
        return weights[index];
    }

    public double getActivation(){
        return activation;
    }

    public double getBias(){
        return bias;
    }

    public double getError(){
        return error;
    }

    /**
     * Stores the weight change so it can be updated after the backwards
     * bass of the backprop algorithm has completed.
     */
    public void setWeight(int index, double change, double momentum){
        changes[index] = change + (momentum * prevChanges[index]);
    }

    public void setActivation(double activation){
        this.activation = activation;
    }

    public void setBias(double change){
        bias += change;
    }

    public void setError(double error){
        this.error = error;
    }

    /**
     * Implements the weight changes stored in the changes array.
     */
    public void updateWeights(){
        
        for(int i = 0; i < weights.length; i++){
            weights[i] += changes[i];
            prevChanges[i] = changes[i];
        }
    }
                                              
}
