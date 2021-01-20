package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import core.FileUtilHandler;
import repository.Workspace;

public class FileUtilImpl implements FileUtilHandler{

	@Override
	public void saveContext(String wsPath) {
		File file = new File("lastws.txt");
		file.delete();	//pronalazi fajl lastws.txt, brise ga
		try {
			FileWriter fw = new FileWriter(new File("lastws.txt"), true);
			PrintWriter pw = new PrintWriter(fw);
			pw.print(wsPath);
			fw.close();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	@Override
	public void deleteTxt() {
		File file = new File("lastws.txt");
		file.delete();	// pronalazi fajl lastws.txt, brise ga	
		
		try {
			FileWriter fw = new FileWriter(new File("lastws.txt"), true);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("resource")
	@Override
	public Workspace readLastWs() {
		Workspace ws = null;
		try {
			FileReader	fr = new FileReader(new File("lastws.txt"));
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();	// ucitavamo liniju iz txt fajla
			if(line != null) {	// proveravamo da li je ucitano nesto
				File file = new File(line);
			
				if (!file.exists())
					return null;
				ws = new Workspace(file.getName().substring(0, file.getName().indexOf(".")));
				ws.setWorkspaceFile(file);
			}
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ws;
	}
	
}
