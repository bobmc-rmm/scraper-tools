// -*- Java -*-
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ 
/**@file  Scraper1.java 
 * @brief scrape a HTML page with Jsoup
 * 
 */ 

package scraper1;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Scraper1 {
   Document doc;
   Elements links;
   Elements tags;
   
   Scraper1(){
      //start();
   }
   
   //--------------------------------------------------------------
   // \brief Open file .. get pricelist
   void start(){
      String url;
      String fn = "?";
      try{
	 //fn = "/home/bobmc/NetBeansProjects/Scraper1/Robotix.html";
	 fn = "Robotix.html";
	 xprintf("Opening %s...", fn);
	 doc = Jsoup.parse( new File(fn), "utf-8" );
	 //doc = Jsoup.connect(url).get();
      }catch(IOException e){
	 System.out.println("Jsoup can't get " + fn);
      }
      show_links();
      show_record("name");
   }

   //--------------------------------------------------------------
   boolean show_links(){
      links = doc.select("a[href]");
      xprintf("Links: (%d)", links.size());
      for (Element link : links) {
	 xprintf(" * a: <%s>", trim(link.attr("abs:href"),65));
      }
      return true;
   }
   
   //--------------------------------------------------------------
   boolean show_record(String item_name) {
      Elements elems = doc.getElementsByClass(item_name);
      xprintf("Num Records (%d)", elems.size());
      for (Element el : elems) {
	 xprintf(" * %s:  %s", el.className(), el.text());
	 for (int i = 0; i < 3; i++) {
            Element nextSibling = el.nextElementSibling();
            xprintf(" * %s: (%s)",
		    nextSibling.className(), nextSibling.text());
            el = nextSibling;
	 }
      }
      return true;
   }
   
   //--------------------------------------------------------------
   float str_to_float( String item ){
      float fn = 0;
      try {
	 fn = Float.parseFloat(item);
      } catch (NumberFormatException e) {
	 fn = 0;
	 xprintf("Err: (%s) not a number", item);
      }
      return fn;
   }
   
   //--------------------------------------------------------------
   void xprintf(String msg, Object... args) {
      System.out.println(String.format(msg, args));
   } // xprintf
   
   //--------------------------------------------------------------
   String trim(String s, int width) {
      if (s.length() > width)
	 return s.substring(0, width-1) + ".";
      else
	 return s;
   } // trim


   //--------------------------------------------------------------
   public static void main(String[] args) {
      System.out.println("Scraper1...");
      Scraper1 pgm = new Scraper1();
      pgm.start();
   }

} // class Scraper1


/*
   //--------------------------------------------------------------
   boolean show_prices(String item){
      Elements elems = doc.getElementsByClass(item);
      System.out.println("Num prices " + elems.size());
      
      for (Element el : elems) {
	 String ss = el.text();
	 //float fn = str_to_float(ss);
	 //String[] strs = ss.split(" ");
	 //System.out.println("Substrings length:"+strs.length);
	 //for (int i=0; i < strs.length; i++) {
	 // System.out.println("Str["+i+"]:"+strs[i]);
	 //}
	 
	 System.out.println("* price: " + ss );
      }
      return true;
   }
      
   //--------------------------------------------------------------
   boolean show_product(String item){
      Elements elems = doc.getElementsByClass(item);
      System.out.println("Num products " + elems.size());
      for (Element el : elems) {
	 //System.out.println(" * product: <%s>  (%s)", "_"
	 ,trim(el.text(), 35));
      }
      return true;
   }

   //--------------------------------------------------------------
   boolean show_model(String item){
      Elements elems = doc.getElementsByClass(item);
      System.out.println("Num models " + elems.size());
      for (Element el : elems) {
	 String ss = el.text();
	 //float fn = str_to_float(ss);
	 //String[] strs = ss.split(" ");
	 //System.out.println("Substrings length:"+strs.length);
	 //for (int i=0; i < strs.length; i++) {
	 // System.out.println("Str["+i+"]:"+strs[i]);
	 //}
	 
	 System.out.println("* model: " + ss );
      }
      return true;
   }
      
  <h3 class="product">Four Cheese Spinach Dip</h3>
  <h3 class="specialcallout">Vegetarian</h3>
  <h4 class="productdescription">The original, made in-house creamy blend
  of four cheeses, spinach, red pepper and onion. Served warm with fried
  pita chips.</h4>
  <h4 class="calories"><span class="productprice">11.99</span>
  &nbsp;&nbsp;(620 CALS; serves 2)</h4>


  Attributes atts = el.attributes();
  for( Attribute att : atts ){
  System.out.print(" * att: <%s>  (%s)", att.getKey(), 
  trim(att.getValue(), 35));
  }

  String[] parts = string.split("-");
  String part1 = parts[0]; // 004
  String part2 = parts[1]; // 034556

  Float number = null;
  try {
  number = Float.parseFloat(numberStr);
  } catch (NumberFormatException e) {
  System.out.println("numberStr is not a number");
  }

  boolean show_product() {
  Elements elems = doc.getElementsByClass("product");
  System.out.println("Num products " + elems.size() + "\n");
  for (Element el : elems) {
  System.out.printf(" * %s: <%s>  (%s)\n", el.className(), "_", el.text());
  for (int i = 0; i < 3; i++) {
  Element nextSibling = el.nextElementSibling();
  System.out.printf(" * %s: <%s>  (%s)\n", nextSibling.className(), "_", nextSibling.text());
  el = nextSibling;
  }
  Element spanEl = el.select("span").first();
  System.out.printf(" * %s: <%s>  (%s)\n", spanEl.className(), "_", spanEl.text());
  }
  return true;
  }

*/
