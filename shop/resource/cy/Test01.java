
package cy;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class Test01 {
	//爬取网页的地址
		private static String urlString;
		//一个界面图片的张数
		private static int num = 1;
		
		//多少个界面
		private static int count = 1;
		
		private static String blogNameString = "tiaozhuan";
	      public static void main(String[] args) {
	         for (int i = 0; i < 4; i++) {
	        	 for (int j = 0; j <=9; j++) {
	        		 for(int k = 0;k<=9;k++){
	        			 urlString = "https://3c.163.com/detail/1000"+i+j+k+".do";
	        			 getGoodsFromUrl(urlString);
	        		 }
				}
			}
		}
	      
	      public static void getGoodsFromUrl(String urlString){
	    	  Document doc = null;
	    	  //获取网址的所有内容
	    	  try{
	    		  doc = Jsoup.connect(urlString).get();
	    		if(doc!=null){
	    			count++;
	    			Elements href = doc.select("div[class=goodsPic]");
	    			InputStream is = null;
	    			FileOutputStream fos = null;
	    		  num = 1;
	    			for (int i =0;i<href.size();i++) {
	    				
	    				String url = href.get(i).selectFirst("div[class=goodsPic]>img").attr("src");
	    				URL u = new URL(url);
	    				URLConnection conn = u.openConnection();
	    				is = conn.getInputStream();
	    				File file2 = new File("D:/photo/"+"photo2"+"/"+count+"--"+(num++)+".jpg");
	    				fos = new FileOutputStream(file2);
	    				
	    				byte[] b = new byte[1024];
	    				int len = -1;
	    				while((len=is.read(b))!=-1){
	    					
	    					fos.write(b,0,len);
	    					fos.flush();
	    				}
	    				is.close();
	        			fos.close();
	    			}
	    		
	    		  }else{
	    			  count++;
	    		  }
	    	  }catch(IOException e){
	    		  e.printStackTrace();
	    	  }
//	    	  System.out.println(doc);
	      }
} 
