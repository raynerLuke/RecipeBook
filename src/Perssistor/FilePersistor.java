package Perssistor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.RecipesList;

public class FilePersistor {

    private RecipesList model = new RecipesList();
		
	public void save(RecipesList model){
		try{
		        //Write to disk with FileOutputStream
				FileOutputStream f_out = new 
					FileOutputStream("myobject.data");

				// Write object with ObjectOutputStream
				ObjectOutputStream obj_out = new
					ObjectOutputStream (f_out);

				// Write object out to disk
				obj_out.writeObject ( model );
				} 
		catch (IOException e) {
	        e.printStackTrace();
		}
	}
	    
	public RecipesList load() throws ClassNotFoundException{
		try{ 
			// Read from disk using FileInputStream
			FileInputStream f_in = new 
			    FileInputStream("myobject.data");

			// Read object using ObjectInputStream
			ObjectInputStream obj_in = 
				new ObjectInputStream (f_in);

			// Read an object
			model = (RecipesList) obj_in.readObject();
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return this.model;
	}
}
