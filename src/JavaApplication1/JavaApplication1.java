
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

public class JavaApplication1 {
    public static void main(String[] args) throws IOException, InterruptedException {
        Boolean stat = true;
        Scanner s = new Scanner(System.in);
        String directory = "F:\\OS Assignment\\New folder\\";
        String defaultDirectory="F:\\OS Assignment\\New folder\\";
        System.out.println("If you want to finish wirte exit please " + System.getProperty("user.name") + " : ");
        Terminal obj =new Terminal();
        Parser par=new Parser();
        do {
            String str = s.nextLine();
            par.Parse(str);
            //commands that takes no arguments like ls pwd clear date help and special case of cd
            if(par.args.length==1) {
            	if(par.cmd.equals("ls"))
            		obj.listFiles(directory);
            	else if(par.cmd.equals("pwd"))
            		System.out.println(directory);	
            	else if(par.cmd.equals("date"))
            		obj.getTime();
            	else if(par.cmd.equals("clear"))
            		obj.clear();
             	else if(par.cmd.equals("help"))
            		obj.help();
             	else if(par.cmd.equals("cd"))
             		directory=defaultDirectory;
             	else if (par.cmd.equals("exit"))
                    stat = false;
             	else
             		System.out.println("Error: You Entered Wrong Command");
            }
            //commands that takes one argument like args cd rm mkdir rmdir cat
            else if(par.args.length==2) {
            	if(par.cmd.equals("args"))
            		obj.args((par.args[1]));
            	else if(par.cmd.equals("cd"))
            		directory=obj.cd(directory,par.args[1]);	
            	else if(par.cmd.equals("rm")&&obj.CheckFile(directory, par.args[1]))
            		obj.rm(directory + par.args[1]);
            	else if(par.cmd.equals("mkdir")) {
            		try {
                        obj.mkdir(directory + par.args[1]);
                    } catch (Exception e) {
                        System.out.println("Error in your command");
                    }
            	}
            	else if(par.cmd.equals("rmdir")&&obj.CheckFile(directory, par.args[1]))
                        obj.rmdir(directory,directory + par.args[1]);
             	else if(par.cmd.equals("cat")&&obj.CheckFile(directory, par.args[1]))
            		obj.cat(directory+par.args[1]);
             	else if(par.cmd.equals("more")&&obj.CheckFile(directory, par.args[1]))
            		obj.more(directory+par.args[1]);
             	else
             		System.out.println("Error: You Entered Wrong Command or Missing correct arguments");
            }//commands that takes two argument like mv cp
            else if(par.args.length==3) {
            	if(par.cmd.equals("cd"))//waiting//cd new folder
            		directory=obj.cd(directory,par.args[1]+" "+par.args[2]);
            	else if(par.cmd.equals("cp")) {
            		 try {
                         obj.cp(directory, par.args[1], par.args[2]);
                     } catch (Exception e) {
                         System.out.println("Error in your command. Make sure that both files are exist ");
                     }
            	}
            	else if(par.cmd.equals("mv")&&obj.CheckFile(directory,par.args[1])&&obj.CheckFile(directory,par.args[2])){
                        obj.cp(directory, par.args[1], par.args[2]);
                        obj.rm(directory+par.args[1]);
           	    }
            }
            else if(par.args.length>=3&&par.IScombination()) {
            	
            	if(par.cmd.equals("cd")&&par.args[3].equals("pwd")) {
            		directory=obj.cd(directory,par.args[1]);
            		System.out.println(directory);
            	}
            	else if(par.cmd.equals("cd")&&par.args[3].equals("ls")) {
            		directory=obj.cd(directory,par.args[1]);
            		obj.listFiles(directory);
            	}
            	else if(par.cmd.equals("cd")&&par.args[3].equals("mkdir")) {
            		directory=obj.cd(directory,par.args[1]);
            		obj.mkdir(directory+par.args[4]);
            	}
            	else if(par.cmd.equals("cd")&&par.args[3].equals("rmdir")) {
            		directory=obj.cd(directory,par.args[1]);
            		obj.rmdir(directory,directory+par.args[4]);
            	}
            	else if(par.cmd.equals("cp")&&par.args[3].equals("cat")) {
            		obj.cp(directory, par.args[1],par.args[1] );
            		obj.cat(directory+par.args[5]);
            	}
            	else if(par.cmd.equals("cp")&&par.args[4].equals("mv")) {
            		obj.cp(directory, par.args[1],par.args[2] );
            		obj.cp(directory,par.args[5],par.args[6]);
            		if(obj.CheckFile(directory, par.args[1])&&obj.CheckFile(directory, par.args[2]))
            			obj.rm(directory+par.args[5]);
            	}
            	else if(par.cmd.equals("mkdir")&&par.args[3].equals("rmdir")) {
            		obj.mkdir(directory+par.args[1]);
            		obj.rmdir(directory,directory+par.args[4]);
            	}
            }
            else 
            	System.out.println("You Entered Wrong Command or Wrong Arguments");
        } while (stat == true);
    }
    }