/**
 * Template for a Time class
 * @author ICS3UE
 * @version Nov 2021
 */
class Time{
    int hours;
    int minutes;
    int seconds;
//------------------------------------------------------------------------------    
    Time(){
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
    }    
//------------------------------------------------------------------------------    
    Time(int hh, int mm, int ss){
        this.hours = hh;
        this.minutes = mm;
        this.seconds = ss;
    }
//------------------------------------------------------------------------------   
    public String getTimeInfo(){
        return this.hours +":"+ this.minutes +":"+this.seconds;
    }    
//------------------------------------------------------------------------------   
}
