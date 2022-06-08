package ratingSystem.Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import ratingSystem.DTO.RestaurantDTO;

public class FileRepository {

    static ArrayList<RestaurantDTO> storeDTOList;
    File file;
    static final char regex = '/';

    public FileRepository(String filename) throws IOException {
        this.file = new File(filename);
        storeDTOList = loadData();
    }

    public void close() throws IOException {
        saveData();
    }

    private ArrayList<RestaurantDTO> loadData() throws IOException {
        storeDTOList = new ArrayList<RestaurantDTO>();
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

            String[] strArr = str.split("/");
            RestaurantDTO now = new RestaurantDTO(strArr);
            storeDTOList.add(now);
        }

        br.close();
        return storeDTOList;
    }

    private void saveData() throws IOException {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));

        for (int i = 0; i < storeDTOList.size(); i++) {
            RestaurantDTO now = storeDTOList.get(i);
            pw.println(now.toString(regex));
        }
        pw.close();
    }


    public String save(RestaurantDTO dto) {
        Long max = 0L;
		if (dto.getIndex() != 0L) {
			return null;
		}
        for (int i = 0; i < storeDTOList.size(); i++) {
            Long temp = storeDTOList.get(i).getIndex();
			if (max < temp) {
				max = temp;
			}
        }
        dto.setIndex(max + 1);
        if (storeDTOList.add(dto)) {
            return dto.toString(regex);
        }

        return "";
    }

}
