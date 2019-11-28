
package com.shopping.web.core;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.Attributes;

import com.shopping.pojo.Cart;
import com.shopping.web.action.ActionFather;
import com.shopping.web.form.FormFather;

public class ActionServlet extends HttpServlet {
	
	private static Document doc = null;
    private static Properties actionPool = new Properties();
	

	/**
	 * Constructor of the object.
	 */
	public ActionServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取客户传过来的uri
        String url = request.getRequestURI();
        System.out.println("url："+url);
        //获取action名
        String actionName = url.substring(url.lastIndexOf("/")+1,url.lastIndexOf(".do"));
        System.out.println("actionName："+actionName);
        //通过action名获取action所对应的的类的全路径
        Element element = (Element) doc.selectSingleNode("/actions/action[@name='"+actionName+"']");
        //判断是否是要跳转页面
        String isPageJump = element.selectSingleNode("pagejump").getStringValue().trim();
        //获得执行动作的相关action类路径
        String actoinClassName =  element.attributeValue("class").toString().trim();
        //new出此action类实例
        ActionFather af = null;
        
        Object obj = actionPool.get(actoinClassName);
        try{
        	//判断action池中有没有此action实例，如果有就拿出来用，没有就new出来放入action池
        	if(obj!=null){
        		af = (ActionFather)obj;       		
        	}else{
        		af = (ActionFather)Class.forName(actoinClassName).newInstance();
        		actionPool.put(actoinClassName, af);
        	}	
        }catch(Exception e){
        	e.printStackTrace();
        }
        //调用相关action方法
        //获取相关form对象
        FormFather ff = null;
        String formInstanceClass = element.selectSingleNode("form").getStringValue().trim();
        System.out.println(formInstanceClass);
        try{
        	if(formInstanceClass!=null){
            	Class formClass = Class.forName(formInstanceClass);
            	ff = (FormFather)formClass.newInstance();
            	Set<Map.Entry<String,String[]>> entries = request.getParameterMap().entrySet();
                for (Map.Entry<String, String[]> entry : entries) {
                    String param = entry.getKey();         
                    Method method = formClass.getDeclaredMethod("set"+param.substring(0, 1).toUpperCase()+param.substring(1), String.class);
                    method.invoke(ff,entry.getValue()[0]);
                }
        	}
        }catch(Exception e){
        	e.printStackTrace();
        }
        
        
        //action实例调用相关方法,拿到结果,准备跳转页面
        //通过action判断返回值==要传的值：是否需要跳转别的页面，如果是，直接得到返回值字符串，其他需要通过action判断返回值
        //if(){}====在外面统一判断登录if()
        
       
        
        //如果是页面跳转==直接跳转
        if("true".equalsIgnoreCase(isPageJump)){
        	String getResult = (String)af.doAction(request, response, ff);
            if(getResult!=null){
            	Element jumpPageEle = (Element)element.selectSingleNode("result[@name='" + getResult + "']");
            	List jumpPageEleAttr = jumpPageEle.attributes();//获得属性值
            	String jumpPage = jumpPageEle.getStringValue().trim();//获得跳转页面
            	String redirect = "false";//不二次请求页面
            	for (Object attribute : jumpPageEleAttr) {
                    if (attribute.toString().indexOf("direct") != -1){
                    	redirect = jumpPageEle.attributeValue("direct").trim();
                    }
                }
            	PageJump pf = new PageJump();
            	pf.forword(request,response,jumpPage,redirect);
            	
            }
        }else if("false".equalsIgnoreCase(isPageJump)){
        	//通过action判断返回值
        	 if("shoppingCar".equalsIgnoreCase(actionName)){
        		System.out.println("enter shoppingcar");
        		ArrayList getResult = (ArrayList)af.doAction(request, response, ff);
        		 
        		getResult.add("111");
             	response.setContentType("text/html;charset=UTF-8");
             	PrintWriter out = response.getWriter();
             	JSONArray json  =  JSONArray.fromObject(getResult);
             	out.write(json.toString());
             }else if("123".equalsIgnoreCase(actionName)){
             	
             }
        }
        
        
        
        
        

	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		String configFileName = this.getServletConfig().getInitParameter("configLocation");
		System.out.println("configFileName："+configFileName);
        
		if (configFileName == null){
            configFileName = "/controller.xml";
            PrintWriter printWriter = null;
            try {
                printWriter = new PrintWriter(new FileWriter(configFileName,false),true);
                printWriter.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (printWriter != null) {
                    printWriter.close();
                }
            }
        }
        
        SAXReader reader = new SAXReader();
        try {
            doc = reader.read(ActionServlet.class.getResourceAsStream(configFileName));
            
        } catch (DocumentException e) {
            e.printStackTrace();
        }
		
	}

}
