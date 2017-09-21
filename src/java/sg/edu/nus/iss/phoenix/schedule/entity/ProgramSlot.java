/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.entity;
import java.sql.Time;
import java.sql.Timestamp;

/**
 *
 * @author Prasanna
 */
public class ProgramSlot {
    
    private Time duration;
    private Timestamp dateofProgram;
    private Timestamp startTime;
    private String programName;

    /**
     *default constructor for programSlot
     */
    public ProgramSlot() {
        
    }

    
    /**
     *Parameterized Constructor for programSlot
     * @param duration
     * @param dateofProgram
     * @param startTime
     * @param programName
     */
    public ProgramSlot(Time duration, Timestamp dateofProgram, Timestamp startTime, String programName) {
        this.duration = duration;
        this.dateofProgram = dateofProgram;
        this.startTime = startTime;
        this.programName = programName;
    }

    public void setDateofProgram(Timestamp dateofProgram) {
        this.dateofProgram = dateofProgram;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getDateofProgram() {
        return dateofProgram;
    }

    public Time getDuration() {
        return duration;
    }

    public String getProgramName() {
        return programName;
    }

    public Timestamp getStartTime() {
        return startTime;
    }
    
    
     /** 
     * hasEqualMapping-method will compare two ProgramSlot instances
     * and return true if they contain same values in all persistent instance 
     * variables. If hasEqualMapping returns true, it does not mean the objects
     * are the same instance. However it does mean that in that moment, they 
     * are mapped to the same row in database.
     * @param valueObject
     * @return 
     */
    public boolean hasEqualMapping(ProgramSlot valueObject) {

          if (this.dateofProgram == null) {
                    if (valueObject.getDateofProgram() != null)
                           return(false);
          } else if (!this.dateofProgram.equals(valueObject.getDateofProgram())) {
                    return(false);
          }
          if (this.duration == null) {
                    if (valueObject.getDuration() != null)
                           return(false);
          } else if (!this.duration.equals(valueObject.getDuration())) {
                    return(false);
          }
          if (this.programName == null) {
                    if (valueObject.getProgramName() != null)
                           return(false);
          } else if (!this.programName.equals(valueObject.getProgramName())) {
                    return(false);
          }
          
          if (this.startTime == null) {
                    if (valueObject.getStartTime() != null)
                           return(false);
          } else if (!this.startTime.equals(valueObject.getStartTime())) {
                    return(false);
          }

          return true;
    }

    /**
     * toString will return String object representing the state of this 
     * valueObject. This is useful during application development, and 
     * possibly when application is writing object states in text log.
     */
        @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("\nProgramSlot class, mapping to table program-slot\n");
        out.append("Persistent attributes: \n"); 
        out.append("date = ").append(this.dateofProgram).append("\n");
        out.append("duration = ").append(this.duration).append("\n");
        out.append("program Name = ").append(this.programName).append("\n"); 
        out.append("startTime = ").append(this.startTime).append("\n"); 
        return out.toString();
    }


    /**
     * Clone will return identical deep copy of this valueObject.
     * Note, that this method is different than the clone() which
     * is defined in java.lang.Object. Here, the returned cloned object
     * will also have all its attributes cloned.
     * @return 
     * @throws java.lang.CloneNotSupportedException 
     */
        @Override
    public Object clone() throws CloneNotSupportedException {
        ProgramSlot cloned = new ProgramSlot();

        if (this.dateofProgram != null)
             cloned.setDateofProgram((Timestamp)this.dateofProgram.clone());
        if (this.startTime != null)
             cloned.setStartTime((Timestamp)this.startTime.clone()); 
        if (this.programName != null)
             cloned.setProgramName(this.programName); 
        if (this.duration != null)
             cloned.setDuration((Time)this.duration.clone()); 
        return cloned;
    }

}
