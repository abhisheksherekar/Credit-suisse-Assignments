package com.Assignment.MAIN;

import java.io.FileNotFoundException;
import java.io.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.*;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.Assignment.DAO.EventDAO;
import com.Assignment.DTO.EventDTO;

public class JSONreader {

    private static final Logger LOGGER = Logger.getLogger(JSONreader.class.getName());

	public static void main(String[] args) {
		JSONParser jsonParser = new JSONParser();
		try {
			File file=new File("C:/Users/ABHISHEKH/Documents/Advance JAVA/logFile.txt");//creates a new file instance  
			FileReader fr=new FileReader(file);   //reads the file  
			BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
			List<EventDTO> list = new ArrayList<>();
			String line;  
			while((line=br.readLine())!=null)  
			{  
				JSONObject jsonObject = (JSONObject) jsonParser.parse(line);
				LOGGER.info("Fething JSON object line by line "+jsonObject);
				String eventId = (String)jsonObject.get("id");
				String eventType = (String)jsonObject.get("type");
				String eventTimeStamp = (String)jsonObject.get("timestamp");
				String hostName = (String)jsonObject.get("host");
				String eventState = (String)jsonObject.get("state");
				
				list.add(new EventDTO(eventId, eventType, eventTimeStamp, hostName, eventState));
				LOGGER.info("Storing each Object into ArrayList ");
				
			}  
			LOGGER.info("Parsing .txt file to JSON object succesfully. and stored to ArrayList.");
			LOGGER.info("ArrayList of EventDTO Objects "+list);
			Map<String,Long> mapp = new HashMap<>(); 
			for(EventDTO obj : list) {
				if(mapp.containsKey(obj.getEventId())) {
					Long val = Long.valueOf(obj.getEventTimeStamp());
					Long preInsertedVal = mapp.get(obj.getEventId());
					mapp.put(obj.getEventId(), Math.abs(val-preInsertedVal));
				}else
					mapp.put(obj.getEventId(), Long.valueOf(obj.getEventTimeStamp()));
			}
			LOGGER.info("Map Before Filtration of timeStamps "+mapp);
			mapp = 
				mapp.entrySet()
			        .stream()
			        .filter(e -> e.getValue() > 4)
			        .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
			
			LOGGER.info("Map After Filtration of timeStamps "+mapp);
			
			LOGGER.info("Changing Event status to TRUE into DB. ");
			EventDAO dao = new EventDAO();
			try {
				String dbResponse = dao.updateEventStatus(mapp);
				if(dbResponse.equalsIgnoreCase("SUCCESS")) {
					LOGGER.info("Database status of event is updated succesfully. ");
				}
			} catch (SQLException e1) {
				LOGGER.info("Exception occurs while updating Database status of event. ");
				e1.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		
	}

}
