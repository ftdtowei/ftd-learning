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
        System.out.println("/n------------开始读取网页(" + htmlUrl + ")-----------");
        String htmlSource = "";
        htmlSource = getHtmlSource(htmlUrl);//获取htmlUrl网址网页的源码
        if(htmlSource.equals("9999")){
        	return htmlSource;
        }
        System.out.println("------------读取网页(" + htmlUrl + ")结束-----------/n");
        System.out.println("------------分析(" + htmlUrl + ")结果如下-----------/n");
        String title = getTitle(htmlSource);
        System.out.println("网站标题： " + title);
		return title;
    }
    
    /**
     * 根据网址返回网页的源码
     * @param htmlUrl
     * @return
     */
    public String getHtmlSource(String htmlUrl){
        URL url;    
        StringBuffer sb = new StringBuffer();
        try{
            url = new URL(htmlUrl);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));//读取网页全部内容
            String temp;
            while ((temp = in.readLine()) != null)
            {            
                sb.append(temp);
            }
            in.close();
        }catch (MalformedURLException e) {
            System.out.println("你输入的URL格式有问题！请仔细输入");
        }catch (IOException e) {
            e.printStackTrace();
            return "9999";
        }    
        return sb.toString();
    }
    
    /**
     * 从html源码(字符串)中去掉标题
     * @param htmlSource
     * @return
     */
    public String getTitle(String htmlSource){
        List<String> list = new ArrayList<String>();
        String title = "";
        
        //Pattern pa = Pattern.compile("<title>.*?</title>", Pattern.CANON_EQ);也可以
        Pattern pa = Pattern.compile("<title>.*?</title>");//源码中标题正则表达式
        Matcher ma = pa.matcher(htmlSource);
        while (ma.find())//寻找符合el的字串
        {
            list.add(ma.group());//将符合el的字串加入到list中
        }
        for (int i = 0; i < list.size(); i++)
        {
            title = title + list.get(i);
        }
        return outTag(title);
    }
    
    /**
     * 去掉html源码中的标签
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