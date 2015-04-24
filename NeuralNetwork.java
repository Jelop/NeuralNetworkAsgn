import java.util.*;

public class NeuralNetwork{

    Neurode[] input, hidden, output;
    double learning, momentum, errCriterion;
    double[][] patterns;
    double[][] teacher;
    int[] indexShuffle;

    public NeuralNetwork(int inputsize, int hiddensize, int outputsize,
                           double learning, double momentum,
                           double errCriterion, double[][] patterns,
                           double[][] teacher){

        input = new Neurode[inputsize];
        for(int i = 0; i < input.length; i++){
            input[i] = new Neurode(hiddensize, true, false);
        }

        hidden = new Neurode[hiddensize];
        for(int i = 0 ; i < hidden.length; i++){
            hidden[i] = new Neurode(outputsize, false, false);
        }

        output = new Neurode[outputsize];
        for(int i = 0; i < output.length; i++){
            output[i] = new Neurode(0, false, true);
        }

        indexShuffle = new int[patterns.length];
        for(int i = 0; i < indexShuffle.length; i++){
            indexShuffle[i] = i;
        }
        
        this.learning = learning;
        this.momentum = momentum;
        this.errCriterion = errCriterion;
        this.patterns = patterns;
        this.teacher = teacher;
    }

    public void learn(){

        double popError = 1;
        double populationErrSum = 0;
        int epochs = 0;
        //For each pattern
        while(true){
            if(epochs % 100 == 0){
                System.out.println("Epochs: " + epochs);
                System.out.println("Population error " + popError);
            }
            shuffle();
            // System.out.println("PopError " + popError);
        for(int i = 0; i < patterns.length; i++){
            //Sets activation for each neurode in each layer
            for(int j = 0; j < input.length; j++){
                input[j].setActivation(patterns[indexShuffle[i]][j]);
            }

            for(int j = 0; j < hidden.length; j++){
                hidden[j].setActivation(calculateActivation(j, input, hidden));
            }

            for(int j = 0; j < output.length; j++){
                output[j].setActivation(calculateActivation(j, hidden, output));
            }

            for(int j = 0; j < output.length; j++){
                double activation = output[j].getActivation();
                double error = (teacher[indexShuffle[i]][j] - activation)
                    * activation * (1 - activation);
                output[j].setError(error);
                calculateWeightChange(j, error, hidden, output);
            }

            for(int j = 0; j < hidden.length; j++){
                double activation = hidden[j].getActivation();
                double outputsum = 0;
                for(int k = 0; k < output.length; k++){
                    outputsum += output[k].getError() * hidden[j].getWeight(k);
                }
                double error = activation * (1 - activation) * outputsum;
                calculateWeightChange(j, error, input, hidden);
            }
            
            for(int j = 0; j < hidden.length; j++){
                hidden[j].updateWeights();
            }

            for(int j = 0; j < input.length; j++){
                input[j].updateWeights();
            }

            double patternError = 0;
            for(int j = 0; j < output.length; j++){
                patternError += Math.pow(
                                         (teacher[indexShuffle[i]][j] -
                                          output[j].getActivation()), 2);
            }
            populationErrSum += patternError;
            
        }

        popError = (double)(populationErrSum / (output.length *
                                                patterns.length));
        populationErrSum = 0;
        epochs++;
        if(popError < errCriterion) break;
        //System.out.println("Epochs: " + epochs);
        }

        System.out.println("Epochs: " + epochs);
        System.out.print("Input: ");
        for(int j = 0; j < input.length; j++){
            System.out.print(input[j].getActivation() + " ");
        }
        System.out.print("\nOutput: ");
        for(int j = 0; j < output.length; j++){
            System.out.print(output[j].getActivation() + " ");
        }
        System.out.println();
    }



     public void shuffle(){
        Random rand = new Random();
        for(int i = indexShuffle.length - 1; i > 0; i--){
            int j = rand.nextInt(i+1);
            int temp = indexShuffle[i];
            indexShuffle[i] = indexShuffle[j];
            indexShuffle[j] = temp;
        }
    }

    public double calculateActivation(int index, Neurode[] prevLayer,
                                      Neurode[] layer){

        double sum = 0;
        for(int i = 0; i < prevLayer.length; i++){
            sum += prevLayer[i].getWeight(index) * prevLayer[i].getActivation();
        }
        sum += layer[index].getBias();
        return (double)(1/(1 + Math.pow(Math.E, -sum)));
    }

    public void calculateWeightChange(int index, double error,
                                      Neurode[] prevLayer, Neurode[] layer){

        for(int i = 0; i < prevLayer.length; i++){
            double change = learning * error * prevLayer[i].getActivation();
            prevLayer[i].setWeight(index, change, momentum);
        }

        layer[index].setBias(learning * error * 1);
        
    }

    public void printWeights(){

        System.out.println("\nHidden Weights");
        for(int i = 0; i < hidden.length; i++){
            for(int j = 0; j < input.length; j++){
                System.out.println("I" + j + " -> " + "H" + i + ": " +
                                   input[j].getWeight(i));
            }
            System.out.println("H" + i + " Bias: " + hidden[i].getBias() +"\n");
        }

        System.out.println("\nOutput Weights");
        for(int i = 0; i < output.length; i++){
            for(int j = 0; j < hidden.length; j++){
                System.out.println("H" + j + " -> " + "O" + i + ": " +
                                   hidden[j].getWeight(i));
            }
            System.out.println("O" + i + " Bias: " + output[i].getBias() +"\n");
        }
    }

    public void printActivations(){

        System.out.println("\nInput Activations");
        for(int i = 0; i < input.length; i++){
            System.out.println("I" + i + ": " + input[i].getActivation());
        }

        System.out.println("\nHidden Activations");
        for(int i = 0; i < hidden.length; i++){
            System.out.println("H" + i + ": " + hidden[i].getActivation());
        }

        System.out.println("\nOutput Activations");
        for(int i = 0; i < output.length; i++){
            System.out.println("O" + i + ": " + output[i].getActivation());
        }
    }
}
