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
        
        List<Tag> tags= tagcache.getTagList();  //从缓存获取
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
    			keys.add(ma.group(0));  //获取标题中的关键字
    		} 
    	
    	 
    	 List<Tag> match = new ArrayList<Tag>(); //存储匹配的Tag
    	 if(keys.size()>0){  //匹配过程
    		 for(String e : keys){
    			 for(Tag tg : tags){
    				 for(String key : tg.getTagKey()){
    					 if(key.equals(e.toLowerCase())){//全小写匹配
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
    		return keys;   //如果没有匹配的关键字 便于关键字的新增
    	}
		return match;

    }


	public void setTagcache(tagCache tagcache) {
		this.tagcache = tagcache;
	}

	
}
