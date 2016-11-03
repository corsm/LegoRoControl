package ch.bbw.legorocontrol;

import java.util.Map;

/**
 * Created by admin on 03.11.2016.
 */
public class LegoRobot {

    Connection connection;

    public LegoRobot(Connection connection){
        this.connection = connection;
    }


    public void drive(Map<String, Float> direction){
        for (Map.Entry<String, Float> command: direction.entrySet()) {
            try {
                connection.sendRequest(command.getKey()+"="+command.getValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
