
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author urvax
 */
public class DvdLibraryDaoFileImpl implements DvdLibraryDao {
    private Map<String, Dvd> dvds = new HashMap<>();

    public static final String DVD_FILE="library.txt";
    public static final String DELIMITER="::";
    
    private Dvd unmarshallDvd(String dvdAsText){
    
    String[] dvdTokens = dvdAsText.split(DELIMITER);

    
    String title = dvdTokens[0];

    
    Dvd dvdFromFile = new Dvd(title);

    
    dvdFromFile.setReleaseDate(dvdTokens[1]);

    
    dvdFromFile.setMpaaRating(dvdTokens[2]);

    
    dvdFromFile.setDirectorsName(dvdTokens[3]);
    
    
    dvdFromFile.setStudio(dvdTokens[4]);
    
    
    dvdFromFile.setAdditionalInfo(dvdTokens[5]);

    
    return dvdFromFile;
}
    
    private void loadDvd() throws DvdLibraryDaoException {
    Scanner scanner;

    try {
               
        scanner = new Scanner(
                new BufferedReader(
                        new FileReader(DVD_FILE)));
    } catch (FileNotFoundException e) {
        throw new DvdLibraryDaoException(
                "-_- Could not load library data into memory.", e);
    }
    
    String currentLine;
    
    Dvd currentDvd;
    
    while (scanner.hasNextLine()) {
        
        currentLine = scanner.nextLine();
        
        currentDvd = unmarshallDvd(currentLine);

        dvds.put(currentDvd.getTitle(), currentDvd);
    }
    
    scanner.close();
}
    
    private String marshallDvd(Dvd aDvd){
    
    String dvdAsText = aDvd.getTitle() + DELIMITER;

   
    dvdAsText += aDvd.getReleaseDate() + DELIMITER;

   
    dvdAsText += aDvd.getMpaaRating() + DELIMITER;
    
    dvdAsText += aDvd.getDirectorsName() + DELIMITER;
    
    dvdAsText += aDvd.getStudio() + DELIMITER;

    
    dvdAsText += aDvd.getAdditionalInfo();

    
    return dvdAsText;
}
    
    private void writeDvd() throws DvdLibraryDaoException {
    
    PrintWriter out;

    try {
        out = new PrintWriter(new FileWriter(DVD_FILE));
    } catch (IOException e) {
        throw new DvdLibraryDaoException(
                "Could not save DVD data.", e);
    }

   
    String dvdAsText;
    List <Dvd> dvdList = this.getAllDvds();
    for (Dvd currentDvd : dvdList) {
        
        dvdAsText = marshallDvd(currentDvd);
        
        out.println(dvdAsText);
        
        out.flush();
    }
 
    out.close();
}
    
    @Override
    public Dvd addDvd(String title, Dvd dvd) 
    throws DvdLibraryDaoException {
    loadDvd();
    Dvd newDvd = dvds.put(title, dvd);
    writeDvd();
    return newDvd;
    }

    @Override
    public List<Dvd> getAllDvds() 
    throws DvdLibraryDaoException {
    loadDvd();
    return new ArrayList(dvds.values());
}

    @Override
    public Dvd getDvd(String title) 
    throws DvdLibraryDaoException {
    loadDvd();
    return dvds.get(title);
}

    @Override
    public Dvd removeDvd(String title) 
    throws DvdLibraryDaoException {
    loadDvd();
    Dvd removedDvd = dvds.remove(title);
    writeDvd();
    return removedDvd;
}

    @Override
    public Dvd editDvd(String title, Dvd dvd) 
    throws DvdLibraryDaoException {
    loadDvd();
    Dvd editDvd = dvds.put(title, dvd);
    writeDvd();
    return editDvd;
    }
    
}
