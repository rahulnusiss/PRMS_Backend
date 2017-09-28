/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    ProgramDAO rpdao;
    ScheduleDAO sdao;

    Logger logger = LoggerFactory.getLogger(ScheduleService.class);

    public ScheduleService() {
        super();
        factory = new DAOFactoryImpl();
        sdao = factory.getScheduleDAO();
    }

    public ArrayList<ProgramSlot> findProgramSlotByCriteria(ProgramSlot ps) {
        ArrayList<ProgramSlot> currentList = new ArrayList<>();

        try {
            currentList = (ArrayList<ProgramSlot>) sdao.searchMatching(ps);
        } catch (SQLException e) {
            logger.error("SQL EXCEPTION: " + e.getMessage());
        }

        return currentList;

    }

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

    public ArrayList<ProgramSlot> findAllProgramSlots() {
        ArrayList<ProgramSlot> currentList = new ArrayList<>();
        try {
            currentList = (ArrayList<ProgramSlot>) sdao.loadAll();
        } catch (SQLException e) {
            logger.error("SQL EXCEPTION: " + e.getMessage());
        }
        return currentList;

    }

    public void processCreate(ProgramSlot ps) {
        try {
            sdao.create(ps);
        } catch (SQLException e) {
            logger.error("SQL EXCEPTION: " + e.getMessage());
        } catch (NotFoundException e) {
            logger.error("NotFound EXCEPTION: " + e.getMessage());
        }
    }

    public void processModify(ProgramSlot ps) {

        try {
            sdao.update(ps);
        } catch (NotFoundException e) {
            logger.error("Not Found EXCEPTION: " + e.getMessage());

        } catch (SQLException e) {
            logger.error("SQL EXCEPTION: " + e.getMessage());
        }

    }

    public void processDelete(String dateofProgram) {

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date date = dateFormatter.parse(dateofProgram);
            java.sql.Date sqlDateOfProgram = new java.sql.Date(date.getTime());

            ProgramSlot ps = findProgramSlotByDate(sqlDateOfProgram);
            if (ps != null) {
                sdao.delete(ps);
            } else {
                throw new NotFoundException("Program Slot object not found for given date");
            }
        } catch (NotFoundException | SQLException e) {
            logger.error(" EXCEPTION: " + e.getMessage());
        } catch (ParseException ex) {
            logger.error("Parse EXCEPTION: " + ex.getMessage());
        }
    }

}
