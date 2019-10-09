/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ann;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
 /*
 * @author IqraAkhtar
 */
public class ANN {
//  static DecimalFormat df = new DecimalFormat("#.####");
    private static NeuralNetwork network;
    private static ArrayList<Node> input_Layer;
    private static ArrayList<Node> hidden_Layer;
    private static ArrayList<Node> output_Layer;
    private static ArrayList<String> inputFile;
    private static double epochError = 0;
    private static boolean isFirstEpoch = true;
    static double threshold =0.0025;
    

    private static void readFile(String fileName) {

    
        String line = null;

        try {
       
            FileReader fileReader
                    = new FileReader(fileName);

            BufferedReader bufferedReader
                    = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {

                String values[] = line.split("//s+");
                inputFile.add(line);


            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '"
                    + fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                    + fileName + "'");
         
        }
    }

    private static void initializeNetwork() {

        input_Layer = new ArrayList<>();
        hidden_Layer = new ArrayList<>();
        output_Layer = new ArrayList<>();

        //creating initial nodes  
        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();

        //nodes added in input layer 
        input_Layer.add(node1);
        input_Layer.add(node2);
        input_Layer.add(node3);

        //setting hidden layer
        Node node4 = new Node();
       // double bias;
      //   bias=Double.parseDouble(df.format(ThreadLocalRandom.current().nextDouble(0, 1)));

        node4.setBias(Mathematics.GenerateRandomDouble(0.0,1.0));
        Node node5 = new Node();
        node5.setBias(Mathematics.GenerateRandomDouble(0.0,1.0));

        hidden_Layer.add(node4);
        hidden_Layer.add(node5);

        //setting output  layer
        Node outputNode = new Node();
        outputNode.setBias(Mathematics.GenerateRandomDouble(0.0,1.0));

        output_Layer.add(outputNode);

        network.setInputLayer(input_Layer);
        network.setHiddenLayer(hidden_Layer);
        network.setOutputLayer(output_Layer);

       
        network.initializeNodesWeights(input_Layer, hidden_Layer);

    
        network.initializeNodesWeights(hidden_Layer, output_Layer);

    }
    
    public static void main(String[] args) {
        // TODO code application logic here

        inputFile = new ArrayList<>();
        network = new NeuralNetwork();

        readFile("inputdata2.txt");
initializeNetwork();
    
      double j = 1.0;
        //epoch
        while (true) {
System.out.println("EPOCHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH"+j); 
            network.setLearningRate(0.9);

            //for each tupple
           for (int i = 0; i < inputFile.size(); i++) {
                   

                String values[] = inputFile.get(i).split(",");

              
                  
                double nor_val0 = Double.parseDouble(values[4]);
                 double nor_val1 = Double.parseDouble(values[5]);
                  double nor_val2 = Double.parseDouble(values[6]);
                   double nor_val3 = Double.parseDouble(values[7]);
                  
                  
                //setting input
                network.input_Layer.get(0).addAttribute(nor_val0);
                network.input_Layer.get(0).setOutput(nor_val0);

                network.input_Layer.get(1).addAttribute(nor_val1);
                network.input_Layer.get(1).setOutput(nor_val1);

                network.input_Layer.get(2).addAttribute(nor_val2);
                network.input_Layer.get(2).setOutput(nor_val2);

                while (true) {
                    
                    //calculating output
                    network.calculateNodesOutput(network.input_Layer, network.hidden_Layer);
                    network.calculateNodesOutput(network.hidden_Layer, network.output_Layer);

                    //calculating error
                    network.calculateOutputLayerError(nor_val3, network.output_Layer.get(0).getOutput());

                    if (Math.abs(network.getError()) <= 0.0025) {

//
//

                        
                        break;

                    } else {
                        // do bacckpropagation
                        network.calculateHiddenLayerErrorsAndUpdateWeights();
                   
                    }

                }
                //System.out.println(network.getError());
//                System.out.println("THIS IS EPOCH :1 ");
       //   System.out.println("FOR RECORD : "+(i+1));
//                  System.out.println("INPUT x1 : "+Double.parseDouble(values[0])+"\t Normalizied_Value:"+nor_val0);
//                    System.out.println("INPUT x2 : "+Double.parseDouble(values[1])+"\t Normalizied_Value:"+nor_val1);
//                      System.out.println("INPUT x3 : "+Double.parseDouble(values[2])+"\t Normalizied_Value:"+nor_val2);
//                System.out.println("TARGET y : "+Double.parseDouble(values[3])+"\t Normalizied_Value:"+nor_val3);
             System.out.println("Epoch " + j + "\n" + network.toString());  
//              System.out.println(network.toString());  
//            System.out.println("previous " + epochError + " new " + network.getError());

   System.out.println(network.tooString()); 
   epochError=epochError+network.getError();
   
            }
       epochError=epochError/705;
            if (epochError <= threshold && !isFirstEpoch) {
                break;
            }
            else{
                epochError=network.getError();
            }
            isFirstEpoch = false;

            j++;
        }
    }
}

