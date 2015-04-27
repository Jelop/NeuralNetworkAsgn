//NetworkApp

//double[][] testpatterns = new double[0][0];
//double[][] testteacher = new double[0][0];
//double[][] datasum = new double[500][2];
//double[][] temp = new double[500][2];
//int[] epochs = new int[50];
//boolean parseTest;

/*if(args.length == 0){
  System.out.print("Use cl argument boolean to indicate desire");
  System.out.println(" to parse test patterns");
  System.exit(0);
  }

  parseTest = Boolean.parseBoolean(args[0]);*/


/*if(parseTest){
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
  }*/

/*
  //Used for momentum and learning constant test generation
          for(momentum = 0; momentum < 1; momentum += 0.05){
          int sum = 0;
          int num = 50;
          for(int i = 0; i < 50; i++){
          net = new NeuralNetwork(input, hidden, output, learning,
          momentum, errCriterion,
          patterns, teacher, testpatterns,
          testteacher);
          epochs[i] = net.learn(parseTest);
          if(epochs[i] == 10000){
          num--;
          } else {
          sum += epochs[i];
          }
          }

          double mean = (double)sum/(double)num;
          double stddev = 0;
          for(int i = 0; i < 50; i++){
          if(epochs[i] != 10000){
          stddev += Math.pow((epochs[i] - mean), 2);
          }
          }

          stddev /= 49;
          stddev = Math.sqrt(stddev);
                
          System.out.printf("%.2f %.4f %.4f\n", momentum, mean, stddev);
          }*/
            
        
//Used for generating generalisation graph data
/*
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
*/



//NeuralNetwork.java

// double[][] testpatterns;
//double[][] testteacher;
//double[][] data;
//data = new double[500][2];
// this.testpatterns = testpatterns;
//this.testteacher = testteacher;
// System.out.println("PopError " + popError);
/*if(generalise){
  data[epochs][0] = popError;
  data[epochs][1] = test();
  }*/
