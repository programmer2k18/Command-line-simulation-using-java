package JavaApplication1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
public class Terminal {
    public Terminal() {
    	
    }
	public void ls() {
	    File dir = new File(System.getProperty("user.dir"));
	    String childs[] = dir.list();
	    for (String child : childs) {
	        System.out.println(child);
	    }
	}
	public  void listFiles(String directoryName) {
	    File directory = new File(directoryName);
	    //get all the files from a directory
	    File[] fList = directory.listFiles();
	    for (File file : fList) {
	        if (file.isFile() || file.isDirectory()) {
	            System.out.println(file.getName());
	        }
	    }
	}
	public  void mkdir(String directory) {
	    File f = null;
	    try {
	        f = new File(directory);
	        f.mkdirs();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public  void rmdir(String directoryPath,String directory) {
		  File f = null;
		  try{
		        f = new File(directory);
		        f.delete();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}
	public void rm(String directory) {
	    File file = new File(directory);
	    if (file.delete()) {
	        System.out.println("");
	    } else {
	        System.out.println("operation is failed." + "\n");
	    }
	}
	public  void cp(String directory, String copy, String paste) throws FileNotFoundException, IOException {
	    // The name of the file to open.
		if(CheckFile(directory,copy)&&CheckFile(directory,paste)) {
			String fileName = paste;
		    try {
		        // Assume default encoding.
		        FileWriter fileWriter = new FileWriter(directory + fileName);
		        // Always wrap FileWriter in BufferedWriter.
		        BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
		        // This will reference one line at a time
		        String line = null;
		        // FileReader reads text files in the default encoding.
		        FileReader fileReader= new FileReader(directory + copy);
		        // Always wrap FileReader in BufferedReader.
		        BufferedReader bufferedReader= new BufferedReader(fileReader);
		        while ((line = bufferedReader.readLine()) != null) {
		            bufferedWriter.write(line);
		            bufferedWriter.newLine();
		        }
		        bufferedReader.close();
		        bufferedWriter.close();
		    } catch (IOException ex) {
		        System.out.println("Error writing to file "+ fileName + " " + ex.getMessage());
		    }
		}
		else
			System.out.println("Error with this command");
	}
public boolean CheckFile(String directory, String data) {
		File all = new File(directory);
        File[] fList = all.listFiles();
        for (File file : fList) {
            if (file.isFile()||file.isDirectory()) {
                if (file.getName().equals(data)) 
                   return true;
            }
        }
        return false;
	}
public void cat(String file) {
	    // This will reference one line at a time
	    String line = null;
	    try {
	        // FileReader reads text files in the default encoding.
	        FileReader fileReader = new FileReader(file);
	        // Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader= new BufferedReader(fileReader);
	        while ((line = bufferedReader.readLine()) != null) {
	            System.out.println(line);
	        }
	        bufferedReader.close();
	    } catch (FileNotFoundException ex) {
	        System.out.println("Error reading file" + ex.getMessage());
	    } catch (IOException ex) {
	        System.out.println("Error reading file " + ex.getMessage());
	    }
	}	
//cd c:/
public   String cd(String directory,String str)
{
        String[] forcd = directory.split("\\\\");
        char[] stringToCharArray = str.toCharArray();
        
            if (stringToCharArray[1] == ':') {
                directory = str;
                System.out.println(" command completed \n");
            } 
            else 
            {
            	if(str.equals("..")) {
            		directory = "";
                    for (int i = 0; i < forcd.length - 1; i++) {
                        directory += forcd[i] + "\\";
                    }
            	}
            	else
            	{
            		File all = new File(directory);
                    //get all the files from a directory
                    File[] fList = all.listFiles();
                    int count = 0;
                    for (File file : fList) {
                        if (file.isDirectory()) {
                            if (file.getName().equals(str)) {
                                count++;
                                directory += str + "\\";
                                break;
                            }
                        }
                    }
                    if (count == 0) 
                         System.out.println("there is no folder with name" + str);
                    else 
                         System.out.println("command completed");
            	}
              }
            return directory;
} 
public void more(String filename) {
    String CurrentLine = null;
    try {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader= new BufferedReader(fileReader);
        CurrentLine=bufferedReader.readLine();
        if(CurrentLine!=null)
        	System.out.println(CurrentLine);
        while((CurrentLine=bufferedReader.readLine())!=null) {
        	 String s=new Scanner(System.in).nextLine();
        	 if(s.length()==0)
        		 System.out.println(CurrentLine);
        }
        
        bufferedReader.close();
    } catch (FileNotFoundException ex) {
        System.out.println("Error reading file" + ex.getMessage());
    } catch (IOException ex) {
        System.out.println("Error reading file " + ex.getMessage());
    }
}
public  void clear(){
	System.out.println(new String(new char[50]).replace("\0", "\r\n"));    	
}
public  void getTime() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    System.out.println(dtf.format(now));
}
public  void help() {
    System.out.println(""
            + "ls ---> show the folders and files in the current directory | ls \n"
            + "clear ---> removes all in screeen | clear\n"
            + "date ---> get current time and date | date\n"
            + "mv ---> move content of a file to another one | mv sourceFile destinationFile\n"
            + "cp ---> content of a file to another one | cp sourceFile destinationFile\n"
            + "cd ---> change the current directory | cd .. returns back or cd ('New Directory')\n"
            + "mkdir ---> create new folder | mkdir Folder Name \n"
            + "rmdir ---> remove folder | rmdir foldername\n"
            + "rm ---> delete file | rm File Name \n"
            + "cat ---> read the content of file | cat \n"
            + "pwd ---> show the current directory | pwd \n"
            + "args ---> List all command arguments | args Name of command line "
    );
    getTime();
}
public void args(String command) {
    if (command.equals("ls") || (command.equals("date")) || (command.equals("pwd")) || (command.equals("clear"))) {
        System.out.println("Takes no parameters");
    } else if (command.equals("mv") || command.equals("cp")) {
        System.out.println("Number of args : 2\nargs : Source path Destination path");
    } else if (command.equals("mkdir") || command.equals("rmdir")) {
        System.out.println("Number of args : 1\nargs :  Name of folder");
    } else if (command.equals("rm") || command.equals("cat")) {
        System.out.println("Number of args : 1\nargs :Name of the file txt");
    } else if (command.equals("cd")) {
        System.out.println("Number of args : 1\nargs : cd .. returns back or cd (new directory)");
    } 
    else
    	System.out.println("You Entered Wrong command");
}
}
