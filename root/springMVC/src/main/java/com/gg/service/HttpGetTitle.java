package com.gg.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gg.datamodel.Tag;


public class HttpGetTitle {
    
    public String GetTitle(String htmlUrl){
        System.out.println("/n------------��ʼ��ȡ��ҳ(" + htmlUrl + ")-----------");
        String htmlSource = "";
        htmlSource = getHtmlSource(htmlUrl);//��ȡhtmlUrl��ַ��ҳ��Դ��
        if(htmlSource.equals("9999")){
        	return htmlSource;
        }
        System.out.println("------------��ȡ��ҳ(" + htmlUrl + ")����-----------/n");
        System.out.println("------------����(" + htmlUrl + ")�������-----------/n");
        String title = getTitle(htmlSource);
        System.out.println("��վ���⣺ " + title);
		return title;
    }
    
    /**
     * ������ַ������ҳ��Դ��
     * @param htmlUrl
     * @return
     */
    public String getHtmlSource(String htmlUrl){
        URL url;    
        StringBuffer sb = new StringBuffer();
        try{
            url = new URL(htmlUrl);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));//��ȡ��ҳȫ������
            String temp;
            while ((temp = in.readLine()) != null)
            {            
                sb.append(temp);
            }
            in.close();
        }catch (MalformedURLException e) {
            System.out.println("�������URL��ʽ�����⣡����ϸ����");
        }catch (IOException e) {
            e.printStackTrace();
            return "9999";
        }    
        return sb.toString();
    }
    
    /**
     * ��htmlԴ��(�ַ���)��ȥ������
     * @param htmlSource
     * @return
     */
    public String getTitle(String htmlSource){
        List<String> list = new ArrayList<String>();
        String title = "";
        
        //Pattern pa = Pattern.compile("<title>.*?</title>", Pattern.CANON_EQ);Ҳ����
        Pattern pa = Pattern.compile("<title>.*?</title>");//Դ���б���������ʽ
        Matcher ma = pa.matcher(htmlSource);
        while (ma.find())//Ѱ�ҷ���el���ִ�
        {
            list.add(ma.group());//������el���ִ����뵽list��
        }
        for (int i = 0; i < list.size(); i++)
        {
            title = title + list.get(i);
        }
        return outTag(title);
    }
    
    /**
     * ȥ��htmlԴ���еı�ǩ
     * @param s
     * @return
     */
    public String outTag(String s)
    {
        return s.replaceAll("<.*?>", "");
    }
    
    public static void main(String[] args) {
        String htmlUrl = "https://spring.weixin.SpringMVC.com/";
        
        List<Tag> tags= new ArrayList<Tag>();
        Tag t =new Tag();
        t.setTagId("123");
        String[] ts = {"spring","springmvc"};
        t.setTagKey(ts);
        t.setTagName("SpringFrameWork");
        tags.add(t);
    	
    	 Pattern pa = Pattern.compile("[a-zA-z]+");
    	 Matcher ma = pa.matcher(htmlUrl);
    	List<String> keys = new ArrayList<String>();
    	
    	 while(ma.find()){
    			keys.add(ma.group(0));
    		} 
    	 List<Tag> match = new ArrayList<Tag>(); 
    	 if(keys.size()>0){
    		 for(String e : keys){
    			 for(Tag tg : tags){
    				 for(String key : tg.getTagKey()){
    					 if(key.equals(e)){
    						 match.add(tg);
    					 }
    				 }
    			 }
    		 }
    	 }
    	 
    	 
    	 for(Tag tag : match){
    		 System.out.print(tag.getTagName());
    	 }

    }
}