import java.util.*;

public class Neurode{

    private double bias = 0;
    private double[] weights;
    private double[] changes;
    private double[] prevChanges;
    
    private double activation;
    private double error;
    
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

    public void updateWeights(){
        
        for(int i = 0; i < weights.length; i++){
            weights[i] += changes[i];
            prevChanges[i] = changes[i];
        }
    }
                                              
}
