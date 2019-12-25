import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

public class User {
    private int coordinateI, coordinateJ, value, duidokouWins = 0, duidokuLosses = 0;
    private HashSet<Integer> classicPuzzlesPlayed, killerPuzzlesPlayed;
    private String name;
    public User(String aName){
          name = aName;
          classicPuzzlesPlayed = new HashSet<>();
          killerPuzzlesPlayed = new HashSet<>();
    }

    public int getUsersValue(){ return this.value; }
    public int getUsersCoordinateI(){
        return this.coordinateI;
    }

    public int getUsersCoordinateJ(){
        return this.coordinateJ;
    }

    public HashSet<Integer> getClassicPuzzlesPlayed() {
        return classicPuzzlesPlayed;
    }

    public HashSet<Integer> getKillerPuzzlesPlayed() {
        return killerPuzzlesPlayed;
    }

    public void addValueToClassicPuzzlesPlayed(int i){
        classicPuzzlesPlayed.add(i);
    }

    public void addValueToKillerPuzzlesPlayed(int i){
        killerPuzzlesPlayed.add(i);
    }

    public void addWins(){
        duidokouWins++;
    }

    public void addLosses(){
        duidokuLosses++;
    }

    public boolean checkIfExistsInKillerPuzzlesPlayed(int value){
        return killerPuzzlesPlayed.contains(value);
    }

    public boolean checkIfExistsInClassicPuzzlesPlayed(int value){
        return classicPuzzlesPlayed.contains(value);
    }

    public void insertStatisticsInTheFile(){
        try (BufferedWriter out = new BufferedWriter(
                new FileWriter(name + ".txt"));
        ){
            Iterator<Integer> c = classicPuzzlesPlayed.iterator();
            while(c.hasNext()){
                out.write(c.next());
            }
            out.write("\n");
            out.write("\n");

            Iterator<Integer> k = killerPuzzlesPlayed.iterator();
            while(k.hasNext()){
                out.write(k.next());
            }
            out.write("\n");
            out.write("\n");
            out.write(duidokouWins);
            out.write("\n");
            out.write("\n");
            out.write(duidokuLosses);





        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void readTheStatisticsFromTheFile(){
        int k = 0;
        try (BufferedReader in = new BufferedReader(
                new FileReader(name + ".txt"));
        ){
            String l;
            while ((l = in.readLine()) != null) {
                if(l.isEmpty())
                    k++;
                if(k == 0) {
                    for (int j = 0; j < l.length(); j++) {
                        classicPuzzlesPlayed.add(Character.getNumericValue(l.charAt(j)));
                    }
                }else if(k == 1){
                    for (int j = 0; j < l.length(); j++) {
                        killerPuzzlesPlayed.add(Character.getNumericValue(l.charAt(j)));
                    }
                }else if(k == 2){
                    int x = 0;
                    for (int j = 0; j < l.length(); j++) {
                        x = x + Character.getNumericValue(l.charAt(j)) * 10^(l.length()-j-1);
                    }
                    duidokouWins = duidokouWins + x;
                }else if(k == 3){
                    int x = 0;
                    for (int j = 0; j < l.length(); j++) {
                        x = x + Character.getNumericValue(l.charAt(j)) * 10^(l.length()-j-1);
                    }
                    duidokuLosses = duidokuLosses + x;
                }

            }


        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
