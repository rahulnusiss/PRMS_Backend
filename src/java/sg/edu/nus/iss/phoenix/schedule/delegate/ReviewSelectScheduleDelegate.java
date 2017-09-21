/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.delegate;

import java.util.List;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.service.ReviewSelectScheduleService;

/**
 *
 * @author Prasanna
 */
public class ReviewSelectScheduleDelegate {

        private ReviewSelectScheduleService service;

            public ReviewSelectScheduleDelegate() {
                    service = new ReviewSelectScheduleService();
            }

            public List<ProgramSlot> reviewSelectRadioProgram() {
                    return service.reviewSelectScheduledProgram();
            }

    
}
