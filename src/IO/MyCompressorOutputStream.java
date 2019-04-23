package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;


    public MyCompressorOutputStream(OutputStream ops) {
        out = ops;
    }


    public void write(int b) {
    }

    // Change binary number to decimal
    private byte BinaryToDecimal(byte[] array) {
        int intNum = 0;
        double power = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            intNum = intNum + array[i] * (int) Math.pow(2, power);
            power++;
        }
        return (byte) intNum;
    }
    //Array list to Byte []
    public byte[] listToByte(ArrayList al) {
        byte[] b = new byte[al.size()];
        for (int i = 0; i < al.size(); i++) {
            b[i] = (byte) al.get(i);
        }
        return b;
    }

    //        ArrayList<Byte> temp = new ArrayList<>();
//
//        int j = 8;//where the maze's values start
//        byte[] bitSend = new byte[8];
//        while (j < b.length) {
//            int count = 0;
//            int tempSize;
//            while (count < 8 && j < b.length) { //send first 8 details
//                bitSend[count] = b[j];
//                j++;
//                count++;
//            }
//            if (count == 8)
//                temp.add(BinaryToDecimal(bitSend));
//            else
//            {//when last 8 bytes are less then 8
//                tempSize = b.length % 8;
//                byte[] bitSend2 = new byte[tempSize];
//                for (int i = 0; i < bitSend2.length; i++)
//                    bitSend2[i] = bitSend[i];
//                temp.add(BinaryToDecimal(bitSend2));
//            }
//        }
//        byte[] compressedMaze = new byte[8 + temp.size()];
//        int copy = 0;
//        for (; copy < 8; copy++)
//            compressedMaze[copy] = b[copy];//8 cells for maze's info
//        while (temp.size() != 0) {
//            compressedMaze[copy] = temp.remove(0);
//            copy++;
//        }
//        try {
//            for (int i = 0; i < compressedMaze.length; i++)
//                out.write(compressedMaze[i]);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    public void write(byte[] b) throws IOException {
        ArrayList<Byte> listByte = new ArrayList<>();
        byte[] tempByte = new byte[8];
        int counter = 0, i=0;

        //Add the details to list:
        for (i = 0; i < 8; i++) {
            listByte.add(b[i]);
        }
        //take binary number that compound 8 numbers and change to decimal, until  binary number small from 8,
        while(i <= b.length) {
            if (counter < 8 && i< b.length) {
                tempByte[counter] = b[i];
                counter++;
                i++;
            } else if (counter == 8) {
                byte decimalNum = BinaryToDecimal(tempByte);
                listByte.add(decimalNum);
                counter = 0;
                tempByte[counter] = b[i];
                i++;
                //small from 8:
            } else {
                byte[] tempSmallByte = new byte[b.length % 8];
                for (int j = 0; j < tempSmallByte.length; j++)
                    tempSmallByte[j] = tempByte[j];
                byte decimalSmallNum = BinaryToDecimal(tempSmallByte);
                listByte.add(decimalSmallNum);
                i=b.length+1;
            }
        }
        //Write out
        byte[] compressed = listToByte(listByte);
        for (int k = 0; k < compressed.length; k++) {
                out.write(compressed[k]);
        }
    }

    

}








