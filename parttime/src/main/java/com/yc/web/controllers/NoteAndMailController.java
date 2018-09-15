package com.yc.web.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yc.bean.Applyinfo;
import com.yc.biz.ApplyinfoBiz;
import com.yc.mail.SendMailService;
import com.yc.note.IndustrySMS;
import com.yc.web.dto.JsonModel;

@Api(value = "", tags = "邮件和短信")
@Slf4j
@RestController
public class NoteAndMailController {
	@Autowired
	private IndustrySMS industrySms;
	@Autowired
	private SendMailService sendMailService;
	@Resource(name = "applyinfoBizImpl")
	private ApplyinfoBiz applyinfoBiz;
	@Autowired
	private JsonModel jsonModel;

	@ApiOperation(value = "发短信", notes = "发短息")
	@ApiImplicitParam(name = "String", value = "String", required = true, dataType = "String")
	@RequestMapping("sendNote.action")
	public JsonModel sendNoto(HttpSession session, String Merchant_username) {
		int random = (int) (Math.random() * 9000 + 1000);
		log.info("发送验证码");
		log.info("" + random);
		session.setAttribute("random", random + "");
		// industrySms.execute2(random, Merchant_username);
		jsonModel.setObj(random + "");
		return jsonModel;

	}

	@ApiOperation(value = "发邮件", notes = "发邮件")
	@ApiImplicitParam(name = "String", value = "String", required = true, dataType = "String")
	@RequestMapping("sendEmail.action")
	@ResponseBody
	public JsonModel sendEmail(HttpSession session, Applyinfo applyinfo,String email) {
		// TODO:将二者关系对应status改为1； user中存对象 因为有时用id 有时间用名字
		applyinfoBiz.updataApplyinfo(applyinfo);
			
		String subject = "工作通知";
		String content = "工作通知";
		// TODO:
		email = "476212140@qq.com";
		sendMailService.sendSimpleMail(subject, content, email);

		jsonModel.setCode(1);
		return jsonModel;

	}
}
