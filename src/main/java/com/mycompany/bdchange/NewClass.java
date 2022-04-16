/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bdchange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author nalog_ot01
 */
public class NewClass {

    static String ss = "";

    public static void main(String... args) {
        WebDriver driver = enterToDB();
        changeStringsOnPage(getElement(driver, 2, 2));
        driver.navigate().back();
        driver.navigate().back();
    }

    public static String changeContacts(String st) {
        List<String> list = new ArrayList<>();//массив строк с телефонами
        List<String> list1 = new ArrayList<>();
        String sss = st;
        list = Arrays.asList(sss.split(" "));
        Boolean b, c;
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            b = list.get(i).matches("\\d{2,3}");
            if (b) {
                s = s + list.get(i);
            } else {
                if (s.length() > 0) {
                    list1.add(s + " ");
                }

                s = "";
                if (list.get(i).matches("\\D+")) {
                    list1.add(list.get(i) + " ");
                }
            }
        }
        s = "";

        for (String str : list1) {
            s = s + str;
        }
        s = s.trim();
        s = addSpaceBetween(s);
        return s;
    }

    public static String addSpaceBetween(String s) {//s - данные контактных лиц
        String d = "Директор", g = "Генеральный директор", i = "Индивидуальный предприниматель",
                m = "Менеджер по закупкам", c = "Специалист ВЭД", cc = "cпециалист ВЭД",
                mv = "Менеджер ВЭД", mvv = "менеджер ВЭД",
                a = "Администратор";
        if (s.contains(d) || s.contains(d.toLowerCase()) || s.contains(d.toUpperCase())) {
            s = s.replaceAll(d, d + ", ");
            s = s.replaceAll(d.toLowerCase(), d + ", ");
            s = s.replaceAll(d.toUpperCase(), d + ", ");
        }
        if (s.contains(g) || s.contains(g.toLowerCase()) || s.contains(g.toUpperCase())) {
            s = s.replaceAll(g, g + ", ");
            s = s.replaceAll(g.toLowerCase(), g + ", ");
            s = s.replaceAll(g.toUpperCase(), g + ", ");
        }
        if (s.contains(i) || s.contains(i.toLowerCase()) || s.contains(i.toUpperCase())) {
            s = s.replaceAll(i, i + ", ");
            s = s.replaceAll(i.toLowerCase(), i + ", ");
            s = s.replaceAll(i.toUpperCase(), i + ", ");
        }
        if (s.contains(m) || s.contains(m.toLowerCase()) || s.contains(m.toUpperCase())) {
            s = s.replaceAll(m, m + ", ");
            s = s.replaceAll(m.toLowerCase(), m + ", ");
            s = s.replaceAll(m.toUpperCase(), m + ", ");
        }
        if (s.contains(c) || s.contains(cc) || s.contains(c.toUpperCase())) {
            s = s.replaceAll(c, c + ", ");
            s = s.replaceAll(cc, c + ", ");
            s = s.replaceAll(c.toUpperCase(), c + ", ");
        }
        if (s.contains(mv) || s.contains(mvv) || s.contains(mv.toUpperCase())) {
            s = s.replaceAll(mv, mv + ", ");
            s = s.replaceAll(mvv, mv + ", ");
            s = s.replaceAll(mv.toUpperCase(), mv + ", ");
        }
        if (s.contains(a) || s.contains(a.toLowerCase()) || s.contains(a.toUpperCase())) {
            s = s.replaceAll(a, a + ", ");
            s = s.replaceAll(a.toLowerCase(), a + ", ");
            s = s.replaceAll(a.toUpperCase(), a + ", ");
        }
        return s;
    }

    public static WebDriver enterToDB() {
        System.setProperty("webdriver.chrome.driver", "C:\\Новая_папка\\chromedriver.exe");
        WebDriver driver1 = new ChromeDriver();
        driver1.manage().window().maximize();
        driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver1.get("*****");
        driver1.findElement(By.xpath("//*[@id=\"all_nonmenu_text\"]/div/form/table/tbody/tr[1]/td[2]/input")).sendKeys("Sveshnikov");
        driver1.findElement(By.xpath("//*[@id=\"all_nonmenu_text\"]/div/form/table/tbody/tr[2]/td[2]/input")).sendKeys("sea");
        driver1.findElement(By.xpath("//*[@id=\"all_nonmenu_text\"]/div/form/table/tbody/tr[2]/td[2]/input")).submit();
//        driver1.findElement(By.xpath("//*[@id=\"all_nonmenu_text\"]/div/table/tbody/tr/td[2]/a"))
//                .sendKeys(Keys.ENTER);
//        driver1.findElement(By.xpath("/html/body/div[9]/div/form/table[1]/tbody/tr[3]/td[3]")).click();//клик по компании

        return driver1;
    }

    public static WebDriver getElement(WebDriver driver, int pageNumber, int position) {
        //pageNumber-номер страницы от 1 до 5, position - порядковый номер на странице от 1 до 100
        int position1 = 2 + position;
        int pageNumber1 = pageNumber - 1;
        if (pageNumber > 1) {
            driver.findElement(By.xpath("//*[@id=\"all_nonmenu_text\"]/div/table/tbody/tr/td[3]/a[" + pageNumber1 + "]")).click();
        }
        driver.findElement(By.xpath("/html/body/div[9]/div/form/table[1]/tbody/tr[" + position1 + "]/td[3]")).click();
        //в итоге переходим в карточку компании
        return driver;
    }

    public static String changePhones(String s) {
        List<String> list = new ArrayList<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            list.add(Character.toString(arr[i]));
        }
        for (int i = 1; i < list.size() - 1; i++) {
            if (list.get(i - 1).matches("\\d") && list.get(i).matches("\\s") && list.get(i + 1).matches("\\d")) {
                list.remove(i);
            }
        }
        list.forEach((t) -> {
            ss = ss + t;
        });
        System.out.println(ss);
        return ss;
    }

    public static void changeStringsOnPage(WebDriver driver) {
        driver.findElement(By.xpath("//*[@id=\"view_buttons\"]/div[1]/a/span")).click();
        String contacts = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div[1]/form/table/tbody/tr[2]/td[1]/div[2]/table/tbody[1]/tr[9]/td[2]/input[1]"))
                .getAttribute("value");
        String phone = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div[1]/form/table/tbody/tr[2]/td[1]/div[2]/table/tbody[1]/tr[13]/td[2]/input"))
                .getAttribute("value");
        contacts = addSpaceBetween(contacts);
        contacts = changeContacts(contacts);
        System.out.println("contacts" + " " + contacts);
        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div[1]/form/table/tbody/tr[2]/td[1]/div[2]/table/tbody[1]/tr[9]/td[2]/input[1]"))
                .clear();
        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div[1]/form/table/tbody/tr[2]/td[1]/div[2]/table/tbody[1]/tr[9]/td[2]/input[1]"))
                .sendKeys(contacts);
        phone = changePhones(phone);
        System.out.println("phone" + " " + phone);
        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div[1]/form/table/tbody/tr[2]/td[1]/div[2]/table/tbody[1]/tr[13]/td[2]/input"))
                .clear();
        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div[1]/form/table/tbody/tr[2]/td[1]/div[2]/table/tbody[1]/tr[13]/td[2]/input"))
                .sendKeys(phone);
        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div/div[1]/form/table/tbody/tr[2]/td[2]/div[2]/div[1]/a"))
                .click();

    }
}
