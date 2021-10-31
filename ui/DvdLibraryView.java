
package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.Dvd;
import java.util.List;

/**
 *
 * @author urvax
 */
public class DvdLibraryView {
     private UserIO io;
    public DvdLibraryView(UserIO io) {
    this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List All DVD's");
        io.print("2. Add a New DVD To The List");
        io.print("3. View a DVD In The List");
        io.print("4. Remove a DVD In The List");
        io.print("5. Edit The Information For an Existing DVD In The List");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }
    
    public Dvd getNewDvdInfo() {
    String title = io.readString("Please enter DVD title");
    String releaseDate = io.readString("Please enter DVD Release Date");
    String mpaaRating = io.readString("Please enter DVD MPAA Rating");
    String directorsName = io.readString("Please enter Directors Name for the DVD");
    String studio = io.readString("Please enter Studio Name for the DVD Production");
    String additionalInfo = io.readString("Please enter any Additional Information Like User Rating or Note for the DVD");
    Dvd currentDvd = new Dvd(title);
    currentDvd.setReleaseDate(releaseDate);
    currentDvd.setMpaaRating(mpaaRating);
    currentDvd.setDirectorsName(directorsName);
    currentDvd.setStudio(studio);
    currentDvd.setAdditionalInfo(additionalInfo);
    return currentDvd;
}
    
    public void displayCreateDvdBanner() {
    io.print("=== Create DVD ===");
}
    
    public void displayCreateSuccessBanner() {
    io.readString(
            "DVD successfully created.  Please hit enter to continue");
}
    
    public void displayDvdList(List<Dvd> dvdList) {
    for (Dvd currentDvd : dvdList) {
        String dvdInfo = String.format("%s (%s) \n%s  \n%s \n%s \n%s",
              currentDvd.getTitle(),
              currentDvd.getReleaseDate(),
              currentDvd.getMpaaRating(),
              currentDvd.getDirectorsName(),
              currentDvd.getStudio(),
              currentDvd.getAdditionalInfo());
        io.print(dvdInfo);
    }
    io.readString("Please hit enter to continue.");
}
    
    public void displayDisplayAllBanner() {
    io.print("=== Display All DVDs ===");
}
    
    public void displayDisplayDvdBanner () {
    io.print("=== Display DVD ===");
}

public String getDvdTitleChoice() {
    return io.readString("Please enter the DVD Title.");
}

public void displayDvd(Dvd dvd) {
    if (dvd != null) {
        io.print(dvd.getTitle());
        io.print(dvd.getReleaseDate());
        io.print(dvd.getMpaaRating());
        io.print(dvd.getDirectorsName());
        io.print(dvd.getStudio());
        io.print(dvd.getAdditionalInfo());
        io.print("");
    } else {
        io.print("No such DVD in the List.");
    }
    io.readString("Please hit enter to continue.");
}

public void displayRemoveDvdBanner () {
    io.print("=== Remove DVD ===");
}

public void displayRemoveResult(Dvd dvdRecord) {
    if(dvdRecord != null){
      io.print("DVD successfully removed.");
    }else{
      io.print("No such DVD in the List.");
    }
    io.readString("Please hit enter to continue.");
}

public void displayEditDvdBanner () {
    io.print("=== Edit DVD ===");
}

 public Dvd editDvd(Dvd dvdToEdit) {
     dvdToEdit.setDirectorsName(io.readString("Enter new directors name: "));
     dvdToEdit.setReleaseDate(io.readString("Enter new release date: "));
     dvdToEdit.setMpaaRating(io.readString("Enter new mpaa rating: "));
     dvdToEdit.setStudio(io.readString("Enter new studio name: "));
     dvdToEdit.setAdditionalInfo(io.readString("Enter any additional information: "));
     return dvdToEdit;
     
 }

 public void displayEditedDvdBanner() {
     io.print("Sucessfully Edited!");
     io.readString("\n Hit Enter To Continue");
 }

public void displayExitBanner() {
    io.print("Good Bye!!!");
}

public void displayUnknownCommandBanner() {
    io.print("Unknown Command!!!");
}
public void displayErrorMessage(String errorMsg) {
    io.print("=== ERROR ===");
    io.print(errorMsg);
}
}
