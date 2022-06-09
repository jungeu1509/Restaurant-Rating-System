package ratingSystem.Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import ratingSystem.Compare.IndexCompare;
import ratingSystem.Compare.NameCompare;
import ratingSystem.Compare.RateCompare;
import ratingSystem.DTO.RestaurantDTO;

public class FileRepository {

    ArrayList<RestaurantDTO> storeDTOList;
    File file;
    static final String regex = "/";

    Long maxIndex;

    public FileRepository(String filename) throws IOException {
        maxIndex = 0L;
        this.file = new File(filename);
        loadData();
    }

    public void close() throws IOException {
        saveData();
    }

    private void loadData() throws IOException {
        this.storeDTOList = new ArrayList<RestaurantDTO>();
        BufferedReader br = null;
        while (br == null) {
            try {
                br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                file.createNewFile();
            }
        }

        String str = "";
        while ((str = br.readLine()) != null) {
            String[] strArr = str.split(regex);
            RestaurantDTO now = new RestaurantDTO(strArr);
            storeDTOList.add(now);
            if(now.getIndex() > maxIndex) maxIndex = now.getIndex();
        }

        br.close();

        sortByRate();
    }

    private void saveData() throws IOException {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));

        for (int i = 0; i < storeDTOList.size(); i++) {
            RestaurantDTO now = storeDTOList.get(i);
            pw.println(now.toString(regex));
        }
        pw.close();
    }

    public String create(RestaurantDTO dto) {
		if (dto.getIndex() != 0L) {
			return null;
		}

        dto.setIndex(++maxIndex);
        if (this.storeDTOList.add(dto)) {
            return dto.toString(regex);
        }

        return "";
    }

    public ArrayList<String> showAllByIndex() {
        ArrayList<String> ret = new ArrayList<>();
        if(checkEmpty()) return ret;

        sortByIndex();

        for(int i = 0; i < this.storeDTOList.size(); i++) {
            ret.add(this.storeDTOList.get(i).toString(regex));
        }
        return ret;
    }

    public ArrayList<String> showAllByRate() {
        ArrayList<String> ret = new ArrayList<>();
        if(checkEmpty()) return ret;

        sortByRate();

        for(int i = 0; i < this.storeDTOList.size(); i++) {
            ret.add(this.storeDTOList.get(i).toString(regex));
        }

        return ret;
    }

    public ArrayList<String> searchByName(String name) {
        ArrayList<String> ret = new ArrayList<>();
        ArrayList<RestaurantDTO> temp = new ArrayList<>();
        if(checkEmpty()) return ret;

        for (RestaurantDTO now : this.storeDTOList) {
            if (now.getName().contains(name)) {
                temp.add(now);
            }
        }

        NameCompare nameCompare = new NameCompare();
        temp.sort(nameCompare);

        for(RestaurantDTO now : temp) {
            ret.add(now.toString(regex));
        }

        return ret;
    }

    public Long delete(Long index) {
        Long ret = -1L;
        if(checkEmpty()) return ret;

        for(int i = 0; i < this.storeDTOList.size(); i++) {
            RestaurantDTO now = storeDTOList.get(i);
            if(now.getIndex().equals(index)) {
                ret = now.getIndex();
                storeDTOList.remove(now);
                break;
            }
        }
        return ret;
    }

    public String update(Long index, String name, String menu, int price, int rate) {
        String ret = "";
        if(checkEmpty()) return ret;

        RestaurantDTO dto = null;
        for(int i = 0; i < this.storeDTOList.size(); i++) {
            RestaurantDTO now = storeDTOList.get(i);
            if(now.getIndex().equals(index)) {
                dto = now;
                storeDTOList.remove(now);
                break;
            }
        }
        if(dto == null) {
            return ret;
        }

        RestaurantDTO changed = new RestaurantDTO(
            dto.getIndex(),
            name, menu,
            price, rate,
            dto.getCreatedAt(), LocalDate.now());

        if(storeDTOList.add(changed)){
            ret = changed.toString(regex);
        }

        return ret;
    }

    private boolean checkEmpty(){
        return storeDTOList.size() == 0;
    }

    private void sortByIndex() {
        IndexCompare indexCompare = new IndexCompare();
        storeDTOList.sort(indexCompare);
    }

    private void sortByRate() {
        RateCompare rateCompare = new RateCompare();
        storeDTOList.sort(rateCompare);
    }
}
