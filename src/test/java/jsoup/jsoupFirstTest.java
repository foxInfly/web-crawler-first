package jsoup;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import java.io.File;
import java.net.URL;
import java.util.Set;

/**
 * @author : lipu
 * @since : 2020-08-16 09:29
 */
public class jsoupFirstTest {



    @Test
    public void testUrl() throws Exception{
        //解析url
        //第一参数：访问的url,第二个参数是访问时的超时时间
        Document doc = Jsoup.parse(new URL("http://www.itcast.cn"), 1000);


        //使用标签选择器，获取title中的内容
        String title = doc.getElementsByTag("title").first().text();
        System.out.println(title);
    }


    @Test
    public void tesString() throws Exception{
        //解析字符串
        String content = FileUtils.readFileToString(new File("F:\\project\\IDEA\\study\\javaee57\\web-crawler-first\\docs\\test.html"), "utf8");
        System.out.println(content);

        //第一参数：访问的url,第二个参数是访问时的超时时间
        Document doc = Jsoup.parse(content);


        //使用标签选择器，获取title中的内容
        String title = doc.getElementsByTag("title").first().text();
        System.out.println(title);
    }


    @Test
    public void testFile() throws Exception{
        //第一参数：访问的url,第二个参数是访问时的超时时间
        Document doc = Jsoup.parse(new File("F:\\project\\IDEA\\study\\javaee57\\web-crawler-first\\docs\\test.html"),"utf8");


        //使用标签选择器，获取title中的内容
        String title = doc.getElementsByTag("title").first().text();
        System.out.println(title);
    }

    @Test
    public void testDOM() throws Exception{
        //第一参数：访问的url,第二个参数是访问时的超时时间
        Document doc = Jsoup.parse(new File("F:\\project\\IDEA\\study\\javaee57\\web-crawler-first\\docs\\test.html"),"utf8");

        //1.根据id查询元素getElementById
        String city_bj = doc.getElementById("city_bj").text();
        System.out.println(city_bj);
        //2.根据标签获取元素getElementsByTag
        String span = doc.getElementsByTag("span").first().text();
        System.out.println(span);
        //3.根据class获取元素getElementsByClass
        String class_a_class_b = doc.getElementsByClass("class_a class_b").first().text();
        String class_a = doc.getElementsByClass("class_a").first().text();
        System.out.println(class_a_class_b);
        System.out.println(class_a);
        //4.根据属性获取元素getElementsByAttribute
        String abc = doc.getElementsByAttribute("abc").first().text();
        System.out.println(abc);

        //5.组合
        String abc1 = doc.getElementsByAttributeValue("href","http://gz.itcast.cn").first().text();
        System.out.println(abc1);

    }


    @Test
    public void testData() throws Exception{
        //第一参数：访问的url,第二个参数是访问时的超时时间
        Document doc = Jsoup.parse(new File("F:\\project\\IDEA\\study\\javaee57\\web-crawler-first\\docs\\test.html"),"utf8");

        Element element = doc.getElementById("test");

        String str = "";

//        str = element.id();
//        str = element.className();
//        Set<String> classNames = element.classNames();
//        for (String className : classNames) {
//            System.out.println(className);
//        }

//        str = element.attr("id");
        Attributes attributes = element.attributes();

        System.out.println(str);

        //使用标签选择器，获取title中的内容
        String title = doc.getElementsByTag("title").first().text();
        System.out.println(title);
    }


    @Test
    public void tesSelector() throws Exception{
        //第一参数：访问的url,第二个参数是访问时的超时时间
        Document doc = Jsoup.parse(new File("F:\\project\\IDEA\\study\\javaee57\\web-crawler-first\\docs\\test.html"),"utf8");

        //标签获取元素
        Elements elements = doc.select("span");

        for (Element element : elements) {
            System.out.println(element.text());
        }

        //id获取元素
        String city_bj = doc.select("#city_bj").first().text();
        System.out.println(city_bj);

        //class获取元素
        String class_a_class_b = doc.select(".class_a").first().text();
        System.out.println(class_a_class_b);


        //属性获取元素
        String abd = doc.select("[abc]").first().text();
        System.out.println(abd);
    }


    @Test
    public void tesSelectorAll() throws Exception{
        //第一参数：访问的url,第二个参数是访问时的超时时间
        Document doc = Jsoup.parse(new File("F:\\project\\IDEA\\study\\javaee57\\web-crawler-first\\docs\\test.html"),"utf8");

        //el#id: 元素+ID，比如： h3#city_bj
        Elements elements = doc.select("h3#city_bj");

        for (Element element : elements) {
            System.out.println(element.text());
        }

        //el.class: 元素+class，比如： li.class_a
        String class_a_class_b = doc.select("li.class_a").first().text();
        System.out.println(class_a_class_b);


        //el[attr]: 元素+属性名，比如： span[abc]
        String abd = doc.select("span[abc]").first().text();
        System.out.println(abd);

        //任意组合: 比如：span[abc].s_name
        String na = doc.select("span[abc].s_name").first().text();
        System.out.println(na);

        //ancestor child: 查找某个元素下子元素，比如：.city_con li 查找"city_con"下的所有li
        Elements elements1 = doc.select(".city_con li");
        for (Element element : elements1) {
            System.out.println(element.text());
        }

        //parent > child: 查找某个父元素下的直接子元素，比如：
        //.city_con > ul > li 查找city_con第一级（直接子元素）的ul，再找所有ul下的第一级li
        String na1 = doc.select(".city_con > ul > li").first().text();
        System.out.println(na1);

        //parent > *: 查找某个父元素下所有直接子元素
        String na3 = doc.select(".city_con > *").first().text();
        System.out.println(na3);

    }
}
