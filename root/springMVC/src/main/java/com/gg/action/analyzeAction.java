package com.gg.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
		Map result = new HashMap();
		String url = (String) request.getAttribute("Url");
		
		String title =httpGetTitle.GetTitle(url);
		if(title.equals("9999")){
			result.put("ReturnMsg", "network error");
			return result;
		}
		
		List res = MatchProcess.completeMatch(title);
		
		result.put("List", res);
		return result;
	}

	public void setHttpGetTitle(HttpGetTitle httpGetTitle) {
		this.httpGetTitle = httpGetTitle;
	}

	public void setMatchProcess(MatchProcess matchProcess) {
		this.matchProcess = matchProcess;
	}
	
	

}
