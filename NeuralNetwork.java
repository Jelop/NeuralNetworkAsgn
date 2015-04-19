public class NeuralNetwork{

    Neurode[] input, hidden, output;
    double learning, momentum, errCriterion;
    double[][] patterns;
    double[][] teacher;

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

        this.learning = learning;
        this.momentum = momentum;
        this.errCriterion = errCriterion;
        this.patterns = patterns;
        this.teacher = teacher;
    }

    public void learn(){

        for(int i = 0; i < patterns.length; i++){
            for(int j = 0; j < input.length; j++){
                input[j].setActivation(patterns[i][j]);
            }

            for(int j = 0; j < hidden.length; j++){
                hidden[j].setActivation(calculateActivation(j, input));
            }
        }
    }
            
    public double calculateActivation(int index, Neurode[] prevLayer,
                                      Neurode[] layer){

        double sum = 0;
        for(int i = 0; i < prevLayer.length; i++){
            sum += prevLayer[i].getWeight[index] * prevLayer[i].getActivation();
        }
        sum += layer[index].getBias();
    }
       
}
