package com.gg.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.gg.datamodel.Tag;

public class tagCache {
	private List<Tag> tagList= new ArrayList<Tag>();
	
	private SqlMapClientTemplate sqlMap;
	
	public void init(){
		this.tagList.removeAll(tagList);
		List<HashMap> tmp  = this.sqlMap.queryForList("MVC.queryAllTags");
		
		for(int i = 0 ; i< tmp.size();i++ ){
			Tag t = new Tag();
			t.setTagId((String) tmp.get(i).get("Id"));
			t.setTagName((String) tmp.get(i).get("Name"));
			String key =(String) tmp.get(i).get("Keys");
			String[] keys = key.split(",");
			t.setTagKey(keys);
			this.tagList.add(t);
			
		}
		
	}
	
	public List<Tag> getTagList() {
		return tagList;
	}
	
	
	public void updateTagCache(){
		this.init();
	}

	public SqlMapClientTemplate getSqlMap() {
		return sqlMap;
	}

	public void setSqlMap(SqlMapClientTemplate sqlMap) {
		this.sqlMap = sqlMap;
	}

}
