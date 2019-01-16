package classes;

import classes.api_objects.*;

import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

public class ApiInputParser {

    private final String stationsURL = "http://api.gios.gov.pl/pjp-api/rest/station/findAll";
    private final String sensorsURL = "http://api.gios.gov.pl/pjp-api/rest/station/sensors/{stationId}";
    private final String dataURL = "http://api.gios.gov.pl/pjp-api/rest/data/getData/{sensorId}";
    private final String aqindexURL = "http://api.gios.gov.pl/pjp-api/rest/aqindex/getIndex/{stationId}";

    private ConcurrentHashMap<Integer, Station> stations = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, City> cities = new ConcurrentHashMap<>();

    public ApiInputParser() {
    }

    public void parseStations() {
        try {
            URL url = new URL(stationsURL);
            InputStream is = url.openStream();
            JsonParser parser = Json.createParser(is);
            while (parser.hasNext()) {
                JsonParser.Event e = parser.next();
                if (e == JsonParser.Event.START_OBJECT) {
                    parseStation(parser);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ConcurrentHashMap<Integer, Station> getStations() {
        return stations;
    }

    public ConcurrentHashMap<Integer, City> getCities() {
        return cities;
    }

    public Station parseStation(JsonParser parser) {
        StationBuilder result = new StationBuilder();
        boolean endFound = false;
        while (parser.hasNext() && !endFound) {
            JsonParser.Event e = parser.next();
            if (e == JsonParser.Event.KEY_NAME) {
                switch (parser.getString()) {
                    case "id":
                        parser.next();
                        String stationId = parser.getString();
                        result.setId(stationId);
                        parseSensors(result, stationId);
                        parseAQIndexData(result, stationId);
                        break;
                    case "stationName":
                        parser.next();
                        result.setStationName(parser.getString());
                        break;
                    case "city":
                        parser.next();
                        result.setCity(parseCity(parser));
                        break;
                    default:
                        parser.next();
                        break;
                }
            } else if (e == JsonParser.Event.END_OBJECT) {
                endFound = true;
            }
        }
        Station tmp = result.createStation();
        stations.put(Integer.valueOf(tmp.id), tmp);
        return tmp;
    }

    public City parseCity(JsonParser parser) {
        CityBuilder result = new CityBuilder();
        boolean endFound = false;
        while (parser.hasNext() && !endFound) {
            JsonParser.Event e = parser.next();
            if (e == JsonParser.Event.KEY_NAME) {
                switch (parser.getString()) {
                    case "id":
                        parser.next();
                        result.setId(parser.getString());
                        break;
                    case "name":
                        parser.next();
                        result.setName(parser.getString());
                        break;
                    case "commune":
                        parser.next();
                        result.setCommune(parseCommune(parser));
                        break;
                }
            } else if (e == JsonParser.Event.END_OBJECT) {
                endFound = true;
            }
        }
        City tmp = result.createCity();
        cities.put(Integer.valueOf(tmp.id), tmp);
        return tmp;
    }

    public Commune parseCommune(JsonParser parser) {
        CommuneBuilder result = new CommuneBuilder();
        boolean endFound = false;
        while (parser.hasNext() && !endFound) {
            JsonParser.Event e = parser.next();
            if (e == JsonParser.Event.KEY_NAME) {
                switch (parser.getString()) {
                    case "communeName":
                        parser.next();
                        result.setCommuneName(parser.getString());
                        break;
                    case "districtName":
                        parser.next();
                        result.setDistrictName(parser.getString());
                        break;
                    case "provinceName":
                        parser.next();
                        result.setProvinceName(parser.getString());
                        break;
                }
            } else if (e == JsonParser.Event.END_OBJECT) {
                endFound = true;
            }
        }
        return result.createCommune();
    }

    public void parseSensors(StationBuilder result, String stationId) {
        try {
            URL url = new URL(sensorsURL.replace("{stationId}", stationId));
            InputStream is = url.openStream();
            JsonParser parser = Json.createParser(is);
            while (parser.hasNext()) {
                JsonParser.Event e = parser.next();
                if (e == JsonParser.Event.START_OBJECT) {
                    result.addSensor(parseSensor(parser));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Sensor parseSensor(JsonParser parser) {
        SensorBuilder result = new SensorBuilder();
        boolean endFound = false;
        while (parser.hasNext() && !endFound) {
            JsonParser.Event e = parser.next();
            if (e == JsonParser.Event.KEY_NAME) {
                switch (parser.getString()) {
                    case "id":
                        parser.next();
                        String sensorId = parser.getString();
                        result.setId(sensorId);
                        parseSensorData(result, sensorId);
                        break;
                    case "stationId":
                        parser.next();
                        result.setStationId(parser.getString());
                        break;
                    case "param":
                        parser.next();
                        result.setParam(parseParam(parser));
                        break;
                    default:
                        parser.next();
                        break;
                }
            } else if (e == JsonParser.Event.END_OBJECT) {
                endFound = true;
            }
        }
        return result.createSensor();
    }

    public Param parseParam(JsonParser parser) {
        ParamBuilder result = new ParamBuilder();
        boolean endFound = false;
        while (parser.hasNext() && !endFound) {
            JsonParser.Event e = parser.next();
            if (e == JsonParser.Event.KEY_NAME) {
                switch (parser.getString()) {
                    case "paramName":
                        parser.next();
                        result.setParamName(parser.getString());
                        break;
                    case "paramFormula":
                        parser.next();
                        result.setParamFormula(parser.getString());
                        break;
                    case "paramCode":
                        parser.next();
                        result.setParamCode(parser.getString());
                        break;
                    case "idParam":
                        parser.next();
                        result.setIdParam(parser.getString());
                        break;
                }
            } else if (e == JsonParser.Event.END_OBJECT) {
                endFound = true;
            }
        }
        return result.createParam();
    }

    public void parseSensorData(SensorBuilder result, String sensorId) {
        try {
            URL url = new URL(dataURL.replace("{sensorId}", sensorId));
            InputStream is = url.openStream();
            JsonParser parser = Json.createParser(is);
            while (parser.hasNext()) {
                JsonParser.Event e = parser.next();
                if (e == JsonParser.Event.START_OBJECT) {
                    result.setData(parseData(parser));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Data parseData(JsonParser parser) {
        DataBuilder result = new DataBuilder();
        boolean endFound = false;
        while (parser.hasNext() && !endFound) {
            JsonParser.Event e = parser.next();
            if (e == JsonParser.Event.KEY_NAME) {
                switch (parser.getString()) {
                    case "key":
                        parser.next();
                        result.setKey(parser.getString());
                        break;
                    case "values":
                        parser.next();
                        result.setValues(parseValues(parser));
                        break;
                }
            } else if (e == JsonParser.Event.END_OBJECT) {
                endFound = true;
            }
        }
        return result.createData();
    }

    public Values parseValues(JsonParser parser) {
        ValuesBuilder result = new ValuesBuilder();
        while (parser.hasNext()) {
            JsonParser.Event e = parser.next();
            if (e == JsonParser.Event.START_OBJECT) {
                Value tmp = parseValue(parser);
                if (tmp != null)
                    result.addValue(tmp);
            }
        }
        return result.createValues();
    }

    public Value parseValue(JsonParser parser) {
        ValueBuilder result = new ValueBuilder();
        boolean nullValue = false;
        boolean endFound = false;
        while (parser.hasNext() && !endFound) {
            JsonParser.Event e = parser.next();
            if (e == JsonParser.Event.KEY_NAME) {
                switch (parser.getString()) {
                    case "date":
                        parser.next();
                        result.setDate(parser.getString());
                        break;
                    case "value":
                        if (parser.next() != JsonParser.Event.VALUE_NULL)
                            result.setValue(parser.getString());
                        else nullValue = true;
                        break;
                }
            } else if (e == JsonParser.Event.END_OBJECT) {
                endFound = true;
            }
        }
        if (!nullValue)
            return result.createValue();
        return null;
    }

    public void parseAQIndexData(StationBuilder result, String stationId) {
        try {
            URL url = new URL(aqindexURL.replace("{stationId}", stationId));
            InputStream is = url.openStream();
            JsonParser parser = Json.createParser(is);
            while (parser.hasNext()) {
                JsonParser.Event e = parser.next();
                if (e == JsonParser.Event.START_OBJECT) {
                    result.setAQIndex(parseAQIndex(parser));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AQIndex parseAQIndex(JsonParser parser) {
        AQIndexBuilder result = new AQIndexBuilder();
        boolean endFound = false;
        while (parser.hasNext() && !endFound) {
            JsonParser.Event e = parser.next();
            if (e == JsonParser.Event.KEY_NAME) {
                if (parser.getString().equals("id")) {
                    parser.next();
                    result.setId(parser.getString());
                } else if (parser.getString().contains("CalcDate")) {
                    result.addParamIndex(parseParamIndex(parser));
                }
            }
        }
        return result.createAQIndex();
    }

    public ParamIndex parseParamIndex(JsonParser parser) {
        ParamIndexBuilder result = new ParamIndexBuilder();
        boolean endFound = false;
        String currPrefix = parser.getString().replace("CalcDate", "");
        result.setPrefix(currPrefix);
        if (parser.next() != JsonParser.Event.VALUE_NULL)
            result.setCalcDate(parser.getString());
        while (parser.hasNext() && !endFound) {
            JsonParser.Event e = parser.next();
            if (e == JsonParser.Event.KEY_NAME) {
                switch (parser.getString().replace(currPrefix, "")) {
                    case "IndexLevel":
                        if (parser.next() != JsonParser.Event.VALUE_NULL)
                            result.setIndexLevel(parseIndexLevel(parser));
                        break;
                    case "SourceDataDate":
                        if (parser.next() != JsonParser.Event.VALUE_NULL)
                            result.setSourceDataDate(parser.getString());
                        endFound = true;
                        break;
                    default:
                        break;
                }
            }
        }
        return result.createIndexLevel();
    }

    public IndexLevel parseIndexLevel(JsonParser parser) {
        IndexLevelBuilder result = new IndexLevelBuilder();
        boolean endFound = false;
        while (parser.hasNext() && !endFound) {
            JsonParser.Event e = parser.next();
            if (e == JsonParser.Event.KEY_NAME) {
                switch (parser.getString()) {
                    case "id":
                        parser.next();
                        result.setId(parser.getString());
                        break;
                    case "indexLevelName":
                        result.setIndexLevelName(parser.getString());
                        break;
                    default:
                        break;
                }
            } else if (e == JsonParser.Event.END_OBJECT) {
                endFound = true;
            }
        }
        return result.createIndexLevel();
    }
}