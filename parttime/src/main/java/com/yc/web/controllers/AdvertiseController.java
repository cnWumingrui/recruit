package com.yc.web.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yc.bean.Admin;
import com.yc.bean.Advertise;
import com.yc.bean.JsonModel;
import com.yc.biz.AdverBiz;
import com.yc.utils.Encrypt;

@Controller
public class AdvertiseController {
	@Resource(name="adverBizImpl")
	private AdverBiz adverBiz;
	
	@Value("${file.uploadImageFolder}")
    private String uploadImageFolder;

	
	@RequestMapping(value = "/upload.action", method = RequestMethod.POST)
	@ResponseBody
	public JsonModel saveOrUpdate (@RequestParam(required=false)MultipartFile file,HttpServletRequest request){
		JsonModel jm = new JsonModel();
		try {
        //获取项目绝对路径，从例如：（D:\eclipse\workspaceproduct3\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\origin-web\static\abc）
            String path = uploadImageFolder;
            String pathPhoto = "images/advertisement";
            //保存上传
            if(!file.isEmpty()){
               String name = file.getOriginalFilename();//获取接受到的图片名称
                File fi = new File(path,name);       //将path路径与图片名称联系在一起
                if(!fi.getParentFile().exists()){    //判断是否存在path路径下的文件夹
                    fi.getParentFile().mkdirs();       //不存在创建path路径下的文件夹
                }
            	file.transferTo(fi);                        //上传图片
            	jm.setCode(1);
            	jm.setMsg("上传成功");
            	jm.setObj(pathPhoto+"/"+name);
            }
        } catch (Exception e) {
            jm.setCode(0);
            jm.setMsg(e.getMessage().toString());
            return jm;
        }
        return jm;
	}
	
	@RequestMapping("/Administrator/Advertise_toAdd.action")
	@ResponseBody
	public JsonModel toAdd(JsonModel jm, Advertise adver, HttpSession session) {
				boolean re;
				try {
					re = adverBiz.toAdd(adver);
					if (re) {
						jm.setCode(1);
						jm.setMsg("添加成功");
					}
				} catch (Exception e) {
					jm.setCode(0);
					jm.setMsg(e.getMessage());
				}
			
			session.setAttribute("jsonModel", jm);
			return jm;
		
	}
	
	@RequestMapping("/Administrator/Advertise_update.action")
	@ResponseBody
	public JsonModel update(JsonModel jm, Advertise adver, HttpSession session) {
			System.out.println(adver);
			
				int re;
				try {
					re = (int)adverBiz.update(adver);
					if (re==1) {
						jm.setCode(1);
						jm.setMsg("修改成功");
					}
				} catch (Exception e) {
					jm.setCode(0);
					jm.setMsg(e.getMessage());
				}
			
			session.setAttribute("jsonModel", jm);
			return jm;
		
	}
	
	@RequestMapping("/Administrator/Advertise_list.action")
	@ResponseBody
	public String adverList(HttpServletRequest request, HttpSession session,@RequestParam(name = "page") Integer pages,
			@RequestParam(name = "limit") Integer pagesize) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer start = (pages - 1) * pagesize;
		map.put("start", start);
		map.put("pages", pages);
		map.put("pagesize", pagesize);
		try {
            List<Map<String,Object>> list = adverBiz.selectList(Advertise.class, map);
            List<Advertise> adverlist = new ArrayList<Advertise>();
            int total = 0;
            for(Map<String,Object> returnmap : list){
            	total = (Integer)returnmap.get("total");
            	adverlist = (List<Advertise>) returnmap.get("adverList");
            }
            JSONArray json = new JSONArray();
            //将集合变为json
            for (Advertise u: adverlist) {
                JSONObject jo = new JSONObject();
                jo.put("id",u.getId());
                jo.put("picture",u.getPicture());
                jo.put("toURL",u.getToURL());
                jo.put("customer",u.getCustomer());
                jo.put("describes",u.getDescribes());
                jo.put("jointime",u.getJointime().toString());
                if(u.getTimeway()==0){
                	jo.put("timeway", "月结");
                }else if(u.getTimeway()==1){
                	jo.put("timeway", "季度");
                }else if(u.getTimeway()==2){
                	jo.put("timeway", "半年");
                }else{
                	jo.put("timeway", "全年");
                }
                if(u.getStatus()==0){
                	jo.put("status","下架");
                }else{
                	jo.put("status","上架中");
                }
                json.add(jo);
            }
            //将数据准备好layUI的格式
            JSONObject jobj = new JSONObject();
            jobj.put("code",0);
            jobj.put("msg","");
            jobj.put("count",total);
            jobj.put("data",json);
//            System.out.println("total"+total+jobj.toString());
            return jobj.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}
	
	//TODO:封装以及BizImpl业务包装
	
	@RequestMapping("/Administrator/Advertise_delMutil.action")
	@ResponseBody
	public JsonModel delMutil(JsonModel jm, HttpSession session,Integer[] list, HttpServletRequest request) {
		List<Advertise> adverList = new ArrayList<Advertise>();
		List<Integer> intList = Arrays.asList(list);
		for(int i=0;i<intList.size();i++){
			Advertise a = new Advertise();
			a.setId((Integer)intList.get(i));
			adverList.add(a);
		}
		boolean flags = false;
		for(int i = 0;i<intList.size();i++){
			Advertise a = new Advertise();
			a.setId((Integer)intList.get(i));
			Advertise b = adverBiz.selectName(a);
			String name = uploadImageFolder +"/"+b.getPicture().toString().split("/")[2];
			File file = new File(name);
			if (file.exists()) {
				flags = true;
			} else {
				flags = false;
			}
			
		}
		if(flags){
			for(int i = 0;i<intList.size();i++){
				Advertise a = new Advertise();
				a.setId((Integer)intList.get(i));
				Advertise b = adverBiz.selectName(a);
				String name = uploadImageFolder +"/"+b.getPicture().toString().split("/")[2];
				File file = new File(name);
				if (file.exists()) {
					if (file.delete()) {
						boolean flag = adverBiz.delete(a);
						if(flag){
							jm.setCode(1);
							jm.setMsg("删除成功!");
						}else{
							jm.setCode(0);
							jm.setMsg("删除失败!");
						}
					} else {
						jm.setCode(0);
						jm.setMsg("删除失败!");
					}
				} else {
					jm.setCode(0);
					jm.setMsg("删除失败!");
				}
			}
		}else{
			jm.setCode(0);
			jm.setMsg("删除失败!");
		}
		session.setAttribute("jsonModel", jm);
		return jm;
	}
	
	@RequestMapping("/Administrator/Advertise_del.action")
	@ResponseBody
	public JsonModel del(JsonModel jm, HttpSession session, int id, HttpServletRequest request) {
		Advertise a = new Advertise();
		a.setId(id);
		Advertise b = adverBiz.selectName(a);
			String name = uploadImageFolder +"/"+b.getPicture().toString().split("/")[2];
			File file = new File(name);
			if (file.exists()) {
				if (file.delete()) {
					boolean flag = adverBiz.delete(a);
					if(flag){
						jm.setCode(1);
						jm.setMsg("删除成功!");
					}else{
						jm.setCode(0);
						jm.setMsg("删除失败!");
					}
				} else {
					jm.setCode(0);
					jm.setMsg("删除失败!");
				}
			} else {
				jm.setCode(0);
				jm.setMsg("删除失败!");
			}
		session.setAttribute("jsonModel", jm);
		return jm;
	}
	
	@RequestMapping("/Administrator/Advertise_out.action")
	@ResponseBody
	public JsonModel outAdvertise(JsonModel jm, String id,HttpSession session) {
		
			Advertise a = new Advertise();
			a.setId(Integer.parseInt(id));
			int status = adverBiz.selectOne(a);
			a.setStatus(status);
				int re;
				try {
					re = (int)adverBiz.outAdver(a);
					if (re==1) {
						jm.setCode(1);
						jm.setMsg("修改成功");
					}
				} catch (Exception e) {
					jm.setCode(0);
					jm.setMsg(e.getMessage());
				}
			
			session.setAttribute("jsonModel", jm);
			return jm;
		
	}
	
	//定时器：
	@Scheduled(cron="0 0 10 * * ?")
	public void findMonthtimeway(){
		java.util.Date now = new Date();  //获取当前日期
		Advertise adver = new Advertise();
		for(int i=0;i<4;i++){
			int days;
			if(i==0){
				days =30;
			}else if(i==1){
				days =90;
			}else if(i==2){
				days =180;
			}else{
				days =365;
			}
			adver.setTimeway(i);
			List<Advertise> list = adverBiz.findTimeway(adver);
			for(Advertise a:list){
				Date d = a.getJointime();
				long l=now.getTime()-d.getTime();
				long day=l/(24*60*60*1000);
				if(day>=days){
					a.setStatus(0);
					adverBiz.outAdver(a);
				}
			}
		}
	}
	
}
