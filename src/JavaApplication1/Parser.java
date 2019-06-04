package JavaApplication1;

public class Parser {
	public String args[];
	public String cmd;
	public Parser() {
		cmd="";
	}
	public boolean Parse(String input) {
		input=input.trim();
		args=input.split(" ");
		cmd=args[0];
		if(cmd.equals("ls")||cmd.equals("pwd")||cmd.equals("date")||cmd.equals("clear")||cmd.equals("help")
		   ||cmd.equals("cd")||cmd.equals("exit")||cmd.equals("args")||cmd.equals("rm")||cmd.equals("mkdir")
		   ||cmd.equals("rmdir")||cmd.equals("cp")||cmd.equals("mv")||cmd.equals("more")||cmd.equals("cat")) {
			return true;
		}
		return false;
	}
	public boolean IScombination() {
		for(int i=0;i<args.length;i++) {
			if(args[i].equals("|"))
				return true;
		}
		return false;
	}

}
