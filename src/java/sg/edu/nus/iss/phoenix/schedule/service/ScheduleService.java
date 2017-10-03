/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.radioprogram.dao.ProgramDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

/**
 *
 * @author Prasanna
 */
public class ScheduleService {

    DAOFactoryImpl factory;
    ScheduleDAO sdao;

    Logger logger = LoggerFactory.getLogger(ScheduleService.class);

    /**
     *default constructor to retrieve scheduleDAO object
     */
    public ScheduleService() {
        super();
        factory = new DAOFactoryImpl();
        sdao = factory.getScheduleDAO();
    }

    /**
     *
     * @param ps
     * @return
     */
    public ArrayList<ProgramSlot> findProgramSlotByCriteria(ProgramSlot ps) {
        ArrayList<ProgramSlot> currentList = new ArrayList<>();

        try {
            currentList = (ArrayList<ProgramSlot>) sdao.searchMatching(ps);
        } catch (SQLException e) {
            logger.error("SQL EXCEPTION: " + e.getMessage());
        }

        return currentList;

    }

    /**
     *
     * @param date
     * @return ProgramSlot based on date
     */
    public ProgramSlot findProgramSlotByDate(Date date) {
        ProgramSlot currentps = new ProgramSlot();
        currentps.setDateofProgram(date);
        try {
            currentps = ((ArrayList<ProgramSlot>) sdao
                    .searchMatching(currentps)).get(0);
            return currentps;
        } catch (SQLException e) {
            logger.error("SQL EXCEPTION: " + e.getMessage());
        }
        return currentps;

    }

    /**
     *
     * @param id
     * @return ProgramSlot based on Id
     */
    public ProgramSlot findProgramSlotById(int id) {
        ProgramSlot currentps = new ProgramSlot();
        currentps.setId(id);
        try {
            currentps = ((ArrayList<ProgramSlot>) sdao
                    .searchMatching(currentps)).get(0);
            return currentps;
        } catch (SQLException e) {
            logger.error("SQL EXCEPTION: " + e.getMessage());
        }
        return currentps;

    }

    /**
     *
     * @return  list of all ProgramSlots
     */
    public ArrayList<ProgramSlot> findAllProgramSlots() {
        ArrayList<ProgramSlot> currentList = new ArrayList<>();
        try {
            currentList = (ArrayList<ProgramSlot>) sdao.loadAll();
        } catch (SQLException e) {
            logger.error("SQL EXCEPTION: " + e.getMessage());
        }
        return currentList;

    }

    /**
     *
     * @param ps ProgramSlot to create
     * @return the status and message
     */
    public String processCreate(ProgramSlot ps) {
        String status="";
        try {
            if(sdao.create(ps)){
                     status= "{\"status\":\"true\",\"message\":\"ProgramSlot details Inserted Successfully...\"}";   
            }
        } catch (SQLException e) {
            logger.error("SQL EXCEPTION: " + e.getMessage());
            status = "{\"status\":\"false\",\"message\":\" SQLException while creating ProgramSlot...\"}";
        } catch (NotFoundException e) {
            logger.error("NotFound EXCEPTION: " + e.getMessage());
            status = "{\"status\":\"false\",\"message\":\" ProgramSlot with given details not found...\"}";
        }
        return status;
    }

    /**
     *
     * @param ps ProgramSlot to modify
     * @return the status
     */
    public String processModify(ProgramSlot ps) {
            String status="";
        try {
            if(sdao.update(ps)){
                     status= "{\"status\":\"true\",\"message\":\"ProgramSlot Modified Successfully...\"}";   
            }
            
        } catch (NotFoundException e) {
            logger.error("Not Found EXCEPTION: " + e.getMessage());
            status = "{\"status\":\"false\",\"message\":\" ProgramSlot with given details not found...\"}";

        } catch (SQLException e) {
            logger.error("SQL EXCEPTION: " + e.getMessage());
            status = "{\"status\":\"false\",\"message\":\" SQL Exception Occured while Modifying ProgramSlot...\"}";
        }
        return status;
    }

    /**
     *
     * @param id
     * @return the deletion status
     */
    public String processDelete(int id) {
        String status="";
        
        try {

            ProgramSlot ps = findProgramSlotById(id);
            if (ps != null) {
    
                if ( sdao.delete(ps)) {
                    status = "{\"status\":\"true\",\"message\":\"ProgramSlot deleted Successfully...\"}";
                } 
            } else {
                throw new NotFoundException("Program Slot object not found for given id");
                
            }
        } catch (NotFoundException e) {
            logger.error(" NotFoundException: " + e.getMessage());
            status = "{\"status\":\"false\",\"message\":\" ProgramSlot with given Id not found... \"}";
        }catch (SQLException e) {
            logger.error(" SQLException: " + e.getMessage());
            status = "{\"status\":\"false\",\"message\":\" SQL Exception Occured while deleting ProgramSlot... \"}";
        }
        
        return status;
    }

}
