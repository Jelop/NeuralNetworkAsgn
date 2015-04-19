import java.util.*;

public class Neurode{

    private double bias = 0;
    private double[] weights;
    private double[] changes;
    
    private double activation;
    
    public Neurode(int nextLayer, boolean inputNeurode, boolean outputNeurode){

        Random random = new Random();
        if(!inputNeurode)
            bias = random.nextDouble() * 0.6 - 0.3;
        if(!outputNeurode){
            weights = new double[nextLayer];
            changes = new double[nextLayer];
            for(int i = 0; i < nextLayer; i++){
                weights[i] = random.nextDouble() * 0.6 - 0.3;
                System.out.println(weights[i]);
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
    
    public void setWeight(int index, double change){
        changes[index] = change;
    }

    public void setActivation(double activation){
        this.activation = activation;
    }

    public void setBias(double change){
        bias += change;
    }

    public void updateWeights(){
        
        for(int i = 0; i < weights.length; i++){
            weights[i] += changes[i];
        }
    }
                                              
}
