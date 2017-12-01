package com.gg.springMVC;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractView;

import com.alibaba.fastjson.JSONObject;

public class JsonView extends AbstractView {
	
	private Logger logger = LoggerFactory.getLogger(JsonView.class);
	
	public static final String DEFAULT_CONTENT_TYPE = "application/json";

	private String DEFAULT_CHARSET = "UTF-8";

	private boolean disableCaching = true;
	
	@Override
	protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType(DEFAULT_CONTENT_TYPE);
		response.setCharacterEncoding(DEFAULT_CHARSET);
		if (this.disableCaching) {
			response.addHeader("Pragma", "no-cache");
			response.addHeader("Cache-Control", "no-cache, no-store, max-age=0");
			response.addDateHeader("Expires", 1L);
		}
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug(model.toString());
		}
		
		String jsonStr = JSONObject.toJSONString(model);
		
		response.getWriter().write(jsonStr);
		
	}
	
}
