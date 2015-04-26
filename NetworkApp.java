import java.util.*;
import java.io.*;

public class NetworkApp{

    public static void main(String[] args){

        int input = 0, hidden = 0, output = 0, lines = 0;
        double learning = 0, momentum = 0, errCriterion = 0;
        double[][] patterns = new double[0][0];
        double[][] teacher = new double[0][0];
        double[][] testpatterns = new double[0][0];
        double[][] testteacher = new double[0][0];
        double[][] datasum = new double[500][2];
        double[][] temp = new double[500][2];
        boolean parseTest;

        if(args.length == 0){
            System.out.print("Use cl argument boolean to indicate desire");
            System.out.println(" to parse test patterns");
            System.exit(0);
        }

        parseTest = Boolean.parseBoolean(args[0]);

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
            
          
            
            if(parseTest){
                lines = 0;
                counter = new Scanner(new File("test.txt"));
                while(counter.hasNextLine()){
                    counter.nextLine();
                    lines++;
                }

                testpatterns = new double[lines][input];
                inputdata = new Scanner(new File("test.txt"));
                for(int i = 0; i < testpatterns.length; i++){
                    for(int j = 0; j < testpatterns[i].length; j++){
                        testpatterns[i][j] = inputdata.nextDouble();
                    }
                }

                testteacher = new double[lines][output];
                teachdata = new Scanner(new File("testteach.txt"));
                for(int i = 0; i < testteacher.length; i++){
                    for(int j = 0; j < testteacher[i].length; j++){
                        testteacher[i][j] = teachdata.nextDouble();
                    }
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
                                              patterns, teacher, testpatterns,
                                              testteacher);


        //Used for generating graph data

        for(int i = 0; i < 50; i++){
            net = new NeuralNetwork(input, hidden, output, learning,
                                    momentum, errCriterion,
                                    patterns, teacher, testpatterns,
                                    testteacher);
            temp = net.learn(parseTest);
            for(int j = 0; j < datasum.length; j++){
                for(int k = 0; k < datasum[j].length; k++){
                    datasum[j][k] += temp[j][k];
                }
            }
        }

        for(int j = 0; j < datasum.length; j++){
            for(int k = 0; k < datasum[j].length; k++){
                datasum[j][k] = datasum[j][k]/50;
            }
        }

        for(int j = 0; j < datasum.length; j++){
                System.out.println(datasum[j][0]);
            }
        System.out.println();

        for(int j = 0; j < datasum.length; j++){
            System.out.println(datasum[j][1]);
        }
        /* while(userinput.hasNext()){
            switch(userinput.next()){


            case "c":
                net = new NeuralNetwork(input, hidden, output, learning,
                                        momentum, errCriterion,
                                        patterns, teacher, testpatterns,
                                        testteacher);
                System.out.println("New network initialised");
                break;
                
            case "l":
                net.learn(parseTest);
                break;

            case "t":
                if(parseTest)
                    net.test();
                break;

            case "g":
                net.learn(parseTest);
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
            }*/
    }
}
            

            
