package codegym.Crawl_Data_from_tiki;

public class TestCrawl {
    public static void main(String[] args) {
        String url1= "https://tiki.vn/dien-gia-dung/c1882?src=c.1882.hamburger_menu_fly_out_banner";
        String localFile1= "D:\\Codegym Module\\Casestudy_module2\\src\\repository\\Do_da_dung.csv";
        DataThread DataDoDaDung= new DataThread(url1);
        String url2= "https://tiki.vn/dien-thoai-may-tinh-bang/c1789?src=c.1789.hamburger_menu_fly_out_banner";
        String localFile2= "D:\\Codegym Module\\Casestudy_module2\\src\\repository\\Dienthoai_maytinh.csv";
        DataThread Datadienthoai= new DataThread(url2);
        DataDoDaDung.start();
        Datadienthoai.start();
    }
}
