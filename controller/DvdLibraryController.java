/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryDaoException;
import com.sg.dvdlibrary.dto.Dvd;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author urvax
 */
public class DvdLibraryController {
    private DvdLibraryView view;
    private UserIO io = new UserIOConsoleImpl();
    private DvdLibraryDao dao;
    
    public DvdLibraryController(DvdLibraryDao dao, DvdLibraryView view) {
    this.dao = dao;
    this.view = view;
}

    public void run() {
    boolean keepGoing = true;
    int menuSelection = 0;
    try {
        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    listDvds();
                    break;
                case 2:
                    createDvd();
                    break;
                case 3:
                    viewDvd();
                    break;
                case 4:
                    removeDvd();
                    break;
                case 5:
                    editDvd();
                    break;
                case 6:
                    keepGoing = false;
                    break;    
                default:
                    unknownCommand();
            }

        }
        exitMessage();
    } catch (DvdLibraryDaoException e) {
        view.displayErrorMessage(e.getMessage());
    }
}
    
    private int getMenuSelection() {
    return view.printMenuAndGetSelection();
}

private void createDvd() throws DvdLibraryDaoException {
    view.displayCreateDvdBanner();
    Dvd newDvd = view.getNewDvdInfo();
    dao.addDvd(newDvd.getTitle(), newDvd);
    view.displayCreateSuccessBanner();
}

private void listDvds() throws DvdLibraryDaoException {
    view.displayDisplayAllBanner();
    List<Dvd> dvdList = dao.getAllDvds();
    view.displayDvdList(dvdList);
}

private void viewDvd() throws DvdLibraryDaoException {
    view.displayDisplayDvdBanner();
    String title = view.getDvdTitleChoice();
    Dvd viewdvd = dao.getDvd(title);
    view.displayDvd(viewdvd);
}

private void removeDvd() throws DvdLibraryDaoException {
    view.displayRemoveDvdBanner();
    String title = view.getDvdTitleChoice();
    Dvd removedDvd = dao.removeDvd(title);
    view.displayRemoveResult(removedDvd);
}

private void editDvd() throws DvdLibraryDaoException {
    String title = view.getDvdTitleChoice();
    Dvd dvdToEdit = dao.getDvd(title);
        if (dvdToEdit != null){
        view.displayEditDvdBanner();
        view.editDvd(dvdToEdit);
        dao.editDvd(dvdToEdit);
        view.displayEditedDvdBanner();
    }
        else
        {
         view.displayEditedDvdNotPresentBanner();
        }
  
    
   }

private void unknownCommand() {
    view.displayUnknownCommandBanner();
}

private void exitMessage() {
    view.displayExitBanner();
}
}
