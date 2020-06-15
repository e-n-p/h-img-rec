package h.img.rec;

import java.io.FileWriter;
import java.util.concurrent.TimeUnit;

class Logging {
    private FileWriter fileWriter;
    private int setLogLevel;
    enum logLevel{
        INFO,
        DEBUG,
        ERROR
    }

    Logging(String fileName, String drawMethod, String timeCode, int setLevel) {
        try {
            String finalFileName = fileName + "-" + drawMethod + "-" + timeCode + ".txt";
            this.fileWriter = new FileWriter("D://Documents//workspace//h-img-rec//res//out//" + finalFileName);
            if(setLevel > 2 || setLevel < 0){
                throw new Exception("Invalid Log level");
            }
            this.setLogLevel = setLevel;
        }catch (Exception e){
            System.out.println("Exception caught while instantiating Logging: " + e);
        }
    }

    private void write(String in){
        try {
            fileWriter.write(in);
        }catch (Exception e){
            System.out.println("Exception caught while writing to file: " + e);
        }
    }

    void logImageDetails(int w, int h, int colorQuantity){
        System.out.println("Width: " + w + " Height: " + h);
        System.out.println("There are " + colorQuantity + " colors");
        String toFile = "Input image details: \n" +"Width: " + w + " Height: " + h + "\n" +"There are " + colorQuantity + " colors\n";
        write(toFile);
    }

    void logCompletion(int i){
        String starLine = "*******************************************\n";
        System.out.println(starLine);
        System.out.println("*  Program completed in " + i + " iterations   *\n");
        System.out.println(starLine);
        String toFile = starLine + "*  Program completed in " + i + " iterations   *\n" +starLine;
        write(toFile);
    }

    void logLoopData(int i, double currentScore){
        String loop = "loop count: ";
        String score = "Stored canvas score: ";
        System.out.println(loop + i);
        System.out.println(score + currentScore);
        String toFile = loop + i + "\n" +score + currentScore + "\n";
        write(toFile);
    }

    void logTimeInfo(long completionTime){
        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(completionTime),
                TimeUnit.MILLISECONDS.toMinutes(completionTime) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(completionTime) % TimeUnit.MINUTES.toSeconds(1));
        String toFile = "Processing time taken: " + hms;
        System.out.println(toFile);
        write(toFile);

        try {
            fileWriter.close();
        }catch (Exception e){
            System.out.println("Exception caught while closing file: " + e);
        }
    }
}
