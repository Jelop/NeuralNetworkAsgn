import java.util.*;
import java.io.*;

public class NetworkApp{

    public static void main(String[] args){

        int input = 0, hidden = 0, output = 0, lines = 0;
        double learning = 0, momentum = 0, errCriterion = 0;
        double[][] patterns = new double[0][0];
        double[][] teacher = new double[0][0];
        

        try{
            Scanner parameters = new Scanner(new File("param.txt"));
            input = parameters.nextInt();
            hidden = parameters.nextInt();
            output = parameters.nextInt();
            learning = parameters.nextDouble();
            momentum = parameters.nextDouble();
            errCriterion = parameters.nextDouble();

            Scanner counter = new Scanner(new File("in.txt"));
            while(counter.hasNextLine()){
                counter.nextLine();
                lines++;
            }

            patterns = new double[lines][input];
            Scanner inputdata = new Scanner(new File("in.txt"));
            for(int i = 0; i < patterns.length; i++){
                for(int j = 0; j < patterns[i].length; j++){
                    patterns[i][j] = inputdata.nextDouble();
                }
            }

            teacher = new double[lines][output];
            Scanner teachdata = new Scanner(new File("teach.txt"));
            for(int i = 0; i < teacher.length; i++){
                for(int j = 0; j < teacher[i].length; j++){
                    teacher[i][j] = teachdata.nextDouble();
                }
            }
            
        }catch(Exception ex){
            System.err.println("Out of bounds array or IO problem");
            ex.printStackTrace();
            System.exit(0);
        }

        Scanner userinput = new Scanner(System.in);
        NeuralNetwork net = new NeuralNetwork(input, hidden, output, learning,
                                              momentum, errCriterion,
                                              patterns, teacher);

        
        while(userinput.hasNext()){
            switch(userinput.next()){


            case "c":
                net = new NeuralNetwork(input, hidden, output, learning,
                                        momentum, errCriterion,
                                        patterns, teacher);
                System.out.println("New network initialised");
                break;
                
            case "l":
                net.learn();
                break;

            case "t":
                System.out.print("Please enter an input pattern one number");
                System.out.println(" at a time");
                int count = 0;
                double[] testArgs = new double[input];
                while(count < input){
                    testArgs[count] = userinput.nextDouble();
                    count++;
                }
                net.test(testArgs);
                break;
                
            case "w":
                net.printWeights();
                break;

            case "a":
                net.printActivations();
                break;

            case "q":
                System.exit(0);
                break;
            } 
        }
    }
}
            

            
