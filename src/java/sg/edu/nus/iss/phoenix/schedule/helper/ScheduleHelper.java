/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.helper;

import java.util.HashMap;
import java.util.List;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author Prasanna
 */
public class ScheduleHelper {
    
    /**
     * Check conflict based on start time and duration compute endTime and check whether entered dateofProgram and startTime result in conflict
     * @param psList
     * @param ps
     * @return
     * @throws NotFoundException
     */
    public boolean checkConflict(List<ProgramSlot> psList, ProgramSlot ps) throws NotFoundException{
    
        if(ps != null && !psList.isEmpty()){
            
                HashMap<Long,Long> map = getStartEndTimeMap(psList);
                
                long startTime = ps.getStartTime().getTime();
                
                long endTime = Math.abs(startTime + ps.getDuration().getTime());
                
                if(!map.isEmpty()){
                
                for(long key : map.keySet()){
                    
                    if(  (startTime>= key && startTime<=map.get(key))  &&  (endTime>=key && endTime <= map.get(key)) )
                        return true;// startTime is between the current start time and end time
                
                }//for
        }
        
        }//if
    
    return false;
    }//checkConflict
    
    /**
     *Return HashMap which contains startTime and computed endTime
     * @param psList
     * @return
     * @throws NotFoundException
     */
    public HashMap<Long,Long> getStartEndTimeMap(List<ProgramSlot> psList) throws NotFoundException{
    
        HashMap<Long,Long> timeMap = new HashMap<>();
        
          for(ProgramSlot ps : psList){
              if(ps!=null){      
              Long startTime=ps.getStartTime().getTime();
              Long duration = ps.getDuration().getTime();
              System.out.print("StartTime: "+startTime + " and  duration: "+duration);
              Long endTime =  Math.abs(ps.getDuration().getTime() + ps.getStartTime().getTime());
              timeMap.put(startTime, endTime);
          }//for
        
          }
       return timeMap;  
    }//getStartEndTimeMap
    
}
