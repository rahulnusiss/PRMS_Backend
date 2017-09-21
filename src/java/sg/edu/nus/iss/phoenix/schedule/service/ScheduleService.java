/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.radioprogram.dao.ProgramDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author Prasanna
 */
public class ScheduleService {
    
                DAOFactoryImpl factory;
                ProgramDAO rpdao;
                ScheduleDAO sdao;

            public ScheduleService() {
                    super();
                    factory = new DAOFactoryImpl();
                    sdao = factory.getScheduleDAO();
            }
            public ArrayList<ProgramSlot> searchPrograms(ProgramSlot ps) {
                    ArrayList<ProgramSlot> list = new ArrayList<>();
                    try {
                            list = (ArrayList<ProgramSlot>) sdao.searchMatching(ps);
                    } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                    }
                    return list;
            }

            public ArrayList<ProgramSlot> findRPByCriteria(ProgramSlot ps) {
                    ArrayList<ProgramSlot> currentList = new ArrayList<ProgramSlot>();

                    try {
                            currentList = (ArrayList<ProgramSlot>) sdao.searchMatching(ps);
                    } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                    }

                    return currentList;

            }

            public ProgramSlot findProgramSlot(String rpName) {
                    ProgramSlot currentps = new ProgramSlot();
                    currentps.setProgramName(rpName);
                    try {
                            currentps = ((ArrayList<ProgramSlot>) sdao
                                            .searchMatching(currentps)).get(0);
                            return currentps;
                    } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                    }
                    return currentps;

            }
            
            public ProgramSlot findProgramSlotByDate(Timestamp date) {
                    ProgramSlot currentps = new ProgramSlot();
                    currentps.setDateofProgram(date);
                    try {
                            currentps = ((ArrayList<ProgramSlot>) sdao
                                            .searchMatching(currentps)).get(0);
                            return currentps;
                    } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                    }
                    return currentps;

            }

            public ArrayList<ProgramSlot> findAllProgramSlots() {
                    ArrayList<ProgramSlot> currentList = new ArrayList<ProgramSlot>();
                    try {
                            currentList = (ArrayList<ProgramSlot>) sdao.loadAll();
                    } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                    }
                    return currentList;

            }

            public void processCreate(ProgramSlot ps) {
                    try {
                            sdao.create(ps);
                    } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                    }
            }

            public void processModify(ProgramSlot ps) {

                            try {
                                    sdao.save(ps);
                            } catch (NotFoundException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                            } catch (SQLException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                            }

            }
            
            public void processCopy(ProgramSlot ps) {

                            try {
                                    sdao.copy(ps);
                            } catch (NotFoundException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                            } catch (SQLException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                            }

            }

              public void processDelete(String name) {

                try {
                    ProgramSlot ps = findProgramSlot(name);
                    if(ps!=null)
                    sdao.delete(ps);
                } catch (NotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

    
}
