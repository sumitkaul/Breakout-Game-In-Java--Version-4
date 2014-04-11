package gameMakerModel;

import gameMaker.gameMaker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class SaveSprite  {
	
	private static final org.apache.log4j.Logger LOG = 
			org.apache.log4j.Logger.getLogger(gameMaker.class);
   private SpriteList saveModels;
   private AssociationList associations;
	public SaveSprite(SpriteList sm,AssociationList associations)
	{
		this.saveModels = sm;
		this.associations=associations;
	}
	
	
	
	public void Save(File filename) {
		XStream writer = new XStream(new StaxDriver());
		try
		{
			    //LOG.debug(saveModels.size());
	            //LOG.debug("InsideSave"+saveModels.getList().get(0).getSpriteName());
	            FileWriter fstream = new FileWriter(filename);
	    		BufferedWriter out = new BufferedWriter(fstream);
	    		String xml = writer.toXML(saveModels);
	    		out.write(xml);
	            /*for(int i=0;i<saveModels.size();i++){
	            String xml = writer.toXML(saveModels.get(i));           
		        out.write(xml);
		        
	            }*/
	            //LOG.debug(saveModels.size());
	            LOG.debug("File contents");
	            FileWriter writeAssociation = new FileWriter("saveGames.xml");
	    		BufferedWriter associationWriter = new BufferedWriter(writeAssociation);
	            /*for(int j=0;j<associations.size();j++){
	            	LOG.debug(associations.get(j).getEventName());
	            String xmlAssociation = writer.toXML(associations.get(j));*/ 
	    		String xmlAssociation = writer.toXML(associations);
	            associationWriter.write(xmlAssociation);
	            
	            
		//out.append(xml);
        associationWriter.close();
		out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
		
	
	public SpriteList load(File loadFile) throws FileNotFoundException
		{
			//XStream loader = new XStream(new StaxDriver());
		
		//	SpriteModel spriteModel = (SpriteModel) loader.fromXML("saveGame.xml");
			 FileReader freader = new FileReader(loadFile);
		        BufferedReader in = new BufferedReader(freader);
		        XStream reader = new XStream(new StaxDriver());
		        //List<SpriteModel> spriteModels =  (List<SpriteModel>) reader.fromXML(in);
		        //SpriteModel s =  (SpriteModel)reader.fromXML(in);
		        //LOG.debug("Hello"+s.getSpriteImage());
		        SpriteList pList = (SpriteList)reader.fromXML(in);
		        return pList;
			
}
	public AssociationList loadAssociation() throws FileNotFoundException
	{
		//XStream loader = new XStream(new StaxDriver());
		
	//	SpriteModel spriteModel = (SpriteModel) loader.fromXML("saveGame.xml");
		 FileReader freader = new FileReader("saveGames.xml");
	        BufferedReader in = new BufferedReader(freader);
	        XStream reader = new XStream(new StaxDriver());
	        AssociationList list = (AssociationList)reader.fromXML(in);
	        return list;
	        
	        
		
}
		
		
		
	}
	
	

