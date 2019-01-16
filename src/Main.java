import classes.ApiInputParser;
import classes.DataManager;
import classes.api_objects.*;

import java.util.Map;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        boolean notUpdate = true;

        DataManager.getInstance().initDataCollector(notUpdate);

        if (!notUpdate) {
            ApiInputParser parser = new ApiInputParser();
            parser.parseStations();
            DataManager.getInstance().collectStations(parser);
            DataManager.getInstance().collectCities(parser);
        }

        System.out.println();
        for (Map.Entry<Integer, Station> s : DataManager.getInstance().getStations().entrySet()) {
            System.out.println(s.getValue());
        }

        DataManager.getInstance().saveData();


//        City y = new City("y", "y", new Commune("z", "z", "z"));
//        LinkedList<Sensor> sensors = new LinkedList<>();
//        sensors.addLast(new Sensor("s1", "s1", new Param("p", "p", "p", "p"), new Data("d", new Values())));
//        LinkedList<ParamIndex> aq = new LinkedList<ParamIndex>();
//        aq.addLast(new ParamIndex("B", "B", new IndexLevel("C", "C"), "B"));
//        aq.addLast(new ParamIndex("B2", "B", new IndexLevel("C", "C"), "B"));
//
//        StationBuilder xb = new StationBuilder().setId("52").setStationName("x").setCity(y).addSensor(new Sensor("s1", "s1", new Param("p", "p", "p", "p"), new Data("d", new Values())))
//                ;//.setAQIndex(new AQIndex("A", aq));
//        ApiInputParser parser2 = new ApiInputParser();
//        parser2.parseAQIndexData(xb, "52");
//        Station x = xb.createStation();
//        System.out.println(x);



//        String dataURL = "http://api.gios.gov.pl/pjp-api/rest/aqindex/getIndex/{stationId}";
//
//        try {
//            URL url = new URL(dataURL.replace("{stationId}", "52"));
//            InputStream is = url.openStream();
//            JsonParser parser = Json.createParser(is);
//            while (parser.hasNext()) {
//                JsonParser.Event e = parser.next();
//                System.out.print(e);
//                System.out.print("     ");
//                if (e != JsonParser.Event.START_OBJECT && e != JsonParser.Event.START_ARRAY && e != JsonParser.Event.END_ARRAY
//                        && e != JsonParser.Event.END_OBJECT && e != JsonParser.Event.VALUE_NULL
//                        && e != JsonParser.Event.VALUE_TRUE && e != JsonParser.Event.VALUE_FALSE)
//                System.out.println(parser.getString());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
