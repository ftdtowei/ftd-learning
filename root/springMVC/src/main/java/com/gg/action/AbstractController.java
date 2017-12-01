package com.gg.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;




public abstract class AbstractController implements Controller, MessageSourceAware {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	private MessageSource messageSource;
	
	private SqlMapClientTemplate sqlMap;
	
	protected TransactionTemplate trsTemplate;
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.logger.info("start trans " + request.getRequestURL());
		Map<String,Object> map = this.handleInner(request, response);
		if (map.get("ReturnCode") != null && !"0000".equals(map.get("ReturnCode"))) {
			String returnMsg = this.messageSource.getMessage(map.get("ReturnCode").toString(), null, "9999", null);
			map.put("ReturnMsg", returnMsg);
		}
		return new ModelAndView("jsonView", map);
	}
	
	protected abstract Map<String,Object> handleInner(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public SqlMapClientTemplate getSqlMap() {
		return sqlMap;
	}

	public void setSqlMap(SqlMapClientTemplate sqlMap) {
		this.sqlMap = sqlMap;
	}
	public TransactionTemplate getTrsTemplate() {
		return trsTemplate;
	}
	public void setTrsTemplate(TransactionTemplate trsTemplate) {
		this.trsTemplate = trsTemplate;
	}

}
