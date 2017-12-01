package com.gg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gg.cache.tagCache;
import com.gg.datamodel.Tag;

public class MatchProcess {
	private static tagCache tagcache;
	
	
	public static List completeMatch(String htmlUrl){
        
        List<Tag> tags= tagcache.getTagList();  //�ӻ����ȡ
//        Tag t =new Tag();
//        t.setTagId("123");
//        String[] ts = {"spring","springmvc"};
//        t.setTagKey(ts);
//        t.setTagName("SpringFrameWork");
//        tags.add(t);
    	
    	 Pattern pa = Pattern.compile("[a-zA-z]+");
    	 Matcher ma = pa.matcher(htmlUrl);
    	List<String> keys = new ArrayList<String>();
    	
    	 while(ma.find()){
    			keys.add(ma.group(0));  //��ȡ�����еĹؼ���
    		} 
    	
    	 
    	 List<Tag> match = new ArrayList<Tag>(); //�洢ƥ���Tag
    	 if(keys.size()>0){  //ƥ�����
    		 for(String e : keys){
    			 for(Tag tg : tags){
    				 for(String key : tg.getTagKey()){
    					 if(key.equals(e.toLowerCase())){//ȫСдƥ��
    						 match.add(tg);
    					 }
    				 }
    			 }
    		 }
    	 }
    	 
    	 
    	 for(Tag tag : match){
    		 System.out.print(tag.getTagName());
    	 }
    	 
    	if(match.size()== 0){
    		return keys;   //���û��ƥ��Ĺؼ��� ���ڹؼ��ֵ�����
    	}
		return match;

    }


	public void setTagcache(tagCache tagcache) {
		this.tagcache = tagcache;
	}

	
}
