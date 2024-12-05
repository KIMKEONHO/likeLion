package day04;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tv {
    private boolean power_button = false;
    private int channel = 0;
    private int volume = 0;
    public boolean tvOn(){
        return true;
    }

    public boolean tvOff(){
        return false;
    }

    public int upChannel(){
        return channel+1;
    }

    public int downChannel(){
        return channel-1;
    }

    public int upVolume(){
        return volume+1;
    }

    public int downVolume(){
        return volume-1;
    }
}
