/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.delegate;

import java.util.ArrayList;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.radioprogram.service.ProgramService;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.service.ScheduleService;

/**
 *
 * @author Prasanna
 */
public class ScheduleDelegate {
    
        public ArrayList<ProgramSlot> findAllProgramSlots() {
                    ScheduleService service = new ScheduleService();
                    return service.findAllProgramSlots();

            }

            public void processCreate(ProgramSlot ps) {
                    ScheduleService service = new ScheduleService();
                    service.processCreate(ps);

            }
            public void processModify(ProgramSlot ps) {
                    ScheduleService service = new ScheduleService();
                    service.processModify(ps);

            }

            public void processDelete(String name) {
                    ScheduleService service = new ScheduleService();
                    service.processDelete(name);
            }
    
}
