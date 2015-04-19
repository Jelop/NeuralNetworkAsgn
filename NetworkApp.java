import java.util.*;
import java.io.*;

public class NetworkApp{

    public static void main(String[] args){

        int input = 0, hidden = 0, output = 0, lines = 0;
        double learning = 0, momentum = 0, errCriterion = 0;
        double[][] patterns = new double[1][1];
        double[][] teacher = new double[1][1];
        
        

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
        }

        /*System.out.println(input);
        System.out.println(hidden);
        System.out.println(output);
        System.out.println(lines);
        System.out.println(learning);
        System.out.println(momentum);
        System.out.println(errCriterion);
        for(int i = 0; i < patterns.length; i++){
            System.out.println(Arrays.toString(patterns[i]));
        }
        for(int i = 0; i < teacher.length; i++){
            System.out.println(Arrays.toString(teacher[i]));
            }*/

        NeuralNetwork net = new NeuralNetwork(input, hidden, output, learning,
                                              momentum, errCriterion,
                                              patterns, teacher);
        net.learn();
    }
}
            

            
