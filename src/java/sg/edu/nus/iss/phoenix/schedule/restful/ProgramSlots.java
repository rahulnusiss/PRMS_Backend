/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.restful;

import java.util.List;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author Prasanna
 */
public class ProgramSlots {
    
    List<ProgramSlot> psList;

    /**
     *
     * @param psList
     */
    public void setPsList(List<ProgramSlot> psList) {
        this.psList = psList;
    }

    /**
     *
     * @return Program Slot List
     */
    public List<ProgramSlot> getPsList() {
        return psList;
    }
    
}
