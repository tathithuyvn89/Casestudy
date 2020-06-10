package codegym.Crawl_Data_from_tiki;

import codegym.model.Product;
import codegym.utils.JDBCUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataThread extends Thread {

    String url;
    private Product product;
    private static final  String INSERT_NEW_PRODUCT= "insert into products(name,price,description,img,maker)"+
            "values" +"(?,?,?,?,?);";

    public DataThread(String url) {
        this.url = url;

    }

    @Override
    public void run() {
        product = new Product();
        try {
            URL url = new URL(this.url);
            Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));
            scanner.useDelimiter("\\Z");
            String content = scanner.next();
            scanner.close();
            content = content.replaceAll("\\n+", "");
//            content= content.replaceAll("\\t", "");
            Pattern patternName = Pattern.compile("data-title=\"(.*?)\" data-price=\"");
            Matcher matcher1 = patternName.matcher(content);
            Pattern patternPrice = Pattern.compile("\" data-price=\"(.*?)\" data-id");
            Matcher matcher2 = patternPrice.matcher(content);
            Pattern patternID = Pattern.compile("\" data-id=\"(.*?)\"data-score=");
            Matcher matcher3 = patternID.matcher(content);
            Pattern patternBranch = Pattern.compile("\" data-brand=\"(.*?)\" data-category=\"");
            Matcher matcher4 = patternBranch.matcher(content);
//          <img class="product-image img-responsive" src="https://salt.tikicdn.com/cache/280x280/ts/product/ad/66/b6/6dbf4c96741da06228484c8f33a7d150.jpg"
            Pattern patternImage = Pattern.compile("<img class=\"product-image img-responsive\" src=\"(.*?)\" alt=\"\"");
            Matcher matcher5 = patternImage.matcher(content);
            Connection connection = JDBCUtils.getConnection();
            while (matcher2.find() && matcher1.find() && matcher3.find() && matcher4.find() && matcher5.find()) {
//                String line = (matcher1.group(1).trim()).replaceAll(",", "-") + "," +
//                        matcher4.group(1) + "," + matcher2.group(1).trim() + "," + matcher3.group(1).replaceAll("\"", "");

                PreparedStatement preparedStatement = null;
                preparedStatement = connection.prepareStatement(INSERT_NEW_PRODUCT);
                preparedStatement.setString(1, (matcher1.group(1).trim()).replaceAll(",", "-"));
                preparedStatement.setDouble(2, Double.parseDouble(matcher2.group(1).trim()));
                preparedStatement.setString(3, "Good");
                preparedStatement.setString(4, matcher5.group(1));
                preparedStatement.setString(5, matcher4.group(1));
                preparedStatement.executeUpdate();

            }
//

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();


        }
    }
}
