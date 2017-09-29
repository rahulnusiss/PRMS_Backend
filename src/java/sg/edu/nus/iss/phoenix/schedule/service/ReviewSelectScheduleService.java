/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author Prasanna
 */
public class ReviewSelectScheduleService {

            DAOFactoryImpl factory;
            ScheduleDAO sdao;

            public ReviewSelectScheduleService() {
                    super();
                    factory = new DAOFactoryImpl();
                    sdao = factory.getScheduleDAO();
            }

            public List<ProgramSlot> reviewSelectScheduledProgram() {
                List<ProgramSlot> data = null;
                try {
                    data = sdao.loadAll();
                } catch (SQLException ex) {
                    Logger.getLogger(ReviewSelectScheduleService.class.getName()).log(Level.SEVERE, null, ex);
                }
                return data; 
            }

    
}
