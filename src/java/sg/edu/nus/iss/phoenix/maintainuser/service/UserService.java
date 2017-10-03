package sg.edu.nus.iss.phoenix.maintainuser.service;

import sg.edu.nus.iss.phoenix.maintainuser.service.*;
import java.sql.SQLException;
import java.util.ArrayList;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.authenticate.dao.*;
import sg.edu.nus.iss.phoenix.authenticate.entity.*;

public class UserService {

    DAOFactoryImpl factory;
    UserDao usrdao;

    public UserService() {
        super();
        // Sorry. This implementation is wrong. To be fixed.
        factory = new DAOFactoryImpl();
        usrdao = factory.getUserDAO();
    }

    /**
     * To search User
     * @param usrso
     * @return
     */
    public ArrayList<User> searchUsers(User usrso) {
        ArrayList<User> list = new ArrayList<User>();
        try {
            list = (ArrayList<User>) usrdao.searchMatching(usrso);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    /**
     *Retrieve User based on criteria
     * @param usr
     * @return
     */
    public ArrayList<User> findUSRByCriteria(User usr) {
        ArrayList<User> currentList = new ArrayList<User>();

        try {
            currentList = (ArrayList<User>) usrdao.searchMatching(usr);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return currentList;

    }

    /**
     *Retrieve User based on name
     * @param usrName
     * @return the user object
     */
    public User findUSR(String usrName) {
        User currentusr = new User();
        currentusr.setName(usrName);
        try {
            currentusr = ((ArrayList<User>) usrdao
                    .searchMatching(currentusr)).get(0);
            return currentusr;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return currentusr;

    }

    /**
     *Retrieve all users with associated roles
     * @return list of Users
     */
    public ArrayList<User> findAllUSR() {
        ArrayList<User> currentList = new ArrayList<User>();
        try {
            currentList = (ArrayList<User>) usrdao.loadAll();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return currentList;

    }

    /**
     *create User  
     * @param usr
     */
    public void processCreate(User usr) {
        try {
            usrdao.create(usr);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     *To Modify User details
     * @param usr
     */
    public void processModify(User usr) {

        try {
            usrdao.save(usr);
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * To delete the User
     * @param id
     * @return the status of user deletion
     */
    public String processDelete(String id) {

        String status="";
        
        try {
            User usr = new User(id);
            status= usrdao.delete(usr);
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return status;
    }
}
