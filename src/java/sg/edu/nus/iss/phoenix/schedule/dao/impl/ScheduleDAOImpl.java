/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sg.edu.nus.iss.phoenix.core.dao.DBConstants;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.core.exceptions.ProgramSlotOverlapException;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.helper.ScheduleHelper;

/**
 *
 * @author Prasanna
 */
public class ScheduleDAOImpl implements ScheduleDAO {

    Connection connection;
    Logger logger = LoggerFactory.getLogger(ScheduleDAOImpl.class);


    @Override
    public ProgramSlot createValueObject() {
        return new ProgramSlot();
    }

    @Override
    public ProgramSlot getObject(Date dateOfProgram) throws NotFoundException, SQLException {
        ProgramSlot valueObject = createValueObject();
        valueObject.setDateofProgram(dateOfProgram);
        load(valueObject);
        return valueObject;
    }

    @Override
    public void load(ProgramSlot valueObject) throws NotFoundException, SQLException {

        if (valueObject.getDateofProgram() == null) {
            throw new NotFoundException("Cannot Select without Primary-key! ");
        }

        String sql = "SELECT * FROM `program-slot` WHERE (`id` = ? ); ";
        PreparedStatement stmt = null;
        openConnection();

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, valueObject.getId());
            singleQuery(stmt, valueObject);

        } finally {
            if (stmt != null) {
                stmt.close();
            }
            closeConnection();
        }

    }

    @Override
    public List<ProgramSlot> loadAll() throws SQLException {
        openConnection();
        String sql = "SELECT *  FROM `program-slot` ORDER by `dateOfProgram` ASC; ";
        List<ProgramSlot> searchResults = listQuery(connection.prepareStatement(sql));
        closeConnection();
        System.out.println("record size " + searchResults.size());
        return searchResults;
    }

    @Override
    public boolean create(ProgramSlot valueObject) throws SQLException, NotFoundException {
        String sql = "";
        PreparedStatement stmt = null;
        int rowcount;
        openConnection();
        boolean status=false;
        try {
            sql = "INSERT into `program-slot` (`duration`,\n" + "`dateOfProgram`,\n" + "`startTime`,\n" + "`program-name`,\n" + "`presenter-id`,\n" + "`producer-id`) VALUES (?,?,?,?,?,?);";
            stmt = connection.prepareStatement(sql);
            stmt.setTime(1, valueObject.getDuration());
            stmt.setDate(2, valueObject.getDateofProgram());
            stmt.setTime(3, valueObject.getStartTime());
            stmt.setString(4, valueObject.getProgramName());
            stmt.setString(5, valueObject.getPresenterId());
            stmt.setString(6,valueObject.getProducerId());

            if (!checkProgramSlotAssigned(valueObject)) {
                rowcount = databaseUpdate(stmt);
            } else {
              throw new ProgramSlotOverlapException("Entered Program Slot Overlaps with existing Program Slots(In Create): Try to change startTime or dateofProgram");
            }

            if (rowcount != 1) {
                throw new SQLException("PrimaryKey Error when updating DB");
            }else{
                   status=true;
            }
        }catch(ProgramSlotOverlapException ex){
            logger.error(ex.getMessage());
            status=false;
                
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            closeConnection();
        }
        return status;
    }

    @Override
    public boolean update(ProgramSlot valueObject) throws NotFoundException, SQLException {
        System.out.println("Request Object---> "+valueObject.toString());
        String sql = "UPDATE  `program-slot` SET  `dateOfProgram`= ?,  `startTime` = ?, `duration` = ?,`program-name`= ? ,`presenter-id`=?,`producer-id`=?  WHERE (`id`=?);";
        PreparedStatement stmt = null;
        int rowcount = 0;
        boolean status;
        openConnection();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setDate(1, valueObject.getDateofProgram());
            stmt.setTime(2, valueObject.getStartTime());
            stmt.setTime(3, valueObject.getDuration());
            stmt.setString(4, valueObject.getProgramName());
            stmt.setString(5, valueObject.getPresenterId());
            stmt.setString(6, valueObject.getProducerId());
            stmt.setInt(7, valueObject.getId());
           if (!checkProgramSlotAssigned(valueObject)) {
                rowcount = databaseUpdate(stmt);
           }else{
                throw new ProgramSlotOverlapException("Entered Program Slot Overlaps with existing Program Slots: Try to change startTime or dateofProgram");
           }
            if (rowcount == 0) {
                throw new NotFoundException("Object could not be saved! (Primarykey not found)");

            }else if (rowcount > 1) {
                throw new SQLException("PrimaryKey Error when updatingDB !(Many objects were affected)");

            }else{
                    status = true;
            }
            
        }catch(ProgramSlotOverlapException ex){
                        logger.error(ex.getMessage());
                        status =false;
                
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            closeConnection();

        }
        return status;
    }

    @Override
    public boolean delete(ProgramSlot valueObject) throws NotFoundException, SQLException {

        if (valueObject.getId() == null) {
            throw new NotFoundException("Can not delete without Primary-key! ....");
        }

        String sql = "DELETE FROM `program-slot` where (`id`=?);";
        PreparedStatement stmt = null;
        int rowcount = 0;
        boolean status;
        openConnection();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, valueObject.getId());

            rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                throw new NotFoundException("Object could not be deleted! (PrimaryKey not found)");
            }else if (rowcount > 1) {
                throw new SQLException("Primary Key Error when updating DB! (Many objects were deleted)");
            } else{
                status = true;
            }

        } finally {
            if (stmt != null) {
                stmt.close();
            }
            closeConnection();

        }
            return status;
    }

    @Override
    public List<ProgramSlot> searchMatching(ProgramSlot valueObject) throws SQLException {

        List<ProgramSlot> searchResults = new ArrayList<>();
        openConnection();
        boolean first = true;
        StringBuilder sql = new StringBuilder("SELECT * FROM `program-slot` WHERE 1=1");

        if (valueObject.getDateofProgram() != null) {
            if (first) {
                first = false;
            }
            sql.append(" AND `dateOfProgram` LIKE '").append(valueObject.getDateofProgram()).append("%' ");
        }

        if (valueObject.getDuration() != null) {
            if (first) {
                first = false;
            }
            sql.append(" AND `duration` LIKE '").append(valueObject.getDuration()).append("%' ");
        }

        if (valueObject.getStartTime() != null) {
            if (first) {
                first = false;
            }
            sql.append(" AND `startTime` LIKE '").append(valueObject.getStartTime()).append("%' ");
        }

        if (valueObject.getProgramName() != null) {
            if (first) {
                first = false;
            }
            sql.append(" AND `program-name` LIKE '").append(valueObject.getProgramName()).append("%' ");
        }

        if (valueObject.getId() != null) {
            if (first) {
                first = false;
            }
            sql.append(" AND `id` = ").append(valueObject.getId()).append(" ");
        }
        
        if (valueObject.getPresenterId()!= null) {
            if (first) {
                first = false;
            }
            sql.append(" AND `presenter-id` = ").append(valueObject.getId()).append(" ");
        }
        
        if (valueObject.getProducerId()!= null) {
            if (first) {
                first = false;
            }
            sql.append(" AND `producer-id` = ").append(valueObject.getId()).append(" ");
        }

        sql.append("ORDER BY `program-name` ASC");
        if (first) {
            searchResults = new ArrayList<>();
        } else {
            searchResults = listQuery(connection.prepareStatement(sql.toString()));
        }
        closeConnection();
        return searchResults;
    }

    /**
     *Prepare Single Query based on ProgramSlot object
     * @param stmt
     * @param valueObject
     * @throws NotFoundException
     * @throws SQLException
     */
    protected void singleQuery(PreparedStatement stmt, ProgramSlot valueObject)
            throws NotFoundException, SQLException {

        ResultSet result = null;
        openConnection();
        try {
            result = stmt.executeQuery();

            if (result.next()) {
                valueObject.setProgramName(result.getString("program-name"));
                valueObject.setDateofProgram(result.getDate("dateOfProgram"));
                valueObject.setDuration(result.getTime("duration"));
                valueObject.setStartTime(result.getTime("startTime"));
                valueObject.setPresenterId(result.getString("presenter-id"));
                valueObject.setProducerId(result.getString("producer-id"));
                valueObject.setId(result.getInt("id"));

            } else {
                throw new NotFoundException("ProgramSlot Object Not Found!");
            }
        } finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            closeConnection();
        }
    }
    
    
    
      private List<ProgramSlot> listQuery(PreparedStatement stmt) throws SQLException {

        ArrayList<ProgramSlot> searchResults = new ArrayList<>();
        ResultSet result = null;
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

        openConnection();
        try {
            result = stmt.executeQuery();
            
            if (!result.next() ) {    
                System.out.println("zero records retrieved");
                 /*To handle when result set doesnot contains any record*/
                ProgramSlot empty = null;
                searchResults.add(empty);
     
            }else{ 
            
            do {
               
                ProgramSlot temp = createValueObject();
                temp.setDateofProgram(result.getDate("dateOfProgram"));
                temp.setDuration(result.getTime("duration"));
                String timeFormat = formatter.format(result.getTime("startTime"));
                temp.setStartTime(Time.valueOf(timeFormat));
                temp.setProgramName(result.getString("program-name"));
                temp.setPresenterId(result.getString("presenter-id"));
                temp.setProducerId(result.getString("producer-id"));
                temp.setId(result.getInt("id"));

                searchResults.add(temp);

            }while(result.next());
            }
              
        } finally {
            if (result != null) {
              result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            closeConnection();

        }
        return (List<ProgramSlot>) searchResults;
    }

    private boolean checkProgramSlotAssigned(ProgramSlot valueObject) throws SQLException, NotFoundException {
        ScheduleHelper helper = new ScheduleHelper();
        String sql = "SELECT * FROM `program-slot` where (`dateOfProgram` = ?);";
        PreparedStatement stmt = null;
        openConnection();
        stmt = connection.prepareStatement(sql);
        stmt.setDate(1, valueObject.getDateofProgram());

        List<ProgramSlot> psList = listQuery(stmt);

        if (psList.size() > 0) {

            return helper.checkConflict(psList, valueObject);
        }

        return false;
    }

    private void openConnection() {
        try {
            Class.forName(DBConstants.COM_MYSQL_JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            this.connection = DriverManager.getConnection(DBConstants.dbUrl,
                    DBConstants.dbUserName, DBConstants.dbPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *Return the status no. of rows affected while performing crud operation on database
     * @param stmt
     * @return
     * @throws SQLException
     */
    protected int databaseUpdate(PreparedStatement stmt) throws SQLException {

        int result = stmt.executeUpdate();

        return result;
    }
    
    /**
     * To check Selected User Has been Assigned in Program Slot Table
     * @param userId
     * @return true/false as Status
     * @throws SQLException
     * @throws NotFoundException
     */
    public boolean checkUserExistInSchedule(String userId) throws SQLException,NotFoundException{
    
        String sql = "SELECT * FROM `program-slot` where (`presenter-id` = ? OR `producer-id`=?);";
        PreparedStatement stmt = null;
        openConnection();
        stmt = connection.prepareStatement(sql);
        stmt.setString(1, userId);
        stmt.setString(2,userId);

        List<ProgramSlot> psList = listQuery(stmt);

        if (psList.size() >0 || !psList.isEmpty()) {

            for(ProgramSlot ps : psList){
            
                        if(ps!=null){
                                    return true;
                        }
            }
            
        }

        return false;
    
    }
    
    
    
    
    

}
