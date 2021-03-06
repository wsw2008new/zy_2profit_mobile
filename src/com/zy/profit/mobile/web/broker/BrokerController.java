package com.zy.profit.mobile.web.broker;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zy.broker.dto.BrokerExtInfoDto;
import com.zy.broker.entity.BrokerExtInfo;
import com.zy.broker.service.BrokerExtInfoService;
import com.zy.common.entity.PageModel;

@Controller
@RequestMapping("/bk")
public class BrokerController {

	@Autowired
	private BrokerExtInfoService brokerExtInfoService;
	
	@RequestMapping("/list")
	public String brokerIndex(Model model,BrokerExtInfoDto queryDto,PageModel<BrokerExtInfo> pageModel){
		
		String param = queryDto.getOrderP();
		if(StringUtils.isNotBlank(param)){
			queryDto.getOrderByParamMap().put(param, "desc");
		}
		
		model.addAttribute("page", brokerExtInfoService.queryPage(queryDto, pageModel));
		model.addAttribute("queryDto", queryDto);
		
		return "broker/brokerIndex";
	}
	
	@RequestMapping("/detail")
	public String borkerDetail(Model model,BrokerExtInfoDto queryDto){
		
		model.addAttribute("broker", brokerExtInfoService.get(queryDto.getId()));
		
		return "broker/brokerDetail";
	}
	
}
