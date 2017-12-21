package com.gg.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gg.service.HttpGetTitle;
import com.gg.service.MatchProcess;

public class analyzeAction extends AbstractController{
	
	private HttpGetTitle httpGetTitle;
	
	private MatchProcess matchProcess;

	@Override
	protected Map<String, Object> handleInner(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		(?<=A).*?(?=B)
		Map result = new HashMap();
		String url = (String) request.getAttribute("Url");
		
		String title =httpGetTitle.GetTitle(url);
		if(title.equals("9999")){
			result.put("ReturnMsg", "network error");
			return result;
		}
		
		Map res = MatchProcess.completeMatch(title);
		
		result.put("Result", res);
		return result;
	}

	public void setHttpGetTitle(HttpGetTitle httpGetTitle) {
		this.httpGetTitle = httpGetTitle;
	}

	public void setMatchProcess(MatchProcess matchProcess) {
		this.matchProcess = matchProcess;
	}
	
	
	
	private static String readString()  
	  
	{  
	  
	    String str="";  
	  
	    File file=new File("d://CSII/bookmarks_2017_12_16.html");  
	  
	    try {  
	  
	        FileInputStream in=new FileInputStream(file);  
	  
	        // size  Ϊ�ִ��ĳ��� ������һ���Զ���  
	  
	        int size=in.available();  
	  
	        byte[] buffer=new byte[size];  
	  
	        in.read(buffer);  
	  
	        in.close();  
	  
	        str=new String(buffer,"UTF-8");  
	  
	    } catch (IOException e) {  
	  
	        // TODO Auto-generated catch block  
	  
	        return null;  
	  
	    }  
	  
	    return str;  
	  
	} 
	
public static List<String> completeMatch(String htmlUrl){
        
    	
    	 Pattern pa = Pattern.compile("[a-zA-z]+");
    	 Matcher ma = pa.matcher(htmlUrl);
    	List<String> keys = new ArrayList<String>();
    	
    	 while(ma.find()){
    			keys.add(ma.group(0));  //��ȡ�����еĹؼ���
    		} 
    	

    	 
		return keys;

    }
	
	public static void main(String[] args) {
		String s = readString();
//		System.out.print(s);
   	 Pattern pa = Pattern.compile("(?<=>).*?(?=<)");
   	 Matcher ma = pa.matcher(s);
   	List<String> keys = new ArrayList<String>();
   	String key = new String();
   	
   	 while(ma.find()){
   		 if(ma.group(0).equals("")){
   			 continue;
   		 }
   			keys.add(ma.group(0));  //��ȡ�����еĹؼ���
   			
   		} 
   	 for(String k : keys){
   		 List<String> l = completeMatch(k);
   		 for(String s1 : l){
   			key = key + s1+':';
   		 }
   	 }
   	 
   	System.out.println(key);
   	
   	try {  
        File file = new File("D://CSII/key.txt");  
        PrintStream ps = new PrintStream(new FileOutputStream(file));  
//        ps.println("http://www.jb51.net");// ���ļ���д���ַ���  
//        ps.append(key);// �����еĻ���������ַ���  
      	 for(String k : keys){
       		 List<String> l = completeMatch(k);
       		 for(String s1 : l){
       			ps.println(s1);
       		 }
       	 }
    } catch (FileNotFoundException e) {  
        // TODO Auto-generated catch block  
        e.printStackTrace();  
    }  
   	 
	}
	

}
